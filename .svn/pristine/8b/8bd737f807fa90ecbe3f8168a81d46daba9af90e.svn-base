/**
 * Created by admin on 3/29/2017.
 */
require.config({
    waitSeconds : 0,
    baseUrl:"/",
    paths : {
        jquery:'/resources/js/lib/jquery-3.1.1.min',
        Pingan : '/resources/js/dun/common',
        register:"/resources/js/dun/register"
    },
    shim : {
        Pingan : {
            deps : [ 'jquery']
        },
        register : {
            deps : [ 'Pingan']
        }
    },
    deps : [ 'Pingan', 'register' ]
});
require(['Pingan'],function () {

    Pingan.Event = function () {
        var show_company = function ()
        {
            if($("#type_select option:selected").text() != "个人")
            {
                $("#type_span").show();
            }else {
                $("#type_span input").each(function ()
                {
                    $(this).val('');
                });
                $("#type_span").hide();
            }
        };
        var countdown=6;
        function wait_time(dom) {
            if (countdown == 0) {
                dom.removeAttr("disabled");
                dom.val('免费获取验证码');
                dom.css('color', '#444');
                countdown = 6;
            } else {
                dom.attr("disabled", true);
                dom.val("重新发送("+countdown+")s");
                dom.css('color', 'gray');
                countdown--;
                setTimeout(function() {
                    wait_time(dom)
                },1000)
            }
        }

        $().ready(function ()
        {
            var regForm = new Pingan.MForm($("#reg_form"));
            var sms_flag = false;
            regForm.isAjax=true;
            regForm.afterSubmit = function (result)
            {
                if(result.result ==6)
                {
                    Pingan.malertInfo('注册失败');
                }else if(result.result == 4)
                {
                    Pingan.malertInfo("验证码已过期")
                }else if(result.result == 501)
                {
                    Pingan.malertInfo("短信验证码不正确")
                }else if(result.result == 500)
                {
                    Pingan.malertInfo("注册异常")
                }else if(result.result == 0)
                {
                    Pingan.malertInfo("注册成功.")
                }
            };
            regForm.beforeSubmit = function ()
            {
                if ($("#type_span input:visible").length != 0)
                {
                    var company_name = $("[name=companyName]").val();
                    var company_phone = $("[name=companyPhone]").val();
                    if(company_name == "")
                    {
                        $("[name=companyName]").next('div').show();
                        return false;
                    }
                    if(company_phone == "")
                    {
                        $("[name=companyPhone]").next('div').show();
                        return false;
                    }
                    if(sms_flag)
                    {
                        return true;
                    }else {
                        Pingan.malertInfo("请先获取验证码.")
                    }
                }
                if(sms_flag)
                {
                    return true;
                }else {
                    Pingan.malertInfo("请先获取验证码.")
                }
            };


            $("#type_span input").each(function ()
            {
                $(this).on('blur',function ()
                {
                    if($(this).val() == "")
                    {
                        $(this).next('div').show();
                    }else
                    {
                        $(this).next('div').hide();
                    }
                });
            });
            
            $("[name=captcha]").on('blur',function ()
            {
               if(regForm.getItem($(this).attr('name')).validate())
               {
                   $("#get_sms").removeAttr('disabled');
                   $("#get_sms").css('color', '#444');
               }else {
                   $("#get_sms").attr("disabled", true);
                   $("#get_sms").css('color', 'gray');
               }
            });

            $("#captchaImage").on('click',function ()
            {
                $(this).hide().attr('src', '/dun/image' ).fadeIn();
            });

            show_company();

            $("#type_select").on('change',function ()
            {
                show_company();
            });

            $("#get_sms").on('click',function ()
            {
                if(regForm.getItem('phone').validate())
                {
                    wait_time($("#get_sms"));
                }else {
                    return false;
                }
                sms_flag = true;
                var phone = $("[name=phone]").val();
                $.ajax({
                    url:'/sms/code',
                    method: "POST",
                    data: {receiver:phone},
                    dataType:'json',
                    async:true,
                    success:function (res)
                    {
                        if(res.result == 200)
                        {
                            $("#sms_id").val(res.data);
                            Pingan.malertInfo('已成功发送验证码到您的手机，请注意查收','infoMessage warning',{});
                        }else
                        {
                            Pingan.malertInfo('发送失败,'+res.message,'style="color:red;"',{});
                        }
                    },
                    error:function ()
                    {
                        alert('error');
                    }
                });
            });

        });
        /**
         * 弹窗 context(容器对象),closer(关闭对象),config(json格式配置参数:opacity(透明度),time(时间))
         * malert($("#bankValid"),$("#bankValid").find(".closeImg"),{opacity:0.2});
         */
        function modalShow(context,closer,config){
            var opacity=0.2,time=300,z=99;
            if(config!=null){
                opacity=config.opacity==null?opacity:config.opacity;
                time=config.time==null?time:config.time;
                z=config["z-index"]==null?z:config["z-index"];
            }
            var maskStr="<div id='malert_mask' style='position:fixed;display:none;z-index:"+z+";top:0;left:0; width:100%;" +
                " height:100%;background-color:black;filter:alpha(opacity="+(opacity*100)+");-moz-opacity:"+opacity+";opacity:"+opacity+";'></div>";
            if($("#malert_mask").length!=0){
                $("#malert_mask").remove();
            }

            $("body").append(maskStr);
            var mask=$("#malert_mask");
            var h = $(window).height()-context.height();
            if(h<0){h = 100;}
            context.css({"top":((h)/3)+"px","position":"fixed", "z-index":z+1,"left":"50%"});
            context.css("margin-left","-"+(context.width()/2)+"px");
            context.fadeIn(time);mask.fadeIn(time);
            //关闭
            var closeFunction = function (){
                context.fadeOut(time);
                mask.fadeOut(time).remove();
                if(config.onclose){config.onclose();}
            }
            mask.click(closeFunction);
            if(closer){
                closer.click(closeFunction);
            }
        }

    };

    Pingan.Event();

});
