<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="theme-color" content="#ffffff"> 
	<title>查验催收号码-催收黄页</title>
</head>
<body>
<div class="header03"></div>
<div class="number">
    <p>凭安已发现催收号码<span id="dunCount">0</span>个&nbsp;&nbsp;覆盖逾期用户<span id="coverageCount">0</span>个</p>
</div>
<div class="layout index-main">
    <div class="index-in">
        <input type="text" id="numberTxt" placeholder="请输入您要查验的电话号码" class="index-in1" />
        <input type="button" id="searchBtn" style="cursor: pointer" value="查一查" class="index-in2">
        <p class="apply-p" style="display: none"><img src="/resources/img/dun/infor.png"><em>上传号码文件（支持文件格式txt、csv）</em></p>
    </div>
</div>


<div class="pop" id="resultPop" style="display: none">
    <h2><a href="#"><img src="/resources/img/dun/close.png"></a><span>查询结果</span></h2>
<%--    <div class="pop-border">
        <div class="res-left">
            <img src="/resources/img/dun/icon5.png">
        </div>
        <div class="res-right">
            <h4>互联网金融·客服</h4>
            <h5>电话号码：021-33533026</h5>
        </div>
    </div>
    <div class="pop-border">
        <div class="res-left">
            <img src="/resources/img/dun/icon6.png">
        </div>
        <div class="res-right">
            <h4>互联网金融·催收</h4>
            <h5>电话号码：15921896320</h5>
        </div>
    </div>
    <div class="pop-border">
        <div class="res-left">
            <img src="/resources/img/dun/icon8.png">
        </div>
        <div class="res-right">
            <h4>互联网金融·风险</h4>
            <h5>电话号码：15921896320</h5>
        </div>
    </div>
    <div class="pop-border">
        <div class="res-left">
            <img src="/resources/img/dun/icon7.png">
        </div>
        <div class="res-right">
            <h4>互联网金融·普通</h4>
            <h5>电话号码：15921896320</h5>
        </div>
    </div>--%>
    <div class="pop-border" id="popBody">
        <div class="res-left">
            <img src="/resources/img/dun/icon11.png">
        </div>
        <div class="res-right">
            <h4>互联网金融·普通<</h4>
            <h5>电话号码：15921896320</h5>
        </div>
    </div>
    <input type="button" style="cursor: pointer" value="继续查询" />
</div>

<div class="pop" id="noData" style="display: none">
    <h2><a href="javascript:void(0)"><img src="/resources/img/dun/close.png"></a><span>查询结果</span></h2>
    <div class="pop-border">
        <div class="res-left">
            <img src="/resources/img/dun/icon11.png">
        </div>
        <div class="res-right">
            <h3>在凭安的<span id="formatCount"></span></h3>
            <h3>催收号码库中未发现该号码</h3>
        </div>
    </div>
    <input type="button" style="cursor: pointer" value="继续查询" />
</div>
<div class="pop" id="often" style="display: none">
    <h2><a href="javascript:void(0)"><img src="/resources/img/dun/close.png"></a><span>查询结果</span></h2>
<div class="pop-border">
    <div class="res-left">
        <img src="/resources/img/dun/icon14.png">
    </div>
    <div class="res-right">
        <h3>为了给您提供更好的服务</h3>
        <h3>请通过集团用户通道联系我们</h3>
    </div>
</div>
    <input type="button" onclick="location.href='/dun/company'" style="cursor: pointer" value="进入集团用户通道" />
</div>
<script>
    $(document).ready(function () {
        search();
        $("#numberTxt").focus();
        window.document.onkeydown =function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){ // enter 键

                if(isMalert()){
                    closeMalert($("#resultPop,#noData"));
                    $("#numberTxt").focus();
                }else{
                    $("#searchBtn").click();
                }
            }

    };


    });
    function search() {
        $("#searchBtn").click(function () {
            var phone = $("#numberTxt").val();
            if(!phone||phone==""||$.trim(phone)==""){
                $(".apply-p").find("em").text("请输入电话号码");
                $(".apply-p").show();
                $("#numberTxt").focus();
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
                        $("#popBody").find("img").attr("src","/resources/img/dun/"+data.demandImg);
                        var str = data.orgName;
                        if(data.demandName!=""){
                            str+="·"+data.demandName;
                        }
                        $("#popBody").find("h4").text(str);
                        $("#popBody").find("h5").text("电话号码："+phone);

                        malert($("#resultPop"),$("input,a"),{onclose:function () {
                            $("#numberTxt").focus();
                        }});

                    }
                    else if(data.result==6){
                       /* malert($("#often"),$("input,a"),{onclose:function () {
                            $("#numberTxt").focus();
                        }});*/
                       window.location.href = "/login";
                    }else {
                        var count = pingan_dun_total;
                        if(count/10000> 1){
                            $("#formatCount").text( parseInt(count/10000)+"万");
                        }else{
                            $("#formatCount").text(count);
                        }

                        malert($("#noData"),$("input,a"),{onclose:function () {
                            $("#numberTxt").focus();
                        }});
                    }
                }
            })
        });
    }
</script>
<script type="text/javascript" src="/resources/js/lib/countUp.js"></script>
<script type="text/javascript" src="/resources/js/dun/dunCount.js"></script>
</body>
</html>