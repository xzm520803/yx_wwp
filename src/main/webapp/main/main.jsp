<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">应学视频App后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎：${sessionScope.admin.username}</a></li>
                <li><a href="#">登录</a></li>
                <li><a href="${path}/admin/loginOut">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<!--栅格系统-->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <!--菜单-->
            <!--左边手风琴部分-->
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne" style="background: #985f0d">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-bell"></span> 用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <button class="btn btn-info">
                                <a href="javascript:$('#mainId').load('${path}/user/showUser.jsp')">用户信息</a>
                            </button><br><br>
                            <button class="btn btn-info">
                                <a href="javascript:$('#mainId').load('${path}/test/testChina.jsp')">用户分布</a>
                            </button><br><br>
                            <button class="btn btn-info">
                                <a href="javascript:$('#mainId').load('${path}/test/testEchartsgoeasy.jsp')"> 用户统计</a>
                            </button><br>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading"role="tab" id="headingTwo" style="background: #79c9ec">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <span class="glyphicon glyphicon-bell"></span> 分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <button class="btn btn-success">
                                <a href="javascript:$('#mainId').load('${path}/category/showCategory.jsp')">类别信息</a>
                            </button>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree" style="background: #660066">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <span class="glyphicon glyphicon-apple"></span> 视频管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <button class="btn btn-success">
                                <a href="javascript:$('#mainId').load('${path}/Video/showVideo.jsp')">视频信息</a>
                            </button>
                        </div>
                    </div>
                </div>


                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour" style="background: #b4d100">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                <span class="glyphicon glyphicon-apple"></span> 日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <button class="btn btn-success">
                                <a href="javascript:$('#mainId').load('${path}/Log/showLog.jsp')">日志信息</a>
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--巨幕开始-->
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-10" id="mainId">
                    <div class="jumbotron">
                        <h1>欢迎来到应学视频App后台管理系统</h1>
                    </div>

                    <div class="container-fluid col-sm-2">
                        <div class="row">
                            <div>
                                <!--创建轮播图-->
                                <div id="carousel-example-generic" style="height: 300px; width: 1250px" class="carousel slide" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                                    </ol>

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner" role="listbox">
                                        <div class="item active">
                                            <img src="/bootstrap/img/pic4.jpg"  style="height: 400px; width: 1250px" alt="...">
                                            <div class="carousel-caption">

                                            </div>
                                        </div>
                                        <div class="item">
                                            <img src="/bootstrap/img/pic2.jpg"  style="height: 400px; width: 1250px" alt="...">
                                            <div class="carousel-caption">

                                            </div>
                                        </div>
                                        <div class="item">
                                            <img src="/bootstrap/img/pic3.jpg"  style="height: 400px; width: 1250px" alt="...">
                                            <div class="carousel-caption">

                                            </div>
                                        </div>
                                        <div class="item">
                                            <img src="/bootstrap/img/pic1.jpg"  style="height: 400px; width: 1250px" alt="...">
                                            <div class="carousel-caption">

                                            </div>
                                        </div>
                                    </div>

                                    <!-- Controls -->
                                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                    </a>

                                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    </a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top: 15px">
    <div class="container" style="text-align: center">
        <p>吴三炮文化传媒&copy;WuWenPeng&reg;有限公司</p >
    </div>
</nav>

</body>
</html>
