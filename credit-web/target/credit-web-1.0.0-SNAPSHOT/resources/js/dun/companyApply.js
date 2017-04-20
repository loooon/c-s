
$().ready(function () {
    window.document.onkeydown =function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键

            if(isMalert()){
                closeMalert($(".pop"));
                $("#companyName").focus();
            }else{
                $("#submitApply").click();
            }
        }

    };

    $("#companyName").focus();
    $("#submitApply").click(function () {
        $("#message").hide();
        $("#phoneMessage,#companyNameMessage,#contactsMessage,#dutyMessage").hide();
        $("#applyForm").find('input').each(function(){
            $(this).val($.trim($(this).val()));
        });
        var companyName = $("#companyName").val();
        if(companyName == ''){
            $("#companyNameMessage").show();
            $("#companyName").focus();
            return;
        }
        var contacts = $("#contacts").val();
        if(contacts == ''){
            $("#contactsMessage").show();
            $("#contacts").focus();
            return;
        }
        var duty = $("#duty").val();
        if(duty == '')
        {
            $("#dutyMessage").show();
            $("#duty").focus();
            return;
        }

        var phone = $("#phone").val();
        if(phone == ''||(!phone.isMobile()&&!phone.isPhone()))
        {
            $("#phoneMessage").show();
            $("#phone").focus();
            return;
        }
        var values = $("#applyForm").serializeArray();
        $.ajax({
            dataType: 'json',
            data: values,
            type: 'post',
            url: '/dun/company',
            success: function(data, r, xhr) {
                if (data.result == 0) {
                    malert($(".pop"),$("a,input"),{});
                    $("#companyName").val("");
                    $("#contacts").val("");
                    $("#phone").val("");
                    $("#duty").val("");
                    $("#companyName").focus();
                } else {
                    $('#message').text(data.message);
                }
            }
        });
    });
});


