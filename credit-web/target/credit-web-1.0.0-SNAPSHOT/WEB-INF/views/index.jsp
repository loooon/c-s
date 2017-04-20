<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>凭安信用</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link href="/resources/css/index.css" rel="stylesheet" type="text/css" />
    <script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
    <script src="/resources/js/lib/bootstrap.min.js"></script>
    <script src="/resources/js/common.js"></script>
</head>
<body>
<div class="layout nav">
    <h1><img src="/resources/img/dun/logo1.png"></h1>
    <ul id="nav_toggle_class">
        <a href="/" class="current"><li>首页</li></a>
        <a href="/dun"><li>催收黄页</li></a>
        <a href="/credit"><li>信贷统计</li></a>
        <a href="/grayscale"><li>染黑度</li></a>
        <a href="/about"><li>关于我们</li></a>
    </ul>
</div>
<%--<div class="mod-index">
    <div class="mod-banner">
        <div class="banner-slide" style="width: 5000px; overflow: hidden; height: 396px;position: relative;">
            <a href="#" class="banner-il"><img src="/resources/img/dun/index-il.png"></a>
            <div class="slideBox" data-role="scrollContent" id="scrollDiv" style="position: absolute; overflow: hidden; width: 3402px; transition-duration: 0.5s; transform: translate3d(-400px, 0px, 0px); backface-visibility: hidden; left: 0px;margin-left: -399px;">
                <div mark='lightShow' name="lightPage1" style="overflow: hidden;float: left;">
                    <a class=" "  data-monitor="home_lunbo_lb1" style="width: 100%;height: 100%; background-image: url(/resources/img/dun/ban01.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/rush/item?item_id=58dd0ba2e059bf27f4064e2d" target="_blank"> </a>
                </div>
                <div mark='lightShow' name="lightPage2" style="overflow: hidden;float: left;">
                    <a class=" " data-monitor="home_lunbo_lb2" style="width: 100%;height: 100%;background-image: url(/resources/img/dun/ban01.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/rush/item?item_id=58dd0ba2e059bf27f4064e2d" target="_blank"> </a>
                </div>
                <div mark='lightShow' name="lightPage3" style="overflow: hidden;float: left;">
                    <a class=" " data-monitor="home_lunbo_lb3" style="width: 100%;height: 100%; background-image: url(/resources/img/dun/ban01.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/rush/item?item_id=58dd0ba2e059bf27f4064e2d" target="_blank"> </a>
                </div>
                <div mark='lightShow' name="lightPage4" style="overflow: hidden;float: left;">
                    <a class="" data-monitor="home_lunbo_lb4" style="width: 100%;height: 100%;background-image: url(/resources/img/dun/ban01.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/rush/item?item_id=58dd0ba2e059bf27f4064e2d" target="_blank"> </a>
                </div>
                &lt;%&ndash;<a class="slider-film js-lazyload" data-monitor="home_lunbo_lb2" style="display: none; background-image: url(/resources/img/dun/ban02.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/rush/item?item_id=58cf7433215ea017ee0ed277" target="_blank" data-size="_448_" > </a>&ndash;%&gt;
                &lt;%&ndash;<a class="slider-film js-lazyload" data-monitor="home_lunbo_lb3" style="display: inline; background-image: url(/resources/img/dun/ban03.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/shop/item?item_id=580da193215ea00e23b1c827" target="_blank" data-size="_448_"> </a>&ndash;%&gt;
                &lt;%&ndash;<a class="slider-film js-lazyload" data-monitor="home_lunbo_lb4" style="display: none; background-image: url(/resources/img/dun/ban04.jpg); background-position: 50% 50%; background-repeat: no-repeat;" href="http://mall.360.cn/shop/item?item_id=58462ee2eac4991082836e31" target="_blank" data-size="_448_"> </a>&ndash;%&gt;
            </div>
            <div class="slide-point" data-monitor="home_lunbo_change" style="display: block; margin-left: -69px;">
                <a href="javascript:;" class=""></a>
                <a href="javascript:;" class=""></a>
                <a href="javascript:;" class="curr-point"></a>
                <a href="javascript:;" class=""></a>
            </div>
            <a href="#" class="banner-ir"><img src="/resources/img/dun/index-ir.png"></a>
        </div>
    </div>
</div>--%>


<%--<a href="javascript:void(0);" id="kitchenLeft"  data-nav="1"   class="banner-il" style="position: absolute;z-index: 1;left:16%;top:38%;"><img src="/resources/img/dun/index-il.png"></a>--%>
<%--<a href="javascript:void(0);" id="kitchenRight"  data-nav="2"  class="banner-il" style="position: absolute;z-index: 1;left:92%;top:38%;"><img src="/resources/img/dun/index-ir.png"></a>--%>


<%--<div id="slide_circle" class="slide-point" data-monitor="home_lunbo_change"
     style="display: block;position: absolute;left: 50%;top: 48%;z-index: 1;margin-left: -69px;">
    <a href="javascript:;" class=""></a>
    <a href="javascript:;" class=""></a>
    <a href="javascript:;" class="curr-point"></a>
<a href="javascript:;" class=""></a>
</div>--%>


<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
        <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="/resources/img/dun/ban01.jpg" alt="First slide">
            <%--<div class="carousel-caption">标题 1</div>--%>
        </div>
        <div class="item">
            <img src="/resources/img/dun/ban02.jpg" alt="Second slide">
            <%--<div class="carousel-caption">标题 2</div>--%>
        </div>
        <div class="item">
            <img src="/resources/img/dun/ban03.jpg" alt="Third slide">
            <%--<div class="carousel-caption">标题 3</div>--%>
        </div>
        <div class="item">
            <img src="/resources/img/dun/ban04.jpg" alt="Third slide">
            <%--<div class="carousel-caption">标题 3</div>--%>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev"></a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next"></a>
</div>
<!-- end mod-index-->
<div class="layout mod-main">
    <a href="/dun">
        <img src="/resources/img/dun/indexc.png">
        <span>催收黄页</span>
    </a>
    <a href="/credit">
        <img src="/resources/img/dun/indexx.png">
        <span>信贷统计</span>
    </a>
    <a href="/grayscale">
        <img src="/resources/img/dun/indexh.png">
        <span>染黑度</span>
    </a>
</div>
<!-- end mod-main-->
<div class="footer">
    <p>上海凭安征信服务有限公司 版权所有 沪ICP备12039960号-19</p>
</div>

<script type="text/javascript">
    new NavHandler($("#nav_toggle_class").find("[data-nav]"));
</script>
</body>
</html>