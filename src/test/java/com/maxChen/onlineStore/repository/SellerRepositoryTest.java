package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.OrderDetail;
import com.maxChen.onlineStore.dataobjective.Seller;
import com.maxChen.onlineStore.utils.keyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerRepositoryTest {
    @Autowired
    private SellerRepository repository;

    @Test
    public void saveTest() {
        Seller seller = new Seller();
        seller.setSellerId(keyUtil.genUniqueKey());
        seller.setSellerName("admin");
        seller.setPassword("admin");
        Seller result = repository.save(seller);
        Assert.assertNotNull(result);
    }

}