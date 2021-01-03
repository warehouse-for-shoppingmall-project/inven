package com.inven.param;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductInformation {

    // title param
    private String product_code;
    private String gender;
    private String unit_price;
    private String make_factory;
    private Date final_update;
    private String make_code;
    private String product_status;


    //detail param
//    private String product_code;
    private String color_name;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int f;
    private int total;
    private Date manufacture_day;
}
