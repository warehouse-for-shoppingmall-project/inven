$('#submit_btn').css('display', 'none');
document.onkeydown = keyCodeBlock;
let keyDownEvent = function (e) {
    if (e.ctrlKey && e.keyCode === 32)
        $('#submit_btn').css('display', '');
}

document.addEventListener('keydown', keyDownEvent);

document.getElementById('submit_btn').addEventListener('click',
    function () {
        let frm = document.getElementById('loginForm');
        let xhr = new XMLHttpRequest();
        /* 준비상태가 변하면 실행한다. */
        xhr.onreadystatechange = function () {
            if (xhr.readyState === xhr.DONE) {
                /* 상태가 200, 201 이면 서버 통신 성공 */
                if (xhr.status === 200 || xhr.status === 201) {
                    let obj = JSON.parse(xhr.responseText);
                    if (obj.code === 200) {
                        location.href = "req/list";
                    } else {
                        alert('비밀번호를 잘못입력하셨습니다.');
                        frm.pwd.value = '';/* 틀린 비밀번호 지워주기 */
                    }
                } else {
                    /* 서버통신 실패 */
                    console.error(xhr.responseText);
                }
            }
        };
        /* 메소드에 따라 다른방식의 접근을 해줘야한다. */
        if (frm.method === 'get') {
            xhr.open(frm.method, frm.action + serialize(frm), false);
            xhr.send();
        } else {
            xhr.open(frm.method, frm.action, false);
            xhr.send(new FormData(frm));
        }
    }
);