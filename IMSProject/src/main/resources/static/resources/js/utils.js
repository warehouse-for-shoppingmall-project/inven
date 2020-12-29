/* 
	2020-12-08
	공통으로 사용할 기능들을 넣어둘 JS File
*/

// form data -> obj { key : value } jQuery 방식
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

// param 형식을 obj 형태로 변환 ?? wait
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
let serializeParam = function(obj){
	let str = "?";
	let idx = 0;
	for (let key in obj) {
		if(idx > 0) str += "&";
		str += key + "=" + obj[key];
		idx++;
	}
	return str;
}

// type=number 인놈만 사용할 함수
let numberSet = function (){
	// 만약 비어있으면 0을 넣어준다.
	let val = $(this).val();
	if(val === '') $(this).val(0);
	// 아니면 문자를 int 로 바꿔서 넣어줄거다. 문자 ->  숫자
	else $(this).val(parseInt(val));
}

/* 전화번호와 같이 숫자만 입력제한 */
let onlyNumber = function (){
	$(this).val($(this).val().replace(/[^0-9]/g,""));
}

let keyCodeBlock = function (e){
	// f12, 새로고침, 엔터 막기,
	if(e.keyCode === 123 || e.keyCode === 13 || (e.ctrlKey && e.keyCode === 82)){
		e.preventDefault();
		e.returnValue = false;
	}
}

