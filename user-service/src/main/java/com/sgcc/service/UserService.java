package com.sgcc.service;

import com.example.Utils;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.CommerceInfoCorrectDao;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.dto.ElectricityTypeDTO;
import com.sgcc.dto.MonthlyBillsDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectEditDTO;
import com.sgcc.dto.commerce.CommerceInfoCorrectSubmitDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectEditDTO;
import com.sgcc.dto.inhabitant.InhabitantInfoCorrectSubmitDTO;
import com.sgcc.entity.event.CommerceEventEntity;
import com.sgcc.entity.event.InhabitantEventEntity;
import com.sgcc.entity.query.CommerceQueryEntity;
import com.sgcc.entity.query.InhabitantQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private InhabitantQueryEntity inhabitantQueryEntity;

    @Autowired
    private InhabitantEventEntity inhabitantEventEntity;


    @Autowired
    private CommerceQueryEntity commerceQueryEntity;

    @Autowired
    private CommerceEventEntity commerceEventEntity;



    /**********************************************居民*********************************************/

    /**
     * 查询所有居民信息修正订单
     * @return
     */
    public Result queryInhabitantInfoCorrect(){
        try {
            List<InhabitantInfoCorrectDao> infoCorrectDaos = inhabitantQueryEntity.findAll();
            UserModel userModel = new UserModel();
            userModel.queryInhabitantInfoCorrectTransform(infoCorrectDaos);

            if (userModel.getInhabitantInfoCorrectQueryDTOS().size() > 0){
                return Result.success(userModel.getInhabitantInfoCorrectQueryDTOS());
            }else {
                return Result.success("没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 新增居民信息修正订单
     * @param dto
     * @return
     */
    public Result addInhabitantInfoCorrect(InhabitantInfoCorrectSubmitDTO dto){
        if (dto == null)
            return Result.failure(TopErrorCode.ZERO_OBJ);

        try {
            UserModel userModel = new UserModel();
            userModel.addInhabitantInfoCorrectTransform(dto);
            int count = inhabitantEventEntity.addInfoCorrectOrder(userModel.getInhabitantInfoCorrectDao());
            if (count > 0){
                return Result.success("新增成功");
            }else {
                return Result.failure("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 修改居民信息修正订单
     * @param dto
     * @return
     */
    public Result updateInhabitantInfoCorrect(InhabitantInfoCorrectEditDTO dto){
        try {
            UserModel userModel = new UserModel();
            userModel.updateInhabitantInfoCorrectTransform(dto);
            int count = inhabitantEventEntity.updateInfoCorrectOrder(userModel.getInhabitantInfoCorrectDao());
            if (count > 0){
                return Result.success("修改成功");
            }else {
                return Result.failure("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 删除居民信息修正订单
     * @param ids
     * @return
     */
    public Result delInhabitantInfoCorrect(List<String> ids){
        try {

            int count = inhabitantEventEntity.delInfoCorrectOrder(ids);
            if (count > 0){
                return Result.success("删除成功");
            }else {
                return Result.failure("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**********************************************个体工商业*********************************************/

    /**
     * 新增个体工商业信息修正订单
     * @param dto
     * @return
     */
    public Result addCommerceInfoCorrect(CommerceInfoCorrectSubmitDTO dto) {
        if (dto == null)
            return Result.failure(TopErrorCode.GENERAL_ERR);

        try {
            UserModel userModel = new UserModel();
            userModel.addCommerceInfoCorrectTransform(dto);
            int count = commerceEventEntity.addCommerceInfoCorrectOrder(userModel.getCommerceInfoCorrectDao());
            if (count > 0 ){
                return Result.success("新增成功");
            }else {
                return Result.failure("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 查询所有个体工商业信息修正订单
     * @return
     */
    public Result queryCommerceInfoCorrect() {
        try {
            List<CommerceInfoCorrectDao> commerceInfoCorrectDaos = commerceQueryEntity.findCommerceAll();
            UserModel userModel = new UserModel();
            userModel.queryCommerceInfoCorrectTransform(commerceInfoCorrectDaos);
            if (userModel.getCommerceInfoCorrectQueryDTOS().size() > 0){
                return Result.success(userModel.getCommerceInfoCorrectQueryDTOS());
            }else {
                return Result.failure("没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 修改个体工商业信息修正订单
     * @param dto
     * @return
     */
    public Result updateCommerceInfoCorrect(CommerceInfoCorrectEditDTO dto) {
        try {
            UserModel userModel = new UserModel();
            userModel.updateCommerceInfoCorrectTransform(dto);
            int count = commerceEventEntity.updateCommerceInfoCorrectOrder(userModel.getCommerceInfoCorrectDao());
            if (count > 0 ){
                return Result.success("修改成功");
            }else {
                return Result.failure("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 删除个体工商业信息修正订单
     * @param ids
     * @return
     */
    public Result delCommerceInfoCorrect(List<String> ids) {
        if (ids.size() <= 0)
            return Result.failure(TopErrorCode.GENERAL_ERR);
        try {
            int count = commerceEventEntity.delCommerceInfoCorrectOrder(ids);
            if (count > 0 ){
                return Result.success("删除成功");
            }else {
                return Result.failure("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



    public Result queryBillInfoById(String userNo,long date){
        if (Strings.isNullOrEmpty(userNo))
            return Result.failure(TopErrorCode.NO_DATAS);

        try {

            MonthlyBillsDTO monthlyBillsDTO = new MonthlyBillsDTO();
            monthlyBillsDTO.setUserName("张三");
            monthlyBillsDTO.setUserNo(userNo);
            monthlyBillsDTO.setBillMonth(new Date(date));
            monthlyBillsDTO.setStartDate(Utils.GetMonth(new Date(date)));
            monthlyBillsDTO.setEndDate(Utils.GetTimeForYMD(new Date(date)));
            monthlyBillsDTO.setStartNum(16366);
            monthlyBillsDTO.setEndNum(16699);
            monthlyBillsDTO.setTotalNum(monthlyBillsDTO.getEndNum()-monthlyBillsDTO.getStartNum());
            monthlyBillsDTO.setTotalSum(201.43);

            List<ElectricityTypeDTO> flist = new ArrayList<>();
            List<ElectricityTypeDTO> slist = new ArrayList<>();


            ElectricityTypeDTO electricityTypeDTO = null;
            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第一阶梯");
            electricityTypeDTO.setTotalNum(180);
            electricityTypeDTO.setTotalSum(117.63);
            flist.add(electricityTypeDTO);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第二阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);
            flist.add(electricityTypeDTO);

            electricityTypeDTO = new ElectricityTypeDTO();
            electricityTypeDTO.setName("第三阶梯");
            electricityTypeDTO.setTotalNum(100);
            electricityTypeDTO.setTotalSum(67.20);
            flist.add(electricityTypeDTO);


            ElectricityTypeDTO electricityTypeDTO2 = null;
            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("峰段");
            electricityTypeDTO2.setTotalNum(180);
            electricityTypeDTO2.setTotalSum(117.63);
            slist.add(electricityTypeDTO2);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("平段");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);
            slist.add(electricityTypeDTO2);

            electricityTypeDTO2 = new ElectricityTypeDTO();
            electricityTypeDTO2.setName("谷段");
            electricityTypeDTO2.setTotalNum(100);
            electricityTypeDTO2.setTotalSum(67.20);
            slist.add(electricityTypeDTO2);

            monthlyBillsDTO.setFlist(flist);
            monthlyBillsDTO.setSlist(slist);
            return Result.success(monthlyBillsDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("查询月度账单失败!");
        }
    }

}
