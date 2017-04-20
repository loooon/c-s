<%--
  Created by IntelliJ IDEA.
  User: wangjunling
  Date: 2017/3/29
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>信贷信息查询</title>
</head>
<body>
<div class="m-title clearfix">
    <div class="m-title-left">信贷信息查询</div>
    <%--<div class="m-title-right"><p class="m-title-t3" id="clear_search_history">清空查询记录</p></div>--%>
</div>
<div class="m-content id-verify" id="print_id">
    <div  class="m-error">
        ${errorMsg}
        <c:forEach items="${errMsgList}" var="error">
            ${error} <br/>
        </c:forEach>
    </div>
    <div class="m-content-b search-content clearfix">
        <div class="c-left3">
            <form id="loanForm" action="/user/credit/loan" method="post" target="_blank">
                <div class="c-left3-content">
                    <p><label>手机号</label> <input value="13348877561" type="mobile" class="input-type3" maxlength="11" notnul="ture" ajaxmsg="*手机号不为空" msgtip="innerLeft" name="phone"  placeholder="被查询用户的手机号" autocomplete="off"/></p>
                    <p><label>姓名</label> <input value="样本" type="text" class="input-type3" notnul="ture" ajaxmsg="*姓名不为空" msgtip="innerLeft" name="name"  placeholder="被查询用户的姓名" autocomplete="off"/></p>
                    <p><label>身份证号</label> <input value="510824199511012894" type="id" class="input-type3" notnul="ture" ajaxmsg="*身份证不为空" msgtip="innerLeft" maxlength="18" name="idcard" des="*身份证" placeholder="请输入身份证号码" autocomplete="off"/></p>
                    <p><label>手机设备编号</label> <input type="text" class="input-type3"   msgtip="innerLeft" name="imei"  placeholder="被查询用户的手机设备编号" autocomplete="off"/></p>
                    <p><label>SIM卡编号</label> <input type="text" class="input-type3"   msgtip="innerLeft" name="imsi"  placeholder="被查询用户的SIM卡编号" autocomplete="off"/></p>
                    <p><label>查询类型</label>

                    多头借贷：<input type="checkbox"  name="types" value="loan"/>
                    逾期：<input type="checkbox"   name="types" value="overdue"/>
                    黑名单：<input type="checkbox"   name="types" value="blacklist"/>

                    </p>
                    <p class="s-bottom" style="    margin-left: 34%;    float: left;">
                        <button type="submit" id="submit_btn" class="custombtn2">提交</button>
                    </p>
                </div>
            </form>
        </div>
        <div class="c-right3">
            <div class="default"><p><label>查询记录</label></p></div>
            <div class="loading">
                <p><span class="limg"></span><label>查询中...</label></p>
            </div>

            <div class="no-result">
                <p><label>查询记录</label></p>
            </div>
          </div>
    </div>
</div>
<script>
    $().ready(function () {
        var loanForm = new mForm($("#loanForm"));
        loanForm.isAjax = true;
        $("#searchBtn").click(function () {
            var values = $("#loanForm").serializeArray();
            $.ajax({
                dataType: 'json',
                data: values,
                type: 'post',
                url: '/user/loan',
                success: function (data, r, xhr) {
                    if (data.result == 0) {

                    } else {

                    }
                }
            });
        });

    });
</script>
</body>
</html>
