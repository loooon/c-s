
RSF.regist("Credit.User.ConsumeHistory");
Credit.User.ConsumeHistory =  function(){
    this.myChartMap= echarts.init(document.getElementById('consumechart'));
    this.option={};
    this.dataSeries=[];
    this.dataTime=[];
    this.dateSelectTitle='过去7日';
    this.sDate=new Date();
    this.eDate=new Date();
}

Credit.User.ConsumeHistory.prototype = {
    'init':function(){
        this.eDate.setDate(this.eDate.getDate());
        this.sDate.setDate(this.sDate.getDate());
        var endDate=new Date();
        endDate.setDate(endDate.getDate()-1);
        endDate=this.formatDate(endDate,'yyyy-MM-dd hh:mm:ss');
        var startDate=new Date();
        startDate.setDate(startDate.getDate()-365);
        startDate=this.formatDate(startDate,'yyyy-MM-dd hh:mm:ss');
        new SelectHandler($('.all-time li'), false, true);
        $('.week').on('click',function (e) {
            var start=new Date();
            var end=new Date();
            start.setDate(start.getDate()-8);
            end.setDate(end.getDate()-1);
            this.dateSelectTitle="过去7日";
            this.getDateData(start,end);
        }.bind(this));
        $('.month').on('click',function (e) {
            var start=new Date();
            var end=new Date();
            start.setDate(start.getDate()-31);
            end.setDate(end.getDate()-1);
            this.dateSelectTitle="过去30日";
            this.getDateData(start,end);
        }.bind(this));
        $('#datetimepicker').bind('click', function(e) {
            $('.date-picker').toggle();
            this.datepickerInit(startDate,endDate);
            $(document).one("click", function(){
                $(".date-picker").hide();
            });
            e.stopPropagation();
            return false;
        }.bind(this));
        $(".date-picker").on("click", function(e){
            e.stopPropagation();
        });
        $('.date-picker input.cancel').on('click', function() {
            $(".date-picker").hide();
        });
        $('.date-picker input.sure').on('click', function() {
            $('.date-picker').hide();
            this.dateSelectTitle=this.formatDate(this.sDate,'yyyyMMdd')+"-"+this.formatDate(this.eDate,'yyyyMMdd');
            this.getDateData(this.sDate,this.eDate);
        }.bind(this));

        this.option = {
            title : {
                text: '消费记录',
                subtext: '过去7日'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['费用'],
                selectedMode:false
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : [0]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'费用',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:[0]
                }
            ]
        };
        this.myChartMap.setTheme(EcahrtTheme.bluetheme);
        this.myChartMap.setOption(this.option);
        this.chartSet();

    },
    'getDateData':function (sDate,eDate) {
        var sd=this.formatDate(sDate,'yyyyMMdd');
        var ed=this.formatDate(eDate,'yyyyMMdd');
        $.getJSON('',{
            action:'get_charts_data',
            start_date:sd,
            end_date:ed
        },function (result) {
            if(result.data_value.length>0){
                this.dataSeries=result.data_value;
                this.dataTime=result.x;
                this.chartSet();
            }
        }.bind(this));
    },
    'formatDate':function (date,format) {
        var paddNum = function(num){
            num += "";
            return num.replace(/^(\d)$/,"0$1");
        }
        //指定格式字符
        var cfg = {
            yyyy : date.getFullYear() //年 : 4位
            ,yy : date.getFullYear().toString().substring(2)//年 : 2位
            ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
            ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
            ,d  : date.getDate()   //日 : 如果1位的时候不补0
            ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
            ,hh : date.getHours()  //时
            ,mm : date.getMinutes() //分
            ,ss : date.getSeconds() //秒
        }
        format || (format = "yyyy-MM-dd");
        return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
    },
    'datepickerInit': function(startDate,endDate) {
        $('#datepickerStart').datetimepicker({
            format: 'yyyy-mm-dd',
            startView:2,
            minView: 2,
            viewSelect:2,
            endDate:endDate,
            startDate:startDate,
            language:  'zh-CN'
        }).on('changeDate.datepicker.amui', function(event) {
            if (event.date.valueOf() > this.eDate.valueOf()) {
                console.log('开始日期应小于结束日期');
            } else {
                this.sDate = new Date(event.date);
            }
            $('#datepickerStart').datetimepicker('update',this.sDate);
            // $('#datepickerStart').datepicker('close');
        }.bind(this));
        $('#datepickerEnd').datetimepicker({
            format: 'yyyy-mm-dd',
            startView:2,
            minView: 2,
            viewSelect:2,
            endDate:endDate,
            startDate:startDate,
            language:  'zh-CN'
        }).on('changeDate.datepicker.amui', function(event) {
            if (event.date.valueOf() < this.sDate.valueOf()) {
                console.log('结束日期应大于开始日期');
            } else {
                this.eDate = new Date(event.date);
            }
            $('#datepickerEnd').datetimepicker('update',this.eDate);

        }.bind(this));

        $('#datepickerStart').datetimepicker('show');
        $('#datepickerEnd').datetimepicker('show');
    },
    'chartSet':function () {
        this.myChartMap.clear();
        this.myChartMap.setOption(this.option);
        this.myChartMap.setOption(
            {
                title : {
                    subtext: this.dateSelectTitle
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : this.dataTime
                    }
                ],
                series : [
                    {
                        name:'费用',
                        type:'line',
                        smooth:true,
                        itemStyle: {normal: {areaStyle: {type: 'default'}}},
                        data:this.dataSeries
                    }
                ]
            }
        );
    },
    'setChartsData':function (data) {
        this.dataTime=data.x;
        this.dataSeries=data.data_value;
    }
}

