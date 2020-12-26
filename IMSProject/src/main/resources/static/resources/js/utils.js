/* 
	2020-12-08
	공통으로 사용할 기능들을 넣어둘 JS File
*/

/* 	
	jobj 는 JSONObject를 말한다. 
	form 요소들 jobj로 만들기 
	jobj = { key : value}
*/
// form data -> obj { key : value }
jQuery.fn.serializeObject = function() {
	let obj = null;
	try {
		if (this[0].tagName) {
			let arr = this.serializeArray();
			if (arr) {
				obj = {};
				jQuery.each(arr, function() {
					obj[this.name] = this.value;
				});
			}
		}
	} catch (e) {
		alert(e.message);
	} finally {
	}
	return obj;
};

let returnNumber = function (txt){
	return txt.replace(/[^0-9]/g,"");
}

/* 전화번호와 같이 숫자만 입력제한 */
let onlyNumber = function (){
	$(this).val(returnNumber($(this).val()));
}

/* 입력되는 글 숫자만 입력제한 하여 int Type 으로 Return */
let onlyInt = function () {
	$(this).val(parseInt(returnNumber($(this).val())));
}

let keyCodeBlock = function (e){
	// f12, 새로고침, 엔터 막기,
	if(e.keyCode === 123 || e.keyCode === 13 || (e.ctrlKey && e.keyCode === 82)){
		e.preventDefault();
		e.returnValue = false;
	}
}

// param 형식을 obj 형태로 변환
let serialize = function(form) {
	let param = '?', and = '';
	let formData = new FormData(form);
	let i = 0;
	for (let key of formData.keys()) {
		if(i > 0) and = '&';
		param += and + key + '=' + formData.get(key);
	}
	return param;
};

// param 형식을 obj 형태로 변환
let serializeObject = function(form) {
	let obj = {};
	let formData = new FormData(form);
	for (let key of formData.keys()) {
		obj[key] = formData.get(key);
	}
	return obj;
};

/*
	obj 를 파라미터 형식으로 변환해준다.
	method='GET' 방식에서만 사용한다.
*/
let serializeForm = function(obj){
	let str = "?";
	let idx = 0;
	for (let key in obj) {
		if(idx > 0) str += "&";
		str += key + "=" + obj[key];
		idx++;
	}
	return str;
}
/*
function ajax_submit(frm) {
	let xhr = new XMLHttpRequest();
	/!* 준비상태가 변하면 실행한다. *!/
	xhr.onreadystatechange = function() {
		if (xhr.readyState === xhr.DONE) {
			/!* 상태가 200, 201 이면 서버 통신 성공 *!/
			if (xhr.status === 200 || xhr.status === 201) {
				console.log(xhr.responseText);
			} else {
				/!* 서버통신 실패 *!/
				console.error(xhr.responseText);
			}
		}
	};
	console.log(2);
	/!* 메소드에 따라 다른방식의 접근을 해줘야한다. *!/
	if(frm.method == 'get'){
		xhr.open(frm.method, frm.action+toparamJson(serializeForm(frm)), false);
		xhr.send();
	} else {
		xhr.open(frm.method, frm.action, false);
		xhr.send(new formData(frm));
	}
	console.log(3);
	
}*/
