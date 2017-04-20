<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>report</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" rev="stylesheet" href="/resources/css/report.css" type="text/css"/>
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>凭安染黑度报告</h2>
		</div>
		<div class="content">
			<h2 class="h2-infor"><span>编号：<i>${serialNumber}</i></span><span>报告时间：<i>${reportTime}</i></span><span class="print" onclick="window.print();">打印</span></h2>
			<div class="layout">
				<div class="tb-block">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th colspan="4">申请人信息</th>
						</tr>
						<tr>
							<td class="td1">姓名</td>
							<td class="td2">${grayscaleModel.basicInfo.uname}</td>
							<td class="td1">身份证号</td>
							<td class="td2">${grayscaleModel.basicInfo.idcard}</td>
						</tr>
						<tr class="bg">
							<td class="td1">手机号</td>
							<td class="td2">${grayscaleModel.basicInfo.phone}</td>
							<td class="td1">年龄</td>
							<td class="td2">${grayscaleModel.basicInfo.age}</td>
						</tr>
						<tr>
							<td class="td1">身份证是否有效</td>
							<td class="td2">${grayscaleModel.basicInfo.effectiveIdcard}</td>
							<td class="td1">运营商</td>
							<td class="td2">${grayscaleModel.basicInfo.operator}</td>
						</tr>
						<tr class="bg">
							<td class="td1">手机归属地</td>
							<td class="td2">${grayscaleModel.basicInfo.phoneOwnership}</td>
							<td class="td1"></td>
							<td class="td2"></td>
						</tr>
					</table>
				</div>
				<div class="right-block">
					<span style="padding-left: 15px;">染黑分</span>
					<div class="process">
						<div class="pro-infor">
							<a href="javascript:void(0);" id="tips_id" ><img src="/resources/img/report/icon4.png"></a>
							<div class="pro-main" style="display: none;">
								<p>＜100分，逾期风险极大</p>
								<p>100分~250分，有一定的逾期风险</p>
								<p>＞250分，逾期风险极小</p>
							</div>
						</div>
						<h4 class="pro-h1" id="black_dyeing_id">0</h4>

							<%--<h4 class="pro-h2">逾期风险极小</h4>--%>
						<c:if test="${not empty grayscaleModel.attributeTag.grayscaleScore}">
							<c:choose>
								<c:when test="${grayscaleModel.attributeTag.grayscaleScore > 250}">
									<p class="pro-h3">＞250分，逾期风险极小</p>
								</c:when>
								<c:when test="${100 <= grayscaleModel.attributeTag.grayscaleScore && grayscaleModel.attributeTag.grayscaleScore <= 250 }">
									<p class="pro-h3">100分~250分，有一定的逾期风险</p>
								</c:when>
								<c:otherwise>
									<p class="pro-h3">＜100分，逾期风险极大</p>
								</c:otherwise>
							</c:choose>
						</c:if>

						<div class="pro-score">
							<%--<img src="images/score.png">--%>

								<div id="wrapper">
									<div id="qiu_pause" class="loader-container">
										<div class="meter" style="display: none;">0</div>
										<span id="bar" class="runner"></span>
									</div>
								</div>

								<div id="grad1" >
									<div style="position:relative;float: left;width: 33.3%;height: 14px;   border-right: 1px solid white;"></div>
									<div style="position:relative;float: left;width: 50%;height: 14px;   border-right: 1px solid white;"><i style="position: absolute;top: 23px;left:-16px;">100</i></div>
									<div style="position:relative;float: left;width: 33.3%;  height: 14px;   "><i style="position: absolute;top: 8px;left:198px;">250</i></div>
								</div>

						</div>

					</div>
				</div>
			</div>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="4">用户属性标签</th>
					</tr>
					<tr>
						<td class="td3">标签项</td>
						<td class="td4">标签值</td>
						<%--<td class="td5">依据</td>--%>
						<td class="td3">标签项</td>
						<td class="td4">标签值</td>
						<%--<td class="td5">依据</td>--%>
					</tr>
					<tr class="bg">
						<td class="td3">朋友圈区域</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.friendsCircle}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">平均通话时间</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.avgDurationStr}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr>
						<td class="td3">电话活跃度</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.active}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">最短通话间隔时间</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.minCallTimeIntervalStr}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr class="bg">
						<td class="td3">电话稳定度</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.stability}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">最长通话间隔时间</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.maxCallTimeIntervalStr}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr>
						<td class="td3">联系号码数量</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.contactCount}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">平均通话间隔时间</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.avgCallTimeIntervalStr}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr class="bg">
						<td class="td3">存在周期性联系的号码数量</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.periodicContactCount}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">主叫一线城市电话通话统计</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.callOutFirstTierCitiesCount}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr>
						<td class="td3">月均联系个数</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.monthAvgContactCount}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3">通话频率最高的时间段</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.maxCallTimeSlot}</span></td>
						<%--<td class="td5">文字说明</td>--%>
					</tr>
					<tr class="bg">
						<td class="td3">月均通话次数</td>
						<td class="td4"><span>${grayscaleModel.attributeTag.monthAvgCallCount}</span></td>
						<%--<td class="td5">文字说明</td>--%>
						<td class="td3"></td>
						<td class="td4"></td>
						<%--<td class="td5"></td>--%>
					</tr>
				</table>
			</div>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="9">详单统计<em class="tag">(详单中共有${grayscaleModel.attributeTag.contactCount}个联系人)</em></th>
					</tr>
					<tr>
						<td class="td6">号码</td>
						<td class="td7">行业类型</td>
						<td class="td7">需求类型</td>
						<td class="td7">联系总时长</td>
						<td class="td8">联系次数</td>
						<td class="td8">主叫次数</td>
						<td class="td8">被叫次数</td>
						<td class="td6">是否存在周期性联系</td>
						<td class="td7">互动标识</td>
					</tr>

                    <c:forEach var="risk" items="${grayscaleModel.riskStat}" varStatus="index">
                        <c:if test="${index.index%2!=0}">
                            <tr class="bg">
                        </c:if>
                            <c:if test="${index.index%2==0}">
                    <tr>
                        </c:if>
                        <td class="td6">
                            <em class="number">${risk.number}</em>
                            <em class="addr">${risk.ownership}</em>
                        </td>
                        <td class="td7"><em class="${risk.demandClass} ${risk.orgClass}">${risk.orgType}</em></td>
                        <td class="td7"><em class="${risk.demandClass}">${risk.demandType}</em></td>

                            <td class="td7">${risk.callDurationStr}</td>
                            <td class="td8">${risk.callCount}</td>
                            <td class="td8">${risk.callOutCount}</td>
                            <td class="td8">${risk.callInCount}</td>
                            <td class="td6">${risk.periodicallyContact}</td>
                            <td class="td7">${risk.callFrequency}</td>
                        </tr>
                    </c:forEach>
				</table>
			</div>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="8">联系人区域统计<em class="tag">(详单中共涉及${fn:length(grayscaleModel.contactAreaStat)}个地区)</em></th>
					</tr>
					<tr>
						<td class="td9">地区</td>
						<td class="td9">联系人个数</td>
						<td class="td9">主叫次数</td>
						<td class="td9">被叫次数</td>
						<td class="td9">主叫总时长</td>
						<td class="td9">被叫总时长</td>
						<td class="td9">主叫比率</td>
						<td class="td9">被叫比率</td>						
					</tr>
                    <c:forEach var="contactArea" items="${grayscaleModel.contactAreaStat}" varStatus="index">
                    <c:if test="${index.index%2!=0}">
                    <tr class="bg">
                        </c:if>
                        <c:if test="${index.index%2==0}">
                    <tr>
                        </c:if>
                            <td class="td9">${contactArea.area}</td>
                            <td class="td9">${contactArea.numberCount}</td>
                            <td class="td9">${contactArea.callOutCount}</td>
                            <td class="td9">${contactArea.callInCount}</td>
                            <td class="td9">${contactArea.callOutDurationStr}</td>
                            <td class="td9">${contactArea.callInDurationStr}</td>
                            <td class="td9">${contactArea.callOutRatio}</td>
                            <td class="td9">${contactArea.callInRatio}</td>
                        </tr>
                        </c:forEach>
					
				</table>
			</div>
            <c:set var="callDurationStat" value="${grayscaleModel.callDurationStat}"/>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="2">通话时间段统计</th>
					</tr>
					<tr>
						<td class="td10">
							<div class="time time-bg1">
								<h4>工作日</h4>
								<h5>联系人个数：<span>${callDurationStat.workingDay.numberCount}</span></h5>
							</div>
							<div class="time-block time-border">
								<h4>主叫次数：<span>${callDurationStat.workingDay.callOutCount}</span></h4>
								<h4>主叫总时长：<span>${callDurationStat.workingDay.callOutDurationStr}</span></h4>
								<h4>主叫比率：<span>${callDurationStat.workingDay.callOutRatio}</span></h4>
							</div>
							<div class="time-block">
								<h4>被叫次数：<span>${callDurationStat.workingDay.callInCount}</span></h4>
								<h4>被叫总时长：<span>${callDurationStat.workingDay.callInDurationStr}</span></h4>
								<h4>被叫比率：<span>${callDurationStat.workingDay.callInRatio}</span></h4>
							</div>							
						</td>
						<td class="td10 td10-pad">
							<div class="time time-bg2">
								<h4>休息日</h4>
								<h5>联系人个数：<span>${callDurationStat.weekend.numberCount}</span></h5>
							</div>
                            <div class="time-block time-border">
                                <h4>主叫次数：<span>${callDurationStat.weekend.callOutCount}</span></h4>
                                <h4>主叫总时长：<span>${callDurationStat.weekend.callOutDurationStr}</span></h4>
                                <h4>主叫比率：<span>${callDurationStat.weekend.callOutRatio}</span></h4>
                            </div>
                            <div class="time-block">
                                <h4>被叫次数：<span>${callDurationStat.weekend.callInCount}</span></h4>
                                <h4>被叫总时长：<span>${callDurationStat.weekend.callInDurationStr}</span></h4>
                                <h4>被叫比率：<span>${callDurationStat.weekend.callInRatio}</span></h4>
                            </div>
						</td>
					</tr>
				</table>
			</div>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="6">主叫联系人TOP10统计</th>
					</tr>
					<tr>
						<td class="td11">联系人号码</td>
						<td class="td12">主叫次数</td>
						<td class="td12">主叫总时长</td>
						<td class="td12">主叫比率</td>
						<td class="td12">是否存在周期性联系</td>
						<td class="td11">互动标识</td>
					</tr>

          <c:forEach var="topCallOutStat" items="${grayscaleModel.topCallOutStat}" varStatus="index">
                    <c:if test="${index.index%2!=0}">
                         <tr class="bg">
                    </c:if>
                    <c:if test="${index.index%2==0}">
                         <tr>
                    </c:if>
                        <td class="td11"><em class="n${index.index+1}">${index.index+1}</em>${topCallOutStat.number}</td>
                        <td class="td12">${topCallOutStat.callOutCount}</td>
                        <td class="td12">${topCallOutStat.callOutDurationStr}</td>
                        <td class="td12">${topCallOutStat.callOutRate}</td>
                        <td class="td12">${topCallOutStat.periodicallyContact}</td>
                        <td class="td11">${topCallOutStat.callFrequency}</td>
                        </tr>
          </c:forEach>

				</table>
			</div>
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="6">被叫联系人TOP10统计</th>
					</tr>
					<tr>
						<td class="td11">联系人号码</td>
						<td class="td12">被叫次数</td>
						<td class="td12">被叫总时长</td>
						<td class="td12">被叫比率</td>
						<td class="td12">是否存在周期性联系</td>
						<td class="td11">互动标识</td>
					</tr>

                    <c:forEach var="topCallInStat" items="${grayscaleModel.topCallInStat}" varStatus="index">
                        <c:if test="${index.index%2!=0}">
                            <tr class="bg">
                        </c:if>
                        <c:if test="${index.index%2==0}">
                            <tr>
                        </c:if>
                        <td class="td11"><em class="n${index.index+1}">${index.index+1}</em>${topCallInStat.number}</td>
                        <td class="td12">${topCallInStat.callInCount}</td>
                        <td class="td12">${topCallInStat.callInDurationStr}</td>
                        <td class="td12">${topCallInStat.callInRate}</td>
                        <td class="td12">${topCallInStat.periodicallyContact}</td>
                        <td class="td11">${topCallInStat.callFrequency}</td>
                        </tr>
                    </c:forEach>

				</table>
			</div>
            <c:if test="${not empty grayscaleModel.contactCheck}">
			<div class="layout table">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th colspan="3">联系人验真<em class="tag">(共有${fn:length(grayscaleModel.contactCheck)}个联系人)</em></th>
					</tr>
                    <c:forEach var="contactCheck" items="${grayscaleModel.contactCheck}" varStatus="index">
                        <c:if test="${index.index%3==0}">
                            <tr>
                        </c:if>
                        <c:if test="${index.index%2!=0}">
                            <td class="td13 td13-bg">
                        </c:if>
                        <c:if test="${index.index%2==0}">
                            <td class="td13">
                        </c:if>
                            <div class="link-left">
                                <img src="/resources/img/report/icon${index.index%3+1}.png">
                                <h4>姓名：<span>${contactCheck.name}</span></h4>
                                <h4>关系：<span>${contactCheck.relation}</span></h4>
                            </div>
                            <div class="link-right">
                                <h4>联系人号码：<span>${contactCheck.number}</span></h4>
                                <h4>联系总次数：<span>${contactCheck.callCount}</span></h4>
                                <h4>主叫/被叫次数：<span>${contactCheck.callOutCount}/${contactCheck.callInCount}</span></h4>
                                <h4>互动标识：<span>${contactCheck.callFrequency}</span></h4>
                                <h4>是否存在周期性联系：<span>${contactCheck.periodicallyContact}</span></h4>
                                <%--<h4>联系人活跃度：<span>${contactCheck.active}</span></h4>--%>
                            </div>
                        </td>
                        <c:if test="${index.index!=0&&index.index%3==0}">
                            </tr>
                        </c:if>
                    </c:forEach>
				</table>
			</div>
            </c:if>
		</div>
	</div>
	<script src="/resources/js/lib/jquery-3.1.1.min.js"></script>
	<script src="/resources/js/lib/countUp.js"></script>

	<script type="text/javascript">
        var count = '${grayscaleModel.attributeTag.grayscaleScore}';
//        var count = 300;
        var slide_time =10000;
        var Loader = function () {
            var loader = document.querySelector('.loader-container'),
                meter = document.querySelector('.meter'),
                k, i = 1,
                counter = function () {
                    if (i <= 100) {
                        meter.innerHTML = i.toString();
                        var stop_flag = count/300.0*100;
                        if(i >= stop_flag-1)
                        {
                            window.clearInterval(k);
                            $("#bar").css('animation-play-state', 'paused');
                            $("#black_dyeing_id").css('animation-play-state', 'paused');
                            $(".pro-h3").css('animation-play-state', 'paused');
                            document.styleSheets[0].addRule('.loader-container::after','animation-play-state: paused');
                            /*var str = window.getComputedStyle(document.querySelector('.loader-container'), '::after').getPropertyValue('background');
                            var color = str.trim().split("none")[0];
                            $(".pro-h3").css('color',color);*/
                            return;
                        }
                        i++;

                    } else {
                        window.clearInterval(k);
                    }
                };

            return {
                init: function (options) {
                    options = options || {};
                    var time = options.time ? options.time : 0,
                        interval = time/100;

                    loader.classList.add('run');
                    k = window.setInterval(counter, interval);
                    setTimeout(function () {
                        loader.classList.add('done');
                    }, time);
                },
            }
        }();

        Loader.init({
            // If you have changed the @time in LESS, update this number to the corresponding value. Measured in miliseconds.
            time: slide_time
        });
        var options = {
            useEasing : true,
            useGrouping : true,
            separator : ',',
            decimal : '.',
            prefix : '',
            suffix : ''
        };
        var count_time = count/300*slide_time/1000+1.2 ;
        var report_count  = new CountUp("black_dyeing_id", 0, count, 0, count_time, options);
        report_count.start();

        $("#tips_id").hover(function () {
            $(this).next().show();
        },function () {
            $(this).next().hide();
        });
	</script>
</body>
</html>
