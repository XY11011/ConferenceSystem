package com.example.demo.mapper;

import com.example.demo.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Repository
@Mapper
public interface PaperManageMapper {

    // 根据会议id和论文名称查询未分配的论文
    List<Paper> queryUndistriPaperBySearch(int confer_id, String paperTitle);
    // 根据会议id和论文名称查询未分配的论文（未指定会议情况下）
    List<Paper> queryUndistriPaperBySearchPaperTitle(String paperTitle);
    // 根据会议名查找会议信息
    Conference queryConferenceBySearchTitle(String title);
    // 根据发布者ID查找会议信息
    List<Conference> queryConferenceByAnnouncerId(int announcer_id);
    // 根据会议ID查找会议信息
    Paper queryPaperById(int id);
    // 查找所有的审稿人信息
    List<Reviewer> queryAllReviewer();
    // 添加论文分配信息
    boolean createPaperDistri(Paper_distri paper_distri);
    // 论文分配审稿人后修改论文分配状态
    boolean updatePaperDistriState(int id);


    // 根据会议id和论文名称查询未分配的论文——已分配论文
    List<Paper> queryDistributedPaperBySearch(int confer_id, String paperTitle);
    // 根据会议id和论文名称查询未分配的论文（未指定会议情况下）——已分配论文
    List<Paper> queryDistributedPaperBySearchPaperTitle(String paperTitle);
    // 录用论文后修改状态
    boolean acceptPaper(int id);
    // 退回论文后修改状态
    boolean refusePaper(int id);

}
