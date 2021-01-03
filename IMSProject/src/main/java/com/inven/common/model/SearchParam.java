package com.inven.common.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchParam {
    private String productStatus;
    private String gender;
    private String startDate;
    private String endDate;
    private String productCode;
    private int pageSize;
    private int pageNo;
    private int start_idx;
    private int end_idx;

    public SearchParam(){
        this.productStatus="";
        this.gender="";
        this.startDate="";
        this.endDate="";
        this.productCode="";
        this.pageNo = 1;
        this.pageSize = 10;
    }
//    private String query;
}
