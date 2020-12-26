package com.inven.param;


import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;

import java.util.List;

//Class for modify
public class ProductModifiedInformation {
    //productTitle 공통이니까 하나
    //productDetail N개니까 List
    // how??????
    public ProductModifiedInformation(ProductTitle productTitle ,List<ProductDetail> productDetails){
        this.productTitle = productTitle;
        this.productDetails = productDetails;
    }
    private ProductTitle productTitle;
    private List<ProductDetail> productDetails;

    public ProductTitle getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(ProductTitle productTitle) {
        this.productTitle = productTitle;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
