package com.example.demo.controller;

import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Reviewer;
import com.example.demo.pojo.User;
import com.example.demo.service.ConferenceService;
import com.example.demo.service.Impl.PaperServiceImpl;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConferenceController {
    @Resource
    ConferenceService conferenceService;

    @Resource
    UserService userService;

    @Resource
    ReviewerServiceImpl reviewerService;

    @RequestMapping("/myConference")
    String myConference(HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        User user = userService.queryUserByUsername(username);
        int userId = user.getId();
        PageHelper.startPage(pageNum,10);
        List<Conference> myConference = conferenceService.getMyConference(userId);
        PageInfo<Conference> pageInfo = new PageInfo<Conference>(myConference);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        model.addAttribute("role",role);
        model.addAttribute("href", "/myConference");
        System.out.println(pageInfo);


        return "user/conference_my";
    }

    @RequestMapping({"/", "/allConference"})
    String allConference(HttpSession session,Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        PageHelper.startPage(pageNum,10);
        List<Conference> allConference = conferenceService.getAllConference();
        PageInfo<Conference> pageInfo = new PageInfo<Conference>(allConference);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("role",0);
        model.addAttribute("href", "/allConference");
        System.out.println(pageInfo);
        //        每个要显示头部导航栏信息的页面都要传递user和role信息
//        初始化
        model.addAttribute("user", "");
        model.addAttribute("username", "");
        model.addAttribute("role", 3);
        //  获取登录用户的角色
        if (session.getAttribute("role")!=null) {
            int role = Integer.parseInt(session.getAttribute("role").toString());
            System.out.println(role);
            model.addAttribute("role", role);
            String username = session.getAttribute("username").toString();
            model.addAttribute("username",username);
            //登录的角色为用户
            if (role == 0) {
                User user = userService.queryUserByUsername(username);
                model.addAttribute("user", user);
            }
//            如果登录的角色为评审人
            if (role == 1) {
                Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
                model.addAttribute("user", reviewer);
            }
        }
        return "user/conference_all";
    }

    //按title搜索会议
    @RequestMapping("/search_myConference")
    String search_myConference(HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                               @RequestParam(value = "title") String title){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        User user = userService.queryUserByUsername(username);
        int userId = user.getId();
        PageHelper.startPage(pageNum,10);
        HashMap<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("title",title);
        List<Conference> searchConference =  conferenceService.searchMyConference(map);
        PageInfo<Conference> pageInfo = new PageInfo<Conference>(searchConference);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        model.addAttribute("role",role);
        model.addAttribute("href", "/search_myConference");
        model.addAttribute("title", title);

        return "user/conference_my";
    }

    //按title搜索会议
    @RequestMapping("/search_allConference")
    String search_allConference(HttpSession session,Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                               @RequestParam(value = "title") String title){

        PageHelper.startPage(pageNum,10);
        HashMap<String,Object> map = new HashMap<>();
        map.put("title",title);
        List<Conference> searchConference =  conferenceService.searchAllConference(map);
        PageInfo<Conference> pageInfo = new PageInfo<Conference>(searchConference);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("href", "/search_allConference");
        model.addAttribute("title", title);

        //        每个要显示头部导航栏信息的页面都要传递user和role信息
//        初始化
        model.addAttribute("user", "");
        model.addAttribute("username", "");
        model.addAttribute("role", 3);
        //  获取登录用户的角色
        if (session.getAttribute("role")!=null) {
            int role = Integer.parseInt(session.getAttribute("role").toString());
            System.out.println(role);
            model.addAttribute("role", role);
            String username = session.getAttribute("username").toString();
            model.addAttribute("username",username);
            //登录的角色为用户
            if (role == 0) {
                User user = userService.queryUserByUsername(username);
                model.addAttribute("user", user);
            }
//            如果登录的角色为评审人
            if (role == 1) {
                Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
                model.addAttribute("user", reviewer);
            }
        }
        return "user/conference_all";
    }

//    @RequestMapping("/showConference")
//    String showConference(HttpSession session,Model model,@RequestParam(value = "conferId") Integer conferId){
//        String username = (String)session.getAttribute("username");
//        Integer role = (Integer)session.getAttribute("role");
//        Map<String,Object> conference = conferenceService.searchConferenceById(conferId);
//        model.addAttribute("conference",conference);
////        model.addAttribute("username", username);
////        model.addAttribute("role", role);
//        return "user/conferenceDetail";
//    }

    //    跳转至会议详情页
    @RequestMapping("/showConference")
    String showConference(@RequestParam(value = "conferId") Integer conferId, Model model, HttpSession session){

        Conference conference = conferenceService.searchConferenceById(conferId);

        System.out.println("===="+session.getAttribute("username"));
        String username="";
        if(session.getAttribute("username")!=null){
            username=session.getAttribute("username").toString();
        }

        System.out.println("==="+username+"---");
        model.addAttribute("conference",conference);
        model.addAttribute("username",username);

        //        每个要显示头部导航栏信息的页面都要传递user和role信息
//        初始化
        model.addAttribute("user", "");
        model.addAttribute("role", 3);
        //  获取登录用户的角色
        if (session.getAttribute("role")!=null) {
            int role = Integer.parseInt(session.getAttribute("role").toString());
            System.out.println(role);
            model.addAttribute("role", role);
            username = session.getAttribute("username").toString();
            //登录的角色为用户
            if (role == 0) {
                User user = userService.queryUserByUsername(username);
                model.addAttribute("user", user);
            }
//            如果登录的角色为评审人
            if (role == 1) {
                Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
                model.addAttribute("user", reviewer);
            }
        }
        System.out.println("conference================");
        System.out.println(conference);
        return "user/conferenceDetail";
    }
}
