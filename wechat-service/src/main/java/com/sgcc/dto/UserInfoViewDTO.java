package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoViewDTO implements Serializable {
    private static final long serialVersionUID = 2982259712037565402L;
    private String openid;
    private String nickname;
    private Integer sex;
    private String city;
    private String headimgurl;
}
