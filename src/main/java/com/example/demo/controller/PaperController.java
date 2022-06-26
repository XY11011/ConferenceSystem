package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.ConferenceService;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.example.demo.service.Impl.UserServiceImpl;
import com.example.demo.service.PaperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PaperController {

    @Resource
    PaperService paperService;

    @Resource
    ConferenceService conferenceService;

    @Resource
    UserServiceImpl userService;

    @Resource
    ReviewerServiceImpl reviewerService;

    @RequestMapping("/showPaper")
    String showPaper(HttpSession session, Model model, @RequestParam(value="conferId") Integer conferId, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        User user = userService.queryUserByUsername(username);
        int userId = user.getId();
        HashMap<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("conferId",conferId);
        PageHelper.startPage(pageNum,3);
        List<Paper> myConferencePaper = paperService.selectMyPaperByConference(map);
        PageInfo<Paper> pageInfo = new PageInfo<Paper>(myConferencePaper);
        List<PaperInfoVo> paperList = new ArrayList<PaperInfoVo>();
        System.out.println("paperList-before");
        System.out.println(pageInfo.getList());
        for (Paper paperDetail : pageInfo.getList()) {
            int paperId = paperDetail.getId();
            List<ReviewedPaperVo> paperReview = paperService.selectReviewByPaper(paperId);
            System.out.println("paperReview");
            System.out.println(paperReview);
            int paper_state= paperDetail.getPass_state();
            // int distri_state = paperDetail.getDistri_state();
            // if(paperReview.size()<3 || distri_state == 0){
                // paper_state = 2;
            // }
            PaperInfoVo paperInfoVo = new PaperInfoVo(paperDetail,paperReview,paper_state);
//            paperDetail.put("review",paperReview);
//            paperDetail.put("paper_state",paper_state);
            paperList.add(paperInfoVo);

        }
        Conference conferInfo = conferenceService.searchConferenceById(conferId);
        System.out.println("paperList--after");
        System.out.println(paperList);
        System.out.println("conferInfo");
        System.out.println(conferInfo);
        System.out.println("PageInfo==================");
        System.out.println(pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("paperList",paperList);
        model.addAttribute("conferInfo",conferInfo);
        model.addAttribute("username",username);
        model.addAttribute("user",user);
        model.addAttribute("role",role);
        model.addAttribute("href", "/showPaper");
        return "user/conference_paper";
    }

    @RequestMapping("/toSubmitPaper")
    String toSubmitPaper(HttpSession session,@RequestParam(value = "conferId") Integer conferId, Model model){
        //传递会议Id???
        System.out.println("====="+conferId.toString());
        model.addAttribute("conferId",conferId);
        //        每个要显示头部导航栏信息的页面都要传递user和role信息
//        初始化
        model.addAttribute("user", "");
        model.addAttribute("username","");
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
        return "user/submitPaper";
    }


    //    提交论文信息
    @RequestMapping("/checkSubmitPaper")
    @ResponseBody
    int checkSubmitPaper(@RequestParam Map<String, String> map, HttpSession session){
        String a1=map.get("author1");
        String a2=map.get("author2");
        String a3=map.get("author3");
        String title=map.get("title");
        String _abstract=map.get("abstract");
        String keywords=map.get("keywords");
        String filePath=map.get("path");
        //拼接作者信息
        String author=a1;
        if(a2!=""){
            author+=","+a2;
        }
        if(a3!=""){
            author+=","+a3;
        }
        System.out.println(author);
        System.out.println(filePath);
        //执行提交论文操作
        String username=session.getAttribute("username").toString();
        int user_id=userService.queryUserByUsername(username).getId();
        Date date=new Date();
//        //这里的会议id还暂时为获取到
        System.out.println("before_confer:"+map.get("conferId").toString());
        int conferId=Integer.parseInt(map.get("conferId").toString());
        Paper paper=new Paper(user_id,conferId,date,author,title,_abstract,keywords,filePath,0,0);
        int res=paperService.addPaper(paper);
        return res;
    }
}
