// select의 name이 status인 놈의 값이 변경되면 실행한다.
$('select[name=status]').change(function () {
    if (!confirm('상태를 변경하시겠습니까?')) return;
    let data = {
        productStatus: $(this).val(),
        productCode: $(this).parents('tr').attr('data')
    };
    console.log(data);
    $.ajax({
        type: "PUT",
        url: "/prod/async/upStatus",
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        success: function (res) {// response) {
            console.log(res);
            if (res) {
                // if(select.val() === '수령')
                //     select.attr('disabled', 'disabled');
                console.log('success');
                alert('적용이 완료되었습니다.');
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
});


let add_popup = function () {
    let action = 'add';
    let popupWidth = 300;
    let popupHeight = 400;
    let popupX = (window.screen.width / 2) - (popupWidth / 2);
    let popupY = (window.screen.height / 2) - (popupHeight / 2);
    window.open(action, 'add_popup', 'width=' + popupWidth + ', height=' + popupHeight + ', left=' + popupX + ', top=' + popupY + 'scrollbars=yes, resizable=no');
}
$('#enrollment').click(add_popup);

$('button[name=mod_popup]').click( function () {
    let code = $(this).closest('tr').attr('data');
    console.log($(this));
    console.log($(this).closest('tr'));
    console.log(code);
    let action = "modify";
    let popupWidth = 280;
    let popupHeight = 360;
    let popupX = (window.screen.width / 2) - (popupWidth / 2);
    let popupY = (window.screen.height / 2) - (popupHeight / 2);
    let data = { productCode : code }
    window.open(action+serializeParam(data), 'mod_popup', 'width=' + popupWidth + ', height=' + popupHeight + ', left=' + popupX + ', top=' + popupY + 'scrollbars=yes, resizable=no');
});

$('button[name=reqAddBtn]').click( function() {
    let code = $(this).closest('tr').attr('data');
    location.href='/req/add?product_code='+code;
});
