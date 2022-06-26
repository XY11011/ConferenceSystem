package com.example.demo.controller.admin;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Paper;
import com.example.demo.service.ConferenceManageService;
import com.example.demo.service.HomeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Controller
public class HomeController {

    @Autowired
    private ConferenceManageService conferenceManageService;
    @Autowired
    private HomeManageService homeManageService;

    @RequestMapping("/admin/home")
    String adminHome(HttpSession session, Model model){
        String username = (String)session.getAttribute("username");
        Announcer announcer = conferenceManageService.queryAnnouncerInfoByName(username);
        int countAllConference = homeManageService.countAllConference(announcer.getId());
        List<Conference> conferences = conferenceManageService.queryAllConferenceByAnnouncerId(announcer.getId());
        int countAllPaper = 0;
        int countUndistriPaper = 0;
        int countDistributedPaper = 0;
        for (Conference conference : conferences) {
            countAllPaper += homeManageService.countPaperByConferenceId(conference.getId());
            countDistributedPaper += homeManageService.countDistributedPaper(conference.getId());
            countUndistriPaper += homeManageService.countUndistriPaper(conference.getId());
        }
        model.addAttribute("countAllConference", countAllConference);
        model.addAttribute("countAllPaper", countAllPaper);
        model.addAttribute("countDistributedPaper", countDistributedPaper);
        model.addAttribute("countUndistriPaper", countUndistriPaper);
        return "admin/home";
    }

    // 退出后台
    @RequestMapping("/admin/logout")
    String adminLogout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("role");
        return "redirect:/";
    }

}
