/*
	2020-12-28
	request part 에서 사용할 함수
*/
// main.js
// select의 name이 status인 놈의 값이 변경되면 실행한다.
$('select[name=status]').change(function () {
    let con = confirm('상태를 변경하시겠습니까?');
    if (!con) return;
    let select = $(this);
    let data = {
        request_status: $(this).val(),
        request_code: $(this).parents('tr').children().first().text()
    };
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
                alert('적용 실패. 잠시 후 다시시도하셈');
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔보셈');
        }
    });
});
// detail popup
let detail_popup = function(e){
    if(e.target.tagName === 'INPUT') return;
    let action = 'detail';
    let data = { request_code : $(this).attr('data') };
    window.open(action+serializeParam(data), '_blank', 'width=420, height=200, scrollbars=yes, resizable=no');
} // detail?reauest_code=201216-01
$('tr[name=each_data_tbody]').click(detail_popup);

//수정하기 버튼 누르면 request_code form 생성 후 controller에 전송
$('input[name=reqModButton]').click(function (){
    let reqCode = $(this).closest("tr").children().eq(0).text();
    <!--        console.log(reqCode);-->
    let modForm = $('<form></form>');
    modForm.attr("name","modForm");
    modForm.attr("method","get");
    modForm.attr("action","mod");

    modForm.append($('<input>', {type: 'hidden', name: 'request_code', value:reqCode }));
    modForm.appendTo('body');
    modForm.submit();
});
/*
$('.pagination li').click(function (){
    // 1. Paging jQuery 방식
    let $pageNo = $(this).find('.sr-only').text();
    console.log($pageNo);
    let $obj = $('form[name=saveParamForm]');
    $obj.children('input[name=pageNo]').val($pageNo);
    console.log($obj.html());
    $obj.submit();
});
*/
// 1. Paging javascript 방식
let pagingFunc = function(){
    let pageNo = this.querySelector('.sr-only').innerText;
    // console.log(pageNo);
    let obj = document.querySelector('form[name=saveParamForm]');
    obj.pageNo.value = pageNo;
    // console.log(obj);
    obj.method = 'get';
    obj.action = 'list';
    obj.submit();
}

document.querySelectorAll('.pagination > .page-item').forEach(function(e, i){
    e.addEventListener('click', pagingFunc);
});

// ./ main.js


// add + modify

// 발주등록,수정 시 2가지 form 전송

let reqData;

function reqObj(){
    let title = $('form[name=titleForm]').serializeObject();
    let tr = $('#detail_tbody').find('tr');
    let details = [];
    let total_order_quantity = 0; // int
    let names = [];
    // tr 수 만큼
    tr.each(function(i, e){
        let row_data = {};
        row_data['request_code'] = title.request_code;
        row_data['product_code'] = title.product_code;

        // () => {} 람다식 문법
        // tr (row) 안에 input 개수 만큼 반복
        $(e).find('input').each((i, e) => {
            // console.log(i, e);
            row_data[$(e).attr('name')] = $(e).val();
            if($(e).attr('name').toUpperCase() === 'TOTAL')
                total_order_quantity += parseInt($(e).val());
            if($(e).attr('name').toUpperCase() === 'COLOR_NAME')
                names.push = $(e).val();
        });
        // console.log(i, row_data);
        details.push(row_data)
    });
    title["total_order_quantity"] = total_order_quantity;

    // console.log(title);
    // console.log(details);

    if(isDuplicate(names)){
        alert('색상명이 겹칩니다.\n색상명을 다르게 지정하세요.');
        return;
    }
/*
    reqData = {
        title : JSON.stringify(title),
        details : JSON.stringify(details)
    };
*/

    let reqData2 = {
        title : JSON.stringify(title),
        details : JSON.stringify(details)
    };

    return reqData2;
}

// 이 페이지를 읽고나면 key event 에 numberSet 함수를 적용.
$('input[type=number]').keyup(numberSet).keydown(numberSet).keypress(numberSet);
// 변경이 일어난 개체와 동일한 row 의 input tag 의 숫자를 모두 더하여 total 에 넣는 식

let func_total_sum = function (){
    // 현 위치에서 가장 가까운 tr을 찾음 현 위치는 tbody > tr > td > input 이다.
    let tr = $(this).closest('tr');
    // tr 안에 있는 type = number 를 모조리 찾는다.
    let number = tr.find('input[type=number]');
    // 저장변수
    let total = 0;
    // number.each()랑 같다.
    for(let i=0; i<number.length; i++)
        // type=number 개수만큼 반복돌려 값을 다 더해 total 에 넣는다.
        total += parseInt(number[i].value);
    // total 을 찾아서 값을 넣어준다.
    $(tr).find('input[name=total]').val(total);
}

// #request_detail_tbody 하위에 input[type=number]인 요소에 key event 모두 적용.
$('.detail_tbody input[type=number]').keyup(func_total_sum).keypress(func_total_sum).keydown(func_total_sum);

// modify
// 발주수정 버튼
$('#reqMod').click(function() {
    // js 에서는 ' 홑 따옴표를 쓴다'
    let con = confirm('해당 발주를 등록 하시겠습니까?');
    if (!con) return;

    reqObj();

    console.log(reqData);

    // return;
    $.ajax({
        type: "put",
        url: "/req/async/reqMod",
        data: reqData,
        dataType: "json",
        success: function(xml) {
            if (xml.code == 200) {
                console.log('success');
                alert('수정 완료');
                location.href = "list";
            } else {
                console.log(xml.code + ':: error');
                alert('수정 실패. 잠시 후 다시시도하셈');
            }
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔보셈');
        }
    });
});

// add.js
let detail_tbody = document.getElementById("detail_tbody");

let rowName = ['s', 'm', 'l', 'xl', 'f', 'total'];
let rowType = ['number', 'number', 'number', 'number', 'number', 'text'];

//발주 코드 생성
let makeCodeAjax = function(){
    console.log("하는중");
    $.ajax({
        type: "get",
        url: "/req/async/makeReqCode",
        dataType: "json",
        success: function(xml) {
            if (xml.code == 200) {
                console.log('success');
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

$('button[name=makeReqCode]').click(makeCodeAjax);
/* 이제 안씀
function addDetail() {
    let row = detail_tbody.insertRow();
    //색상
    //사이즈(s~free), 총 수량
    for (let i = 0; i < rowName.length; i++) {
        row.insertCell(i).innerHTML += '<input class="form-control" type="'+rowType[i]+'" name="' + rowName[i] + '">';
    }
    row.insertCell().innerHTML += '<input type="button" value="-" id="delButton" onclick="delDetail(this)">';
    $(row).find('input[name=total]').attr('readonly', 'readonly').val(0);
    $(row).find('input[type=number]').keyup(func_total_sum).keypress(func_total_sum).keydown(func_total_sum).val(0);
    $(row).find('input[type=number]').keyup(numberSet).keydown(numberSet).keypress(numberSet).val(0);
}

// 해당 행 삭제 버튼
function delDetail(row) {
    let tr = row.parentNode.parentNode;
    tr.parentNode.removeChild(tr);
}
*/

// 발주등록 버튼
$('#reqAdd').click(function() {
    // js 에서는 ' 홑 따옴표를 쓴다'
    let con = confirm('해당 발주를 등록 하시겠습니까?');
    if (!con) return;

    let reqData = reqObj();
    // return;
    $.ajax({
        type: "post",
        url: "/req/async/reqAdd",
        data: reqData,
        dataType: "json",
        success: function(xml) {
            if (xml.code == 200) {
                console.log('success');
                alert('등록 완료');
                location.href="list";
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
// ./ add.js
