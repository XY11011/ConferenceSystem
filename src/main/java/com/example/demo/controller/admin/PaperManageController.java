package com.example.demo.controller.admin;

import com.example.demo.pojo.*;
import com.example.demo.service.ConferenceManageService;
import com.example.demo.service.PaperManageService;
import com.example.demo.service.PaperService;
import com.github.pagehelper.Page;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Controller
public class PaperManageController {

    @Autowired
    private PaperManageService paperManageService;
    @Autowired
    private ConferenceManageService conferenceManageService;
    @Autowired
    private PaperService paperService;

    /**
     * 分页查询（包括搜索）——未分配
     * @param model
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("/admin/paper_manage_undistri")
    public String queryUndistriPaperBySearchConferenceAndName(HttpSession session, Model model,
                                                    @RequestParam(value = ("pageSize"), defaultValue = "3")Integer pageSize,
                                                    @RequestParam(value = ("pageNum"), defaultValue = "1")Integer pageNum,
                                                    @RequestParam(defaultValue = "") String conferenceName,
                                                    @RequestParam(defaultValue = "") String paperTitle) {
        // 需要session获取username
        String username = (String)session.getAttribute("username");
        Announcer announcer = conferenceManageService.queryAnnouncerInfoByName(username);
        Conference conference = paperManageService.queryConferenceBySearchTitle(conferenceName);
        //分页查找，startPage方法只对接下来的一次查询进行分页，注意顺序
        //PageHelper.startPage(pageNum, pageSize);
        List<Paper> papers = new ArrayList<>();
        if (conference != null) {
            PageHelper.startPage(pageNum, pageSize);
            papers = paperManageService.queryUndistriPaperBySearch(conference.getId(), paperTitle);
        }
        else {
            PageHelper.startPage(pageNum, pageSize);
            papers = paperManageService.queryUndistriPaperBySearchPaperTitle(paperTitle);
        }
        //System.out.println(papers);
        //List<PaperInfoVo> paperList = new ArrayList<PaperInfoVo>();
        Page<PaperInfoVo> paperList = new Page<PaperInfoVo>();
        paperList.setCount(true);
        paperList.setPageNum(((Page<Paper>)papers).getPageNum());
        paperList.setPageSize(((Page<Paper>)papers).getPageSize());
        paperList.setStartRow(((Page<Paper>)papers).getStartRow());
        paperList.setEndRow(((Page<Paper>)papers).getEndRow());
        paperList.setTotal(((Page<Paper>)papers).getTotal());
        paperList.setPages(((Page<Paper>)papers).getPages());
        paperList.setReasonable(((Page<Paper>)papers).getReasonable());
        paperList.setPageSizeZero(((Page<Paper>)papers).getPageSizeZero());
        for (Paper paperDetail : papers) {
            int paper_state= 2;
            PaperInfoVo paperInfoVo = new PaperInfoVo(paperDetail, new ArrayList<>(), paper_state);
            paperList.add(paperInfoVo);
        }
        PageInfo<PaperInfoVo> paperPageInfo = new PageInfo<PaperInfoVo>(paperList);
        // 查询会议发布者发布的所有会议
        List<Conference> conferenceInfo = paperManageService.queryConferenceByAnnouncerId(announcer.getId());
        // 获取所有的审稿人信息
        List<Reviewer> reviewerList = paperManageService.queryAllReviewer();
        model.addAttribute("conferenceInfo", conferenceInfo);
        model.addAttribute("paperPageInfo", paperPageInfo);
        model.addAttribute("announcerInfo", announcer);
        model.addAttribute("conferenceName", conferenceName);
        model.addAttribute("paperTitle", paperTitle);
        model.addAttribute("reviewerList", reviewerList);
        return "admin/paper_manage_undistri";
    }

    /**
     * 根据论文ID查询论文信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/queryPaper")
    public Paper queryPaperById(HttpServletRequest request) {
        int paper_id = Integer.parseInt(request.getParameter("paper_id"));
        Paper paper = paperManageService.queryPaperById(paper_id);
        return paper;
    }

    /**
     * 添加论文分配信息
     * @param request
     * @param response
     */
    @RequestMapping("/admin/createPaperDistri")
    public void createPaperDistri(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        boolean flag = true;
        int paper_id = Integer.parseInt(request.getParameter("paper_id"));
        int reviewer1_id = Integer.parseInt(request.getParameter("reviewer1_id"));
        int reviewer2_id = Integer.parseInt(request.getParameter("reviewer2_id"));
        int reviewer3_id = Integer.parseInt(request.getParameter("reviewer3_id"));
        int confer_id = Integer.parseInt(request.getParameter("confer_id"));
        if (reviewer1_id != 0) {
            Paper_distri paper_distri = new Paper_distri();
            paper_distri.setPaper_id(paper_id);
            paper_distri.setReviewer_id(reviewer1_id);
            paper_distri.setConfer_id(confer_id);
            paper_distri.setReview_state(0);
            flag = paperManageService.createPaperDistri(paper_distri);
        }
        if (reviewer2_id != 0) {
            Paper_distri paper_distri = new Paper_distri();
            paper_distri.setPaper_id(paper_id);
            paper_distri.setReviewer_id(reviewer2_id);
            paper_distri.setConfer_id(confer_id);
            paper_distri.setReview_state(0);
            flag = paperManageService.createPaperDistri(paper_distri);
        }
        if (reviewer3_id != 0) {
            Paper_distri paper_distri = new Paper_distri();
            paper_distri.setPaper_id(paper_id);
            paper_distri.setReviewer_id(reviewer3_id);
            paper_distri.setConfer_id(confer_id);
            paper_distri.setReview_state(0);
            flag = paperManageService.createPaperDistri(paper_distri);
        }
        paperManageService.updatePaperDistriState(paper_id);
        if(flag != false) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }




    /**
     * 分页查询（包括搜索）——已分配
     * @param model
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("/admin/paper_manage_distributed")
    public String queryDistributedPaperBySearchConferenceAndName(HttpSession session, Model model,
                                                              @RequestParam(value = ("pageSize"), defaultValue = "3")Integer pageSize,
                                                              @RequestParam(value = ("pageNum"), defaultValue = "1")Integer pageNum,
                                                              @RequestParam(defaultValue = "") String conferenceName,
                                                              @RequestParam(defaultValue = "") String paperTitle) {
        // 需要session获取username
        String username = (String)session.getAttribute("username");
        Announcer announcer = conferenceManageService.queryAnnouncerInfoByName(username);
        Conference conference = paperManageService.queryConferenceBySearchTitle(conferenceName);
        //分页查找，startPage方法只对接下来的一次查询进行分页，注意顺序
        //PageHelper.startPage(pageNum, pageSize);
        List<Paper> papers = new ArrayList<>();
        if (conference != null) {
            PageHelper.startPage(pageNum, pageSize);
            papers = paperManageService.queryDistributedPaperBySearch(conference.getId(), paperTitle);
        }
        else {
            PageHelper.startPage(pageNum, pageSize);
            papers = paperManageService.queryDistributedPaperBySearchPaperTitle(paperTitle);
        }
        //System.out.println(papers);
        //List<PaperInfoVo> paperList = new ArrayList<PaperInfoVo>();
        Page<PaperInfoVo> paperList = new Page<PaperInfoVo>();
        paperList.setCount(true);
        paperList.setPageNum(((Page<Paper>)papers).getPageNum());
        paperList.setPageSize(((Page<Paper>)papers).getPageSize());
        paperList.setStartRow(((Page<Paper>)papers).getStartRow());
        paperList.setEndRow(((Page<Paper>)papers).getEndRow());
        paperList.setTotal(((Page<Paper>)papers).getTotal());
        paperList.setPages(((Page<Paper>)papers).getPages());
        paperList.setReasonable(((Page<Paper>)papers).getReasonable());
        paperList.setPageSizeZero(((Page<Paper>)papers).getPageSizeZero());
        for (Paper paperDetail : papers) {
            List<ReviewedPaperVo> paperReview = paperService.selectReviewByPaper(paperDetail.getId());
            int paper_state= paperDetail.getPass_state();
            int distri_state = paperDetail.getDistri_state();
            if (paper_state == 2) {
                // 论文拒稿
                paper_state = 4;
            } else if (paper_state == 1) {
                // 论文录用
                paper_state = 1;
            } else if (paperReview.size()<3 || distri_state == 0){
                // 有评审还未审完
                paper_state = 2;
            } else if (paperReview.size() >= 3) {
                // 三位评审已评审完成，需要显示是否接收按钮
                paper_state = 3;
            }
            PaperInfoVo paperInfoVo = new PaperInfoVo(paperDetail, paperReview, paper_state);
            paperList.add(paperInfoVo);
        }
        PageInfo<PaperInfoVo> paperPageInfo = new PageInfo<PaperInfoVo>(paperList);
        // 查询会议发布者发布的所有会议
        List<Conference> conferenceInfo = paperManageService.queryConferenceByAnnouncerId(announcer.getId());
        // 获取所有的审稿人信息
        List<Reviewer> reviewerList = paperManageService.queryAllReviewer();
        model.addAttribute("conferenceInfo", conferenceInfo);
        model.addAttribute("paperPageInfo", paperPageInfo);
        model.addAttribute("announcerInfo", announcer);
        model.addAttribute("conferenceName", conferenceName);
        model.addAttribute("paperTitle", paperTitle);
        model.addAttribute("reviewerList", reviewerList);
        return "admin/paper_manage_distributed";
    }

    /**
     * 录用论文后修改状态
     * @param request
     * @param response
     */
    @RequestMapping("/admin/acceptPaper")
    public void acceptPaper(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        int paper_id = Integer.parseInt(request.getParameter("paper_id"));
        boolean flag = paperManageService.acceptPaper(paper_id);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 退回论文后修改状态
     * @param request
     * @param response
     */
    @RequestMapping("/admin/refusePaper")
    public void refusePaper(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        int paper_id = Integer.parseInt(request.getParameter("paper_id"));
        boolean flag = paperManageService.refusePaper(paper_id);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
