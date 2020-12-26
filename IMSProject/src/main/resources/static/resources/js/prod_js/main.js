// select의 name이 status인 놈의 값이 변경되면 실행한다.
$('select[name=status]').change(function () {
    let con = confirm('상태를 변경하시겠습니까?');
    if (!con) return;
    let select = $(this);
    let data = {
        product_status: $(this).val(),
        product_code: $(this).parents('tr').children().first().text()
    };
    console.log(data);
    // data는 내가 보내려고 하는 데이터 안에 내용
    // dataType은 내가 json으로 받겠다.
    // contentType은 내가 json으로 보내겠다.
    $.ajax({
        type: "post",
        url: "/prod/async/upStatus",
        data: JSON.stringify(data), // <- json 변환
        dataType: "json", // 데이터를 주고받을때 형식이이달라도 되긴한다. 하지만 RESTful API에서는 json으로 주고받는다.
        contentType: "application/json",  //@RequestBody로 받기때문
        success: function (res) {// response) {
            console.log(res);
            /*if (res.code == 200) {
                // if(select.val() === '수령')
                //     select.attr('disabled', 'disabled');
                console.log('success');
                alert('적용 완료');
            } else {
                console.log('error');
                alert('적용 실패. 잠시 후 다시시도하셈');
            }*/
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔보셈');
        }
    });
});

/*
console.log($("input[name=searchInput]").text());

// console.log($("tr[name=reqest_table]:eq(0)").children().first().text());
$('button[name=searchButton]').click(function(){
    let search = $("input[name=searchInput]").text();
    let data;
    switch($("select[name=searchSelect]").val()){
        case 0 :
            data =  {
                request_code: $("tr[name=reqest_table]").children().first().text()
            }
    }

	console.log(search);
	console.log($("input[name=searchInput]").text());

    // $.ajax({
    //     type='get',
    //     url : '/req/acyn/searchWhere',
    //     dataType: "json",
    //     //data : 
    // });
});
*/