package com.example.demo.controller.login;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Reviewer;
import com.example.demo.pojo.User;
import com.example.demo.service.Impl.AnnouncerServiceImpl;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.example.demo.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/2 14:14
 */
@Controller
public class RegisterController {
    @Resource
    UserServiceImpl userService;
    @Resource
    ReviewerServiceImpl reviewerService;
    @Resource
    AnnouncerServiceImpl announcerService;

    @RequestMapping("/register")
    String toRegister(){
        return "login/register";
    }

    @RequestMapping("/checkRegister")
    @ResponseBody
    int checkRegister(@RequestParam Map<String, String> map){
        System.out.println(map);
        String role=map.get("role");
//        用户信息
        String username=map.get("username");
        String password=map.get("password");
        String email=map.get("email");
        String name=map.get("name");
        String gender=map.get("sex");
        String birthday=map.get("birthday");
        String education=map.get("education");
        String city=map.get("city");
        String institution=map.get("institution");
//        审稿人信息、发布者信息
        String _username=map.get("username2");
        String _password=map.get("password2");
        String _email=map.get("email2");
        String _name=map.get("name2");
//        System.out.println(role+"====="+username+"===="+password);
        switch (role) {
            // 用户注册
            case "0":
//                检查用户名是否存在
                User user1=userService.queryUserByUsername(username);
                if(user1!=null) {
                    //用户名重复了
                    return 0;
                }else{
                    //执行用户注册操作
                    User user=new User(username, password, email, name, gender,
                            birthday, education, city, institution);
                    userService.addUser(user);
                    return 1;
                }
            case "1":
                //                检查评审人用户名是否存在
                Reviewer reviewer1=reviewerService.queryReviewerByUsername(_username);
                if(reviewer1!=null) {
                    //评审人用户名重复了
                    return 2;
                }else {
                    //执行评审人注册操作
                    Reviewer reviewer = new Reviewer(_username, _password, _email, _name);
                    reviewerService.addReviewer(reviewer);
                    return 3;
                }
            case "2":
                // 检查发布者用户名是否存在
                Announcer announcer1=announcerService.queryAnnouncerByUsername(_username);
                if(announcer1!=null) {
                    //发布者用户名重复了
                    return 4;
                }else {
                    //执行发布者注册操作
                    Announcer announcer = new Announcer(_username, _password, _email, _name);
                    announcerService.addAnnouncer(announcer);
                    return 5;
                }
        }

        return -1;
    }
}
