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
	<title>集团用户通道-催收黄页</title>
</head>
<body>
	<div class="header01"></div>
	<div class="number">
        <p>凭安已发现催收号码<span id="dunCount">0</span>个&nbsp;&nbsp;覆盖逾期用户<span id="coverageCount">0</span>个</p>
	</div>
<%--	<div class="apply-content">
		<div class="apply-input">
			<form id="applyForm">
	    	<div class="apply-block">
	    		<p><span><i>*</i>公司名称：</span><input type="text" name="companyName" id="companyName" value=""></p>
	    		<p class="apply-p"><em id="companyNameMessage" style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的公司名称</em></p>
	    	</div>
	    	<div class="apply-block">
	    		<p><span><i>*</i>联系人：</span><input type="text" name="contacts" id="contacts" value=""></p>
	    		<p class="apply-p"><em id="contactsMessage"  style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的联系人姓名</em></p>
	    	</div>
                <div class="apply-block">
                    <p><span><i>*</i>职务：</span><input type="text" name="duty" id="duty" value=""></p>
                    <p class="apply-p"><em id="dutyMessage"  style="display:none;"><img src="/resources/img/dun/infor.png">请输入职务</em></p>
                </div>
	    	<div class="apply-block">
	    		<p><span><i>*</i>手机号码：</span><input type="text" value="" name="phone" id="phone"></p>
	    		<p class="apply-p"><em id="phoneMessage"   style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的手机号码</em></p>
	    		
	    	</div>	    	
	    	<div class="apply-bot">
	    		<div class="apply-sud">
	    			<p class="apply-p"><em id="message" style="display: none">申请已成功</em></p>
	    		</div>
	    		<div class="apply-btn">
	    			<p><input type="button" style="cursor: pointer"  id="submitApply"  value="立即申请"></p>
	    		</div>
	    	</div>
			</form>
	    </div>
    </div>--%>

    <div class="apply-content">
    <div class="apply-input">
        <form id="applyForm">
        <div class="adv-input input-h2">
            <img src="/resources/img/dun/in02.png">
            <input type="text" name="companyName" id="companyName" placeholder="上海凭安征信服务有限公司">
            <a href="#"></a>
            <p class="apply-p"><em id="companyNameMessage" style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的公司全称</em></p>
        </div>
        <div class="adv-input input-h2">
            <img src="/resources/img/dun/in03.png">
            <input type="text" name="contacts" id="contacts" placeholder="请输入联系人姓名">
            <a href="#"></a>
            <p class="apply-p"><em id="contactsMessage"  style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的联系人姓名</em></p>
        </div>
        <div class="adv-input input-h2">
            <img src="/resources/img/dun/in04.png">
            <input type="text" name="duty" id="duty" placeholder="请输入职务">
            <a href="#"></a>
            <p class="apply-p"><em id="dutyMessage"  style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的职务</em></p>
        </div>
        <div class="adv-input input-h2">
            <img src="/resources/img/dun/in05.png">
            <input type="text" name="phone" id="phone" placeholder="请输入您的手机号码">
            <a href="#"></a>
            <p class="apply-p"><em id="phoneMessage"   style="display:none;"><img src="/resources/img/dun/infor.png">请输入正确的手机号码</em></p>
        </div>

            <div class="apply-bot">
                <div class="apply-sud">
                    <p class="apply-p"><em id="message" style="display: none">申请已成功</em></p>
                </div>
                <div class="apply-btn">
                    <p><input type="button" style="cursor: pointer"  id="submitApply"  value="立即申请"></p>
                </div>
            </div>
        </form>
    </div>
    </div>










    <div class="pop" style="display: none">
        <h2><a href="javascript:void(0)"><img src="/resources/img/dun/close.png"></a><span>查询结果</span></h2>
        <div class="pop-border">
            <div class="res-left">
                <img src="/resources/img/dun/icon9.png">
            </div>
            <div class="res-right">
                <h3>恭喜您，提交成功</h3>
                <h3>我们将尽快与您联系</h3>
            </div>
        </div>
        <input type="button" style="cursor:pointer" value="继续" />
    </div>
    <script type="text/javascript" src="/resources/js/dun/companyApply.js"></script>
	<script type="text/javascript" src="/resources/js/lib/countUp.js"></script>
	<script type="text/javascript" src="/resources/js/dun/dunCount.js"></script>
</body>
</html>
