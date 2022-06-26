package com.example.demo.controller.admin;

import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.ReviewerManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Controller
public class ReviewerManageController {

    @Autowired
    private ReviewerManageService reviewerManageService;

    /**
     * 分页查询（包括搜索）
     * @param model
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("/admin/reviewer_manage")
    public String queryConferenceBySearchName(HttpSession session, Model model,
                                                    @RequestParam(value = ("pageSize"), defaultValue = "10")Integer pageSize,
                                                    @RequestParam(value = ("pageNum"), defaultValue = "1")Integer pageNum,
                                                    @RequestParam(defaultValue = "") String searchString) {
        //分页查找，startPage方法只对接下来的一次查询进行分页，注意顺序
        PageHelper.startPage(pageNum, pageSize);
        List<Reviewer> reviewers = reviewerManageService.queryReviewerBySearchUsername(searchString);
        PageInfo<Reviewer> reviewerPageInfo = new PageInfo<Reviewer>(reviewers);
        model.addAttribute("reviewerPageInfo", reviewerPageInfo);
        model.addAttribute("searchString", searchString);
        return "admin/reviewer_manage";
    }

    /**
     * 添加审稿人
     * @param request
     * @param response
     */
    @RequestMapping("/admin/createReviewer")
    public void createReviewer(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Reviewer reviewer = new Reviewer();
        reviewer.setUsername(username);
        reviewer.setPassword(password);
        reviewer.setName(name);
        reviewer.setEmail(email);
        boolean flag=false;
        flag=reviewerManageService.createReviewer(reviewer);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据审稿人ID查询审稿人信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/queryReviewer")
    public Reviewer queryReviewerById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("reviewer_id"));
        Reviewer reviewer = reviewerManageService.queryReviewerById(id);
        return reviewer;
    }


}
