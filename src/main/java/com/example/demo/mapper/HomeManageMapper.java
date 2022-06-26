package com.example.demo.mapper;

import com.example.demo.pojo.Reviewer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Accelerator
 * @date 2022/5/1 22:47
 */
@Repository
@Mapper
public interface HomeManageMapper {

    // 查找会议发布者的所有会议数量
    int countAllConference(int announcer_id);

    // 查找所属会议下所有论文数量
    int countPaperByConferenceId(int confer_id);

    // 查找所属会议下所有已分配论文数量
    int countDistributedPaper(int confer_id);

    // 查找所属会议下所有未分配论文数量
    int countUndistriPaper(int confer_id);

}
