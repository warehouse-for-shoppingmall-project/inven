package com.inven.param;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderParameter {

    private int orderNo;                //주문 번호(PK)
    private String custId;              //회원 ID
    private String custName;            //회원 명
    private String orderStatus;         //주문 상태 ( 1. 주문 2. 발송 3. 수령 4.취소)

    private String prodName;            //제품 명
    private String prodCode;            //제품 코드
    private String color;               //색상
    private String size;                //사이즈
    private int qty;                    //수량
    private int price;                  //가격

    // html에서는 addr + addr2 만 쓰고 있음. (부산 수영구 광남로 + 562동 310호) 이렇게만 나오게
    // 지번주소도 보여주는게 좋을까?
    private String addr;                //도로명 주소
    private String addr2;               //상세 주소(몇 동 몇 호)
    private String addr3;               //지번 주소
    private String addr4;               //우편 번호
    private String addr5;               //뭔지 모르겠뜸 (~~동) 이렇게만 되어있음

    private String deliveryCompany;     //배송사(택배회사)
    private String deliveryNum;         //운송장번호
    private Date orderTime;             //주문 시각

    //phone은 우리쪽에서 필요한 정보일까 싶어서 html에서 뿌리진 않았음
    private String phone;               //핸드폰 번호


}
