package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.CategoryService;
import com.maxChen.onlineStore.Service.ProductService;
import com.maxChen.onlineStore.dataobjective.ProductCategory;
import com.maxChen.onlineStore.dataobjective.ProductInfo;
import com.maxChen.onlineStore.dto.OrderDTO;
import com.maxChen.onlineStore.enums.ProductStatusEnum;
import com.maxChen.onlineStore.exception.SellException;
import com.maxChen.onlineStore.form.ProductForm;
import com.maxChen.onlineStore.utils.keyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "4") Integer size,
                       Model model) {
        Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(page - 1, size));
        model.addAttribute("productInfoPage", productInfoPage);
        return "productList";
    }

    @GetMapping("/onShelf")
    public String onShelf(@RequestParam("productId") String productId, Model model) {
        try{
            productService.onShelf(productId);
        }catch(SellException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/product/list");
            return "error";
        }

        model.addAttribute("message", "the product is on the shelf");
        model.addAttribute("url", "/seller/product/list");
        return "success";
    }

    @GetMapping("/offShelf")
    public String offShelf(@RequestParam("productId") String productId, Model model) {
        try{
            productService.offShelf(productId);
        }catch(SellException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/product/list");
            return "error";
        }
        model.addAttribute("message", "the product is off the shelf");
        model.addAttribute("url", "/seller/product/list");
        return "success";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "productId", required = false) String productId, Model model) {
        ProductInfo productInfo = new ProductInfo();
        if(!StringUtils.isEmpty(productId)) {
            try{
                productInfo = productService.findOne(productId);
            }catch(SellException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("url", "/seller/product/list");
                return "error";
            }
        }

        List<ProductCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productInfo", productInfo);

        return "index";
    }


    @PostMapping("/save")
    public String save(@Valid ProductForm productForm, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "/seller/product/index");
            return "error";
        }

        ProductInfo productInfo = new ProductInfo();

        try{
            if(!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productService.findOne(productForm.getProductId());
                model.addAttribute("message", "the product has been updated");
            } else {
                productForm.setProductId(keyUtil.genUniqueKey());
                model.addAttribute("message", "the product has been created");
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productService.save(productInfo);

        }catch (SellException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/product/index");
            return "error";
        }


        model.addAttribute("url", "/seller/product/list");
        return "success";
    }


}
