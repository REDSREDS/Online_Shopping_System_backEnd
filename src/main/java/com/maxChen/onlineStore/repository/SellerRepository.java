package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.Buyer;
import com.maxChen.onlineStore.dataobjective.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findBySellerNameAndPassword(String sellerName, String password);

    Seller findBySellerName(String sellerName);

}
