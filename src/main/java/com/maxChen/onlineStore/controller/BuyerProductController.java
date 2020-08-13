package com.maxChen.onlineStore.controller;

import com.maxChen.onlineStore.Service.CategoryService;
import com.maxChen.onlineStore.Service.ProductService;
import com.maxChen.onlineStore.VO.ProductInfoVO;
import com.maxChen.onlineStore.VO.ProductVO;
import com.maxChen.onlineStore.VO.ResultVO;
import com.maxChen.onlineStore.dataobjective.ProductCategory;
import com.maxChen.onlineStore.dataobjective.ProductInfo;
import com.maxChen.onlineStore.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    //we need to organize all products by category type,
    @GetMapping("/list")
    public ResultVO list() {
//        1. look for all up products
        List<ProductInfo> productInfoList = productService.findUpAll();

//        2. look for category (one time)
        List<Integer> categoryTypeList = new ArrayList<>();

//        add the category type
        for(ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
//        3. copy to VO classes as the output to frontend server.
        List<ProductVO> productVOList = new ArrayList<>();

        for(ProductCategory productCategory : productCategoryList) {
//            new productVO
            ProductVO productVO = new ProductVO();

            BeanUtils.copyProperties(productCategory, productVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                    System.out.println(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    @GetMapping("/")
    public String test() {
        return "hello, world";
    }

}
