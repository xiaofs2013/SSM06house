/*
* formatter：function（value，row,index）{}
  formatter 属于列参数，表示对于当前列的数据进行格式化操作，它是一个函数，有三个参数，分别是value，row,index
   value：表示当前单元格中的值
   row：表示当前行
  index：表示当前行的下标
  可以使用return返回想要的数据显示在单元格中
* */

$(function () {
    //使用datagrid显示区域
    $('#dg').datagrid({
        title: "未审核房屋信息",
        url: 'getHouseNoPass',  //服务器地址
        pagination: true,  //启用分页
        toolbar: "#ToolBar",  //工具栏
        rownumbers: true,  //显示行号
        //singleSelect:true,  //实现单行选择
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        pageSize: 3, //每页三条
        columns: [[
            {field: 'ck', checkbox: true, width: 100, align: 'left'},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'title', title: '标题', width: 100, align: 'left'},
            {field: 'price', title: '价格', width: 100, align: 'left'},
            {field: 'pubdate', title: '发布日期', width: 100, align: 'left'},
            {field: 'contact', title: '联系方式', width: 100, align: 'left'},
            {field: 'floorage', title: '面积', width: 100, align: 'left'},
            {field: 'dname', title: '区域名称', width: 100, align: 'left'},
            {field: 'sname', title: '街道名称', width: 100, align: 'left'},
            {field: 'tname', title: '类型', width: 100, align: 'left'},
            {
                field: 'opt', title: '操作', width: 100, align: 'left',
                formatter: function (value, row, index) {
                    //发送同步请求
                    // return "<a href=\"delDistrict?id="+row.id+"\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">删除</a>";
                    //发送异步请求Ajax
                    return "<a href=\"javascript:passHouse(" + row.id + ")\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\">审核</a>";

                }
            }
        ]]
    });
});

/*审核出租房*/
function passHouse(id) {
    //发送异步请求到服务器
    $.post("houseYes", {"id": id}, function (data) {
        if (data.result > 0) {   //如果修改成功了就
            $("#dg").datagrid("reload"); //刷新
        } else {    //如果修改失败了就
            $.messager.alert('提示框', '审核失败！^_^', 'info');
        }
    }, "json");
}


/*实现Datagrid的搜索功能*/
function search() {
    //实现搜索查询
    //datagrid的load方法是重新加载，它会将查询条件，随着页码,页大小
    //一起发送到当前控制所指定的服务器地址进行处理
    //$("#dg").datagrid("load",传查询条件:{键:值,键:值});
    var s_name = $("#s_name").val();
    var s_tel = $("#s_tel").val();
    var s_startage = $("#s_startage").val();
    var s_endage = $("#s_endage").val();

    $("#dg").datagrid("load", {"name": s_name, "telephone": s_tel, "startAge": s_startage, "endAge": s_endage});

}
