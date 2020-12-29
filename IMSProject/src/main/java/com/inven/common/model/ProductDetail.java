package com.inven.common.model;


import com.inven.param.ProductInformation;

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

    public ProductDetail(ProductInformation productInformation) {
        this.product_code = productInformation.getProduct_code();
        this.gender = productInformation.getGender();
        this.color_name = productInformation.getColor_name();
        this.s = productInformation.getS();
        this.m = productInformation.getM();
        this.l = productInformation.getL();
        this.xl = productInformation.getXl();
        this.f = productInformation.getF();
        this.total = productInformation.getTotal();
        this.manufacture_day = productInformation.getManufacture_day();
    }
}
