package com.example.demo.service;

import com.example.demo.pojo.Conference;

import java.util.List;
import java.util.Map;

public interface ConferenceService {
    List<Conference> getMyConference(Integer userId);
    List<Conference> getAllConference();
    List<Conference> searchMyConference(Map<String,Object> map);
    List<Conference> searchAllConference(Map<String,Object> map);
    Conference searchConferenceById(Integer conferId);
}
