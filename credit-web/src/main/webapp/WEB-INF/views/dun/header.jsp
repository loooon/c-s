<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layout navbar">
    <a href="/" class="logo">
        <img src="/resources/img/dun/logo.png" alt="">
    </a>
    <ul id="nav_toggle_class">
        <li><a  data-nav="/dun/search" href="/dun/search">查验催收号码</a></li>
        <li><a data-nav="/dun/auth" href="/dun/auth">看看他有没有被催收</a></li>
        <li><a data-nav="/dun/company" href="/dun/company">集团用户通道</a></li>
        <li><a data-nav="/dun/batch" href="/dun/batch">批量查验催收号码</a></li>
        <li><a data-nav="/dun/dist" href="/dun/dist">催收号码分布热点图</a></li>
    </ul>
</div>
<script type="text/javascript">
    new NavHandler($("#nav_toggle_class").find("[data-nav]"));
</script>