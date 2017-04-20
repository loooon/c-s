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

<form action="/dun/kapt" method="post">
    <p>请输入验证码</p>
    <input type="text" name="captcha" value="" />
    <img src="/dun/image" id="kaptchaImage" /><br/><br/>
    <input type="submit"  value="submit" />
</form>
<script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
<script src="/resources/js/lib/echarts.min.js"></script>

<script type="text/javascript">

$("#kaptchaImage").on('click',function ()
{
    $(this).hide().attr('src', '/dun/image' ).fadeIn();
});
</script>
</body>

</html>
