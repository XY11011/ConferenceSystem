package com.example.demo.controller.admin;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Conference;
import com.example.demo.service.ConferenceManageService;
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
public class ConferenceManageController {

    @Autowired
    private ConferenceManageService conferenceManageService;

    // 不带搜索的查询
    /* @RequestMapping("/admin/conference_manage")
    public String queryAllConferenceByAnnouncerName(HttpSession session, Model model,
                                                    @RequestParam(value = ("pageSize"), defaultValue = "10")Integer pageSize,
                                                    @RequestParam(value = ("pageNum"), defaultValue = "1")Integer pageNum) {
        // 需要session获取username
        String username = (String)session.getAttribute("username");
        Announcer announcer = conferenceManageService.queryAnnouncerInfoByName(username);
        //分页查找，startPage方法只对接下来的一次查询进行分页，注意顺序
        PageHelper.startPage(pageNum, pageSize);
        List<Conference> conferences = conferenceManageService.queryAllConferenceByAnnouncerId(announcer.getId());
        // System.out.println("会议数量:" + conferences.size());
        PageInfo<Conference> conferencePageInfo = new PageInfo<Conference>(conferences);
        // System.out.println("默认每页数量" + pageSize);
        model.addAttribute("conferencePageInfo", conferencePageInfo);
        model.addAttribute("announcerInfo", announcer);
        // System.out.println(announcer.getUsername());
        return "admin/conference_manage";
    } */

    /**
     * 分页查询（包括搜索）
     * @param model
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping("/admin/conference_manage")
    public String queryConferenceBySearchName(HttpSession session, Model model,
                                                    @RequestParam(value = ("pageSize"), defaultValue = "10")Integer pageSize,
                                                    @RequestParam(value = ("pageNum"), defaultValue = "1")Integer pageNum,
                                                    @RequestParam(defaultValue = "") String searchString) {
        // 需要session获取username
        String username = (String)session.getAttribute("username");
        Announcer announcer = conferenceManageService.queryAnnouncerInfoByName(username);
        //分页查找，startPage方法只对接下来的一次查询进行分页，注意顺序
        PageHelper.startPage(pageNum, pageSize);
        List<Conference> conferences = conferenceManageService.queryConferenceBySearchName(announcer.getId(), searchString);
        // System.out.println("会议数量:" + conferences.size());
        PageInfo<Conference> conferencePageInfo = new PageInfo<Conference>(conferences);
        // System.out.println("默认每页数量" + pageSize);
        model.addAttribute("conferencePageInfo", conferencePageInfo);
        model.addAttribute("announcerInfo", announcer);
        model.addAttribute("searchString", searchString);
        // System.out.println(announcer.getUsername());
        return "admin/conference_manage";
    }

    /**
     * 创建会议
     * @param request
     * @param response
     */
    @RequestMapping("/admin/createConference")
    public void createConference(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        String title = request.getParameter("title");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_start= sdf.parse(request.getParameter("date_start"));
        Date date_create= sdf.parse(request.getParameter("date_create"));
        Date date_end= sdf.parse(request.getParameter("date_end"));
        String location = request.getParameter("location");
        String area = request.getParameter("area");
        String topic = request.getParameter("topic");
        String introduction = request.getParameter("introduction");
        String confer_link = request.getParameter("confer_link");
        String external_link = request.getParameter("external_link");
        int announcer_id = Integer.parseInt(request.getParameter("announcer_id"));
        // System.out.println(request.getParameter("date_start"));
        // System.out.println(date_create);
        // System.out.println(date_end);
        Conference conference = new Conference();
        conference.setTitle(title);
        conference.setDate_start(date_start);
        conference.setDate_create(date_create);
        conference.setDate_end(date_end);
        conference.setLocation(location);
        conference.setArea(area);
        conference.setTopic(topic);
        conference.setIntroduction(introduction);
        conference.setConfer_link(confer_link);
        conference.setExternal_link(external_link);
        conference.setAnnouncer_id(announcer_id);
        boolean flag=false;
        flag=conferenceManageService.createConference(conference);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据会议ID查询会议信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/queryConference")
    public Conference queryConferenceById(HttpServletRequest request) {
        int conference_id = Integer.parseInt(request.getParameter("conference_id"));
        Conference conference = conferenceManageService.queryConferenceById(conference_id);
        return conference;
    }

    /**
     * 修改会议信息
     * @param request
     * @param response
     */
    @RequestMapping("/admin/updateConference")
    public void updateConference(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String result = "0";
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_start= sdf.parse(request.getParameter("date_start"));
        Date date_end= sdf.parse(request.getParameter("date_end"));
        String location = request.getParameter("location");
        String area = request.getParameter("area");
        String topic = request.getParameter("topic");
        String introduction = request.getParameter("introduction");
        String confer_link = request.getParameter("confer_link");
        String external_link = request.getParameter("external_link");
        Conference conference = new Conference();
        conference.setId(id);
        conference.setTitle(title);
        conference.setDate_start(date_start);
        conference.setDate_end(date_end);
        conference.setLocation(location);
        conference.setArea(area);
        conference.setTopic(topic);
        conference.setIntroduction(introduction);
        conference.setConfer_link(confer_link);
        conference.setExternal_link(external_link);
        boolean flag = false;
        flag = conferenceManageService.updateConference(conference);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除会议
     * @param request
     * @param response
     */
    @RequestMapping("/admin/deleteConference")
    public void deleteConference(HttpServletRequest request, HttpServletResponse response){
        String result="0";
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag=false;
        flag = conferenceManageService.deleteConference(id);
        if(flag) result = "1";
        try{
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("{\"msg\":\""+result+"\"}");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
