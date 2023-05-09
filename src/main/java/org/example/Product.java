package org.example;

import java.util.List;

public class Product {

    private List<String> availableStoreCodes; //교환 가능한 상점들

    private String storeCode; //교환된 상점

    private String productCode;

    private ExchangeStatus exchangeStatus;

    Product(List<String> availableStoreCodes, String productCode, ExchangeStatus exchangeStatus){
        this.availableStoreCodes = availableStoreCodes;
        this.productCode = productCode;
        this.exchangeStatus = exchangeStatus;
    }

    public void setAvailableStoreCodes(List<String> storeCodes){
        this.availableStoreCodes=storeCodes;
    }

    public List<String> getAvailableStoreCodes(){return availableStoreCodes;}

    public String getProductCode() {
        return productCode;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public void updateAfterExchange(String storeCode){
        this.storeCode = storeCode; //실제 교환한 상점코드 저장
        this.exchangeStatus = ExchangeStatus.UNAVAILABLE; //교환 가능 여부 변경
    }

}
