package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrebookModel {


    public BasicInfoDTO getBasicInfoTrans(String serviceHall, LineUpInfoOutDTO lineUpInfoDTO, String deviceStatus, int count) {
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
        basicInfoDTO.getServiceHalls().add(serviceHall);
        basicInfoDTO.setLineup(lineUpInfoDTO.getData());
        basicInfoDTO.setDeviceStatus(deviceStatus);
        basicInfoDTO.setBlacklistCount(count);
        return basicInfoDTO;
    }


    public PrebookInfoDao addPrebookTrans(PrebookInfoSubmitDTO dto) {

        PrebookInfoDao prebookInfoDao = new PrebookInfoDao(
                UUID.randomUUID().toString(),
                dto.getUserOpenId(),
                dto.getBusinessTypeId(),
                dto.getBusinessTypeName(),
                dto.getServiceHallId(),
                dto.getServiceHallName(),
                dto.getHouseholdNo(),
                null,
                null,
                dto.getContact(),
                dto.getContactTel(),
                Utils.GetCurTime(),
                1,
                null,
                null

        );

        return prebookInfoDao;
    }


    public List<PrebookInfoViewDTO> getPrebookInfoTrans(List<PrebookInfoDao> prebookInfoDaos) {

        List<PrebookInfoViewDTO> prebookInfoViewDTOS = new ArrayList<>();

        prebookInfoDaos.forEach(dao -> {
            prebookInfoViewDTOS.add(new PrebookInfoViewDTO(
                    dao.getId(),
                    dao.getBusinessTypeName(),
                    dao.getContact(),
                    dao.getContactTel(),
                    Utils.GetTime(dao.getSubmitDate())
            ));
        });
        return prebookInfoViewDTOS;
    }



    public PrebookDetailViewDTO getPrebookInfoDetailTrans(PrebookInfoDao prebookInfoDao,String checkName) {

        PrebookDetailViewDTO prebookDetailViewDTO = new PrebookDetailViewDTO(
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                prebookInfoDao.getHouseholdNo(),
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName

        );
        return prebookDetailViewDTO;
    }


    public PrebookDetailViewDTO getCheckDetailListTrans(PrebookInfoDao prebookInfoDao, String checkName) {
        PrebookDetailViewDTO prebookDetailViewDTO =null;

        prebookDetailViewDTO = new PrebookDetailViewDTO(
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                prebookInfoDao.getHouseholdNo(),
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName

        );

        return prebookDetailViewDTO;

    }


    public PrebookInfoDao updateCheckPrebookTrans(PrebookInfoEditDTO dto) {
        PrebookInfoDao prebookInfoDao = new PrebookInfoDao(
                dto.getId(),
                null,
                null,
                null,
                null,
               null,
                null,
                null,
                null,
                null,
                null,
                null,
                dto.getStatus(),
                dto.getRejectReason(),
                dto.getUserOpenId()

        );

        return prebookInfoDao;
    }







}
