package com.maxChen.onlineStore.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "name must be filed")
    private String name;

    @NotEmpty(message = "phone number must be filed")
    private String phone;

    @NotEmpty(message = "address number must be filed")
    private String address;

    @NotEmpty(message = "open id must be filed")
    private String openid;

    @NotEmpty(message = "cart can not be empty")
    private String items;

}
