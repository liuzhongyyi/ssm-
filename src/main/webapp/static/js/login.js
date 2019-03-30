$(function () {
    $("#loginBtn").click(function () {
        $.post("/login",$("form").serialize(),function (data) {
            /*把data  json格式的字符串  转成 json 数据*/
                var data=$.parseJSON(data);
            if (data.successful){
                /*跳转到首页*/
               window.location.href = "/index.jsp"
           } else {
                alert(data.msg);
           }

        });

    })
});