package org.example;

import java.util.List;

public class Product {

    private String storeCode;

    private String productCode;

    private ExchangeStatus exchangeStatus;

    Product(String storeCode, String productCode, ExchangeStatus exchangeStatus){
        this.storeCode = storeCode;
        this.productCode = productCode;
        this.exchangeStatus = exchangeStatus;
    }

    public String getStoreCode(){return storeCode;}

    public String getProductCode() {
        return productCode;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public void updateExchangeStatus(String storeCode){
        this.exchangeStatus = ExchangeStatus.UNAVAILABLE;
    }

}
