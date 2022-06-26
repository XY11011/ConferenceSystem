package com.example.demo.service.Impl;

import com.example.demo.mapper.HomeManageMapper;
import com.example.demo.service.HomeManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Accelerator
 * @date 2022/6/18 21:39
 */
@Service
public class HomeManageServiceImpl implements HomeManageService {

    @Resource
    HomeManageMapper homeManageMapper;

    @Override
    public int countAllConference(int announcer_id) {
        return homeManageMapper.countAllConference(announcer_id);
    }

    @Override
    public int countPaperByConferenceId(int confer_id) {
        return homeManageMapper.countPaperByConferenceId(confer_id);
    }

    @Override
    public int countDistributedPaper(int confer_id) {
        return homeManageMapper.countDistributedPaper(confer_id);
    }

    @Override
    public int countUndistriPaper(int confer_id) {
        return homeManageMapper.countUndistriPaper(confer_id);
    }
}
