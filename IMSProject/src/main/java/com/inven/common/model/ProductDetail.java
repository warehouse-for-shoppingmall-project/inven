package com.inven.common.model;


import com.inven.param.ProductInformation;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@ToString // String 보기좋게 변환
@NoArgsConstructor
public class ProductDetail {
    private String productCode;
    private String colorName;
    private int s;
    private int m;
    private int l;
    private int xl;
    private int f;
    private int total;
    private Date manufactureDay;

    public ProductDetail(ProductInformation productInformation) {
        this.productCode = productInformation.getProductCode();
        this.colorName = productInformation.getColorName();
        this.s = productInformation.getS();
        this.m = productInformation.getM();
        this.l = productInformation.getL();
        this.xl = productInformation.getXl();
        this.f = productInformation.getF();
        this.total = productInformation.getTotal();
    }
}
