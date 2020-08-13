package com.maxChen.onlineStore.Service;


import com.maxChen.onlineStore.dataobjective.Buyer;
import com.maxChen.onlineStore.dataobjective.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoginService {

    Seller saveSeller(Seller seller);

    Seller searchBySellerName(String sellerName);

    Seller checkSeller(String sellerName, String password);

    Buyer saveBuyer(Buyer buyer);

    Buyer searchByBuyerEmail (String buyerEmail);

    Buyer checkBuyer(String buyerEmail, String password);

}

