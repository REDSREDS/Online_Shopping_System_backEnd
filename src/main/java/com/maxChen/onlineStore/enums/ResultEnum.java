package com.maxChen.onlineStore.enums;


import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "parameter is not correct"),
    PRODUCT_NOT_EXIST(10, "this product does not exist"),
    PRODUCT_STOCK_ERROR(11, "stock error"),
    ORDER_NOT_EXIST(12, "order does not exist"),
    ORDERDETAIL_NOT_EXIST(13, "orderdetail not exist"),
    ORDER_STATUS_ERROR(14, "order status is incorrect"),
    ORDER_UPDATE_FAIL(15, "order update is failed"),
    ORDER_DETAIL_EMPTY(16, "order detail is empty"),
    CART_EMPTY(18, "cart cannot be empty"),
    ORDER_OWNER_ERROR(19, "owner does not match"),
    ;


    private Integer code;
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
