<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="theme-color" content="#ffffff">
	<title>首页-催收黄页</title>
	<!--<link rel="stylesheet" type="text/css" href="/assets/css/site.min.css?v=a77632e6b4" />-->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
	<!--<link href="/assets/css/site.min.css" rel="stylesheet">-->
	<link href="/resources/css/dun/mobile/style.css" type="text/css" rel="stylesheet" />

    <script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/lib/countUp.js"></script>
    <script type="text/javascript" src="/resources/js/dun/mobile/dunCount.js"></script>
    <script type="text/javascript" src="/resources/js/dun/mobile/dunIndex.js"></script>
</head>
<body>
	<nav class="navbar navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="/dun/m/index" class="navbar-brand">
					<img class="logo-light" src="/resources/img/dun/mobile/logo.png" alt="">
				</a>
			</div>
		</div>
	</nav>
    <div class="adv adv-top">
        <a href="javascript:void(0)"><img src="/resources/img/dun/mobile/header-bg.jpg"></a>
    </div>
    <div class="adv-in crp-showcase">
        <div class="col-sm-12">
            <h1>凭安已发现催收号码 <span id="dunCount">1,601,711</span>个</h1>
            <h1>已覆盖<span id="coverageCount">1,601,711</span>个被催收用户</h1>
        </div>
    </div>
    <a href="/dun/m/search">
        <div class="icon icon-bg01">
            <div class="icon-left"><img src="/resources/img/dun/mobile/icon01.png"></div>
            <div class="icon-right">
                <h3>查验催收号码</h3>
                <h4>细化催收号码分类，申请用户的历史逾期一目了然</h4>
            </div>
        </div>
    </a>
    <a href="/dun/m/company">
        <div class="icon icon-bg02">
            <div class="icon-left"><img src="/resources/img/dun/mobile/icon02.png"></div>
            <div class="icon-right">
                <h3>集团用户通道</h3>
                <h4>凭安已为数百家集团用户，提供深度服务，期待您的加入</h4>
            </div>
        </div>
    </a>
    <a href="javascript:void(0)" name="popBtn">
        <div class="icon icon-bg01">
            <div class="icon-left"><img src="/resources/img/dun/mobile/icon03.png"></div>
            <div class="icon-right">
                <h3>看看他有没有被催收</h3>
                <h4>通过通话数据和催收号码，精准识别逾期用户</h4>
            </div>
        </div>
    </a>
    <a href="javascript:void(0)" name="popBtn">
        <div class="icon icon-bg02">
            <div class="icon-left"><img src="/resources/img/dun/mobile/icon04.png"></div>
            <div class="icon-right">
                <h3>批量查验</h3>
                <h4>方便快捷，一次完成</h4>
            </div>
        </div>
    </a>

    <div id="pop" class="adv res" style="display: none">
        <img src="/resources/img/dun/mobile/n2.jpg">
    </div>

    <div class="adv adv-bot">
        <img src="/resources/img/dun/mobile/index01.jpg">
    </div>
	
</body>
</html>