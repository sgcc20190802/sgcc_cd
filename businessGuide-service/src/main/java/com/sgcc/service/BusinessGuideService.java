package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.BusinessCategoryDao;
import com.sgcc.dao.BusinessCategoryRedisDao;
import com.sgcc.dao.BusinessGuideDao;
import com.sgcc.dao.BusinessGuideRedisDao;
import com.sgcc.dto.*;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.BusinessModel;
import com.sgcc.repository.BCRedisRepository;
import com.sgcc.repository.BGRedisRepository;
import com.sgcc.repository.BusinessCategoryRepository;
import com.sgcc.repository.BusinessGuideRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BusinessGuideService {

    @Autowired
    private BusinessGuideRepository businessGuideRepository;

    @Autowired
    private BusinessCategoryRepository businessCategoryRepository;

    @Autowired
    private BGRedisRepository bgRedisRepository;

    public Result saveBusinessGuide(BusinessGuideSubmitDto businessGuideSubmitDto){
        BusinessGuideDao businessGuideDao = BusinessModel.sbumitdtotoredisdaoBG(businessGuideSubmitDto);
        businessGuideDao.setCreateDate(new Date());
        businessGuideDao.setId(UUID.randomUUID().toString());
        businessGuideRepository.insertBusinessGuide(businessGuideDao);
        initRedisBusinessGuide();
        return Result.success();
    }

    public Result updateBusinessGuide(BusinessGuideDto businessGuideDto){
        BusinessGuideDao businessGuideDao = BusinessModel.dtotodaoBG(businessGuideDto);
        businessGuideRepository.updateBusinessGuide(businessGuideDao);
        initRedisBusinessGuide();
        return Result.success();
    }

    public Result deleteBusinessGuide(BusinessGuideDeleteDto businessGuideDeleteDto){
        if(businessGuideDeleteDto.getBusinessGuideIds().size()==0){
            return Result.failure(TopErrorCode.INVALID_PARAMS);
        }
        businessGuideRepository.deleteBusinessGuide(businessGuideDeleteDto.getBusinessGuideIds());
        initRedisBusinessGuide();
        return Result.success();
    }

    public Result getBusinessGuideList(String cid){
        List<BusinessGuideDto> businessGuideDtos;
        List<BusinessGuideRedisDao> businessGuideRedisDaos=new ArrayList<>();
        List<BusinessGuideDao> businessGuideDaos;
        if(null==cid) {
            List<BusinessGuideRedisDao> all = (List)bgRedisRepository.findAll();
            if(all!=null||all.size()>0) {

                businessGuideDtos = BusinessModel.redisdaoTodtoBG(all);
            }else {
                businessGuideDaos = businessGuideRepository.selectBusinessGuide(cid);
                List<BusinessGuideRedisDao> businessGuideDaos1 = BusinessModel.daooToredisdaoBG(businessGuideDaos);
                businessGuideDtos = BusinessModel.redisdaoTodtoBG(businessGuideDaos1);
                //初始化Redis
                initRedisBusinessGuide();
            }
        }else{
            List<BusinessGuideRedisDao> allByCategoryId = bgRedisRepository.findAllByCategoryId(cid);
            if(allByCategoryId!=null||allByCategoryId.size()>0){
                businessGuideDtos = BusinessModel.redisdaoTodtoBG(allByCategoryId);
            }else{
                businessGuideDaos = businessGuideRepository.selectBusinessGuide(cid);
                businessGuideDtos = BusinessModel.daoTodtoBG(businessGuideDaos);
                //初始化Redis
                initRedisBusinessGuide();
            }
        }
        return Result.success(businessGuideDtos);
    }


    //分类

    public Result saveBusinessCategory(BusinessCategorySubmitDto BusinessCategorySubmitDto){
        BusinessCategoryDao businessCategoryDao = BusinessModel.sbumitdtotodaoBC(BusinessCategorySubmitDto);
        businessCategoryDao.setId(UUID.randomUUID().toString());
        businessCategoryRepository.insertBusinessCategory(businessCategoryDao);
        return Result.success();
    }

    public Result updateBusinessCategory(BusinessCategoryDto businessCategoryDto){
        BusinessCategoryDao businessCategoryDao = new BusinessCategoryDao();
        BeanUtils.copyProperties(businessCategoryDto,businessCategoryDao);
        businessCategoryRepository.updateBusinessCategory(businessCategoryDao);
        return Result.success();
    }

    public Result deleteBusinessCategory(BusinessCategoryDeleteDto businessCategoryDeleteDto){
        if(businessCategoryDeleteDto.getBusinessCategoryIds().size()==0){
            return Result.failure(TopErrorCode.INVALID_PARAMS);
        }
        businessCategoryRepository.deleteBusinessCategory(businessCategoryDeleteDto.getBusinessCategoryIds());
        return Result.success();
    }

    public Result getBusinessCategoryList(){
        List<BusinessCategoryDao> businessCategoryDaos = businessCategoryRepository.selectBusinessCategory();
        List<BusinessCategoryDto> businessCategoryDtos = BusinessModel.daoTodtoBC(businessCategoryDaos);
        return Result.success(businessCategoryDtos);
    }


    public void initRedisBusinessGuide(){
        List<BusinessGuideDao> businessGuideDaos = businessGuideRepository.selectBusinessGuide(null);
        List<BusinessGuideRedisDao> businessGuideDaos1 = BusinessModel.daooToredisdaoBG(businessGuideDaos);
        bgRedisRepository.saveAll(businessGuideDaos1);
    }
}
