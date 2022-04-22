'use strict';

let index = {
    init: function() {
        //this를 바인딩하기 위해 화샬표 함수 사용
        $('#btn-save').on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() === false) {
                console.log("회원가입 안됨");
            } else {
                this.save();
            }
        });
    },

    save: function () {
        let data = {
            username : $('#username').val(),
            password : $('#password').val(),
            email : $('#email').val(),
            nickname : $('#nickname').val()
        }

        $.ajax({
            type: "POST",   // http method
            url: "/auth/api/v1/user", // api 주소
            data: JSON.stringify(data), // JSON으로 변환
            contentType: "application/json; charset=utf-8", // MIME 타입
            dataType: "json",    //응답 데이터
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function(err) {
           alert(JSON.stringify(err));
        });
    }
}

index.init();