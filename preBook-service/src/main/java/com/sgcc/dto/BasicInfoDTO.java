package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfoDTO implements Serializable {

    private static final long serialVersionUID = 5822620953162887619L;

    private List<String> serviceHalls = new ArrayList<>();

    private Map<String,String> lineup = new LinkedHashMap<>();

    private String deviceStatus;

    private int blacklistCount;

}
