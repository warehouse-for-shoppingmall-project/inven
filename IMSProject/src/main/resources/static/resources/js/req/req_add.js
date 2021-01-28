$('#product_code').trigger('change');

//발주 코드 생성
let codeCheck = true;
let makeCodeAjax = function(){
    console.log("하는중");
    $.ajax({
        type: "get",
        url: "/req/async/makeReqCode",
        dataType: "json",
        success: function(xml) {
            if (xml.code === 200) {
                console.log('success');
                codeCheck = false;
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
}
$('button#makeReqCode').click(makeCodeAjax);

// 발주등록 버튼
$('#reqAdd').click(function() {

    if(codeCheck) {
        alert('발주코드를 생성해주세요');
        return;
    }

    let data = makeReqObj();
    if(isEmpty(data.details)) {
        alert('잘못됐다.');
        return;
    }

    if (!confirm('해당 발주를 등록 하시겠습니까?')) return;

    console.log(data);

    $.ajax({
        type: "post",
        url: "/req/async/reqAdd",
        data: data,
        dataType: "json",
        success: function(xml) {
            if (xml.code === 200) {
                console.log('success');
                alert('등록 완료');
                location.href='list';
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