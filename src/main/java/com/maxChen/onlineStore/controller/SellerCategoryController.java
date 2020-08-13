package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.CategoryService;
import com.maxChen.onlineStore.Service.ProductService;
import com.maxChen.onlineStore.dataobjective.ProductCategory;
import com.maxChen.onlineStore.dataobjective.ProductInfo;
import com.maxChen.onlineStore.exception.SellException;
import com.maxChen.onlineStore.form.CategoryForm;
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
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        model.addAttribute("categoryList", productCategoryList);
        return "categoryList";
    }

    @GetMapping("/index")
    public String index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
        ProductCategory productCategory = new ProductCategory();
        if(categoryId != null) {
            try{
                productCategory = categoryService.findOne(categoryId);
            }catch(SellException e) {
                model.addAttribute("message", e.getMessage());
                model.addAttribute("url", "/seller/category/list");
                return "error";
            }
        }

        model.addAttribute("productCategory", productCategory);

        return "categoryIndex";
    }


    @PostMapping("/save")
    public String save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("message", bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("url", "/seller/category/index");
            return "error";
        }

        ProductCategory productCategory = new ProductCategory();

        try{
            if(categoryForm.getCategoryId()!= null) {
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
                model.addAttribute("message", "the category has been updated");
            } else {
                model.addAttribute("message", "the category has been created");
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);

        }catch (SellException e) {
            model.addAttribute("message", e.getMessage());
            model.addAttribute("url", "/seller/category/index");
            return "error";
        }


        model.addAttribute("url", "/seller/category/list");
        return "success";
    }


}
