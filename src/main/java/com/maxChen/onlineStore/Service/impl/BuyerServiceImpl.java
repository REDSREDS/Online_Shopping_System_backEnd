package com.maxChen.onlineStore.Service.impl;

import com.maxChen.onlineStore.Service.BuyerService;
import com.maxChen.onlineStore.Service.OrderService;
import com.maxChen.onlineStore.dto.OrderDTO;
import com.maxChen.onlineStore.enums.ResultEnum;
import com.maxChen.onlineStore.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);

        if(orderDTO == null) {
            log.error("cannot find the order, orderId = {}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }



    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        if(orderDTO == null) {
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("buyer id does not match. openid = {}, orderDTO = {}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
