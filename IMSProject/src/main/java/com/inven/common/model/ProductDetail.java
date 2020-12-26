package com.inven.common.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString // String 보기좋게 변환
public class ProductDetail {
    private String product_code;
    private String color_name;
    private String gender;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int f;
    private int total;
    private String manufacture_day;


}
