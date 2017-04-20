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
	<title>催收黄页-凭安信用</title>
</head>
<body>

<div style="height: 1000px;margin-top: 100px;">

<form action="/register" method="post" id="reg_form" datatype="json"
    style="background-color: mediumpurple;height: 600px;width: 500px;padding:30px 10px">
    <input type="hidden" name="smsId" id="sms_id">
    手机号：
    <input id="phone" value="18817507351" type="mobile" ajax="/exist" ajaxmsg="手机号已存在" name="phone" minlength="11"  des="手机号" msgtip="right" placeholder="手机号" />
    <br/><br/><br/>
    姓名：
    <input type="text" value="xiaoming" name="name" minlength="1" des="姓名"  placeholder="姓名"/><br/><br/><br/>
    类型：
    <select name="type" id="type_select">
        <option value ="1">个人</option>
        <option value ="2">企业</option>
    </select>
    <br/><br/><br/>
    <span id="type_span" style="display: none">
        <input type="text"  name="companyName"  des="企业名称"
               placeholder="企业名称"/>
        <div class="form-msg" style="display:none;position: absolute; padding: 10px; white-space: nowrap; left: 226px;">企业名称不为空</div>
        <br/><br/><br/>
        <input type="text" name="companyPhone"
               placeholder="企业电话" des="企业电话"/>
                <div class="form-msg" style="display:none;position: absolute; padding: 10px; white-space: nowrap; left: 226px;">企业电话不为空</div>
    </span>
    <br/><br/><br/>
    图片验证码：
    <input type="text" ajax="/dun/kapt" name="captcha" msgtip="bottom" ajaxmsg="图片验证码不正确" maxlength="4"  des="图片验证码"  placeholder="图片验证码"/>
    <img src="/dun/image" id="captchaImage"/>
    <%--<label for="kaptchaImage">看不清？换一张</label>--%>
    <br/><br/><br/>
    手机验证码：
    <input type="text" value="12345" name="sms" minlength="4" des="手机验证码" msgtip="bottom" placeholder="手机验证码" />
    <input type="button"  id="get_sms" disabled style="color:gray;margin-left: 10px;width: 120px;" value="获取验证码"/><br/><br/><br/>
    <input type="password" value="w123456" minlength="6" maxlength="14" typemsg="请输入6-14位数字、字母组合,可以有标点符号" name="password" id="password" des="密码1" placeholder="登陆密码"><br/><br/><br/>
    <input type="password" value="w123456" name="" minlength="6" maxlength="14" typemsg="两次密码不一致" id="password2" compare="==,#password" des="密码2" placeholder="登陆密码"><br/><br/><br/>
    <input type="submit"  value="注册" id="submit_btn"/>
</form>
</div>
<script data-main="/resources/js/dun/register.js" src="/resources/js/lib/require.js"></script>
</body>

</html>
