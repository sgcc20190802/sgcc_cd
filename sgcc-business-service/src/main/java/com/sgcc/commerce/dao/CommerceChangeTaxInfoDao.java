package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceChangeTaxInfoDao implements Serializable {
    private static final long serialVersionUID = 7915805112297299845L;
    String id;
    String user_open_id;
    String new_install_company_name;
    String new_install_address;
    String new_install_license_img;
    String propertyRight_img1;
    String propertyRight_img2;
    String propertyRight_img3;
    String propertyRight_img4;
    String propertyRight_img5;
    String propertyRight_img6;
    String new_install_name;
    String cq_idcard_positive_img;
    String cq_idcard_back_img;
    String new_install_idcard;
    String new_install_telphone;
    boolean new_install_invoice;
    String invoice_company;
    String invoice_number;
    String invoice_bank;
    String invoice_bank_account;
    String invoice_regist_addr;
    String invoice_phone;
    Date invoice_date;
    String invoice_img;
    String new_install_apply_person;
    String new_install_transactor;
    String sq_idcard_positive_img;
    String sq_idcard_back_img;
    String new_install_transactor_idcard;
    String sq_attorney_img;
    String new_install_transactor_tel;
    Date submit_date;
}
