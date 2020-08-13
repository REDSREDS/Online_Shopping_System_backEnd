package com.maxChen.onlineStore.enums;
import lombok.Getter;

@Getter
public enum ProductStatusEnum {

    UP(0, "instock"),
    DOWN(1, "outofstock");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
