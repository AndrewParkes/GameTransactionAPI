package com.sega.dao;

import java.math.BigDecimal;

public class Transaction {

    Integer id;
    Integer fk_user_id;
    String product;
    BigDecimal amount;

    Transaction() {

    }
}
