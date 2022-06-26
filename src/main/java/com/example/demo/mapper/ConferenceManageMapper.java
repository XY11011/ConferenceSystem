package com.example.demo.mapper;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Repository
@Mapper
public interface ConferenceManageMapper {

    // 通过会议发布者名字查找其所有会议
    List<Conference> queryAllConferenceByAnnouncerId(int announcer_id);

    // 搜索框搜索会议标题查找会议
    List<Conference> queryConferenceBySearchName(int announcer_id, String searchString);

    // 通过会议发布者名字查找其个人信息
    Announcer queryAnnouncerInfoByName(String announcer_name);

    // 会议发布者创建会议
    boolean createConference(Conference conference);

    // 根据会议ID查询会议信息
    Conference queryConferenceById(int conference_id);

    // 修改会议信息
    boolean updateConference(Conference conference);

    // 删除会议信息
    boolean deleteConference(int id);

}
