package com.inven.param;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductInformation {

    // title param
    private String productCode;
    private String gender;
    private String unitPrice;
    private String makeFactory;
    private String finalUpdate;
    private String makeCode;
    private String productStatus;


    //detail param
//    private String product_code;
    private String colorName;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int f;
    private int total;
    private Date manufactureDay;
}
