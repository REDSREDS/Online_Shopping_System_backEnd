package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1232141");
        productInfo.setProductName("mixian");
        productInfo.setProductPrice(new BigDecimal(10.0));
        productInfo.setProductStock(50);
        productInfo.setProductDescription("very delicious");
        productInfo.setProductIcon("http://mixian.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        Assert.assertNotEquals(0, result.size());
    }
}