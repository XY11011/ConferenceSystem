package com.example.demo.controller.login;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Reviewer;
import com.example.demo.pojo.User;
import com.example.demo.service.Impl.AnnouncerServiceImpl;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.example.demo.service.Impl.UserServiceImpl;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 14:36
 */
@Controller
public class LoginController {
    @Resource
    UserServiceImpl userService;
    @Resource
    ReviewerServiceImpl reviewerService;
    @Resource
    AnnouncerServiceImpl announcerService;

    @RequestMapping("/login")
    String toLogin(){
        return "login/login";
    }

    // 验证登录
    @RequestMapping("/checkLogin")
    @ResponseBody
    int checkLogin(@RequestParam Map<String, String> map, HttpSession session){
        String role=map.get("role");
        String username=map.get("username");
        String password=map.get("password");
//        System.out.println(role+"====="+username+"========"+password);
        switch (role){
//            用户登录
            case "0":
                User user=userService.queryUserByUsername(username);
                if(user==null){
                    //没有这样的用户
                    return 0;
                }else if(!user.getPassword().equals(password)){
                    // 用户密码不正确
                    return 1;
                }else{
//                    保存用户名到session中
                    session.setAttribute("username",username);
                    session.setAttribute("role",0);
                    return 2; //登录成功，跳到主页
                }
//                审稿人登录
            case "1":
                Reviewer reviewer=reviewerService.queryReviewerByUsername(username);
                if(reviewer==null){
                    //没有这样的审稿人
                    return 0;
                }else if(!reviewer.getPassword().equals(password)){
                    // 审稿人密码不正确
                    return 1;
                }else{
                    // 保存用户名到session中
                    session.setAttribute("username",username);
                    session.setAttribute("role",1);
                    return 3; //登录成功，跳到审稿人管理页
                }
//               会议发布者登录
            case "2":
                Announcer announcer=announcerService.queryAnnouncerByUsername(username);
                if(announcer==null){
                    //没有这样的会议发布者
                    return 0;
                }else if(!announcer.getPassword().equals(password)){
                    // 会议发布者密码不正确
                    return 1;
                }else{
                    // 保存用户名到session中
                    session.setAttribute("username",username);
                    session.setAttribute("role",2);
                    return 4; //登录成功，跳到会议发布者管理页
                }
        }
        return -1;
    }
}
