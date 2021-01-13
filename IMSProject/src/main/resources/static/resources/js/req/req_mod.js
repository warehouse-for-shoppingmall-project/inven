// modify
// 발주수정 버튼
$('#reqMod').click(function() {
    if (!confirm('해당 발주를 등록 하시겠습니까?')) return;
    let data = makeReqObj();
    // return;
    $.ajax({
        type: "put",
        url: "/req/async/reqMod",
        data: data,
        dataType: "json",
        success: function(xml) {
            if (xml.code === 200) {
                console.log('success');
                alert('수정 완료');
                location.href = "list";
            } else {
                console.log(xml.code + ':: error');
                alert('수정 실패. 잠시 후 다시시도해주세요.');
            }
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔보셈');
        }
    });
});