<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    <%--function a(s){--%>
    <%--    $.ajax({--%>
    <%--        url:"${path}/user/update",--%>
    <%--        type:"post",--%>
    <%--        data:"id="+s,--%>
    <%--        datatype:"json",--%>
    <%--        success:function (res) {--%>
    <%--            if (res=="0"){--%>
    <%--                $("#"+s+"a").text("冻结").prop("class","btn btn-success");--%>
    <%--            }else{--%>
    <%--                $("#"+s+"a").text("解除冻结").prop("class","btn btn-danger");--%>
    <%--            }--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>


    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/video/queryAll",
            datatype : "json",
            rowNum : 2,
            rowList : [ 4, 8, 10, 20 ],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,//是否展示总条数
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'id', '标题', '简介', '封面','视频','上传时间','点赞数','播放数','类别id','用户id','分组','状态'],
            colModel : [
                {name : 'id', width: 55},
                {name : 'title',editable:true,width: 90},
                {name : 'brief',editable:true,width: 90},
                {name:'cover_path',width: 400, align: "center",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img id='media' src='" + cellvalue + "' controls width='200px' heigt='600px' poster='" + rowObject.cover + "' />";
                    }
                },
                {name:'video_path',editable:true,width: 400, align: "center",edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<video id='media' src='" + cellvalue + "' controls width='200px' heigt='600px' poster='" + rowObject.cover + "' />";
                    }
                },
                {name : 'upload_time', width: 80, align: "right"},
                {name : 'like_count', width: 80, align: "right"},
                {name : 'play_count', width: 80, align: "right"},
                {name : 'category_id', width: 80, align: "right"},
                {name : 'user_id', width: 80, align: "right"},
                {name : 'group_id', width: 150, sortable: false},
                {name:'status',index:'total', width:80,align:"right",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue == "0"){
                            return "<button class='btn btn-success' type='button' onclick=\"a('"+rowObject.id+"')\" id='"+rowObject.id+'a'+"'>冻结</button>";
                        }else{
                            return "<button class='btn btn-danger' type='button' onclick=\"a('"+rowObject.id+"')\" id='"+rowObject.id+'a'+"'>解除冻结</button>";
                        }
                    }
                },

            ],
            editurl:"${path}/video/edit",
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add : true,edit : true,del : true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                // reloadAfterSubmit: true,
                afterSubmit:function(data){    //data相当于uuid
                    $.ajaxFileUpload({
                        url: "${path}/video/upload",
                        type: "post",
                        data: {"id": data.responseText},
                        fileElementId: "video_path",  //文件选择框的id属性  <input type="file" id="picImg" name="picImg">的id
                        success:function(){
                            //刷新页面
                            $("#videoId").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            }, //添加
            {
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDel: true,
            }//执行删除之后的额外操作
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading" align="center">
        <h2>视频信息</h2>
    </div>

        <%--选项卡--%>
        <div class="nav nav-tabs">
            <li class="active"><a href="">视频信息</a></li>
        </div>

        <%--警告提示框--%>
        <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
            <span id="showMsg"/>
        </div>
    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>

<%--
    删除要有提示信息
--%>
