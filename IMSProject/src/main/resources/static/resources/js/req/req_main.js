
$('select[name=status]').change(function () {
    let con = confirm('상태를 변경하시겠습니까?');
    if (!con) return;
    let select = $(this);
    let data = {
        request_status: $(this).val(),
        request_code: $(this).closest('tr').attr('data')
    }

    $.ajax({
        type: "put",
        url: "/req/async/upStatus",
        data: data,
        dataType: "json",
        success: function (xml) {
            console.log(xml);
            if (xml.code == 200) {
                if(select.val() === '입고')
                    select.attr('disabled', 'disabled');
                console.log('success');
                alert('적용 완료');
            } else {
                console.log('error');
                alert('적용 실패. 잠시 후 다시시도해주세요');
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('변경 오류');
        }
    });
});


$('button[name=reqModButton]').click(function (){
    let reqCode = $(this).closest("tr").attr('data');
    location.href = 'mod?request_code='+reqCode;
});
