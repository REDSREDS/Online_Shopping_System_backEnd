package com.maxChen.onlineStore.dataobjective;

import com.maxChen.onlineStore.enums.OrderStatusEnum;
import com.maxChen.onlineStore.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

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
     * buyer phone
     */
    private String buyerPhone;

    /**
     * buyer address
     */
    private String buyerAddress;

    /**
     * Buyer openID
     */
    private String buyerOpenid;

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
    private Date createTime;

    /** update time */
    private Date updateTime;
}
