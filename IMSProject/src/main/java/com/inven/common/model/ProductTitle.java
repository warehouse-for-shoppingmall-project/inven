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
    private String productCode;
    private String gender;
    private String makeFactory;
    private String unitPrice;
    private String finalUpdate;
    private String makeCode;
    private String productStatus;

    public ProductTitle(String productCode, String productStatus) {
        this.productCode = productCode;
        this.productStatus = productStatus;
    }


    public ProductTitle(ProductInformation productInformation) {
        this.productCode = productInformation.getProductCode();
        this.gender = productInformation.getGender();
        this.makeFactory = productInformation.getMakeFactory();
        this.unitPrice = productInformation.getUnitPrice();
        this.finalUpdate = productInformation.getFinalUpdate();
        this.makeCode = productInformation.getMakeCode();
        this.productStatus = productInformation.getProductStatus();
    }

}
