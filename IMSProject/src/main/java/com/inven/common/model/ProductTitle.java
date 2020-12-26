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
public class ProductTitle {
    private String product_code;
    private String make_factory;
    private String unit_price;
    private String final_update;
    private String make_code;
    private String product_status;

    public ProductTitle(String product_code, String product_status) {
        this.product_code = product_code;
        this.product_status = product_status;
    }

    public ProductTitle(ProductInformation productInformation) {
        this.product_code = productInformation.getProduct_code();
        this.make_factory = productInformation.getMake_factory();
        this.unit_price = productInformation.getUnit_price();
        this.final_update = productInformation.getFinal_update();
        this.make_code = productInformation.getMake_code();
        this.product_status = productInformation.getProduct_status();
    }
}
