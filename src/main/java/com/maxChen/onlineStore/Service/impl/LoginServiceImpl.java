package com.maxChen.onlineStore.Service.impl;


import com.maxChen.onlineStore.Service.LoginService;
import com.maxChen.onlineStore.dataobjective.Buyer;
import com.maxChen.onlineStore.dataobjective.Seller;
import com.maxChen.onlineStore.enums.ResultEnum;
import com.maxChen.onlineStore.exception.SellException;
import com.maxChen.onlineStore.repository.BuyerRepository;
import com.maxChen.onlineStore.repository.SellerRepository;
import com.maxChen.onlineStore.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller checkSeller(String sellerName, String password) {
        Seller seller = sellerRepository.findBySellerNameAndPassword(sellerName, MD5Util.code(password));

        if(seller == null) {
            throw new SellException(ResultEnum.SELLER_NOT_MATCH);
        }
        return seller;
    }

    @Override
    public Seller searchBySellerName(String sellerName) {
        Seller seller = sellerRepository.findBySellerName(sellerName);

        return seller;
    }

    @Override
    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public Buyer searchByBuyerEmail(String buyerEmail) {
        Buyer buyer = buyerRepository.findByBuyerEmail(buyerEmail);
        return buyer;
    }

    @Override
    public Buyer checkBuyer(String buyerEmail, String password) {
        Buyer buyer = buyerRepository.findByBuyerEmailAndPassword(buyerEmail, MD5Util.code(password));

        if(buyer == null) {
            throw new SellException(ResultEnum.BUYER_NOT_MATCH);
        }
        return buyer;
    }
}
