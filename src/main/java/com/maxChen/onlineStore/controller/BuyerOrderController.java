package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.BuyerService;
import com.maxChen.onlineStore.Service.OrderService;
import com.maxChen.onlineStore.VO.ResultVO;
import com.maxChen.onlineStore.converter.OrderForm2OrderDTOConverter;
import com.maxChen.onlineStore.dto.OrderDTO;
import com.maxChen.onlineStore.enums.ResultEnum;
import com.maxChen.onlineStore.exception.SellException;
import com.maxChen.onlineStore.form.OrderForm;
import com.maxChen.onlineStore.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;



    //create order
    @PostMapping("/create")
    @ResponseBody
    public ResultVO<Map<String, String>> create(@RequestBody OrderForm orderForm) {

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("cart cannot be empty");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }


    //order list
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("email") String email,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {

        if(StringUtils.isEmpty(email)) {
            log.error("email is empty");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Page<OrderDTO> orderDTOPage = orderService.findList(email, PageRequest.of(page, size));

        return ResultVOUtil.success(orderDTOPage.getContent());

    }


    //order detail
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("email") String email,
                                     @RequestParam("orderId") String orderId) {
        // safety issue: need email param for safety issue
        OrderDTO orderDTO = buyerService.findOrderOne(email, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //cancel order
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("email") String email,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(email, orderId);
        return ResultVOUtil.success();
    }
}
