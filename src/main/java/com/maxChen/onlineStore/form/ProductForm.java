package com.maxChen.onlineStore.form;

import com.maxChen.onlineStore.enums.ProductStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;

    @NotEmpty(message = "name must be filed")
    private String productName;

    @NotNull(message = "price must be filed")
    private BigDecimal productPrice;

    @NotNull(message = "stock must be filed")
    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer productStatus = ProductStatusEnum.DOWN.getCode();

    @NotNull(message = "categoryType must be filed")
    private Integer categoryType;
}
