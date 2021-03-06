package com.maxChen.onlineStore.repository;

import com.maxChen.onlineStore.dataobjective.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByBuyerEmail(String buyerEmail, Pageable pageable);

}
