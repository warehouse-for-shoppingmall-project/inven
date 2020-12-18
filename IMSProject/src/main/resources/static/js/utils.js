/* 
	2020-12-08
	공통으로 사용할 기능들을 넣어둘 JS File
*/

/* 	
	jobj 는 JSONObject를 말한다. 
	form 요소들 jobj로 만들기 
	jobj = { key : value}
*/
var serializeForm = function(form) {
	var obj = {};
	var formData = new FormData(form);
	for (var key of formData.keys()) {
		obj[key] = formData.get(key);
	}
	return obj;
};

/* 
	아니 여기는 왜 까맣냐고;;
	깃허브라서..?ㅎ
	절대 그럴리 없죠?
	jobj를 파라미터 형식으로 변환해준다.
	method='GET' 방식에서만 사용한다.
*/
var toparamJson = function(jobj){
	var str = "?";
	var idx = 0;
	for (var key in jobj) {
		if(idx > 0) str += "&";
		str += key + "=" + jobj[key];
		idx++;
	}
	return str;
}

function ajax_submit(frm) { 

	var xhr = new XMLHttpRequest();
	/* 준비상태가 변하면 실행한다. */
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			/* 상태가 200, 201 이면 서버 통신 성공 */
			if (xhr.status === 200 || xhr.status === 201) {
				console.log(xhr.responseText);
			} else {
				/* 서버통신 실패 */
				console.error(xhr.responseText);
			}
		}
	};
	console.log(2);
	/* 메소드에 따라 다른방식의 접근을 해줘야한다. */
	if(frm.method == 'get'){
		xhr.open(frm.method, frm.action+toparamJson(serializeForm(frm)), false);
		xhr.send();
	} else {
		xhr.open(frm.method, frm.action, false);
		xhr.send(new formData(frm));
	}
	console.log(3);
	
}