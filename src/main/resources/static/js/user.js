'use strict';

let userIndex = {
    init: function() {
        //this를 바인딩하기 위해 화샬표 함수 사용
        $('#btn-save').on("click", () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() === false) {
                console.log("회원가입 안됨");
            } else {
                console.log("save로 넘어감")
                this.save();
            }
        });

        $('#btn-update').on('click', () => {
            let form = document.querySelector("#needs-validation");
            if (form.checkValidity() === false) {
                console.log("회원수정 안됨");
            } else {
                this.update();
            }
        })
    },

    save: function () {
        let data = {
            username : $('#username').val(),
            password : $('#password').val(),
            email : $('#email').val(),
            nickname : $('#nickname').val()
        }

        console.log("넘어옴");

        $.ajax({
            type: "POST",   // http method
            url: "/auth/api/v1/user", // api 주소
            data: JSON.stringify(data), // JSON으로 변환
            contentType: "application/json; charset=utf-8", // MIME 타입
            dataType: "json",    //응답 데이터
        }).done(function (res) {
            console.log(res+"성공");
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function(err) {
           console.log(JSON.stringify(err) + "실패");
        });
    },

    update: function () {
        let data = {
            id: $('#id').val(),
            password: $('#password').val(),
            nickname: $('#nickname').val()
        }

        $.ajax({
            type: 'PUT',
            url: "/api/v1/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (err) {
            alert(JSON.stringify(err))
        });
    }
}

userIndex.init();