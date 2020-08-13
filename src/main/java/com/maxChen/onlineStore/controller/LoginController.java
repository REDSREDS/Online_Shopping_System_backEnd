package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.LoginService;
import com.maxChen.onlineStore.VO.ResultVO;
import com.maxChen.onlineStore.dataobjective.Buyer;
import com.maxChen.onlineStore.dataobjective.Seller;
import com.maxChen.onlineStore.enums.ResultEnum;
import com.maxChen.onlineStore.exception.SellException;
import com.maxChen.onlineStore.form.LoginForm;
import com.maxChen.onlineStore.form.SignUpForm;
import com.maxChen.onlineStore.utils.MD5Util;
import com.maxChen.onlineStore.utils.ResultVOUtil;
import com.maxChen.onlineStore.utils.keyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    // seller login
    @GetMapping("/seller/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/seller/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/seller/login")
    public String login(@RequestParam("sellerName") String sellerName, @RequestParam("password") String password, HttpSession session, Model model) {
        try{
            Seller seller = loginService.checkSeller(sellerName, password);
            seller.setPassword(null);
            session.setAttribute("seller", seller);
            model.addAttribute("message", "Welcome, admin");
            model.addAttribute("url", "/seller/order/list");

        } catch(SellException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/login");
            return "error";
        }
        return "success";
    }

    @PostMapping("/seller/signup")
    public String signup(@RequestParam("sellerName") String sellerName, @RequestParam("password") String password, Model model) {
        Seller seller = loginService.searchBySellerName(sellerName);

        if (seller != null) {
            model.addAttribute("message", "seller already exist");
            model.addAttribute("url", "/seller/signup");
            return "error";
        }
            seller = new Seller();
            seller.setPassword(MD5Util.code(password));
            seller.setSellerName(sellerName);
            seller.setSellerId(keyUtil.genUniqueKey());
            loginService.saveSeller(seller);
            model.addAttribute("message", "new seller created, please log in...");
            model.addAttribute("url", "/seller/login");
            return "success";
    }

    @GetMapping("/Seller/logout")
    public String logout(HttpSession session, Model model) {
        session.removeAttribute("seller");
        model.addAttribute("message", "logged out");
        model.addAttribute("url", "/seller/login");
        return "success";
    }

    //buyer login
    @PostMapping("/api/buyer/login")
    @ResponseBody
    public ResultVO<Map<String, String>> buyerLogin(@RequestBody LoginForm loginForm, Model model) {
        Map<String,String> map = new HashMap<>();
        System.out.println(loginForm);
        try{
            Buyer buyer = loginService.checkBuyer(loginForm.getBuyerEmail(), loginForm.getPassword());
            map.put("buyerEmail", buyer.getBuyerEmail());
            map.put("buyerName", buyer.getBuyerName());

        } catch(SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVOUtil.success(map);
    }

    @PostMapping("/api/buyer/signup")
    @ResponseBody
    public ResultVO<Map<String, String>> buyerSignup(@RequestBody SignUpForm signUpForm, Model model) {

        Buyer buyer = loginService.searchByBuyerEmail(signUpForm.getBuyerEmail());
        if(buyer != null) {
            return ResultVOUtil.error(ResultEnum.BUYER_ALREADY_EXIST.getCode(), ResultEnum.BUYER_ALREADY_EXIST.getMsg());
        }
        Map<String,String> map = new HashMap<>();
        buyer = new Buyer();
        buyer.setBuyerId(keyUtil.genUniqueKey());
        buyer.setBuyerName(signUpForm.getBuyerName());
        buyer.setPassword(MD5Util.code(signUpForm.getPassword()));
        buyer.setBuyerEmail(signUpForm.getBuyerEmail());
        loginService.saveBuyer(buyer);
        map.put("buyerName", buyer.getBuyerName());
        map.put("buyerEmail", buyer.getBuyerEmail());
        return ResultVOUtil.success(map);
    }


}

