package com.maxChen.onlineStore.dataobjective;

import com.maxChen.onlineStore.enums.OrderStatusEnum;
import com.maxChen.onlineStore.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**
     * order id
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * pay status, default 0
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** create time */
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    /** update time */
    private Date updateTime;


}
