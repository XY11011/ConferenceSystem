package com.example.demo.controller;

import com.example.demo.pojo.Paper_distri;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.Impl.ReviewServiceImpl;
import com.example.demo.service.Impl.ReviewerServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReviewController {
    @Resource
    ReviewServiceImpl reviewService;
    @Resource
    ReviewerServiceImpl reviewerService;


    //显示已评审论文
    @RequestMapping("/reviewedPaper")
    String reviewedPaper(HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
        int reviewerId = reviewer.getId();
        System.out.println("进来了！");
        PageHelper.startPage(pageNum,3);
        List<Map<String, Object>> reviewedPaper = reviewService.getReviewedPaper(reviewerId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(reviewedPaper);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("username", username);
        model.addAttribute("user", reviewer);
        model.addAttribute("role", role);
        model.addAttribute("href", "/reviewedPaper");
        System.out.println(pageInfo);
        return "reviewer/reviewed_paper";
    }

    //显示未评审论文
    @RequestMapping({"reviewer","/unReviewedPaper"})
    String unReviewedPaper(HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
        int reviewerId = reviewer.getId();
        PageHelper.startPage(pageNum,3);
        List<Map<String, Object>> reviewedPaper = reviewService.getUnReviewedPaper(reviewerId);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(reviewedPaper);
        model.addAttribute("pageInfo", pageInfo);
                model.addAttribute("username", username);
        model.addAttribute("user", reviewer);
        model.addAttribute("role", role);
        model.addAttribute("href", "/unReviewedPaper");
        return "reviewer/unreviewed_paper";
    }

    //按title搜索论文
    @RequestMapping("/search_reviewedPaper")
    String searchReviewedPaper(HttpSession session, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                               @RequestParam(value = "title") String title){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
        int reviewerId = reviewer.getId();
        PageHelper.startPage(pageNum,3);
        HashMap<String,Object> map = new HashMap<>();
        map.put("reviewerId",reviewerId);
        map.put("title",title);
        List<Map<String, Object>> searchPaper =  reviewService.searchReviewedPaperByTitle(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(searchPaper);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("username", username);
        model.addAttribute("user", reviewer);
        model.addAttribute("role", role);
        model.addAttribute("href", "/search_reviewedPaper");
        model.addAttribute("title", title);
        System.out.println("执行了");
        System.out.println("123"+pageInfo);
        return "reviewer/reviewed_paper";
    }

    @RequestMapping("/search_unreviewedPaper")
    String searchUnReviewedPaper(HttpSession session, HttpServletRequest request, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                 @RequestParam(value = "title") String title){
        String username = (String)session.getAttribute("username");
        Integer role = (Integer)session.getAttribute("role");
        Reviewer reviewer = reviewerService.queryReviewerByUsername(username);
        int reviewerId = reviewer.getId();
        PageHelper.startPage(pageNum,3);
        HashMap<String,Object> map = new HashMap<>();
        map.put("reviewerId",reviewerId);
        map.put("title",title);
        List<Map<String, Object>> searchPaper =  reviewService.searchUnReviewedPaperByTitle(map);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(searchPaper);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("username", username);
        model.addAttribute("user", reviewer);
        model.addAttribute("role", role);
        model.addAttribute("href", "/search_unreviewedPaper");
        model.addAttribute("title", title);
        return "reviewer/unreviewed_paper";
    }

    //新增评审意见
    @RequestMapping("/addReview")
    @ResponseBody
    Integer addReview(@RequestBody Map<String,Object> map){
        int res = reviewService.addComment(map);
        return res;
    }

    //修改评审意见
    @RequestMapping("/updateReview")
    @ResponseBody
    Integer updateReview(@RequestBody Map<String,Object> map){
        System.out.println("进来了12345");
        System.out.println(map);
        int res = reviewService.updateComment(map);
        return res;
    }
}
