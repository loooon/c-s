<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="">
    <meta name="theme-color" content="#ffffff"> 
	<title>催收黄页-凭安信用</title>
</head>
<body>

<div  id="dist" style="background:  rgba(255,255,255,0.8) url(/resources/img/dun/dist-background.png);">
    <div class="" style="  color:white;;z-index: 1;position: absolute; width: 100%;float: left;height: 100px;">
    </div>
    <div class="" style="  color:white;background-color: rgba(255,255,255,0.1);z-index: 1;position: absolute; width: 100%;float: left;height: 100px;">
        <h2 style="    padding-right: 15%;float: right;margin-right: -12%;margin-top: 1%;">催收号码分布热点图</h2>
        <p style="margin-top: 3%;float: right;margin-right: -10%;">手机表示催收，发出点为催收电话区域，飞入点为逾期用户所在逾期</p>
    </div>
        <div id="map_container" style="width:100%;height: 730px;padding-top: 100px;" class=""></div>
        <div  class="" style="float: right;margin-top: -22%;margin-right: 18.5%;width: 440px;position: relative;background-color: rgba(255,255,255,0.1);height: 175px;opacity: 0.4;">
        </div>
    <div style="letter-spacing: 4px;color:white;font-size: 26px;width: 440px;height: 175px;float: right;margin-top: -21%;margin-right: 16%;position: relative;   padding: 20px 20px;line-height: 32px;">
        <p style="">凭安已发现</p>
        <p style="">催收号码<span style="font-size: 30px;font-weight: bold; " id="dunCount">0</span>个</p>
        <p style="">覆盖逾期用户<span style="font-size: 30px;font-weight: bold; " id="coverageCount">0</span>个</p>
    </div>
</div>
<script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
<script src="/resources/js/lib/echarts.min.js"></script>
<script src="/resources/js/lib/china.js"></script>
<script src="/resources/js/lib/countUp.js"></script>
<script type="text/javascript">


    var graph = echarts.init(document.getElementById("map_container"));
    $("#map_container div:first-child").css('left','-8%').css('background-image','url(/resources/img/dun/dist-background.png)');
    var geoCoordMap = {
        '上海': [121.4648,31.2891],
        '新疆': [87.9236,43.5883],
        '甘肃': [103.5901,36.3043],
        '北京': [116.4551,40.2539],
        '江苏': [118.8062,31.9208],
        '广西': [108.479,23.1152],
        '江西': [116.0046,28.6633],
        '安徽': [117.29,32.0581],
        '内蒙古': [111.4124,40.4901],
        '黑龙江': [127.9688,45.368],
        '天津': [117.4219,39.4189],
        '广东': [113.5107,23.2196],
        '四川': [103.9526,30.7617],
        '西藏': [91.1865,30.1465],
        '浙江': [119.5313,29.8773],
        '湖北': [114.3896,30.6628],
        '辽宁': [123.1238,42.1216],
        '山东': [117.1582,36.8701],
        '海南': [110.3893,19.8516],
        '河北': [114.4995,38.1006],
        '福建': [119.4543,25.9222],
        '青海': [101.4038,36.8207],
        '陕西': [109.1162,34.2004],
        '河南': [113.4668,34.6234],
        '重庆': [107.7539,30.1904],
        '宁夏': [106.3586,38.1775],
        '吉林': [125.8154,44.2584],
        '湖南': [113.0823,28.2568],
        '山西':[112.3352,37.9413],
        '云南': [102.9199,25.4663],
        '贵州': [106.6992,26.7682],
    };
    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var dataItem = data[i];
            var fromCoord = geoCoordMap[dataItem[0].name];
            var toCoord = geoCoordMap[dataItem[1].name];
            var fromData = dataItem[0].value;
            if (fromCoord && toCoord && fromData) {
                res.push({
                    fromName: dataItem[0].name,
                    toName: dataItem[1].name,
                    fromData:dataItem[0].value,
                    coords: [fromCoord, toCoord,fromData]
                });
            }
        }
        return res;
    };
//    var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
//    var planePath = 'path://1 XR 414.0000 545.0000 m 358.0000 481.0000 l 427.0000 330.0000 l 612.0000 226.0000 l 670.0000 280.0000 l 595.0000 324.0000 l 559.0000 299.0000 l 478.0000 352.0000 l 430.0000 424.0000 l 454.0000 458.0000 l 414.0000 545.0000 ln';
    var planePath = 'image:///resources/img/dun/dist-tel.png';
    $(document).ready(function () {
        var options = {
            useEasing : true,
            useGrouping : true,
            separator : ',',
            decimal : '.',
            prefix : '',
            suffix : ''
        };
        updateDistData();
        setInterval(updateDistData,5000);

    });


    var countUp;
    var coverageCountUp;
    var options = {
        useEasing : true,
        useGrouping : true,
        separator : ',',
        decimal : '.',
        prefix : '',
        suffix : ''
    };

    var updateDistData =function ()
    {
        $.ajax({
            url: "/dun/dist",
            method: "post",
//        data: formData,
            dataType: 'json',
            async: true,
            success: function (data)
            {
                if(data.result=200)
                {
                    dist_data_map(data.data.dunNumberDistModelsTotal);

                    var count = data.data.result.find;
                    var coverageCount = data.data.result.coverage;
                    if(countUp){
                        countUp.update(count);
                    }else{
                        countUp  = new CountUp("dunCount", 0, count, 0, 1.2, options);
                    }
                    countUp.start();

                    if(coverageCountUp){
                        coverageCountUp.update(coverageCount);
                    }else{
                        coverageCountUp  = new CountUp("coverageCount", 0, coverageCount, 0, 1.2, options);
                    }
                    coverageCountUp.start();
                }


            },
            error:function ()
            {

            }
        });
    }


    var dist_data_map = function (data)
    {
        var data =[data];

        var convert_data=convertData(data[0]);
        var color = ['#a6c84c', '#ffa022', '#46bee9'];
        var series = [{
            name: '飞机飞入为被催收号码区域',
            type: 'lines',
            zlevel: 1,
            effect: {
                show: true,
                period: 6,
                trailLength: 0.7,
                color: '#fff',
                symbolSize: 1
            },
            lineStyle: {
                normal: {
                    color: 'orange',
                    width: 0,
                    curveness: 0.2
                }
            },
            data: convert_data
        },
            {
                name: '上海',
                type: 'lines',
                zlevel: 2,
                symbol: ['none', 'arrow'],
                symbolSize: 6,
                tooltip: {
                    show: false
                },
                effect: {
                    show: true,
                    period: 6,
                    trailLength: 0,
                    symbol: planePath,
                    symbolSize: 15,
                    color:'red',
                },

                lineStyle: {
                    normal: {
//                            color: 'rgb(60, 130, 241)',
                            color: 'orange',
                  /*      color: function (params)
                        {
                            if (params.data.fromData <= 30)
                            {
                                params.color = 'green';
                                return params.color;
                            }
                            else if (params.data.fromData <= 50)
                            {
                                params.color = 'orange';
                                return params.color;
                            }
                            else if (params.data.fromData <= 70)
                            {
                                params.color = 'blue';
                                return params.color;
                            }
                            else if (params.data.fromData <= 90)
                            {
                                params.color = 'pink';
                                return params.color;
                            }
                            else
                            {
                                params.color = 'red'
                                return params.color;
                            }
                        },*/
                        width: 1,
                        opacity: 0.6,
                        curveness: 0.2
                    }
                },
                data: convert_data
            },
            {
                name: '小飞机表示催收，发出点为催收电话区域，飞入点为逾期用户所在逾期',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                zlevel: 2,
                rippleEffect: {
                    period: 6,
                    scale: 5,
                    brushType: 'stroke'
//                        brushType: 'fill'
                },


                label: {
                    normal: {
                        show: false,
                        position: 'right',
                        formatter: '{b}'
                    }
                },
                symbolSize: function (val)
                {
//                        return val[2] / 8;
                    return val[2] / 4;
                },
                itemStyle: {
                    normal: {
                        color:'orange',
                        /*color: function (params)
                        {
                            if (params.value[2] <= 30)
                            {
                                params.color = 'green';
                                return params.color;
                            }
                            else if (params.value[2] <= 50)
                            {
                                params.color = 'orange';
                                return params.color;
                            }
                            else if (params.value[2] <= 70)
                            {
                                params.color = 'blue';
                                return params.color;
                            }
                            else if (params.value[2] <= 90)
                            {
                                params.color = 'pink';
                                return params.color;
                            }
                            else
                            {
                                params.color = 'red'
                                return params.color;
                            }
                        }*/
                    }, emphasis: {
//                            opacity:0.5,
                        borderWidth: 2,
//                            borderColor:'purple',
                        borderType: 'dashed',
                        shadowBlur: 20,
//                            shadowColor:'red',
                    },
                },
                data: data[0].map(function (dataItem)
                {
                    return {
                        name: dataItem[0].name,
                        value: geoCoordMap[dataItem[0].name].concat([dataItem[0].value])
                    };
                })
            }];
      /*  [data].forEach(function (item, i)
        {
            series.push();
        });*/


        option = {
//            backgroundColor: '#404a59',
//            backgroundColor: 'RGB(60,130,241)',
//            backgroundColor: 'rgb(32,88,179)',
            title : {
//                text: '催收号码分布热点图',
//                subtext: '小飞机表示催收，发出点为催收电话区域，飞入点为逾期用户所在逾期',
                left: 'center',
                top:'40',
                subtextStyle:{
                    color:'#fff',
//                    fontWeight:600,
                    top: '20%',
                    left: '40%',
                },
                textStyle : {
                    color: '#fff'
                }
            },
            tooltip : {
                show:false,
                trigger: 'item',
                formatter: function (params)
                {
                    return '<em style="color:' + params.color + ';font-size:20px">' + params.data.name + '</em>' +
                        ',共呼叫:' +
                        '<em style="color:' + params.color + ';font-size:20px">' + params.data.value[2] + '次</em>';
                }
            },
            legend: {
                orient: 'vertical',
                top: '10%',
                left: '40%',
//                data:['小飞机表示催收，发出点为催收电话区域，飞入点为逾期用户所在逾期','feiji'],
                textStyle: {
                    color: '#fff'
                },
//                selectedMode: 'single'
            },
            geo: {
                map: 'china',
                label: {
                    emphasis: {
                        show: true,
                        textStyle:{
                            color:'#fff',
                            fontWeight:600,
                            fontSize:18
                        },
                    }
                },
                roam: true,
                itemStyle: {
                    normal: {
                        areaColor: '#323c48',
//                        areaColor: 'RGB(60,130,241)',
                        areaColor: 'rgb(32,88,179)',
                        borderColor: '#404a59'
//                        borderColor: 'purple'
                    },
                    emphasis: {
                        areaColor: '#2a333d'
//                        areaColor: 'white'
                    }
                }
            },
            series: series
        };
        graph.setOption(option,true);
    };


</script>
</body>

</html>
