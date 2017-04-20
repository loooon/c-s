
RSF.regist("Credit.User.Password");
Credit.User.Password =  function(){

}

Credit.User.Password.prototype = {
    'init':function(){
        var form = new mForm($("#passwordForm"));
        form.isAjax=true;
        var popovererr = new Popover($('.reset-button'), "right");
        $('input').bind('input', function () {
            popovererr.hide();
        });
        form.afterSubmit=function (data) {
            if(data.code==0){
                $('.password-content').hide();
                $('.password-suc').show();
                popovererr.hide();

            }else{
                popovererr.show(data.message);
                // popovererr.obj.pop.css("color",'red');
            }
        }
        $('.password-suc input').click(function () {
            $('.password-suc').hide();
            $('.password-content').show();
        });
    }
}

