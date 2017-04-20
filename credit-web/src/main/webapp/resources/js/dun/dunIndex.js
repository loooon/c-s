/**
 * Created by wangjunling on 2017/3/22.
 */
$(document).ready(function () {
    $("#searchBtn").click(function () {
        var phone = $("#numberTxt").val();
        if(!phone||phone==""||$.trim(phone)==""){
            return;
        }
        $.ajax({
            url: "/dun/query",
            method: "GET",
            dataType: "json",
            data: {phone: phone},
            success: function (data) {
                if (data.result == 200) {
                    var data = data.data;
                    var str="";
                    if(data.orgType){
                        str +=  data.orgType.orgTypeName;
                    }
                    var img = "adv05.jpg";
                    if(data.demandType){
                        str +="·"+data.demandType.demandTypeName;
                        if(data.demandType.demandType==10){
                            img = "adv04.jpg";
                        }else  if(data.demandType.demandType==15){
                            img = "adv06.jpg";
                        }
                    }else{
                        img= "adv03.jpg";
                    }
                    var model = ' <img src="/resources/img/dun/mobile/'+img+'">'+
                        '<div class="content crp-showcase">'+
                        '  <div class="col-sm-12 res-main">'+
                        ' <h2 class="sourehan text-center animated fadeInDown">'+str+'</h2>'+
                        '</div>'+
                        '</div>';
                    $("#tip").html(model);
                    if($("#noData").is(':visible'))
                    {
                        $("#noData").hide();
                    }
                    if($("#often").is(':visible'))
                    {
                        $("#often").hide();
                    }
                    $("#tip").show();
                } else if(data.result == 6){
                    if($("#tip").is(':visible'))
                    {
                        $("#tip").hide();
                    }
                    if($("#noData").is(':visible'))
                    {
                        $("#noData").hide();
                    }
                    if($("#often").is(':visible'))
                    {
                        $("#often").hide();
                    }
                    $("#often").show();
                }
                else {
                    var count = $("#dunCount").text();
                    count = count.replace(new RegExp(",", 'g'),'');
                    if(count.toString().length>4){
                        var number = parseInt(count)/10000;
                        $("#dunCountFormat").text(number.toString().substring(0,number.toString().length-5)+"万");
                    }else{
                        $("#dunCountFormat").text(count);
                    }
                    if($("#tip").is(':visible'))
                    {
                        $("#tip").hide();
                    }
                    if($("#noData").is(':visible'))
                    {
                        $("#noData").hide();
                    }
                    if($("#often").is(':visible'))
                    {
                        $("#often").hide();
                    }
                    $("#noData").show();
                }
            }
        })
    });

    $("[name='popBtn']").click(function () {
        malert($("#pop"),null,{});
    });
});

/**
 * 弹窗 context(容器对象),closer(关闭对象),config(json格式配置参数:opacity(透明度),time(时间))
 * malert($("#bankValid"),$("#bankValid").find(".closeImg"),{opacity:0.2});
 */
function malert(context,closer,config){
    var opacity=0.2,time=300,z=99;
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
    }
    mask.click(closeFunction);
    if(closer){
        closer.click(closeFunction);
    }
}