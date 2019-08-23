/*
* formatter：function（value，row,index）{}
  formatter 属于列参数，表示对于当前列的数据进行格式化操作，它是一个函数，有三个参数，分别是value，row,index
   value：表示当前单元格中的值
   row：表示当前行
  index：表示当前行的下标
  可以使用return返回想要的数据显示在单元格中
* */

$(function () {

    //使用datagrid绑定数据
    //使用datagrid显示区域
    $('#dg').datagrid({
        toolbar: "#ToolBar",  //显示工具栏
        title: "区域信息",
        url: 'getDistrict',  //服务器地址
        pagination: true,  //启用分页
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            {field: "cb", checkbox: true},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '区域名称', width: 100, align: 'left'},
            {
                field: 's', title: '操作', width: 100, formatter: function (value, row, index) {
                    //var str = "<a href='DeleteDistrict?id=" + row.id + "'>删除</a>|<a href=''>修改</a>"
                    //上面的操作是同步发送请求 不会跳转页面

                    var str = "<a href=\"javascript:deleteDis(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>|<a href=\"javascript:openShowSteetDialog(" + row.id + ");\">查看街道</a>";
                    return str;
                }, align: 'left'
            }


        ]]
    });

    $("#but1").click(function () {
        $.post("getDistrict", null, function (data) {
            alert(data)
        }, "json")
    })
})


//点击添加，打开窗口
function Add() {
    //alert("我要添加区域");
    $('#AddDialog').window('setTitle', "添加区域");
    $('#AddDialog').window('open');
}

//关闭添加对话框
function CloseAddDialog() {
    $('#AddDialog').window('close'); //关闭
}


function SaveDialog() {
    //1.获取数据，发送异步请求实现添加
    //$.post("addDistrict",给服务器传参,function(data){},"json");
    /* $.post("addDistrict",{"name":$("#name2").val()},function(data){
         alert(data);
            },"json");*/

    //2.使用easuyi插件以异步方式提交表单
    $('#form1').form('submit', {
        url: "AddDistrict",
        success: function (data) {  //data表示的字符串
            //将返回的json字符串转化为json对象
            data = $.parseJSON(data);
            if (data.result > 0) {
                $.messager.alert('>>提示', '添加成功！', 'info');  //提示框
                $('#AddDialog').window('close'); //关闭
            } else {
                $.messager.alert('>>提示', '添加失败！', 'error');
                $('#AddDialog').window('close'); //关闭
            }
        }
    });
}

//编辑修改的方法与对话框
function updateDialog() {
    //判断用户选择
    //1.获取dagagrid的选中行
    var SelectRows = $("#dg").datagrid('getSelections'); //返回数组 getSelections 自带的方法 判断是否选择到
    if (SelectRows.length == 1) {   //如果复选框选择的值为1 （选中一个） 就执行
        $('#updateDialog').window('setTitle', "编辑区域");
        $('#updateDialog').window('open');

        //获取当前行的数据:获取主键，查询数据库获取单行数据
        //如果当显示的数据在dagagrid中存在，无需查询数据库，如果当显示的数据在datagrid中不全，需要查询数据库获单条
        //发异步请求查询数据库

        $.post("SelectOne", {"id": SelectRows[0].id}, function (data) {
            $("#form2").form('load', data); //将对象还原表单
        }, "json");  //设置回显 加载SelectRows的行 下标为0

        //获取当前行的数据：获取主键 查询数据库
    } else {
        $.messager.alert('>>提示', '你没有选择行或者选择多行,给我小心点！', 'warn');  //提示框
    }


}

function CloseUPDATADialog() {
    $('#updateDialog').window('close');
}


function SaveDistrict() {

    $('#form2').form('submit', {
        url: "UpdataDistrict",
        success: function (data) {  //data表示的字符串
            //将返回的json字符串转化为json对象
            data = $.parseJSON(data);
            if (data.result > 0) {
                $.messager.alert('>>提示', '修改成功！', 'info');  //提示框
                $('#updateDialog').window('close'); //关闭
                $('#dg').datagrid('reload');
            } else {
                $.messager.alert('>>提示', '修改失败！', 'error');
                $('#updateDialog').window('close'); //关闭
            }
        }
    });
}

function deleteDis(obj) {

    $.messager.confirm("提示框", "确认是否需要删除？", function (r) {  //这里的r 代表true的意思
        if (r) {

            $.post("DeleteDistrict", {"id": obj}, function (data) {
                if (data.result == 1) {
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                } else {
                    $.messager.alert('提示框', '删除失败！^_^', 'info');
                }

            }, "json");


        } else {

            $.messager.alert("没有删除", "撤销操作")

        }
    })


}

function DeleteByFruitName() {
    //获取选中行
    var SelectRows = $("#dg").datagrid('getSelections'); //返回数组 getSelections 自带的方法 判断是否选择到
    if (SelectRows.length == 0) {
        $.messager.alert("提示框", "并未选中行！", "info");
        return;
    }

    //确认删除
    $.messager.confirm("提示框", "确认是否删除？", function (flag) {
        if (flag) {
            $.post("delMoreDistrict")
        }
    })

}

//批量删除操作
function DeleteByFruitName() {
    //获取选中行
    var SelectRows = $("#dg").datagrid('getSelections');
    if (SelectRows.length == 0) {
        $.messager.alert('提示框', '你还没有选中行^_^', 'info');
        return;
    }
    $.messager.confirm("提示框", "确认删除吗？", function (flag) {
        if (flag) {  //为true实现删除
            // 调用服务器接口进行删除
            //获取选中项的值
            var value = "";
            for (var i = 0; i < SelectRows.length; i++) {
                value = value + SelectRows[i].id + ",";
            }
            value = value.substring(0, value.length - 1);  //去除最后的逗号

            //发送异步请求到服务器
            $.post("delMoreDistrict", {"id": value}, function (data) {
                if (data.result > 0) {
                    //实现datagrid的刷新
                    $('#dg').datagrid('reload');
                } else {
                    $.messager.alert('提示框', '删除失败！^_^', 'info');
                }
            }, "json");
        }
    })
}


/*打开街道显示的窗口*/

/*function openShowSteetDialog() {

    $("#Streetwindows").window("setTitle", "街道详情");
    $("#Streetwindows").window("open");

    $("#Streetdg").datagrid({
        title: "街道信息",
        url: 'getStreet',          //这里的URL用单引号
        pagination: true,   //开启分页
        rownumbers: true,   //显示行号
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3,
        columns: [[
            {field: 'ck', checkbox: true, width: 100, align: 'left'},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '街道名称', width: 100, align: 'left'},
            {field: 'districtId', title: '城市编号', width: 100, align: 'left'},
            {
                field: 's', title: '操作', width: 100, formatter: function (value, row, index) {
                    //var str = "<a href='DeleteDistrict?id=" + row.id + "'>删除</a>|<a href=''>修改</a>"
                    //上面的操作是同步发送请求 不会跳转页面

                    var str = "<a href='DelectStreet?id=" + row.id + "'>删除</a>
                    return str;
                }, align: 'left'
            }
        ]]
    })
}*/


/*打开街道显示的窗口*/
function openShowSteetDialog(id) {  //id表示接收区域编号
    //打开窗口
    $('#Streetwindows').window('setTitle', "街道信息");
    $('#Streetwindows').window("open");

    //显示当前区域下的街道
    $('#Streetdg').datagrid({
        title: "街道信息",
        url: 'getStreet?id=' + id,  //服务器地址
        pagination: true,  //启用分页
        //toolbar:"#ToolBar",  //工具栏
        rownumbers: true,  //显示行号
        //singleSelect:true,  //实现单行选择
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            {field: 'cb', checkbox: true, width: 100, align: 'left'},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '街道名称', width: 100, align: 'left'},
            {
                field: 'opt', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:DelectStreet(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";

                }
            }
        ]]
    });
}
