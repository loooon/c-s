
RSF.regist("Credit.User.Personal");
Credit.User.Personal =  function(){

}

Credit.User.Personal.prototype = {
    'init':function(){
        $("#all_contacts").unbind('click').on('click','.select-click',function (e) {
            $(this).closest('p').next().toggle();
        });

        $("#all_contacts").on('click','.input-select-d ul li',function (e) {
            $(this).closest('div').hide();
            $(this).closest('div').prev().find('input').val($(e.currentTarget).html());
        });
        this.search();

    },
    'search':function () {
        var form_id = new mForm($("#id_check"));
        form_id.isAjax=false;
        form_id.loadDate=function () {
            $('.id-verify .c-right3 div').hide();
            $('.id-verify .loading').show();
        };
        form_id.submitError=function () {
                $('.id-verify .c-right3 div').hide();
                $('.id-verify .no-result').show();
        };
        form_id.afterSubmit=function (data) {
       /*     $('.id-verify .c-right3> div').hide();
            if(data!=''&&data!=null){
                var s_type=$('#select-type').val();
                if(data.result==0){
                    /!*$('p.ty1 span').html(data.data.status);
                    $('p.ty2 span').html(data.data.address);
                    $('p.ty2,p.ty3').hide();
                    $('p.ty3 span img').attr("src","data:image/png;base64,"+data.data.photo);
                    if(s_type=='基本查询加地址'){
                        $('p.ty2').show();
                    }
                    if(s_type=='基本查询加照片'){
                        $('p.ty3').show();
                    }
                    if(s_type=='全部信息'){
                        $('p.ty2,p.ty3').show();
                    }*!/
                    $('.id-verify .result .result-on').show();
                    $('.id-verify .result').show()
                }else{
                    $('.id-verify .result  .result-off span').html(data.message);
                    $('.id-verify .result .result-off').show();
                    $('.id-verify .result').show();
                }
            }else{
                $('.id-verify .no-result').show();
            }*/

        };
    }
}

