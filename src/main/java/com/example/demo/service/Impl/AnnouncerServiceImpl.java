package com.example.demo.service.Impl;

import com.example.demo.mapper.AnnouncerMapper;
import com.example.demo.mapper.ReviewerMapper;
import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.AnnouncerService;
import com.example.demo.service.ReviewerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/2 12:39
 */
@Service
public class AnnouncerServiceImpl implements AnnouncerService {
    @Resource
    AnnouncerMapper announcerMapper;


    @Override
    public Announcer queryAnnouncerByUsername(String username) {
        Announcer announcer=announcerMapper.queryAnnouncerByUsername(username);
        return announcer;
    }

    @Override
    public int addAnnouncer(Announcer announcer) {
        int res= announcerMapper.addAnnouncer(announcer);
        return res;
    }
}
