package com.example.demo.controller.user;

import com.example.demo.pojo.Reviewer;
import com.example.demo.pojo.User;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.example.demo.service.Impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 15:00
 */
@Controller
public class IndexController {
    @Resource
    UserServiceImpl userService;
    @Resource
    ReviewerServiceImpl reviewerService;

    @RequestMapping("/common")
    String toCommon() {
        return "common/common";
    }

    //    到主页
//    @RequestMapping({"/", "/index"})
//    String toIndex(HttpSession session, Model model) {
////        每个要显示头部导航栏信息的页面都要传递user和role信息
////        初始化
//        model.addAttribute("user", "");
//        model.addAttribute("role", 3);
//        //  获取登录用户的角色
//        if (session.getAttribute("role")!=null) {
//            int role = Integer.parseInt(session.getAttribute("role").toString());
//            System.out.println(role);
//            model.addAttribute("role", role);
//            String username = session.getAttribute("username").toString();
//            //登录的角色为用户
//            if (role == 0) {
//                User user = userService.queryUserByUsername(username);
//                model.addAttribute("user", user);
//            }
////            如果登录的角色为评审人
//            if (role == 1) {
//                Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
//                model.addAttribute("user", reviewer);
//            }
//        }
////        System.out.println(session.getAttribute("username").toString() + "====" + session.getAttribute("role").toString());
//        return "user/index";
//    }

    //    注销事件
    @RequestMapping("/logOut")
    String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("role");
        return "redirect:/";
    }

    //    更新用户信息
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    int updateUserInfo(@RequestParam Map<String, String> map) {
        System.out.println(map);
        //更新用户表中的信息
        String username = map.get("username");
        String name = map.get("name");
        String email = map.get("email");
        String sex = map.get("sex");
        String birthday = map.get("birthday");
        String education = map.get("education");
        String city = map.get("city");
        String institution = map.get("institution");
        User user = new User(username, email, name, sex, birthday, education, city, institution);
        int res = userService.updateUserInfo(user);
        return res;
    }

    //    更新用户密码
    @RequestMapping("/updateUserPwd")
    @ResponseBody
    int updateUserPwd(@RequestParam Map<String, String> map) {
        System.out.println(map);
//        判断一下输入的原密码是否是正确的
        String username = map.get("username");
        String orginalPwd = map.get("orginalPwd"); //传递过来的原密码
        String trueOrginalPwd = userService.queryUserByUsername(username).getPassword(); //实际的原密码
        String newPwd = map.get("newPwd"); //传递过来的新密码
        System.out.println("输入的原密码:" + orginalPwd + " 真实的原密码：" + trueOrginalPwd);
        if (!orginalPwd.equals(trueOrginalPwd)) {
            System.out.println("原密码不正确");
            return -1;
        } else {
            //执行更新密码的操作
            User user = new User(username, newPwd);
            int res = userService.updateUserPwd(user);
            return res;
        }

    }


    //    更新审稿人信息
    @RequestMapping("/updateReviewerInfo")
    @ResponseBody
    int updateReviewerInfo(@RequestParam Map<String, String> map) {
        System.out.println(map);
        //更新审稿人表中的信息
        String username = map.get("username");
        String name = map.get("name");
        String email = map.get("email");
        Reviewer reviewer = new Reviewer(username, email, name);
        int res = reviewerService.updateReviewerInfo(reviewer);
        return res;
    }

    //    更新审稿人密码
    @RequestMapping("/updateReviewerPwd")
    @ResponseBody
    int updateReviewerPwd(@RequestParam Map<String, String> map) {
        System.out.println(map);
//        判断一下输入的原密码是否是正确的
        String username = map.get("username");
        String orginalPwd = map.get("orginalPwd"); //传递过来的原密码
        String trueOrginalPwd = reviewerService.queryReviewerByUsername(username).getPassword(); //实际的原密码
        String newPwd = map.get("newPwd"); //传递过来的新密码
        System.out.println("输入的原密码:" + orginalPwd + " 真实的原密码：" + trueOrginalPwd);
        if (!orginalPwd.equals(trueOrginalPwd)) {
            System.out.println("原密码不正确");
            return -1;
        } else {
            //执行更新密码的操作
            Reviewer reviewer=new Reviewer(username,newPwd);
            int res = reviewerService.updateReviewerPwd(reviewer);
            return res;
        }
    }


}
