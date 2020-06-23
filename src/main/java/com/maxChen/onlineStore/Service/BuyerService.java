package com.maxChen.onlineStore.Service;

import com.maxChen.onlineStore.dto.OrderDTO;

public interface BuyerService {
    //find an order
    OrderDTO findOrderOne(String openid, String orderId);

    //cancel an order
    OrderDTO cancelOrder(String openid, String orderId);

}
