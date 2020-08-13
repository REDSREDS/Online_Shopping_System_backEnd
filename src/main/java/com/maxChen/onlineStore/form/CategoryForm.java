package com.maxChen.onlineStore.form;

import com.maxChen.onlineStore.enums.ProductStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "name must be filed")
    private String categoryName;

    @NotNull(message = "price must be filed")
    private Integer CategoryType;

}
