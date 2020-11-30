<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>

    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/sort/queryOne?parentId=\" + rowId",
            datatype : "json",
            rowNum : 4,
            rowList : [ 8, 10, 20, 30 ],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'id', '名称', '级别', '父类id'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cate_name',index : 'invdate',width : 90,editable:true},
                {name : 'levels',index : 'name',width : 100},
                {name : 'parent_id',index : 'amount',width : 80,align : "right"},

            ],
            subGrid : true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            },

            editurl:"${path}/sort/edit",
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add : true,edit : true,del : true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDel: true,
                afterSubmit:function (data) {
                console.log(data)

                //设置提示信息
                $("#message").html(data.responseJSON.message);

        //展示警告框
        $("#showMsg").show();

        setTimeout(function () {
            $("#showMsg").hide();
        }, 3000);

        return "hello";
               }
            }, //删除
        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId){

        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );



        $("#" + subgridTableTd).jqGrid({
            url : "${path}/sort/queryTwo?parentId=" + rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'id', '名称', '级别', '父类id' ],
            colModel : [
                {name : "id",  index : "num",width : 80,key : true},
                {name : "cate_name",index : "item",  width : 130, editable:true},
                {name : "levels",index : "qty",width : 70,align : "right"},
                {name : "parent_id",editable:true,index : "unit",width : 70,align : "right",edittype:'select',
                    editoptions:{dataUrl:"${path}/sort/queryAllOne"}
                },
            ],
            editurl:"${path}/sort/edit",
        });

        $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDel: true,
                reloadAfterSubmit: true,

            }, //删除
        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>

<%--
    删除要有提示信息
--%>
