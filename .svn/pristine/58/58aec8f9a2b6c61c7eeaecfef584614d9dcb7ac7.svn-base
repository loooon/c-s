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

	<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
	<link href="/resources/css/dun/mobile/style.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/lib/countUp.js"></script>
    <script type="text/javascript" src="/resources/js/dun/mobile/dunCount.js"></script>
</head>
<body>
	<nav class="navbar navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/dun/m/index" class="navbar-brand">
					<img class="logo-light" src="/resources/img/dun/mobile/logo.png" alt="">
				</a>
				<a href="/dun/m/index" class="navbar-right">返回首页</a>
			</div>
		</div>
	</nav>
    <div class="adv adv-top">
    	<a href="javascript:void(0)"><img src="/resources/img/dun/mobile/header-bg01.jpg"></a>
    </div>    
    <div class="adv-in crp-showcase">
		<div class="col-sm-12">
			<h1>凭安已发现催收号码 <span id="dunCount">1,601,711</span>个</h1>
			<h1>已覆盖<span id="coverageCount">1,601,711</span>个被催收用户</h1>
		</div>
	</div>
	<div class="adv-input input-h1">
    	<img src="/resources/img/dun/mobile/in01.png">
	    <input type="text" id="numberTxt" placeholder="输入电话号码（固话需要添加区号）">
    	<a href="javascript:void(0)" id="searchBtn"></a>
    	<p class="apply-p"><em id="phoneMsg" style="display: none"><img src="/resources/img/dun/mobile/infor.png">请输入正确的电话号码</em></p>
    </div>
    <div class="adv-result">
    	<h3 id="initResult">在这里输出查验结果</h3>

        <div class="result-block" id="resultDiv" style="display: none">
            <div class="res-left" id="resultImg">
                <img src="/resources/img/dun/mobile/icon8.png">
            </div>
            <div class="res-right">
                <h4>互联网金融·风险号码</h4>
                <h5>电话号码：<label>021-33533026</label></h5>
            </div>
        </div>

        <div class="result-block" id="noData" style="display: none">
            <div class="res-left" >
                <img src="/resources/img/dun/mobile/icon11.png">
            </div>
            <div class="res-right">
                <h4>在凭安的<span id="dunCountFormat"></span></h4>
                <h5>催收号码库中未发现该号码</h5>
            </div>
        </div>
    	<img id="bacImg" src="/resources/img/dun/mobile/n1.jpg">
    	<img id="oftenImg" style="display: none" src="/resources/img/dun/mobile/adv10.jpg">
    </div>


    <div class="adv-bot bot-position">
    	<img src="/resources/img/dun/mobile/index01.jpg">
    </div>
	
</body>
</html>
<script>
    $(document).ready(function () {
        $("#searchBtn").click(function () {
            $("#phoneMsg").hide();
            var phone = $("#numberTxt").val();
            if(!phone||phone==""||$.trim(phone)==""){
                $("#phoneMsg").show();
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
                        $("#noData").hide();
                        $("#bacImg").show();
                        $("#oftenImg").hide();
                        var str = data.orgName;
                        if(data.demandName!=""){
                            str+="·"+data.demandName;
                        }

                        $("#resultImg").html('<img src="/resources/img/dun/mobile/'+data.demandImg+'">');
                       $("#resultDiv").find("h4").text(str);
                       $("#resultDiv").find("label").text(phone);
                       $("#initResult").hide();
                       $("#resultDiv").show();

                    } else if(data.result == 6){
                        $("#noData").hide();
                        $("#resultDiv").hide();
                        $("#initResult").hide();
                        $("#bacImg").hide();
                        $("#oftenImg").show();
                    }
                    else {
                        $("#bacImg").show();
                        $("#oftenImg").hide();
                        var count = $("#dunCount").text();
                        count = count.replace(new RegExp(",", 'g'),'');
                        if(count.toString().length>4){
                            var number = parseInt(count/10000);
                            $("#dunCountFormat").text(number+"万");
                        }else{
                            $("#dunCountFormat").text(count);
                        }
                        $("#resultDiv,#initResult").hide();
                        $("#noData").show();
                    }
                }
            })
        });

    });
</script>