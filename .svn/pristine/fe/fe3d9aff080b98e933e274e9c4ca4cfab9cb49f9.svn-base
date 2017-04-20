<%--
  Created by IntelliJ IDEA.
  User: Michael Chan
  Date: 3/8/2017
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="m-title">
    <p class="m-title-t1">修改密码</p>
</div>
<div class="m-content">
    <div class="m-content-b password-content">
        <form id="passwordForm" action="/user/password/?action=update_password" method="post">
            <div class="c-left3-content">
                <p><label>旧密码</label> <input type="password" name="old_password" class="input-type2" minlength="6" maxlength="20" des="原始密码" placeholder="原始密码" autocomplete="off"/></p>
                <p><label>新密码</label> <input type="password" class="input-type2" id="password" name="new_password" minlength="6" maxlength="20" des="新密码" placeholder="新密码" autocomplete="off"/></p>
                <p><label>确认密码</label> <input type="password" class="input-type2" name="repassword" minlength="6" maxlength="20" des="确认密码" placeholder="确认密码" compare="==,#password" autocomplete="off"/></p>
                <p class="s-bottom"><button type="submit"  class="custombtn2 reset-button">修改密码</button></p>
            </div>
        </form>
    </div>
    <div class="m-content-b suc_content">
        <div class="password-suc">
            <div class="prompt-title">
                <p>密码修改成功</p>
                <input type="button" value="返回" class="custombtn2"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/resources/js/user/Password.js"></script>
<script>
    var password = new Credit.User.Password();
    password.init();
</script>