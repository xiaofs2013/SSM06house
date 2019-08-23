<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>区域管理</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script language="JavaScript" src="js/user.js"></script>
</head>
<body>

实现区域管理

<input type="button" value="点击事件" id="but1">

<%--显示所有的区域--%>
<table id="dg"></table>


<!--制作工具栏-->
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:updateDialog()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">多项删除</a>
    </div>

    <div>
        名称：<input type="text" name="name" id="s_name">
        电话：<input type="text" name="telephone" id="s_tel">
        开始年龄：<input type="text" name="startAge" id="s_startage">
        结束年龄：<input type="text" name="endAge" id="s_endage">
        <a href="javascript:Search()" class="easyui-linkbutton"
           iconCls="icon-search" plain="true">搜索</a>

    </div>

</div>


<!--添加对话框-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="" name="form1" id="form1">
        区域名称:<input type="text" name="name" id="name2">
    </form>
</div>
<!--添加对话框的按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseAddDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


<!--修改对话框-->
<div id="updateDialog" class="easyui-dialog" buttons="#updateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form method="post" action="" id="form2" name="form1">
        <input type="hidden" name="id">
        区域名称:<input type="text" name="name" id="name3">
    </form>
</div>
<!--添加修改对话框的按钮-->
<div id="updateDialogButtons">
    <a href="javascript:SaveUsers()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a> <a href="javascript:CloseUPDATADialog('updateDialog')"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>


</body>


</html>
