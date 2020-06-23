package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;


    @Test
    public void findOneTest() {
        Optional<ProductCategory> productCategory = repository.findById(10);
        System.out.println(productCategory.get().toString());
    }

    @Test
    public void saveTest() {
        Optional<ProductCategory> productCategory = repository.findById(10);
        productCategory.get().setCategoryName("category3");
        ProductCategory result = repository.save(productCategory.get());
        Assert.assertNotNull(result);
    }



    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(5, 10, 15);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        System.out.println(result.size());
    }

}