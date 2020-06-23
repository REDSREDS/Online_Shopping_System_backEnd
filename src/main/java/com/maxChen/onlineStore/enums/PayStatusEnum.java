package com.maxChen.onlineStore.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0, "wait for payment"),
    SUCCESS(1, "payment succeed");


    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
