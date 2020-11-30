<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${path}/bootstrap/js/echarts.js"></script>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/bootstrap/js/goeasy-1.0.5.js"></script>
    <script type="text/javascript">

        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            $.get("${path}/user/queryMonthSex",function(data){

                console.log(data);

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '用户注册统计信息',
                        subtext:"纯属虚构",//子标题
                        <%--link:"${path}/main/main.jsp",--%>
                        textStyle:{
                            color:"#cad"
                        }
                    },
                    tooltip: {},  //鼠标的提示
                    legend: {
                        data:['小男生','小姑娘']  //选项卡
                    },
                    xAxis: {
                        data: data.month
                    },
                    yAxis: {},
                    series: [{  //数据
                        name: '小男生',  //选项卡
                        type: 'bar',  //类型
                        data: data.boys
                    },{  //数据
                        name: '小姑娘',
                        type: 'bar',
                        data: data.girls
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            },"json");
        });
    </script>

    <script type="text/javascript">

        //初始化goesay对象
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-d8c82401a6b443348f0ef93d03b1e2ab", //替换为您的应用appkey
        });


        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            //接收消息
            goEasy.subscribe({
                channel: "2005_channel", //替换为您自己的channel
                onMessage: function (message) {
                    alert(message.content)
                    var datas = message.content;

                    //将json的字符串转为javascript对象
                    var data = JSON.parse(datas);
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '用户注册统计信息',
                            subtext: "纯属虚构",//子标题
                            <%--link:"${path}/main/main.jsp",--%>
                            textStyle: {
                                color: "#cad"
                            }
                        },
                        tooltip: {},  //鼠标的提示
                        legend: {
                            data: ['小男生', '小姑娘']  //选项卡
                        },
                        xAxis: {
                            data: data.month
                        },
                        yAxis: {},
                        series: [{  //数据
                            name: '小男生',  //选项卡
                            type: 'bar',  //类型
                            data: data.boys
                        }, {  //数据
                            name: '小姑娘',
                            type: 'bar',
                            data: data.girls
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });
        });
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>