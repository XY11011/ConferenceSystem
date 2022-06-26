package com.example.demo.mapper;

import com.example.demo.pojo.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ConferenceMapper {
    List<Conference> getMyConference(Integer userId);
    List<Conference> getAllConference();
    List<Conference> searchMyConference(Map<String,Object> map);
    List<Conference> searchAllConference(Map<String,Object> map);
    Conference searchConferenceById(Integer conferId);

}
