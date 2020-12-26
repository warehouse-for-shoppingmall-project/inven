

$('button[name=makeReqCode]').click(function() {
	console.log("하는중");
	$.ajax({
		type: "get",
		url: "/req/async/makeReqCode2",
		dataType: "json",
		success: function(xml) {
			if (xml.code == 200) {
				console.log('success');
				$('input#request_code').val(xml.request_code);
			} else {
				console.log(xml.code + ' :: error');
				alert('생성 실패. 다시 해보셈');
			}
		},
		error: function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			alert('콘솔보셈');
		}
	});
});

// 발주등록 버튼
$('#reqAdd').click(function() {
	// js 에서는 ' 홑 따옴표를 쓴다'
	let con = confirm('해당 발주를 등록 하시겠습니까?');
	if (!con) return;

	let title = $('form[name=titleForm]').serializeObject();
	let tr = $('#request_detail_tbody').find('tr');
	let details = [];
	let total_order_quantity = 0; // int
	tr.each(function(i, e){
		let row_data = {};
		row_data['request_code'] = title.request_code;
		row_data['product_code'] = title.product_code;
		$(e).children().each(function(i, e){
			let tag = $(e).children();
			let value = $(tag).val();
			if(value !== '') value = $(tag).val();
			else value = '0';
			row_data[$(tag).attr('name')] = value;
			if($(tag).attr('name') === 'total')
				total_order_quantity += parseInt($(tag).val());
		});
		details.push(row_data);
	});
	title["total_order_quantity"] = total_order_quantity;
	console.log(title);
	console.log(details);
	let data = {
		title : JSON.stringify(title),
		details : JSON.stringify(details)
	};
	console.log(data);

	// return;
	$.ajax({
		type: "post",
		url: "/req/async/reqAdd",
		data: data,
		dataType: "json",
		success: function(xml) {
			if (xml.code == 200) {
				console.log('success');
				alert('등록 완료');
			} else {
				console.log(xml.code + ':: error');
				alert('등록 실패. 잠시 후 다시시도하셈');
			}
		},
		error: function(request, status, error) {
			console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
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