let detail_tbody = document.getElementById('detail_tbody');

function makeReqObj(){
    let title = $('form[name=titleForm]').serializeObject();
    let tr = $('#detail_tbody').find('tr');
    let details = [];
    let total_order_quantity = 0; // int
    // tr 수 만큼
    tr.each(
        (i, e) => {
            // console.log(i, e);
            let row_data = {};
            row_data['request_code'] = title.request_code;
            row_data['product_code'] = title.product_code;

            $(e).find('input').each(
                (i, e) => {
                    // console.log(i, e);
                    row_data[$(e).attr('name')] = $(e).val();
                    if($(e).attr('name').toUpperCase() === 'TOTAL')
                        total_order_quantity += parseInt( $(e).val() );
                }
            );
            // console.log(i, row_data);
            details.push(row_data)
        }
    );
    title["total_order_quantity"] = total_order_quantity;

    console.log(title);
    console.log(details);

    let reqData = { title : JSON.stringify(title) }
    if(details.length > 0)
        reqData.details = JSON.stringify(details)

    return reqData;
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
$('#detail_tbody input[type=number]').keyup(func_total_sum).keypress(func_total_sum).keydown(func_total_sum);


let prod_cd;
$('select#product_code').change(function (){
    if(detail_tbody.rows.length > 0 && this.value !== prod_cd){
        if(!confirm('코드를 변경하면 기존의 내용이 유지되지않습니다. 변경하시겠습니까?')){
            $(this).val(prod_cd);
            $(this).find('option[value=' + prod_cd + ']').attr('selected', 'selected');
            return;
        }
    }
    prod_cd = this.value;
    $(detail_tbody).empty();
    // console.log(this.value);
    if(this.value === '' || this.value === undefined || this.value === null) return;
    let gen = $(this).children('option:selected').attr('data');

    $.ajax({
        type: "get",
        url: "/req/async/selectProdDetail",
        data : { product_code : this.value },
        dataType: "json",
        success: function (xml){
            $('#gender').val(gen);
            if(xml.code === 200)
                xml.details.forEach(e => addDetail(e));
        },
        error: function(request, status, error) {
            console.log("code:" + status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            alert('콘솔보셈');
        }
    });
});

function addDetail(e) {
    let row = detail_tbody.insertRow();

    let rowName = ['s', 'm', 'l', 'xl', 'f', 'total'];
    let rowType = ['number', 'number', 'number', 'number', 'number', 'text'];

    //색상
    row.insertCell(0).innerHTML = '<label class="form-control mb-0">' + e.color_name + '</label>' +
        '<input type="hidden" name="color_name" value="'+e.color_name+'">';
    //사이즈(s~free), 총 수량
    for (let i = 0; i < rowName.length; i++)
        row.insertCell(i+1).innerHTML = '<input class="form-control" type="' + rowType[i]+'" name="' + rowName[i] + '" value="0">';

    $(row).find('input[name=total]').attr('readonly', 'readonly');
    $(row).find('input[type=number]').keyup(numberSet).keydown(numberSet).keypress(numberSet);
    $(row).find('input[type=number]').keyup(func_total_sum).keypress(func_total_sum).keydown(func_total_sum);
}