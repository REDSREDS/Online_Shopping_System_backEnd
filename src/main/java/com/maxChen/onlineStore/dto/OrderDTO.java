package com.maxChen.onlineStore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maxChen.onlineStore.dataobjective.OrderDetail;
import com.maxChen.onlineStore.enums.OrderStatusEnum;
import com.maxChen.onlineStore.enums.PayStatusEnum;
import com.maxChen.onlineStore.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    /**
     * buyer name
     */
    private String buyerName;

    /**
     * buyer phone
     */
    private String buyerPhone;

    /**
     * buyer address
     */
    private String buyerAddress;

    /**
     * Buyer openID
     */
    private String buyerOpenid;

    /**
     * Order amount
     */
    private BigDecimal orderAmount;

    /**
     * order status, default 0
     */
    private Integer orderStatus;

    /**
     * pay status, default 0
     */
    private Integer payStatus;

    /** create time */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /** update time */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


    List<OrderDetail> orderDetailList;
}
