package com.maxChen.onlineStore.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import oracle.jrockit.jfr.openmbean.ProducerDescriptorType;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;


    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;
}
