package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.EmailService;
import com.maxChen.onlineStore.Service.OrderService;
import com.maxChen.onlineStore.dto.OrderDTO;
import com.maxChen.onlineStore.enums.ResultEnum;
import com.maxChen.onlineStore.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       Model model) {
        Page<OrderDTO> orderDTOPage = orderService.findAll(PageRequest.of(page - 1, size));
        model.addAttribute("orderDTOPage", orderDTOPage);
        return "orderList";
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam(value = "orderId") String orderId, Model model) {
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            emailService.sendMail(orderDTO.getBuyerEmail(), "order canceled", "your order: " + orderDTO.getOrderId() + " has been canceled");
        } catch(SellException e) {
            log.error("[seller cancel order] cannot find the order");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/order/list");
            return "error";
        }
        model.addAttribute("message", "order canceled");
        model.addAttribute("url", "/seller/order/list");

        return "success";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,
                       Model model) {
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            model.addAttribute("orderDTO", orderDTO);
        } catch (SellException e) {
            log.error("[seller cancel order] cannot find the order");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/order/list");
            return "error";
        }

        return "orderDetail";
    }

    @GetMapping("/finish")
    public String finish(@RequestParam(value = "orderId") String orderId, Model model) {
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            emailService.sendMail(orderDTO.getBuyerEmail(), "Order ready", "your order: " + orderDTO.getOrderId() + " is ready for pickup");
        } catch(SellException e) {
            log.error("[seller cancel order] cannot find the order");
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/order/list");
            return "error";
        }
        model.addAttribute("message", "order finished");
        model.addAttribute("url", "/seller/order/list");

        return "success";
    }


}