package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.Service.ProductService;
import com.maxChen.onlineStore.dataobjective.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
