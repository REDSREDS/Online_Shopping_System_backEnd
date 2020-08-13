package com.maxChen.onlineStore.Service.impl;

import com.maxChen.onlineStore.dataobjective.ProductInfo;
import com.maxChen.onlineStore.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1232141");
        Assert.assertEquals("1232141", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productService.findUpAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findAll() {
       Page<ProductInfo> productInfoPage = productService.findAll(PageRequest.of(0, 2));
       System.out.println(productInfoPage.getTotalElements());
       Assert.assertNotEquals(0, productInfoPage.getTotalElements());

    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12321");
        productInfo.setProductName("lamian");
        productInfo.setProductPrice(new BigDecimal(20.0));
        productInfo.setProductStock(40);
        productInfo.setProductDescription("very very delicious");
        productInfo.setProductIcon("http://mixian.com");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(5);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onShelf() {
        ProductInfo productInfo = productService.onShelf("product1");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(), productInfo.getProductStatus());

    }

    @Test
    public void offShelf() {
        ProductInfo productInfo = productService.offShelf("product1");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(), productInfo.getProductStatus());

    }
}