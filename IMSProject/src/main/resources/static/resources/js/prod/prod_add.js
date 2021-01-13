let detail_tbody = document.getElementById('detail_tbody');
let overlapCheckTN = true;

<!-- 버튼 클릭 시 detail 테이블 열 추가 -->
function addDetail() {
    let row = detail_tbody.insertRow();
    row.insertCell().innerHTML += '<input class="form-control" type="text" name="color_name">';
    row.insertCell().innerHTML += '<input type="button" class="btn btn-secondary" value="-" onclick="delDetail(this)">';
}

// 해당 행 삭제 버튼
function delDetail(row) {
    let tr = row.parentNode.parentNode;
    tr.parentNode.removeChild(tr);
}

// 중복검사 결과 보여주는 함수
function validCheck(bool, text=''){
    // true : 통과, false : 실패
    if(bool){
        $('.invalid-feedback').css('display', 'none');
        $('.valid-feedback').css('display', 'block');
    } else {
        $('.invalid-feedback').css('display', 'block').text(text);
        $('.valid-feedback').css('display', 'none');
    }
}

// 연속 클릭 방지
let confirm_1 = false;
let confirm_2 = false;

// 상품코드 중복검사
$('#overlapCheck').click(function() {
    if(confirm_1){
        validCheck(false, '기다려주세요.');
        return;
    }

    let value = $('#ip_prod_cd').val();
    if(value === ''){
        validCheck(false, '중복검사를 해주세요.')
        $('#ip_prod_cd').focus();
        return;
    }

    confirm_1 = true;
    $.ajax({
        type: "GET",
        url: "/prod/async/overlapCheck",
        data: { product_code : value },
        dataType: "json",
        success: function(xml) {
                confirm_1 = false;
            if (xml.code === 200) {
                validCheck(true);
                $('input[name=product_code]').val(value);
                $('#ip_prod_cd').attr('readonly', 'readonly');
                overlapCheckTN = false;
            } else {
                console.log(xml.code + ':: error');
                validCheck(false, '이미 존재하는 코드입니다.');
            }
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('등록 오류.');
        }
    });
});

// 상품등록
$('#prodAdd').click(function() {
    // 유효성 검사 출력용
    let clrValid = $('div[name=color_valid]');
    if(confirm_2){
        clrValid.text('기다려주세요.');
        return;
    }


    // js 에서는 ' 홑 따옴표를 쓴다'
    if(overlapCheckTN) {
        $('#ip_prod_cd').focus();
        validCheck(false, '중복검사를 해주세요.');
        return;
    }

    let title = $('form[name=titleForm]').serializeObject();
    let details = [];

    // 함수 정지용용
   let inputYN = false;

    // 중복검사용 변수
    let names = [];

    $('#detail_tbody > tr').each(function (i, e){
        let input = $(e).find('input[name=color_name]');
        if(input.val() === '') {
            clrValid.text('컬러 이름을 적으세요');
            input.focus();
            inputYN = true;
            return;
        }
        let data = { product_code : title.product_code, color_name : input.val()};
        details.push(data);
        names.push(input.val());
    });

    if(inputYN)return;

    if(isDuplicate(names)){
        clrValid.text('컬러 이름이 겹칩니다.');
        return;
    }

    let data = {
        title : JSON.stringify(title),
        details : JSON.stringify(details)
    }

    console.log(data);
    confirm_2 = true;
    // return;
    $.ajax({
        type: "post",
        url: "/prod/async/prodAdd",
        data: data,
        dataType: "json",
        success: function(xml) {
            if (xml.code === 200) {
                $('#alt-c').text('등록완료');
                $('#alert').addClass('show');
                confirm_2 = false;
                setTimeout(function (){
                    window.opener.location.reload();
                    window.open('','_self').close();
                }, 1000);
            } else {
                console.log(xml.code + ':: error');
                $('#alt-c').html('<small>등록실패 :: 잠시후 다시 시도해주세요.</small>');
                if($('#alert').attr('class').indexOf('show') === -1){
                    $('#alert').addClass('show');
                }
            }
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('등록 오류');
        }
    });
});