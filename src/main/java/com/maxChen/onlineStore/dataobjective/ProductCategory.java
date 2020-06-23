package com.maxChen.onlineStore.dataobjective;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /**
     * category id
     */

    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * category name
     */
    private String categoryName;
    /**
     * category type
     */
    private Integer categoryType;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public ProductCategory() {
        super();
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}
