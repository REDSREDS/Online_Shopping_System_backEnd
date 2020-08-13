package com.maxChen.onlineStore.Service;

import com.maxChen.onlineStore.dataobjective.ProductInfo;
import com.maxChen.onlineStore.dto.CartDTO;
import com.maxChen.onlineStore.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * find all product in stock
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

//    add stock
    void increaseStock(List<CartDTO> cartDTOList);

//    delete stock
    void decreaseStock(List<CartDTO> cartDTOList);

    // on shelf
    ProductInfo onShelf(String productId);

    //off shelf
    ProductInfo offShelf(String productId);


}
