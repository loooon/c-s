/**
 * 项目通用对象，包含常用的方法
 * 调用方法  Pingan.方法名()
 */
var Pingan = Pingan || {};
var global = this;


/**
 * ajax参数对象,在调用Pingan.ajax时传入
 * 使用方法
 * var params=new AjaxObj(url,null,reloadNewsAjaxSuccessCallback);
 * 默认的构造器只有三个参数，若需要设置其他参数，在new AjaxObj()后,调用params.参数名=xxx;
 * 例如: params.async=false;
 * @param url  ajax调用的url
 * @param jsondata 传递的json参数
 * @successCallback  ajax调用成功后的回调函数
 */
var AjaxObj=function(url, jsondata, successCallback){
    this.url=url;
    this.jsondata=jsondata;
    this.successCallback=successCallback;
    this.errorCallback;
    this.beforeSend;
    this.async;
    this.type;
    this.dataType;
}


define('Pingan', ['jquery'], function (){

    global.basePath = basePath.substring(0, basePath.length - 1);

    //扩展js基础类
    var initJsExtensions = function (){
        /**
         * 格式化日期
         * new Date().format("yyyy-MM-dd")
         */
        Date.prototype.format = function(format) {
            var o = {
                "M+" : this.getMonth() + 1, // month
                "d+" : this.getDate(), // day
                "h+" : this.getHours(), // hour
                "m+" : this.getMinutes(), // minute
                "s+" : this.getSeconds(), // second
                "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
                "S+" : this.getMilliseconds()
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for ( var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    var formatStr = "";
                    for ( var i = 1; i <= RegExp.$1.length; i++) {
                        formatStr += "0";
                    }
                    var replaceStr = "";
                    if (RegExp.$1.length == 1) {
                        replaceStr = o[k];
                    } else {
                        formatStr = formatStr + o[k];
                        var index = ("" + o[k]).length;
                        formatStr = formatStr.substr(index);
                        replaceStr = formatStr;
                    }
                    format = format.replace(RegExp.$1, replaceStr);
                }
            }
            return format.replace(/NaN-aN-aN/g,"");
        };


        String.prototype.trim=function(){
            return this.replace(/(^\s*)|(\s*$)/g, "");
        }
        String.prototype.ltrim=function(){
            return this.replace(/(^\s*)/g,"");
        }
        String.prototype.rtrim=function(){
            return this.replace(/(\s*$)/g,"");
        }
        String.prototype.isBlank=function(){
            return !this || this.replace(/(^\s*$)/g,"");
        }
        String.prototype.isEmpty=function(){
            return this.replace(/(^\s*$)/g,"");
        }

        /**
         * @desc 格式化输入字符串
         * 示例: "hello {0}".format('world')；返回'hello world'
         * @return {String}
         */
        String.prototype.format = function() {
            var a = arguments;
            return this.replace(/\{(\d+)\}/g, function(s, i) {
                return a[i];
            });
        }

        /**
         * @desc url格式校验
         * @return {boolean}
         */
        String.prototype.isUrl = function(){
            var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z\u4E00-\u9FA5_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"   //ftp's user
                + "(([0-9]{1,3}\.){3}[0-9]{1,3}"  //ip format url
                + "|" //IP or domain name
                + "([0-9a-z\u4E00-\u9FA5_!~*'()-]+\.)*"  //domain name : www
                + "([0-9a-z\u4E00-\u9FA5][0-9a-z\u4E00-\u9FA5-]{0,61})?[0-9a-z\u4E00-\u9FA5]\."  //the second level domain name
                + "[a-z]{2,6})" //first level domain .com or .museum
                + "(:[0-9]{1,4})?"  //port
                + "((/?)|"  // a slash isn't required if there is no file name
                + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
            var re = new RegExp(strRegex);
            return re.test(this);
        };

        /**
         * @desc email格式校验
         * @param {string} strValue
         * @return {boolean}
         */
        String.prototype.isEmail = function(){
            var emailExp  = /(^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$)/;
            return emailExp.test(this);
        };
        /**
         * @desc password格式校验
         * @param {string} strValue
         * @return {boolean}
         */
        String.prototype.isPassword= function(){
            // var passwordExp  = /^(?!\d+$)(?!^[a-zA-Z]+$)[\w]{6,20}$/;
            var passwordExp  = /^((?=.*[0-9])(?=.*[a-zA-Z]))|(((?=.*[0-9])(?=.*[a-zA-Z]))(?=.*[!@#$%^&*,./;'\[\]\-=+\{}:"<>\?])){6,20}$/;
            return passwordExp.test(this);
        };
        /**
         * @desc 域名格式校验
         * @param {string} domain
         * @return {boolean}
         */
        String.prototype.isDomain = function(){
            var domain = this;
            var reg = new RegExp(/^((([A-Za-z0-9_-\u4E00-\u9FA5])|(\*))+\.)?([A-Za-z0-9_-\u4E00-\u9FA5]{1,200})$/);
            var domainPostfix = new Array(".gov.mo",".com.tw",".com.mo",".co.cc",".ce.ms",".osa.pl",".c.la",".com.hk",".net.in",".edu.tw",".org.tw",".bij.pl",".ac.cn",".ah.cn",".bj.cn",".com.cn",".cq.cn",".fj.cn",".gd.cn",".gov.cn",".gs.cn",".gx.cn",".gz.cn",".ha.cn",".hb.cn",".he.cn",".hi.cn",".hk.cn",".hl.cn",".hn.cn",".jl.cn",".js.cn",".jx.cn",".ln.cn",".mo.cn",".net.cn",".nm.cn",".nx.cn",".org.cn",".qh.cn",".sc.cn",".sd.cn",".sh.cn",".sn.cn",".sx.cn",".tj.cn",".tw.cn",".xj.cn",".xz.cn",".yn.cn",".zj.cn",".nl.ae",".org.uk",".org.nz",".org.bz",".org.au",".com.nu",".com.my",".com.au",".co.uk",".co.kr",".co.jp",".nu.ae",".nl.ae",".com.au",".cf.gs",".com.cn",".net.cn",".org.cn",".edu.cn",".com",".cn",".mobi",".tel",".asia",".net",".org",".name",".me",".info",".cc",".hk",".biz",".tv",".la",".fm",".cm",".am",".sh",".us",".in",".ro",".ru",".hu",".tk",".co",".cx",".at",".tw",".ws",".vg",".vc",".uz",".to",".tl",".th",".tf",".tc",".st",".so",".sk",".sg",".sc",".pl",".pe",".nu",".nf",".ne",".my",".mu",".ms",".mo",".lv",".lt",".lc",".jp",".it",".io",".im",".ie",".gs",".gp",".gl",".gg",".gd",".fr",".fi",".eu",".edu",".dk",".de",".cz",".ch",".ca",".bi",".be",".au",".ae",".pw",".ly",".wang",".ren",".top",".club");
            var isReplaced = false;
            for(var idx=0; idx < domainPostfix.length; idx++){
                var postFix = domainPostfix[idx];
                var regStr = "(\\"+postFix+")$";
                var endReg = new RegExp(regStr);
                if(endReg.test(domain.toLowerCase())){
                    isReplaced = true;
                }
                domain = domain.replace(postFix,"");
            }
            if(!isReplaced){
                return false;
            }
            return reg.test(domain);
        };

        /**
         * @desc 邮编格式校验
         * @param String zipcode
         * @return boolean
         */
        String.prototype.isZipCode = function (){
            var reg = /^[0-9]{6}$/;
            return reg.test(this);
        };

        /**
         * @desc 固定电话格式校验
         * @param String phone
         * @return boolean
         */
        String.prototype.isPhone = function (){
            var reg = /((^(\+86)|(86))?(((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$))/;
            return reg.test(this);
        };

        /**
         * @desc 手机号码格式校验
         * @param String mobile
         * @return boolean
         */
        String.prototype.isMobile = function(){
            var reg = /^(\+86)|(86)?(^((1[3,5,8][0-9])|(14[5,7]))\d{8}$)/;
            return reg.test(this);
        };

        /**
         * @desc 纯数字校验
         * @param String mobile
         * @return boolean
         */
        String.prototype.isNumber = function(){
            var reg = /^[0-9]+$/;
            return reg.test(this);
        };


        /**
         * @desc 是否为局域网IP
         * @param String ip
         * @return boolean
         */
        String.prototype.isLanIp = function(){
            var reg = /(127[\.]0[\.]0[\.]1)|(localhost)|(^10[\.]\d{1,3}[\.]\d{1,3}[\.]\d{1,3})|(172[\.]((1[6-9])|(2\d)|(3[01]))[\.]\d{1,3}[\.]\d{1,3})|(192[\.]168[\.]\d{1,3}[\.]\d{1,3})|(255[\.]\d{1,3}[\.]\d{1,3}[\.]\d{1,3})/;
            return reg.test(this);
        }

    };

    //扩展jquery对象
    var initJqueryExtensions = function (){
        /**
         *  带小数点的数字格式化,小数点位数超过指定位数后四舍五入，不足位数的补0
         *  $.numberFormat(2000000.2345,3)		2,000,000.235
         *  $.numberFormat(2000000,2)			2,000,000.00
         */
        (function ($) {
            $.extend({
                numberFormat:function (num,decimal) {
                    var number;
                    number=(num.toFixed(decimal) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                    return number;
                }
            });
        })(jQuery);
        /**
         * 数字或字符串格式化
         * $.format(2000000010, 3, ',')     2,000,000,010
         * $.format('abcdefghijklmnopqrstuvwxyz', 6, '-')    ab-cdefgh-ijklmn-opqrst-uvwxyz
         */
        (function($) {
            $.extend({
                format : function(str, step, splitor) {
                    str = str.toString();
                    var len = str.length;

                    if(len > step) {
                        var l1 = len%step,
                            l2 = parseInt(len/step),
                            arr = [],
                            first = str.substr(0, l1);
                        if(first != '') {
                            arr.push(first);
                        };
                        for(var i=0; i<l2 ; i++) {
                            arr.push(str.substr(l1 + i*step, step));
                        };
                        str = arr.join(splitor);
                    };
                    return str;
                }
            });
        })(jQuery);
    }


    /**
     * 加入收藏
     * 用法：addFavorite(obj, 'XXX网', location.href);
     */
    var addFavorite = function (obj, title, url){
        if(obj.length < 1){
            return ;
        }
        obj.click(function() {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.indexOf("360se") > -1) {
                alert("由于360浏览器功能限制，请按 Ctrl+D 手动收藏！");
            } else if (ua.indexOf("msie 8") > -1) {
                window.external.AddToFavoritesBar(url, title); //IE8
            } else if (document.all) {
                try {
                    window.external.addFavorite(url, title);
                } catch (e) {
                    alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
                }
            } else if (window.sidebar) {
                window.sidebar.addPanel(title, url, "");
            } else {
                alert('您的浏览器不支持,请按 Ctrl+D 手动收藏!');
            }
        });
    }


    /**
     * 设置为主页
     */
    var setHome = function(obj, url){
        if(obj.length < 1){
            return;
        }
        obj.click(function (){
            try{
                obj[0].style.behavior='url(#default#homepage)';
                obj[0].setHomePage(url);
            }
            catch(e){
                if(window.netscape) {
                    try {
                        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    }
                    catch (e) {
                        alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                    }
                    var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                    prefs.setCharPref('browser.startup.homepage', url);
                }else{
                    alert("您的浏览器不支持，请按照下面步骤操作：1.打开浏览器设置。2.点击设置网页。3.输入："+url+"点击确定。");
                }
            }
        });
    }

    /**
     * 搜狗地图
     * @param address 地址
     * 注：使用时需要同时引入搜狗地图
     */
    function createHaosouMap(address){

        var content = $("#haosouMap").length > 0 ? $("#haosouMap") : $('<div id="haosouMap" style="width:60%; height:70%; box-shadow:3px 3px 10px;border-radius:5px 5px 0 0;overflow:hidden;"><div style="height:36px;background:white;padding:10px;border-bottom:solid 1px #d6d6d6;">'+address+'<span class="close" style="float:right;cursor:pointer;">X</span></div><div id="haosouMap_canv" style="width:100%; height:100%;"></div></div>');
        content.appendTo($("body"));
        malert(content, content.find(".close"), {opacity:0.2});

        var geocoder = new so.maps.Geocoder();
        geocoder.getPoint(address, function(data){
            if(data.status=="10000"){
                var mapOptions = {
                    zoom: 14,
                    center: new so.maps.LatLng(data.result[0].location.lat, data.result[0].location.lng),
                    disableDefaultUI: false,
                    scaleControl: true,
                    mapTypeId: so.maps.MapTypeId.ROADMAP
                };
                var map = new so.maps.Map($("#haosouMap_canv")[0], mapOptions);
                new so.maps.MapTypeControl({
                    map: map,
                    style: 2,
                    align: so.maps.ALIGN.TOP_RIGHT
                });
                new so.maps.NavigationControl({
                    map: map
                });
                for(var i=0; i<data.result.length;i++){
                    var pt = new so.maps.LatLng(data.result[i].location.lat, data.result[i].location.lng);
                    var infowindow = new so.maps.InfoWindow({
                        content: address,
                        map:map
                    });
                    var marker = new so.maps.Marker({map:map, position:pt});
                    infowindow.setPosition(marker);
                    infowindow.open();
                    if(i==0){
                        map.panTo(pt);
                    }
                }
            }else{
            }
        }, "北京");
    }


    /**
     * 页面跳转到指定url
     */
    var locationHref=function(url){
        if(url==null){
            return;
        }
        window.location.href = url;
    }


    /**
     * 頁面刷新N秒跳转主页
     */
    var newLocation=function(n,id){
        if(n+"".isBlank()){
            n = 5;
        }
        var cnt = Number(n);
        lod();
        function lod(){
            if (cnt < 0) {
                locationHref(basePath + "/index.jsp");
            } else {
                var str = "页面<font color=red>" + cnt + "</font>秒后跳转,<a href='"+basePath+"/index.jsp'>点击立即跳转</a>";
                cnt--;
                $("#"+id).html(str)
            }
            setTimeout("lod()", 1000);
        }
    }

    /**
     * 禁用鼠标右键
     */
    var forbiddenContextMenu=function()
    {
        if(document.layers)
        {
            document.captureEvents(Event.MOUSEDOWN);
            document.onmousedown=clickNS4;
            document.onkeydown=OnDeny();
        }
        else if(document.all&&!document.getElementById)
        {
            document.onmousedown=clickIE4;
            document.onkeydown=OnDeny();
        }
        document.oncontextmenu=new Function("return false");
    }
    /**
     * Map对象
     * @returns {Map}
     */
    function Map(){
        this.container = new Object();
    }
    Map.prototype = {
        "put" : function(key, value) {
            this.container[key] = value;
        },
        "get" : function(key) {
            return this.container[key];
        },
        "keySet" : function() {
            var keyset = new Array();
            var count = 0;
            for ( var key in this.container) {
                // 跳过object的extend函数
                if (key == 'extend') {
                    continue;
                }
                keyset[count] = key;
                count++;
            }
            return keyset;
        },
        "size" : function() {
            var count = 0;
            for ( var key in this.container) {
                // 跳过object的extend函数
                if (key == 'extend') {
                    continue;
                }
                count++;
            }
            return count;
        },
        "remove" : function(key) {
            delete this.container[key];
        },
        'toString' : function(){
            var str = "";
            for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
                str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
            }
            return str;
        }
    };


    /**
     * 锚点定位obj(目标对象),n(微调距离),time(动画时长)
     */
    var moveTo=function(obj,n,time){
        $("html,body").animate({scrollTop: obj.offset().top+n}, time);
    }


    /**
     * 溢出隐藏,显示省略号,并添加title属性
     * 用法：添加  show_length="10" 属性，并执行Pingan.textOverHide(obj)方法,参数obj为要验证的dom对象
     */
    var textOverHide=function(obj){
        var textobj=obj||$("[show_length]");
        textobj.removeAttr("title");
        textobj.each(function (){
            if($(this).attr("title")==undefined){
                var t=$(this).html();
                var sl=$(this).attr("show_length");
                if(t.length>sl){
                    $(this).html(t.substring(0,sl)+"...");
                }
                $(this).attr("title",t);
            }
        });
    }


    /**
     * 去除字符串中b标签
     */
    var removeBtag=function(str){
        str = str.replace(/<b>/g,"<font color='red'>");
        str = str.replace(/<\/b>/g,"</font>");
        return str;
    }


    /**
     * 元素自动定位，不被浏览器遮挡
     */
    var autoPosition=function(obj){
        var objRight = $(document).width() - (obj.offset().left + obj.width());
        if(objRight < 0){
            obj.css("margin-left", objRight - 35 + "px");
        };
    }


    /**
     * 创建ContentUtil对象
     */
    var createContentUtil=function(obj){
        /**
         * ContentUtil对象
         */
        function ContentUtil(obj){
            this.obj = obj;
            this.setTitle=function(){
                this.obj.each(function (){
                    $(this).attr("title", $(this).html());
                });
            };
            this.maxLength=function(){
                var util = this;
                this.obj.each(function (){
                    var html = $(this).html();
                    var maxlength = $(this).attr("maxlength");
                    if(maxlength != undefined){
                        $(this).data("html", html);
                        if(html.length > maxlength){
                            $(this).html(html.substring(0, maxlength)+"<a more='on' href='javascript:void(0)'  style='color:#45a9ff;'>...更多</a>");
                            util.maxlengthfilter_bangding($(this).find("[more]"));
                        }
                    }
                });
            }

            this.maxlengthfilter_bangding=function(btn){
                var util = this;
                btn.click(function (){
                    var p = btn.parent();
                    if(btn.attr("more")=='on'){
                        p.html(p.data("html")+"<a more='off' href='javascript:void(0)' style='color:#45a9ff;'>&nbsp收起</a>");
                    }else{
                        p.html(p.data("html").substring(0,p.attr("maxlength"))+"<a more='on' href='javascript:void(0)' style='color:#45a9ff;'>…更多</a>");
                    }
                    util.maxlengthfilter_bangding(p.find("[more]"));
                });
            }
        };

        return new ContentUtil(obj);
    }


    /**
     * 弹窗 context(容器对象),closer(关闭对象),config(json格式配置参数:opacity(透明度),time(时间))
     * malert($("#bankValid"),$("#bankValid").find(".closeImg"),{opacity:0.2});
     */
    function malert(context,closer,config){
        var opacity=0.2,time=300,z=99;
        if(config!=null){
            opacity=config.opacity==null?opacity:config.opacity;
            time=config.time==null?time:config.time;
            z=config["z-index"]==null?z:config["z-index"];
        }
        var maskStr="<div id='malert_mask' style='position:fixed;display:none;z-index:"+z+";top:0;left:0; width:100%; height:100%;background-color:black;filter:alpha(opacity="+(opacity*100)+");-moz-opacity:"+opacity+";opacity:"+opacity+";'></div>";
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


    /**
     * malertInfo('你好','infoMessage warning',{});
     */
    function malertInfo(msg,cl,config){
        var c_s="<div id='malert_Info' class='"+cl+"'>"+msg+"</div>";
        if($("#malert_Info").length!=0){
            $("#malert_Info").remove();
        }
        $("body").append(c_s);
        var fi=600,fo=600,st=1500,c_c=$("#malert_Info");
        if(config!=null){
            fi=config.fadeIn==null?600:config.fadeIn;
            fo=config.fadeOut==null?600:config.fadeOut;
            st=config.showTime==null?1500:config.showTime;
        }
        c_c.css({"top":(($(window).height()-c_c.height())/3)+"px","position":"fixed","z-index":"9999","left":"50%"});
        c_c.css("margin-left","-"+(c_c.width()/2)+"px");
        c_c.fadeIn(fi);
        setTimeout(function(){c_c.fadeOut(fo);}, st);
    }


    /**
     * 对话框 mconfirm("测试",function (){alert(1);},function(){alert(0);},{});
     */
    var mconfirm=function (cS,ok,no,config){
        var opacity=0.2,time=300,z=99;
        if(config!=null){
            opacity=config.opacity==null?opacity:config.opacity;
            time=config.time==null?time:config.time;
            z=config["z-index"]==null?z:config["z-index"];
        }
        var maskStr="<div id='malert_mask' style='position:fixed;display:none;z-index:"+z+";top:0;left:0; width:100%; height:100%;background-color:black;filter:alpha(opacity="+(opacity*100)+");-moz-opacity:"+opacity+";opacity:"+opacity+";'></div>";
        var conStr="<div id='mconfirm'>" +
            "<div class='t'>温馨提示</div>" +
            "<div class='c'>"+cS+"</div>" +
            "<div class='b'><button class='y'>确认</button><button class='n'>取消</button></" +
            "</div>";
        if($("#malert_mask").length!=0){
            $("#malert_mask").remove();
        }
        if($("#mconfirm").length!=0){
            $("#mconfirm").remove();
        }
        $("body").append(maskStr+conStr);
        var mask=$("#malert_mask"),c=$("#mconfirm");
        c.css({"top":(($(window).height()-c.height())/3)+"px","position":"fixed", "z-index":z+1,"left":"50%"});
        c.css("margin-left","-"+(c.width()/2)+"px");
        c.fadeIn(time);mask.fadeIn(time);
        c.find(".y").focus();
        c.find(".b button").click(function (){
            c.fadeIn(time);
            mask.fadeIn(time);
            c.remove();
            mask.remove();
            if($(this).attr("class")=='y'){ok();
            }else{no();}
        });
    }


    var showMsg=function(obj, message) {
        $("#" + obj).text(message);
        $("#" + obj).fadeIn("slow");
        $("#" + obj).fadeOut(2800);
        setTimeout("location.reload()", 1800);
    }



    /**
     * 定位Foot
     */
    var autoFoot=function(){
        if($("#foot").length < 1){
            return;
        }
        $("#foot").removeAttr("style");
        if($("#foot").offset().top + $("#foot").height() < $(window).height()){
            $("#foot").css({position:"absolute", bottom:"0px", width:"100%", margin:"0 auto"});
        }else{
            $("#foot").removeAttr("style");
        }
    }


    /**
     * 通用ajax
     */
    var commonAjax=function(paramObj){
        if(paramObj==null || !paramObj instanceof AjaxObj){
            console.log('params is invalid');
            return;
        }
        $.ajax({
            url: paramObj.url,
            beforeSend:paramObj.beforeSend,
            data: paramObj.jsondata,
            dataType:paramObj.dataType || "*",
            type: paramObj.type || "POST",
            async: paramObj.async || true,
            success: paramObj.successCallback,
            error:paramObj.errorCallback
        });
    }


    /**
     * 图片轮播
     * 示例：new ImageBox($(".fancy")).interval();
     */
    function ImageBox(box){
        this.box = box;
        this.lastBtn = box.find(".fancy-left");
        this.nextBtn = box.find(".fancy-right");
        this.items = this.box.find("li");
        this.nowIndex = 0;
        this.running = true;
        var handler = this;
        this.lastBtn.click(function (){
            handler.nowIndex -- ;
            handler.show();
        });
        this.nextBtn.click(function (){
            handler.nowIndex ++ ;
            handler.show();
        });
        this.box.on({
            "mouseenter":function(){
                handler.running = false;
                handler.lastBtn.fadeIn();
                handler.nextBtn.fadeIn();
            },
            "mouseleave":function (){
                handler.running = true;
                handler.lastBtn.fadeOut();
                handler.nextBtn.fadeOut();
            }
        });
    }
    ImageBox.prototype = {
        show : function (){
            var index = this.nowIndex;
            index = index < 0 ? this.items.length-1 : index;
            index = index > this.items.length-1 ? 0 : index;
            this.nowIndex = index;
            this.items.fadeOut(500);
            this.items.eq(this.nowIndex).fadeIn(500);
        },
        interval : function (){
            var handler = this;
            window.setTimeout(function (){
                if(handler.running){
                    handler.nextBtn.click();
                }
                handler.interval();
            }, 2000);
        }
    };


    /**
     * 按钮绑定到元素，作为元素显隐的开关
     * 示例：new SlideButton($("#areachoose"), $(".area"));
     */
    function SlideButton(btn, content){
        this.btn = btn;
        this.content = content;
        this.mouseOn = false;
        var sb = this;
        this.btn.on({
            "click":function(){
                sb.content.toggle();
            },
            "mouseover":function (){
                sb.mouseOn = true;
                sb.content.fadeIn();
            },
            "mouseleave":function(){
                sb.mouseOn = false;
                sb.hideContent();
            }
        });
        this.content.on({
            "mouseover":function (){
                sb.mouseOn = true;
            },
            "mouseleave":function(){
                sb.mouseOn = false;
                sb.hideContent();
            }
        });
    }
    SlideButton.prototype.hideContent = function (){
        var sb = this;
        window.setTimeout(function(){
            if(!sb.mouseOn){
                sb.content.fadeOut();
            }
        },300);
    }


    /**
     * 提示信息组件
     * @param obj 目标对象
     * @param tip 提示信息显示的位置
     * 示例：new Popover(obj, "right");
     */
    function Popover (obj, tip){
        this.obj = obj;
        obj.tip = tip;
        this.isDefaultPip = true;
        if(!/left|right|top|bottom|innerLeft|innerRight/g.test(tip) && $(tip).length == 1){
            obj.pop = $(tip);
            this.isDefaultPip = false;
        }else{
            obj.pop = $("<div class='form-msg'></div>");
        }
    }
    Popover.prototype = {
        "show" : function (msg){
            this.obj.pop.html(msg).show();
            if(this.isDefaultPip){
                this.position(this.obj.tip);
            }
        },
        "hide" : function (){
            this.obj.pop.hide();
        },
        "position" : function (tip){
            var obj = this.obj;
            obj.parent().append(obj.pop);
            obj.pop.css({
                "position" : "absolute",
                "padding" : "10px",
                "white-space" : "nowrap",
                "color":"red"
            });

            var l = obj[0].offsetLeft, t = obj[0].offsetTop, w = obj[0].offsetWidth, h = obj[0].offsetHeight;
            var popW = obj.pop[0].offsetWidth, popH = obj.pop[0].offsetHeight;

            switch(tip){
                case 'left' :
                    l = l - popW ;
                    t = t + h / 2 - popH / 2;
                    break;

                case 'top' :
                    l = l + popW / 2 ;
                    t = t - popH;
                    break;

                case 'right' :
                    l = l + w ;
                    t = t + h / 2 - popH / 2;
                    break;

                case 'bottom' :
                    l = l + popW / 2 ;
                    t = t + h;
                    break;

                case 'innerLeft' :
                    l = l + w - popW ;
                    t = t + h / 2 - popH / 2;
                    break;

                case 'innerRight' :
                    t = t + h / 2 - popH / 2;
                    break;
            }
            obj.pop.css({"left":l + "px", "top": t + "px"});
        }
    };


    /**
     * 表单元素对象
     * @param obj
     * @returns {FormItem}
     */
    function FormItem(obj,popover){
        this.obj = obj;
        this.type = obj.attr("type");
        this.typemsg = obj.attr("typemsg");
        this.ischeck = obj.attr("ischeck");
        this.name = obj.attr("name");
        this.placeholder = obj.attr("placeholder");
        this.des = obj.attr("des");
        this.ajax = obj.attr("ajax");
        this.ajaxMsg = obj.attr("ajaxmsg")? obj.attr("ajaxmsg") : "验证失败";
        this.compare = obj.attr("compare");
        this.ajaxotherparam = obj.attr("ajaxotherparam");
        this.obj.blur = this.onblur();
        this.errors = new Array();
        if(popover){
            this.popover = popover;
        }else{
            this.popover = new Popover(obj, obj.attr("msgtip")==undefined ? "right" : obj.attr("msgtip"));
        }
        this.obj.keydown = this.onkeydown();
        this.obj.FormItem = this;

    }
    FormItem.prototype = {
        "onkeydown" : function (){
            var item = this;
            item.obj.keydown(function (e){
                var length = item.getLength();
                if(length > Number(item.obj.attr("maxlength"))){
                    e.preventDefault();
                    return false;
                }
            });
        },
        "onblur" : function (){
            var item = this;
            item.obj.blur(function (){
                item.validate();
            });
        },
        "validate" : function (){
            if(this.obj.val() != this.placeholder){
                this.removeError();
                if(!this.checkLength()){
                    return false;
                }
                if(!this.checkType()){
                    return false;
                }
                if(!this.checkAjax()){
                    return false;
                }
                if(!this.checkCompare()){
                    return false;
                }
                this.removeError();
                return true;
            }
        },
        //类型校验
        "checkType" : function (){
            var passed = false;
            var value;
            if(this.type=='checkbox'){
                if(this.ischeck){
                    value = this.obj.is(":checked");
                }else{
                    value = true;
                }

            }else{
                value = this.obj.val().replace(/\s*/g,'');
            }

            if(value && isNaN(Number(this.obj.attr("minlength")))){
                return true;
            }
            if(!this.type || this.type == ''){
                return true;
            }
            var types = new Array();
            if(this.type.indexOf(",") != -1){
                types = this.type.replace(/\s*/g,'').split(",");
            }else{
                types.push(this.type);
            }
            for(var i = 0; i<types.length; i++){
                type = types[i];
                if(type && type != ""){
                    switch (type){
                        case 'email' : passed = value.isEmail(); break;
                        case 'number' : passed = value.isNumber(); break;
                        case 'phone' : passed = value.isPhone(); break;
                        case 'mobile' : passed = value.isMobile(); break;
                        case 'zipcode' : passed = value.isZipCode(); break;
                        case 'domain' : passed = value.isDomain(); break;
                        case 'url' : passed = value.isUrl(); break;
                        case 'password' : passed = value.isPassword(); break;
                        case 'checkbox' : passed = value; break;
                        default : passed = true;
                    }
                }
                if(passed) break;
            }
            if(!passed){
                if(this.typemsg){
                    this.addError(this.typemsg);
                }else{
                    this.addError(2);
                }
            }
            return passed;
        },
        "getLength" : function (){
            var length = this.obj.val().replace(/\s*/g,'').length;
            length = /text|password/.test(this.type)? this.obj.val().length : length;
            return length;
        },
        //长度校验
        "checkLength" : function (){
            var length = this.getLength();
            var des = this.obj.attr("des") ? this.obj.attr("des"):'';
            var minLength = this.obj.attr("minlength");
            var maxLength = this.obj.attr("maxlength");
            if(!isNaN(Number(minLength)) && length < Number(minLength)){
                if(length == 0){
                    this.addError(1);
                }else{
                    this.addError(des+"的最小长度为"+minLength);
                }
                return false;
            }
            if(!isNaN(Number(maxLength)) && length > Number(maxLength)){
                this.addError(des+"的最大长度为"+maxLength);
                return false;
            }
            return true;
        },
        //远程校验。例如：注册时校验用户名是否已存在。
        "checkAjax" : function (){
            var passed = true;
            if(this.ajax){
                var handler = this;
                var data = {};
                data[this.name] = this.obj.val();
                if (this.ajaxotherparam) {
                    $.each(this.ajaxotherparam.split(","), function (i, val) {
                        data[$("#" + val).attr('name')] = $("#" + val).val();
                    });
                }
                $.ajax({
                    url : this.ajax,
                    async : false,
                    data : data,
                    success : function (result){
                        if(result != "true"){
                            handler.addError(3);
                            passed = false;
                        }
                    }
                });
            }
            return passed;
        },
        //两个对象值的比较。例如：注册时密码和重复密码的比较
        "checkCompare" : function (){
            if(!this.compare){
                return true;
            }
            var arr = this.compare.split(",");
            var action = arr[0];
            var msgModel = "";
            switch(action){
                case "==":
                    msgModel = "{0}和{1}不一致";
                    for(var i=1; i<arr.length; i++){
                        var comparedObj = $(arr[i]);
                        if(this.obj.val() != comparedObj.val()){
                            this.addError(msgModel.format(this.des, comparedObj.attr("des")));
                            return false;
                        }
                    }
                    break;
            }
            return true;
        },
        //添加错误提示信息
        "addError" : function (code){
            var des = this.obj.attr("des") ? this.obj.attr("des"):'';
            var msg = "";
            if(isNaN(Number(code))){
                msg = code;
                this.errors.push(0);
            }else{
                switch(code){
                    case 1 : msg = "请输入" + des; break;
                    case 2 : msg = des + "格式不正确"; break;
                    case 3 : msg = this.ajaxMsg; break;
                }
                this.errors.push(code);
            }
            this.popover.show(msg);
        },
        //移除错误提示信息
        "removeError" : function (){
            this.errors = new Array();
            this.popover.hide();
        }
    };


    /**
     * 表单验证控制器
     * @param obj
     * @returns {MForm}
     * 示例：new MForm($(".form1"));
     */
    function MForm(obj,popover){
        this.obj = obj;
        this.isAjax = false; //是否以ajax方式提交
        var form = this;
        obj.submit(function (e){
            for(var i=0;i< obj.items.length;i++){
                var item = obj.items[i];
                item.validate();
                if(item.errors.length > 0){
                    e.preventDefault();
                    return false;
                }
            }
            if(!form.beforeSubmit()){
                e.preventDefault();
                return false;
            }
            if(form.isAjax){
                $.post(form.obj.attr("action"), form.obj.serialize(), function (result){
                    form.afterSubmit(result);
                },form.obj.attr("datatype"));
                e.preventDefault();
                return false;
            }
            return true;
        });
        obj.items = new Array();
        obj.find("[name]").each(function (){
            obj.items.push(new FormItem($(this),popover));
        });
    }
    MForm.prototype = {
        //重置表单
        "reset" : function (){
            this.obj[0].reset();
        },
        //获取表单元素对象
        "getItem" : function (name){
            for(var i = 0; i<this.obj.items.length; i++){
                var item = this.obj.items[i];
                if(item.name == name){
                    return item;
                }
            }
        },
        //提交之前的操作，必要时可重写此方法
        "beforeSubmit" : function (){
            return true;
        },
        //提交之后的操作，必要时可重写此方法，一般用于ajax方式提交表单之后的回调。
        "afterSubmit" : function (result){
            return true;
        }
    };


    /**
     * 选择控制器
     * @param items 目标元素
     * @param isMulti 是否多选
     * @param isMust 是否必选
     * 示例：new SelectHandler($('.topbar-nav-ul li'), true, false);
     * 注：通过为dom元素添加data-mark属性可将选择控制器绑定到元素的显示隐藏。如：data-mark="#divContent"。
     */
    function SelectHandler(items, isMulti, isMust) {
        this.items = items;
        var handler = this;
        items.click(function (e){
            e.preventDefault();
            handler.selected($(this));
            if($(this).hasClass("current")){
                if(isMust && items.filter(".current").length == 1 ){
                    return false;
                }
                $(this).removeClass("current");
            }else{
                if(!isMulti){
                    handler.items.not($(this)).removeClass("current");
                }
                $(this).addClass("current");
            }
            handler.displayMark();
            return true;
        });
    }
    SelectHandler.prototype = {
        "clear" : function (){
            this.items.removeClass("current");
        },
        "selected" : function (item){},
        "displayMark" : function (){
            for(var i=0; i<this.items.length; i++){
                var item = this.items.eq(i);
                if(item.hasClass("current")){
                    $(item.attr("data-mark")).show();
                }else{
                    $(item.attr("data-mark")).hide();
                }
            }
        }
    };


    /**
     * 导航栏工具类
     */
    function NavHandler(items){
        this.items = items;
        items = items || $("[data-nav]");
        for (var i=0; i<items.length; i++){
            var item = items.eq(i);
            var re = new RegExp(item.attr("data-nav"));
            if(re.test(window.location.href)){
                item.addClass("current");
                return;
            }
        }
    }
    NavHandler.prototype.select = function (num){
        this.items.removeClass("current");
        this.items.eq(num).addClass("current");
    };


    /**
     * URL工具类
     */
    function UrlHandler(url){
        this.url = url;
        this.uri = url;
        this.params = new Map();
        this.init();
    }
    UrlHandler.prototype = {
        "init" : function(){
            if(this.url.indexOf("?") == -1){
                return true;
            }
            var tempArray = this.url.split("?");
            this.uri = tempArray[0];
            var paramStr = tempArray[1];
            if(paramStr.indexOf("&") == -1){
                paramStr = [paramStr];
            }else{
                paramStr = paramStr.split("&");
            }
            for(var i=0; i<paramStr.length; i++){
                if(paramStr[i].indexOf("=") == -1){
                    this.params.put(paramStr[i], paramStr[i]);
                    continue;
                }
                var keyVal = paramStr[i].split("=");
                this.params.put(keyVal[0], keyVal[1]);
            }
        },
        "getUri" : function (){
            return this.uri;
        },
        "getParamStr" : function (){
            var paramsStr = "";
            var sign = "";
            for(var i = 0, keys = this.params.keySet(), len = keys.length; i< len; i++){
                paramsStr +=  sign + keys[i] + "=" + this.params.get(keys[i]);
                sign = "&";
            }
            return paramsStr;
        },
        "createUrl" : function(){
            var paramStr = this.getParamStr();
            if(paramStr != ""){
                return this.uri + "?" + paramStr;
            }else{
                return this.uri;
            }
        },
        "turn" : function(){
            window.location.href = this.createUrl();
        }
    };


    /**
     * 分页工具类
     */
    function PageHandler(content, pContent){
        if(content.length < 1 && pContent.length < 1){
            return;
        }
        this.content = content;
        this.pContent = pContent;
        var cfg = eval('(' + this.pContent.attr("config") + ')');
        this.cfg = cfg;
        this.pin = cfg.pin ? cfg.pin : 5;
        this.urlHandler = new UrlHandler(cfg.url);
        this.isAjax = cfg.isAjax == undefined ? true : cfg.isAjax;
        this.autoLoad = cfg.autoLoad == undefined ? true : cfg.autoLoad;
        this.npage = cfg.npage == undefined ? 1 : cfg.npage;
        this.spage = cfg.spage == undefined ? 1 : cfg.spage;
        this.total = cfg.total == undefined ? 0 : cfg.total;
        this.fpage = cfg.fpage == undefined ? false : cfg.fpage;
        this.lpage = cfg.lpage == undefined ? false : cfg.lpage;
        this.isShowRedict = cfg.isShowRedict == undefined ? true : cfg.isShowRedict;
        this.allCursorMarks = cfg.allCursorMarks;
        if(this.content.find("[model]").length > 0){
            this.itemModel = this.content.find("[model]").clone().removeAttr("model").removeAttr("style")[0].outerHTML;
        }
        if(this.isAjax && this.autoLoad){
            this.go(this.npage);
        }else{
            this.createPage();
        }
        var pageHandlers = global.pageHandlers || new Map();
        pageHandlers.put(cfg.name, this);
        global.pageHandlers = pageHandlers;
    }
    PageHandler.prototype = {
        "createPage" : function(){
            var start = this.npage - this.pin > 0 ? this.npage - this.pin : 1;
            var end = start + this.pin * 2 > this.spage ? this.spage : start + this.pin * 2;
            start = end - this.pin * 2 > 0 ? end - this.pin * 2 : 1;
            var pHtml="";
            var pageItemModel = "<a href='javascript:void(0)' class='{0}' page='{1}'>{2}</a>";
            for(var i = start; i <= end; i++){
                var clas = (i==this.npage) ? "current" : "";
                pHtml += pageItemModel.format(clas, i, i);
            }
            //上一页
            if(this.npage > 1){
                pHtml = pageItemModel.format("", Number(this.npage) - 1, "上一页") + pHtml;
                if(this.fpage){
                    pHtml = pageItemModel.format("", 1, "首页") + pHtml;}
            }
            //下一页
            if(this.npage < this.spage){
                pHtml += pageItemModel.format("", Number(this.npage) + 1, "下一页");
                if(this.fpage){
                    pHtml += pageItemModel.format("", Number(this.spage), "尾页");}
            }
            if(this.spage>1&&this.isShowRedict){
                pHtml +="跳到<input type='text' value='"+this.npage+"'>页 &nbsp;<a href='javascript:void(0)'>确定</a>";
            }
            if(this.npage == this.spage && this.npage == 1){
                this.pContent.hide();
                return;
            }
            pHtml="<p>"+pHtml+"</p>";
            this.pContent.html(pHtml);
            this.pContent.show();
            var handler = this;
            this.pContent.find("a").click(function(){
                handler.go($(this).attr("page"));
            });
            this.pContent.find("input").keyup(function () {
                var tmptxt=$(this).val();
                var tpage = tmptxt.replace(/\D|^0/g,'');
                if(tpage&&tpage>handler.spage){
                    tpage=handler.spage;
                }else if(!tpage||tpage<0){
                    tpage=1;
                }
                $(this).val(tpage);
                $(this).next("a").attr("page",tpage);
            });
        },
        "go" : function(page){
            page = Number(page);
            if(this.allCursorMarks&&this.allCursorMarks.length>0){
                this.urlHandler.params.put("mark",this.allCursorMarks[page-1>0?page-1:0]);
            }
            if(page >= 1 && page <= this.spage){
                this.npage = page;
                this.urlHandler.params.put("npage", page);
                this.urlHandler.params.put("total", this.total);
                if(this.isAjax){
                    this.loadData();
                }else{
                    this.urlHandler.turn();
                }
            }
        },
        "onLoaded" : function (){ return true; },
        "loadData" : function (){
            var handler = this;
            $.post(this.urlHandler.getUri(), this.urlHandler.getParamStr(), function (result){
                if(result && result.data){
                    var page = result.data;
                    var list = page.content;
                    handler.content.children().not("[model]").not(handler.pContent).remove();
                    handler.total = page.total;
                    handler.spage = page.totalPages;
                    handler.createPage();
                    for(var i = 0; i < list.length; i++){
                        var itemObj = handler.formateItem(i, handler.itemModel, list[i]);
                        if(typeof itemObj == 'string'){
                            itemObj = itemObj.replace(/null|undefined|NaN/g, ' ');
                        }
                        if(handler.pContent.is(handler.content.children())){
                            handler.pContent.before(itemObj);
                        }else{
                            handler.content.append(itemObj);
                        }
                    }
                }
                handler.checkNone();
                handler.onLoaded(result);
            });
        },
        "checkNone" : function () {
            if(this.cfg.nodata){
                if(this.total > 0) {
                    $(this.cfg.nodata).hide();
                }else {
                    $(this.cfg.nodata).show();
                }
            }
        },
        "formateItem" : function(index, model, item){return '';}
    };




    /**
     * placeholder兼容代码
     * 示例：new PlaceHolder($("#username"));
     */
    function PlaceHolder(obj){
        obj = obj || $("[placeholder]");
        obj.each(function (){
            var obj = $(this);
            var disabled = function (obj, bool){
                if(bool){
                    obj.css("display","none").attr("disabled", true);
                }else{
                    obj.css("display","").attr("disabled", false);
                }
            }
            if(obj.attr("type") != 'password'){
                if(obj.val() == ''){
                    obj.val(obj.attr("placeholder"));
                }
                obj.css("color", "silver").focus(function (){
                    if($.trim(obj.val()) == obj.attr("placeholder")){
                        obj.css("color", obj.css("color","")).val("");
                    }
                }).blur(function (){
                    if($.trim(obj.val()) == ''){
                        obj.css("color", "silver").val(obj.attr("placeholder"));
                    }
                });
            }else{
                var clone = $(obj[0].outerHTML.replace(/password/g, "text"));
                clone.removeAttr("name").removeAttr("id");
                clone.css("color", "silver").val(obj.attr("placeholder"));
                disabled(obj, true);
                obj.parent().append(clone);
                clone.focus(function (){
                    disabled(obj, false);
                    disabled(clone, true);
                    obj.val("").focus();
                });
                obj.blur(function (){
                    if($.trim(obj.val()) == ''){
                        disabled(clone, false);
                        disabled(obj, true);
                    }
                });
            }
        });
    }


	/*
	 * 星星
	 * 需要在页面中添加以下内容，然后调用该方法进行初始化
	 * 示例1：<div class='istar' config='{\"n\":35,\"s\":50,\"size\":12,\"img\":\"${basePath}/images/star.png\"}'></div>
	 * 示例2：<div class='istar' config='{"n":35,"s":50,"size":12,"img":"${basePath}/images/star.png"}'></div>
	 * 注：div标签可替换为label
	 */
    function lightStar(obj){
        obj = obj || $(".istar");
        obj.each(function (){
            try{
                var config=eval('('+$(this).attr("config")+')');
                var n=config["n"]<10?config["n"]:config["n"]/10,s=config["s"]/10,size=config["size"];
                if($(this).parent().attr("class")!="istar"){
                    $(this).css({"width":size*s+"px","height":size-1+"px","background":"url('"+config["img"]+"') 0 -"+size+"px /"+size+"px","cursor":"pointer"});
                    $(this).append($(this).html()==""?$(this).clone(true):"");
                    $(this).find(".istar").css({"width":size*n+"px","background-position":"0 -1px"});
                }
            }catch(e){};
        });
    }

    /**
     * 图片验证码
     */
    function ImgCode(obj) {
        obj.attr("src", basePath + "/kaptcha/code?" + Math.random());
        obj.click(function() {
            $(this).attr("src", basePath + "/kaptcha/code?" + Math.random())
        });
    }


    (function (){
        initJsExtensions();
        initJqueryExtensions();
    })();


    /**
     * 初始化执行
     */
    $(function(){
        autoFoot();
        $(window).resize(function(){
            autoFoot();
        });
    });

    /**
     * 所有要返回的通用方法，变量都写在return中
     */
    Pingan = {
        basePath : basePath,
        addFavorite : addFavorite,
        setHome : setHome,
        ajax : commonAjax,
        locationHref : locationHref,
        newLocation : newLocation,
        forbiddenContextMenu : forbiddenContextMenu,
        moveTo : moveTo,
        textOverHide : textOverHide,
        removeBtag : removeBtag,
        autoPosition : autoPosition,
        createContentUtil : createContentUtil,
        lightStar : lightStar,
        malert : malert,
        malertInfo : malertInfo,
        mconfirm : mconfirm,
        showMsg : showMsg,
        createHaosouMap : createHaosouMap,
        autoFoot : autoFoot,
        Map : Map,
        ImgCode : ImgCode,
        FormItem : FormItem,
        MForm : MForm,
        Popover : Popover,
        SlideButton : SlideButton,
        ImageBox : ImageBox,
        PlaceHolder : PlaceHolder,
        UrlHandler : UrlHandler,
        NavHandler : NavHandler,
        SelectHandler : SelectHandler,
        PageHandler : PageHandler
    };
    return Pingan;
});