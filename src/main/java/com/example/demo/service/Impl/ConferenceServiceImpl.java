package com.example.demo.service.Impl;

import com.example.demo.mapper.ConferenceMapper;
import com.example.demo.pojo.Conference;
import com.example.demo.service.ConferenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Resource
    ConferenceMapper conferenceMapper;

    @Override
    public List<Conference> getMyConference(Integer userId) {
        List<Conference> myConference = conferenceMapper.getMyConference(userId);
        return myConference;
    }

    @Override
    public List<Conference> getAllConference() {
        List<Conference> allConference = conferenceMapper.getAllConference();
        return allConference;
    }

    @Override
    public List<Conference> searchMyConference(Map<String, Object> map) {
        List<Conference> myConference = conferenceMapper.searchMyConference(map);
        return myConference;
    }

    @Override
    public List<Conference> searchAllConference(Map<String, Object> map) {
        List<Conference> allConference = conferenceMapper.searchAllConference(map);
        return allConference;
    }

    @Override
    public Conference searchConferenceById(Integer conferId) {
        Conference conference = conferenceMapper.searchConferenceById(conferId);
        return conference;
    }
}
