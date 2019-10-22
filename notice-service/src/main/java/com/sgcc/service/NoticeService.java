package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.NoticeDao;
import com.sgcc.dto.AddFormDTO;
import com.sgcc.dto.NoticeFormDTO;
import com.sgcc.dto.UpdateFormDTO;
import com.sgcc.entity.NoticeQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.NoticeDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeQueryEntity noticeQueryEntity;

    /**
     * 根据区域查询停电公告信息
     * @param district
     * @return
     */
    public Result queryNoticeInfo(String district){

        try {
            if (Strings.isNullOrEmpty(district))
                return Result.failure(TopErrorCode.PARAMETER_ERR);

            List<NoticeDao> noticeDaos = noticeQueryEntity.findNoticeInfoByDistrict(district);

            NoticeDomainModel noticeDomainModel = new NoticeDomainModel(noticeDaos);
            noticeDomainModel.selectByDistrictTransform();

            return Result.success(noticeDomainModel.getNoticeFormDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 新增停电公告
     * @param addFormDTO
     * @return
     */
    public Result insertNoticeInfo(AddFormDTO addFormDTO){

        try {
            if (Strings.isNullOrEmpty(addFormDTO.getDistrict()) ||
                    Strings.isNullOrEmpty(addFormDTO.getTypeName()) ||
                    Strings.isNullOrEmpty(addFormDTO.getTime()) ||
                    Strings.isNullOrEmpty(addFormDTO.getRange())
                )
                return Result.failure(TopErrorCode.PARAMETER_ERR);

            NoticeDomainModel noticeDomainModel = new NoticeDomainModel(addFormDTO);
            noticeDomainModel.insertTransform();
            int result = noticeQueryEntity.insertNotice(noticeDomainModel.getNoticeDao());

            if (result > 0){
                System.out.println("新增成功!");
                return Result.success("新增成功！");
            }else {
                System.out.println("新增失败!");
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 修改停电公告
     * @param updateFormDTO
     * @return
     */
    public Result updateNoticeInfo(UpdateFormDTO updateFormDTO){

        try {
            if (Strings.isNullOrEmpty(updateFormDTO.getNoticeId()))
                return Result.failure(TopErrorCode.PARAMETER_ERR);

            NoticeDomainModel noticeDomainModel = new NoticeDomainModel(updateFormDTO);
            noticeDomainModel.updateTransform();
            noticeQueryEntity.updateNotice(noticeDomainModel.getNoticeDao());
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 删除停电公告
     * @param ids
     * @return
     */
    public Result delNoticeInfo(List<String> ids){

        try {
            if (ids.size() <= 0)
                return Result.failure(TopErrorCode.PARAMETER_ERR);
            noticeQueryEntity.delNotice(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



}
