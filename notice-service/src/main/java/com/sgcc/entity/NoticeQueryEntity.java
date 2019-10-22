package com.sgcc.entity;

import com.sgcc.dao.NoticeDao;
import com.sgcc.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoticeQueryEntity {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<NoticeDao> findNoticeInfoByDistrict(String district){
        return noticeRepository.findNoticeList(district);
    }


    public int insertNotice(NoticeDao noticeDao){
        return noticeRepository.insertNotice(noticeDao);
    }


    public void updateNotice(NoticeDao noticeDao){
        noticeRepository.updateNotice(noticeDao);
    }


    public void delNotice(List<String> ids){
        noticeRepository.delNoticeById(ids);
    }

}
