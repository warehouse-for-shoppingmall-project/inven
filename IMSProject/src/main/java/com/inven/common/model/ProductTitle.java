package com.inven.common.model;

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
    private int unit_price;
    private String final_update;
    private int make_code;


}
