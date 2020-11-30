<%@page pageEncoding="UTF-8" isELIgnored="false"  %>


<script>

    function a(s){
        $.ajax({
            url:"${pageContext.request.contextPath}/user/update",
            type:"post",
            data:"id="+s,
            datatype:"json",
            success:function (res) {
                if (res=="0"){
                    $("#"+s+"a").text("冻结").prop("class","btn btn-success");
                }else{
                    $("#"+s+"a").text("解除冻结").prop("class","btn btn-danger");
                }
            }
        });
    }


    $(function(){
        $("#userId").jqGrid({
            url:"${pageContext.request.contextPath}/user/findAll",
            edit:"",
            datatype: "json",
            rowNum:3,
            rowList:[3,5,10,20,30],
            pager: '#userPage',
            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','昵称', '手机号', '头像','描述','学分','注册时间','状态'],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'name',index:'invdate', width:90},
                {name:'iphone',index:'amount', width:80, align:"right"},
                {name:'head',index:'name asc, invdate', width:100,
                    formatter:function(cellvalue, options, rowObject){
                        return "<img src='${pageContext.request.contextPath}/imgs/"+cellvalue+"' width='50px' height='50px'>";
                    }
                },
                {name:'brief',index:'tax', width:80, align:"right"},
                {name:'score',index:'total', width:80,align:"right"},
                {name:'createDate',index:'note', width:150, sortable:false},
                {name:'state',index:'total', width:80,align:"right",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue == "0"){
                            return "<button class='btn btn-success' type='button' onclick=\"a('"+rowObject.id+"')\" id='"+rowObject.id+'a'+"'>冻结</button>";
                        }else{
                            return "<button class='btn btn-danger' type='button' onclick=\"a('"+rowObject.id+"')\" id='"+rowObject.id+'a'+"'>解除冻结</button>";
                        }
                    }
                },
            ]
        });
        $("#userId").jqGrid('navGrid','#userPage',{edit:false,add:false,del:false});

    });

</script>
<script>
    $(function () {
        $("#aliyun").click(function () {
            //获取手机号
            var phones = $("#phone").val();
            //发送ajax请求
            $.post("${pageContext.request.contextPath}/user/phoneCode",{phones:phones},function (data) {
                if (data.status=="200"){
                    alert(data.message);
                }else {
                    alert(data.message);
                }
            },"JSON")
        });
    });
</script>


<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>

    <div>
        <button class="btn btn-info"> <a href="${pageContext.request.contextPath}/user/Poiexport">导出用户信息</a></button>
        <button class="btn btn-success">导入用户信息</button>
        <div class="pull-right col-sm-5">
            <form>
                <div class="col-md-4 col-md-offset-6" style="padding: 0px;">
                    <input type="text" class="form-control" id="phone" name="phones" placeholder="请输入手机号..." required
                           minlength="11">
                </div>
                <div class="col-md-2 pull-right" style="padding: 0px;">
                    <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                </div>
            </form>
        </div>
    </div><br>


    <%--表单--%>
    <table id="userId" />

    <%--分页工具栏--%>
    <div id="userPage" />

</div>
