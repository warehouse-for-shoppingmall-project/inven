// select의 name이 status인 놈의 값이 변경되면 실행한다.
$('select[name=status]').change(function () {
    var con = confirm('상태를 변경하시겠습니까?');
    if (con) {
        var data = {
            request_status: $(this).val(),
            request_code: $(this).parents('tr').children().first().text()
        };
        $.ajax({
            type: "post",
            url: "/req/acyn/upStatus",
            data: data,
            dataType: "json",
            success: function (xml) {
                console.log(xml);
                if (xml.code == 200) {
                    console.log('success');
                    alert('적용 완료');
                } else {
                    console.log('error');
                    alert('적용 실패. 잠시 후 다시시도하셈');
                }
            },
            error: function (request, status, error) {
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                alert('콘솔보셈');
            }
        });
    }
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