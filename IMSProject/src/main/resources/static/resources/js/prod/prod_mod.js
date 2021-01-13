<!-- 버튼 클릭 시 detail 테이블 열 추가 -->
function addDetail() {
    let detail_tbody = document.getElementById("detail_tbody");
    let row = detail_tbody.insertRow();
    let cell = row.insertCell();
    cell.innerHTML = '<input class="form-control" name="new_color_name" type="text" >';
    row.insertCell().innerHTML = '<button class="btn btn-success" type="button" onclick="delDetail(this)">-</button>';
}

// 해당 행 삭제 버튼
function delDetail(row) {
    let tr = row.parentNode.parentNode;
    tr.parentNode.removeChild(tr);
}   //end of addRow 함수

let product_code = $('#product_detail_table').attr('data');

function prodObj(){

    let update_data = [];
    let update_tr = $('#detail_thead').find('tr');
    let insert_data = [];
    let insert_tr = $('#detail_tbody').find('tr');

    update_tr.each(
        (i, e) => {
            if(i === 0) return true;
            console.log(i, e);
            let input = $(e).find('input[name=update_color_name]');
            let obj = { product_code : product_code };
            if (input[0].value.trim()) {
                $(e).find('input').each(    (i, e) =>  obj[$(e).attr('name')] = $(e).val().trim()   );
                update_data.push(obj);
            }
        }
    );

    insert_tr.each(
        (i, e) => {
            if(i === 0) return true;
            let input = $(e).find('input[name=new_color_name]');
            let obj = { product_code : product_code };
            if (input[0].value.trim()) {
                $(e).find('input').each(    (i, e) =>  obj.color_name = $(e).val().trim()   );
                insert_data.push(obj);
            }
        }
    );

    console.log(update_data);
    console.log(insert_data);

    let data = {};
    // 바뀌는 색 이름을 적지 않았을경우
    if(update_data.length > 0)
        data.update_data = JSON.stringify(update_data);

    // 새로운 색을 추가하지 않았을경우
    if(insert_data.length > 0)
        data.insert_data = JSON.stringify(insert_data);

    console.log(data);
    return data;
}

function isDuplicate(arr)  {
    const isDup = arr.some(function(x) {
        return arr.indexOf(x) !== arr.lastIndexOf(x);
    });
    console.log('중복 여부'+isDup);
    return isDup;
}

function push(arr, val){
    if(val) arr.push(val);
}

// 발주수정 버튼
$('#prodMod').click(function() {

    let con = confirm('상품 수정을 완료하시겠습니까?');
    if (!con) return;

    // 색상명 중복 검사
    let arr = [];
    let tr = $('#detail_thead').find('tr');
    let bodyTr = $('#detail_tbody').find('tr');

    tr.each(
        (i, e) => {
            if(i === 0) return true;
            push(arr, $(e).find('input[name=old_color_name]').val().trim());
            push(arr, $(e).find('input[name=update_color_name]').val().trim());
        }
    );

    bodyTr.each(
        (i, e) => {
            if(i ===0) return true;
            push(arr, $(e).find('input[name=new_color_name]').val().trim());
        }
    );

    console.log('중복검사용 배열 : '+arr);

    //중복검사 시 true(중복 있음) 이면 돌려보내기
    if(isDuplicate(arr)) {
        alert('중복되는 색상 명이 존재합니다.');
        return;
    }
    console.log('중복 유무 : ', isDuplicate(arr));

    let data = prodObj();
    console.log('ajax 송신 중 proData : ',  data);

    if(isEmpty(data)){
        alert('변경사항이 존재하지 않습니다.');
        return;
    }

    $.ajax({
        type: "post",
        url: "/prod/async/prodMod",
        data: data,
        dataType: "json",
        success: function(xml) {
            if (xml.code === 200) {
                console.log('success');
                alert('수정 완료');
                location.reload();
            } else {
                console.log(xml.code + ':: error');
                alert('수정 실패. 잠시 후 다시시도해주세요.');
            }
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('수정 오류');
        }
    });
});