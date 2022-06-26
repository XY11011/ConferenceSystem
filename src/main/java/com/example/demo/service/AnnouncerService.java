package com.example.demo.service;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Reviewer;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:17
 */
public interface AnnouncerService {
    //    通过用户名查找会议发布者
    Announcer queryAnnouncerByUsername(String username);

    //添加会议发布者
    int addAnnouncer(Announcer announcer);
}
