package com.example.demo.service.Impl;

import com.example.demo.mapper.PaperManageMapper;
import com.example.demo.pojo.Conference;
import com.example.demo.pojo.Paper;
import com.example.demo.pojo.Paper_distri;
import com.example.demo.pojo.Reviewer;
import com.example.demo.service.PaperManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Accelerator
 * @date 2022/6/13 13:56
 */
@Service
public class PaperManageServiceImpl implements PaperManageService {

    @Resource
    PaperManageMapper paperManageMapper;

    @Override
    public List<Paper> queryUndistriPaperBySearch(int confer_id, String paperTitle) {
        return paperManageMapper.queryUndistriPaperBySearch(confer_id, paperTitle);
    }

    @Override
    public List<Paper> queryUndistriPaperBySearchPaperTitle(String paperTitle) {
        return paperManageMapper.queryUndistriPaperBySearchPaperTitle(paperTitle);
    }

    @Override
    public Conference queryConferenceBySearchTitle(String title) {
        return paperManageMapper.queryConferenceBySearchTitle(title);
    }

    @Override
    public List<Conference> queryConferenceByAnnouncerId(int announcer_id) {
        return paperManageMapper.queryConferenceByAnnouncerId(announcer_id);
    }

    @Override
    public Paper queryPaperById(int id) {
        return paperManageMapper.queryPaperById(id);
    }

    @Override
    public List<Reviewer> queryAllReviewer() {
        return paperManageMapper.queryAllReviewer();
    }

    @Override
    public boolean createPaperDistri(Paper_distri paper_distri) {
        return paperManageMapper.createPaperDistri(paper_distri);
    }

    @Override
    public boolean updatePaperDistriState(int id) {
        return paperManageMapper.updatePaperDistriState(id);
    }

    @Override
    public List<Paper> queryDistributedPaperBySearch(int confer_id, String paperTitle) {
        return paperManageMapper.queryDistributedPaperBySearch(confer_id, paperTitle);
    }

    @Override
    public List<Paper> queryDistributedPaperBySearchPaperTitle(String paperTitle) {
        return paperManageMapper.queryDistributedPaperBySearchPaperTitle(paperTitle);
    }

    @Override
    public boolean acceptPaper(int id) {
        return paperManageMapper.acceptPaper(id);
    }

    @Override
    public boolean refusePaper(int id) {
        return paperManageMapper.refusePaper(id);
    }
}
