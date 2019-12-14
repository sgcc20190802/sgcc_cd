package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class SuggestionReplyCheckInfoDTO implements Serializable {
        private static final long serialVersionUID = 8649874743434273261L;
//        private String id;

        // 意见id
        private String suggestionId;

        // 用户id
        private String userId;

        // 意见内容
        private String suggestionContent;

        // 联系人
        private String suggestionContact;

        // 联系电话
        private String suggestionTel;

        // 意见提交时间
        private String submitDate;

        private String img_1;

        private String img_2;

        private String img_3;

        private String replyId;

        private String replyContent;

        private String replyDate;

//        private String checkReject;
//
//        private Boolean checkerState;
//
//        private Date checkerDate;

}
