// select의 name이 orderStatus인 놈의 값이 변경되면 실행한다.
$('select[name=orderStatus]').change(function () {
    if (!confirm('상태를 변경하시겠습니까?')) return;
    let data = {
        orderStatus: $(this).val(),
        orderNo: $(this).parents('tr').attr('data')
    };
    console.log(data);
    $.ajax({
        type: "PUT",
        url: "/order/async/upStatus",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            console.log(res);
            if (res) {
                console.log('success');
                alert('적용 완료');
            } else {
                console.log('error');
                alert('적용 실패.');
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔을 봐주세요');
        }
    });
});

//송장 번호 생성

$('button[name=makeTrackingNum]').click(function (){
    let input = $(this).parent().prev();
    if (!confirm('송장번호 생성 하시겠습니까?')) return;
    let button = $(this);

    console.log('data');
$.ajax({
        type: "get",
        url: "/order/async/makeTrackingNumber",
        // data: JSON.stringify(data),
        dataType: "json",
        // contentType: "application/json",
        success: function(xml) {
            if (xml.code === 200) {
                input.val(xml.deliveryNum);
                console.log('success');
                button.hide();
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
})
