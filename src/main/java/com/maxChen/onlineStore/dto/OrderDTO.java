package com.maxChen.onlineStore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maxChen.onlineStore.dataobjective.OrderDetail;
import com.maxChen.onlineStore.enums.OrderStatusEnum;
import com.maxChen.onlineStore.enums.PayStatusEnum;
import com.maxChen.onlineStore.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    /**
     * buyer name
     */
    private String buyerName;

    /**
     * buyer email
     */
    private String buyerEmail;

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

    @JsonIgnore
    public String getOrderStatusMsg() {
        String msg = "";
        for(OrderStatusEnum status : OrderStatusEnum.values()) {
            if(status.getCode() == orderStatus) {
                msg = status.getMsg();
            }
        }
        return msg;
    }

    @JsonIgnore
    public String getPayStatusMsg() {
        String msg = "";
        for(PayStatusEnum status : PayStatusEnum.values()) {
            if(status.getCode() == payStatus) {
                msg = status.getMsg();
            }
        }
        return msg;
    }
}
