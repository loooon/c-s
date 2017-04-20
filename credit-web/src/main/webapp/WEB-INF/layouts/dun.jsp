<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><sitemesh:write property='title' /></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="theme-color" content="#ffffff">
    <link href="/resources/css/dun/style.css" type="text/css" rel="stylesheet" />
    <link href="/resources/css/animate.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/resources/js/lib/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/common.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/dun/header.jsp" %>

            <sitemesh:write property="body" />
<%@ include file="/WEB-INF/views/dun/footer.jsp" %>
</body>
</html>


