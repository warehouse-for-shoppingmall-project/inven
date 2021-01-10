
// 1. Paging javascript 방식
let pagingFunc = function(){
    let pageNo = this.querySelector('.sr-only').innerText;
    let obj = document.querySelector('form[name=saveParamForm]');
    obj.pageNo.value = pageNo;
    obj.method = 'get';
    obj.action = 'list';
    obj.submit();
}
document.querySelectorAll('.pagination > .page-item').forEach(
    e => e.addEventListener('click', pagingFunc)
);

// detail popup
let popup = function(e){
    // tagName 이 a 태그인경우 실행 x
    if(e.target.tagName === 'BUTTON') return;
    if(e.target.tagName === 'SELECT') return;
    let action = 'detail';
    let data = { code : $(this).attr('data') };
    let popupWidth = 420;
    let popupHeight = 200;
    let popupX = (window.screen.width / 2) - (popupWidth / 2);
    let popupY = (window.screen.height / 2) - (popupHeight / 2);
    window.open(action+serializeParam(data), 'popup', 'width=' + popupWidth + ', height=' + popupHeight + ', left=' + popupX + ', top=' + popupY + 'scrollbars=yes, resizable=no');
}
$('tr[name=each_data_tbody]').click(popup);