package com.inven.common.model;

import com.inven.param.ProductInformation;

import lombok.*;

import java.sql.Date;


@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductTitle {
    private String product_code;
    private String gender;
    private String make_factory;
    private String unit_price;
    private Date final_update;
    private String make_code;
    private String product_status;

    public ProductTitle(String product_code, String product_status) {
        this.product_code = product_code;
        this.product_status = product_status;
    }


    public ProductTitle(ProductInformation productInformation) {
        this.product_code = productInformation.getProduct_code();
        this.gender = productInformation.getGender();
        this.make_factory = productInformation.getMake_factory();
        this.unit_price = productInformation.getUnit_price();
        this.final_update = productInformation.getFinal_update();
        this.make_code = productInformation.getMake_code();
        this.product_status = productInformation.getProduct_status();
    }

}
