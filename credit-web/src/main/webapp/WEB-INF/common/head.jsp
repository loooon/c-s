<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/8/2017
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top">
    <div class="header">
        <div class="h-left">
            <div class="logo"><a href="/user/index"><img src="/resources/img/user/logo.png"></a></div>
            <!--            <span>凭安金融反欺诈查询系统</span>-->
        </div>
        <div class="h-right">
            <div class="user" id="hover_user_toggle">${fn:substring(sessionScope.user.userName, 0,1)}</div>
           <%-- <div class="inform">
                <a href="/notice"><img src="/resources/img/user/top_inform.png"></a>
                <div class="notes" style="display: none;"></div>
                <b></b>
            </div>--%>
            <%--<div class="help"><a href="/support"><img src="/resources/img/user/top_help.png"></a></div>--%>
            <div class="user-info" id="hover_user_show" style="   width: 58px;">
                <p>${sessionScope.user.userName}</p>
                <p><a href="/logout">退出登陆</a></p>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var header=new Credit.Header();
    header.init();
    var creditFrame = new CreditFrame();
    creditFrame.init();
</script>