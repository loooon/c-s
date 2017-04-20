<%--
  Created by IntelliJ IDEA.
  User: Michael Chan
  Date: 3/8/2017
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="m-title">
    <p class="m-title-t1">账户信息</p>
</div>
<div class="m-content">
    <div class="m-content-b">
        <div class="c-left2">
                        <span>
                            <div class="t-img c-left2-i"></div>
                            <p class="t1">168</p>
                            <p class="t2">账户余额</p>
                        </span>
        </div>
        <div class="c-right2">
                        <span>
                            <div class="t-img c-right2-i"></div>
                            <p class="t1">999</p>
                            <p class="t2">累计消费</p>
                        </span>
        </div>
    </div>
</div>
<div class="m-title clearfix">
    <div class="m-title-left">消费记录</div>
    <div class="m-title-right">
        <ul class="all-time">
            <li class="appoint-t" id="datetimepicker">指定</li>
            <li class="month">过去30天</li>
            <li class="current week">过去7天</li>
        </ul>
        <div class="date-picker">
            <div id="datepickerStart" class="hasDatepicker"></div>
            <span class="date-to">To</span>
            <div id="datepickerEnd" class="hasDatepicker"></div>
            <p>
                <input type="button" value="确定" class="custombtn sure">
                <input type="button" value="取消" class="custombtn cancel"></p>
        </div>
    </div>

</div>
<div class="m-content m-chart-bg">
    <div class="m-chart" id="consumechart"></div>
</div>
<script type="text/javascript" src="/resources/js/user/ConsumeHistory.js"></script>
<script>
    var consumehistory = new Credit.User.ConsumeHistory();
    consumehistory.setChartsData({"x":["20170301","20170302","20170303","20170304","20170305","20170306","20170307"],"data_value":["0.00","0.00","0.00","0.00","0.00","0.00","0.00"]});
    consumehistory.init();
</script>