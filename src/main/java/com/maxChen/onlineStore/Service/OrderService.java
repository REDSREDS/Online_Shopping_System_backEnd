package com.maxChen.onlineStore.Service;

import com.maxChen.onlineStore.dataobjective.OrderMaster;
import com.maxChen.onlineStore.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    /** create an order */
    OrderDTO create(OrderDTO orderMaster);
    /** search one order */
    OrderDTO findOne(String orderId);
    /** search orderlist */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /** cancel order */
    OrderDTO cancel(OrderDTO orderDTO);
    /** finish order */
    OrderDTO finish(OrderDTO orderDTO);
    /** pay order */
    OrderDTO paid(OrderDTO orderDTO);
}
