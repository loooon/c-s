<%--
  Created by IntelliJ IDEA.
  User: Michael Chan
  Date: 3/8/2017
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="m-title">
    <p class="m-title-t1">帮助中心</p>
</div>
<div class="m-content">
    <ul class="list-content">
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>
        <li><span><a href="javascript:void(0);">1</a></span></li>

    </ul>
    <div class="m-content-p pagination">
        <!--                    <div class="pagination">-->
        <div class="page">
            <p>
                <i>（共1126条记录）</i>


                <a class="current">1</a>


                <a href="/support?page=2">2</a>


                <a href="/support?page=3">3</a>


                <a href="/support?page=4">4</a>


                <a href="/support?page=5">5</a>


                <a href="/support?page=6">6</a>


                <a href="/support?page=7">7</a>


                <a href="/support?page=8">8</a>

                <a class="nextPage" href="/support?page=2">下一页</a>
                <a class="nextPage" href="/support?page=113">尾页</a>
            </p>
        </div>
        <!--                    </div>-->
    </div>
</div>
<script type="text/javascript" src="/resources/js/user/Support.js"></script>
<script>
    var support = new Credit.User.Support();
    support.init();
</script>