package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {

    public static List<Product> products;

    public static void main(String[] args) throws IOException {
        //상품 세팅
        setProducts();

        //사용법 안내
        showInstruction();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            validateInput(br.readLine()); //입력값 유효성 검사
        }
    }

    //상품 세팅
    private static void setProducts(){
        //상점 코드 세팅
        List<String> storeCodes = new ArrayList<>();
        List<String> productCodes = new ArrayList<>();
        storeCodes.add("dEjgDx");
        storeCodes.add("NoejZW");

        //상품 세팅
        products = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Product product = new Product(storeCodes, String.format("%09d", i), ExchangeStatus.AVAILABLE);
            products.add(product); //상품 리스트에 추가

        }
    }

    //입력값 유효성 검사
    private static void validateInput(String input){
        //글자수 제한 (30자 이하 입력 가능)
        if (input.length() > 30){
            System.out.println("30자까지 입력하실 수 있습니다. 다시 입력해주세요.");
        }

        String newInput = input.replace(" ", ""); //공백 제거한 입력값

        StringTokenizer st = new StringTokenizer(input);
        switch (st.nextToken().toUpperCase()){
            case "HELP" :
                showInstruction();
                break;
            case "CHECK" :
                if (Pattern.matches("^(?i)check[0-9]{9}", newInput)){ //숫자 개수 검사
                    System.out.println(checkExchangeStatus(newInput.substring(5)));; //상품코드 검사
                } else {
                    System.out.println("상품 코드를 다시 확인해 주세요.");
                }
                break;
            case "CLAIM" :
                if (Pattern.matches("^(?i)claim[a-zA-Z]{6}[0-9]{9}", newInput)){ //숫자 개수 검사
                    System.out.println(exchangeProduct(newInput.substring(5,11), newInput.substring(11))); //상점, 상품코드 검사
                } else if (Pattern.matches("^(?i)claim[a-zA-Z]{6}.*",newInput)) {
                    System.out.println("상품 코드를 다시 확인해 주세요.");
                } else {
                    System.out.println("상점 코드를 다시 확인해 주세요.");
                }
                break;
            default:
                System.out.println("안내된 형식에 맞춰 다시 입력해주세요. HELP를 입력하시면 안내사항을 다시 확인하실 수 있습니다.");
                break;
        }
    }

    //상품 교환 여부 확인
    private static String checkExchangeStatus(String productCode){ //입력값과 동일한 상품코드를 가진 상품의 exchangeStatus 반환
        for (int i=0;i<products.size();i++){
            if(products.get(i).getProductCode().equals(productCode)){ //입력한 상품코드와 실제 상품들 상품코드 비교
                return products.get(i).getExchangeStatus().toString();
            }
        }

        return "입력하신 상품코드가 존재하지 않습니다.";
    }

    //사용법 안내
    private static void showInstruction(){
        System.out.println("<안내사항>");
        System.out.println("1. 상품 교환 여부를 확인하실 고객님들께서는 입력칸에 'CHECK 상품코드(숫자 9자리)'를 입력해주시기 바랍니다.\n예시) CHECK 000 000 001");
        System.out.println("2. 교환을 진행할 고객님들께서는 입력칸에 'CLAIM 상점코드(6문자) 상품코드(숫자 9자리)'를 입력해주시기 바랍니다.\n예시) CLAIM AAAAAA 000 000 001");
        System.out.println("3. 안내사항을 다시 읽고 싶으시다면 'HELP'를 입력해주세요.");
    }

    //상품 교환
    private static String exchangeProduct(String storeCode, String productCode){
        for (int i=0;i<products.size();i++){
            if(products.get(i).getProductCode().equals(productCode) //상품코드 확인
                    && products.get(i).getAvailableStoreCodes().contains(storeCode)){ //교환 가능한 상점코드 확인
                products.get(i).updateAfterExchange(storeCode); //교환한 상점 코드 저장, 상태 UNAVAILABLE로 변경

                return storeCode + " " + productCode + " 상품이 교환 완료되었습니다.";
            }
        }

        return "입력하신 상점코드 또는 상품코드가 존재하지 않습니다. 다시 확인해주세요.";
    }

}
