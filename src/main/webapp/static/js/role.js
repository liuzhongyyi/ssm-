$(function () {
    $("#dg_role").datagrid({
        url: "/getRoleList",
        columns: [[
            {field: 'rnum', title: '角色编号', width: 100, align: 'center'},
            {field: 'rname', title: '角色名称', width: 100, align: 'center'}
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped: true,
        toolbar: "#Role_tt"
    });

    $("#dd_role").dialog({
        width: 600,
        height: 500,
        closed: true,
        //url:"/getRoleList",
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                var rid =$("[name='rid']").val();
                var url
                if(rid)
                {
                    url="/updateRole";
                }else {
                    url:"/saveRole"
                }
                $("#roleForm").form("submit", {
                    url: url,
                    onSubmit: function (param) {
                        var rows = $("#role_data2").datagrid("getRows");
                        for (var i = 0; i < rows.length; i++) {
                            param["permissions[" + i + "].pid"] = rows[i].pid;
                        }
                    },
                    success: function (data) {
                        var data = $.parseJSON(data);
                        if (data.successful) {
                            $("#dd_role").dialog('close');
                            $.messager.alert("温馨提示", data.msg);
                            $('#dg_role').datagrid('reload');
                        }
                        else {
                            console.log("aaa");
                            $.messager.alert("温馨提示", data.msg)
                        }

                    }


                });

            }
        }, {
            text: '关闭',
            iconCls: 'icon-remove',
            handler: function () {
                $("#dd_role").dialog("close");

            }
        }]


    });


    $("#role_data1").datagrid({
        title: "所有权限",
        width: 250,
        height: 400,
        fitColumns: true,
        singleSelect: true,
        url: '/getPermissionList',
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'},
        ]],
        onClickRow: function (rowIndex, rowData) {
            var rows = $("#role_data2").datagrid("getRows");
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                if (row.pid == rowData.pid) {
                    var index = $("#role_data2").datagrid("getRowIndex", row);
                    $("#role_data2").datagrid("selectRow", index);
                    return;
                }

            }

            $("#role_data2").datagrid("appendRow", rowData);
        }
    });
    $("#role_data2").datagrid({
        title: "已选权限",
        width: 250,
        height: 400,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: 'pname', title: '权限名称', width: 100, align: 'center'},
        ]],
        onClickRow: function (rowIndex, rowData) {
            $("#role_data2").datagrid("deleteRow", rowIndex);
        }


    })
    $("#add").click(function () {
        $("#role_data2").datagrid("loadData",{rows:[]});
        $("#roleForm").form("clear");
        $("#dd_role").dialog("setTitle", "添加");
        $("#dd_role").dialog('open');

    });
    $("#edit").click(function () {

        var row = $("#dg_role").datagrid("getSelected");
        if (!row) {
            $.messager.alert("警告","请选择一行编辑");
            return ;
        }
        $("#roleForm").form("clear");
        $("#roleForm").form("load",row);
        $("#dd_role").dialog("open");
        $("#dd_role").dialog("setTitle", "编辑");
        $("#role_data2").datagrid("loadData",{rows:[]});
        var options=$("#role_data2").datagrid("options");
        console.log(row);
        options.url="/getPermisson?rid="+row.rid;
        $("#role_data2").datagrid("load");

    })
    $("#delete").click(function () {
        var row = $("#dg_role").datagrid("getSelected");
        if (!row) {
         $.messager.alert("警告","请选择一行编辑");
         return ;
        }
        $.ajax({
            url:"/deleteRole",
            data:{"rid":row.rid},
            success:function (data) {

                if (data.successful) {
                    $("#dd_role").dialog('close');
                    $.messager.alert("温馨提示", data.msg);
                    $('#dg_role').datagrid('reload');
                }
                else {
                    console.log("aaa");
                    $.messager.alert("温馨提示", data.msg)
                }
            },
            error:function () {

            }
        })

    })
});
