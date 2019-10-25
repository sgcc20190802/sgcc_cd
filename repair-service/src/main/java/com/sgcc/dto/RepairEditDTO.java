package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairEditDTO implements Serializable {

    private static final long serialVersionUID = -6165795304311113089L;

    private String repairId;

    private String openId;

    private String repairContent;

    private String repairContact;

    private String repairTel;

    private String repairAddr;

    private String repairImg1;

    private String repairImg2;

    private String repairImg3;

}
