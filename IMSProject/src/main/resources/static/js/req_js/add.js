jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        if (this[0].tagName) {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                    obj[this.name] = this.value;
                });
            }//if ( arr ) {
        }
    } catch (e) {
        alert(e.message);
    } finally {
    }
 
    return obj;
};

$('button[name = makeReqCode]').click(function() {
	console.log("하는중");
	$.ajax({
		type: "get",
		url: "/req/acync/makeReqCode",
		dataType: "json",
		success: function(xml) {
			if (xml.code == 200) {
				console.log('success');
				$('input#request_code').val(xml.request_code);
			} else {
				console.log('error');
				alert('생성 실패. 다시 해보셈');
			}
		},
		error: function(request, status, error) {
			console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			alert('콘솔보셈');
		}
// 밥먹고올게 tr to serialize 이런거 검색해서 찾아봐 넵.
	});
});


$('#reqAdd').click(function() {
	// 아니다 화면이 작아서 그런거다
	// js에서는 ' 홑 따옴표를 쓴다'
	var con = confirm('해당 발주를 등록 하시겠습니까?');
	if (!con) return;
	
	var request_status = $("select[name=request_status]").val();
	var title = $('form[name=titleForm]').serializeObject();
	var tbody = $('#request_detail_tbody').find('tr');
	var details = [];
	console.log(tbody);
	tbody.each(function(i, e){
		console.log(i, e);
		console.log($(e).serializeObject());
		details.push($(e).serializeObject());
	})
	/*
		var 뭐시기= $(e).find(input[name=뭐시기]).val()
		e는 element약자고 i는 index 약자다
		var obj = {
					s:값,
					m...
					l...
					xl...
					}
					이런식으로 만들어
					그리고 list에 push해
				} 
	 */
	console.log(request_status);
	console.log(title);
	console.log(details);
	
	/* 흠...hm....so hard...
	title, detail 2가지를 보내야한다.
	{ 
		title : titledata,
		detail : list_detail data
	}
	1. form 을 2개로 분할한다.
	2. button을 누르면 form 2개를 serialize 한다.
	3. map에 담는다.
	4. 보낸다
	5. 테스트 해보자
	6. 제발 되어라...
	$.ajax({
		type: "get",
		url: "/req/acyn/reqAdd",
		data: formData,
		dataType: "json",
		async:false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json; charset=utf-8'
		},
		success: function(xml) {
			if (xml.code == 200) {
				console.log('success');
				var jsonObj = JSON.parse(xml);
				alert('등록 완료');
			} else {
				console.log('error');
				alert('등록 실패. 잠시 후 다시시도하셈');
			}
		},
		error: function(request, status, error) {
			console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
			alert('콘솔보셈');
		}
	});*/
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