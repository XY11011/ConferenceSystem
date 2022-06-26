package com.example.demo.service.Impl;

import com.example.demo.mapper.ConferenceManageMapper;
import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Conference;
import com.example.demo.service.ConferenceManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/2 21:11
 */
@Service
public class ConferenceManageServiceImpl implements ConferenceManageService {
    @Resource
    ConferenceManageMapper conferenceManageMapper;

    @Override
    public List<Conference> queryAllConferenceByAnnouncerId(int announcer_id) {
        return conferenceManageMapper.queryAllConferenceByAnnouncerId(announcer_id);
    }

    @Override
    public List<Conference> queryConferenceBySearchName(int announcer_id, String searchString) {
        return conferenceManageMapper.queryConferenceBySearchName(announcer_id, searchString);
    }

    @Override
    public Announcer queryAnnouncerInfoByName(String announcer_name) {
        return conferenceManageMapper.queryAnnouncerInfoByName(announcer_name);
    }

    @Override
    public boolean createConference(Conference conference) {
        return conferenceManageMapper.createConference(conference);
    }

    @Override
    public Conference queryConferenceById(int conference_id) {
        return conferenceManageMapper.queryConferenceById(conference_id);
    }

    @Override
    public boolean updateConference(Conference conference) {
        return conferenceManageMapper.updateConference(conference);
    }

    @Override
    public boolean deleteConference(int id) {
        return conferenceManageMapper.deleteConference(id);
    }
}
