package com.maxChen.onlineStore.dataobjective;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Buyer {

    @Id
    private String buyerId;

    private String buyerName;

    private String buyerEmail;

    private String password;

}
