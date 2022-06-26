package com.example.demo.mapper;

import com.example.demo.pojo.Announcer;
import com.example.demo.pojo.Reviewer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:13
 */
@Repository
@Mapper
public interface AnnouncerMapper {

    // 通过（用户名）查找会议发布者
    Announcer queryAnnouncerByUsername(String username);

    //添加会议发布者
    int addAnnouncer(Announcer announcer);
}
