package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.Buyer;
import com.maxChen.onlineStore.dataobjective.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Buyer findByBuyerEmailAndPassword(String buyerEmail, String password);

    Buyer findByBuyerEmail(String buyerEmail);

}
