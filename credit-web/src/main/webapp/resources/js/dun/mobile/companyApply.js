
$().ready(function () {

    $("#submitApply").click(function () {
        $("#message").hide();
        $("#phoneMessage,#companyNameMessage,#contactsMessage,#dutyMessage").hide();
        $("#applyForm").find('input').each(function(){
            $(this).val($.trim($(this).val()));
        });
        var companyName = $("#companyName").val();
        if(companyName == ''){
            $("#companyNameMessage").show();
            return;
        }
        var contacts = $("#contacts").val();
        if(contacts == ''){
            $("#contactsMessage").show();
            return;
        }
        var duty = $("#duty").val();
        if(duty == '')
        {
            $("#dutyMessage").show();
            return;
        }

        var phone = $("#phone").val();
        if(phone == ''||(!phone.isMobile()&&!phone.isPhone()))
        {
            $("#phoneMessage").show();
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
                    malert($("#pop"),$("#pop"),{/*timeout:3000*/});
                    $("#companyName").val("");
                    $("#contacts").val("");
                    $("#phone").val("");
                    $("#duty").val("");
                } else {
                    $('#message').text(data.message);
                }
            }
        });
    });
});
function malert(context,closer,config){
    var opacity=0.2,time=300,z=1031;
    if(config!=null){
        opacity=config.opacity==null?opacity:config.opacity;
        time=config.time==null?time:config.time;
        z=config["z-index"]==null?z:config["z-index"];
    }
    var maskStr="<div id='malert_mask' style='position:fixed;display:none;z-index:"+z+";top:0;left:0; width:100%; height:100%;background-color:black;filter:alpha(opacity="+(opacity*100)+");-moz-opacity:"+opacity+";opacity:"+opacity+";'></div>";
    if($("#malert_mask").length!=0){
        $("#malert_mask").remove();
    }

    $("body").append(maskStr);
    var mask=$("#malert_mask");
    var h = $(window).height()-context.height();
    if(h<0){h = 100;}
//    context.css({"top":((h)/3)+"px","position":"fixed", "z-index":z+1,"left":"50%"});
    context.css({"top":((h)/2)+"px","position":"fixed", "z-index":z+1});
//    context.css("margin-left","-"+(context.width()/2)+"px");
    context.fadeIn(time);mask.fadeIn(time);
    //关闭
    var closeFunction = function (){
        context.fadeOut(time);
        mask.fadeOut(time).remove();
        if(config.onclose){config.onclose();}
    };
    if (config.timeout) {
        setTimeout(closeFunction,config.timeout);
    }
    mask.click(closeFunction);
    if(closer){
        closer.click(closeFunction);
    }
}
String.prototype.isPhone = function () {
    var t = /((^(\+86)|(86))?(((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$))/;
    return t.test(this)
}, String.prototype.isMobile = function () {
    // var t = /^(\+86)|(86)?(\d{11})$/;
    // var t = /^(\+86)|(86)?(^((1[3,5,8][0-9])|(14[5,7]))\d{8}$)/;
    var t = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
    return t.test(this)
};
