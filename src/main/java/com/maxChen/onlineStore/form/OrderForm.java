package com.maxChen.onlineStore.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maxChen.onlineStore.converter.RawJsonDeserializer;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class  OrderForm {

    @NotEmpty(message = "name must be filed")
    private String name;

    @NotEmpty(message = "email must be filed")
    private String email;

    @NotEmpty(message = "cart can not be empty")
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String items;

}
