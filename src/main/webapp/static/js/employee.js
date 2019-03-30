$(function () {

    /*员式数据列表*/
    $("#dg").datagrid({
        url: "/getEmployeeList",
        toolbar: "#tt",
        columns: [[
            {field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'inputtime', title: '入职时间', width: 100, align: 'center'},
            {field: 'tel', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            {
                field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.state) {
                        return "在职";
                    } else {
                        return "<font style='color: red'>离职</font>"
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.admin) {
                        return "是";
                    } else {
                        return "否"
                    }
                }
            },
        ]],

        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped :true,
        onClickRow:function (rowIndex, rowData) {
            if(!rowData.state)
            {
                $("#delete").linkbutton("disable");
            }else {
                $("#delete").linkbutton("enable");
            }
        }
    });
    //弹出对话框

    $("#dd").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: [

            {
                iconCls: 'icon-save',
                text: '保存',
                handler:
                    function () {
                        alert("aaa");
                        var id = $("[name='id']").val();

                        var url;
                        if (!id) {
                            url = "/save"
                        } else {
                            url = "/update"
                        }

                        $('#employeeForm').form('submit',
                            {

                                url: url,
                                onSubmit: function(param){
                                   var values=$("#role").combobox("getValues");
                                    for(var i=0;i<values.length;i++)
                                    {
                                        param["roles["+i+"].rid"]=values[i];
                                    }

                                },

                                success: function (data) {

                                    var data=$.parseJSON(data);
                                    console.log(data);
                                    if(data.successful)
                                    {
                                        $("#dd").dialog('close');
                                        $.messager.alert("温馨提示", data.msg);
                                        $('#dg').datagrid('load');
                                    }
                                    else {
                                        console.log("aaa");
                                        $.messager.alert("温馨提示",data.msg)
                                    }


                                }
                            }
                        )

                    }
            },
            {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    $("#dd").dialog('close');
                    $("#employeeForm").form('clear');
                }
            }

        ]


    });
    $("#department").combobox({
        editable: false,
        width: 150,
        panelHeight: 'auto',
        valueField: 'id',
        textField: 'name',
        url: '/departmentList',
        onLoadSuccess: function (data) {

            //$("#department").combobox("select",data[0].name);
            //$("#department").combobox("setValue",data[0].id)
            $("#department").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }

            });

        }
    })
    $("#state").combobox({
        editable: false,
        width: 150,
        valueField: 'value',
        textField: 'label',
        panelHeight: 'auto',
        data: [{
            label: '是',
            value: 'true'
        }, {
            label: '不是',
            value: 'false'
        }],
        onLoadSuccess: function (data) {

            //$("#department").combobox("select",data[0].name);
            //$("#department").combobox("setValue",data[0].id)
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }

            });

        }

    });

    $("#add").click(function () {

        $('#employeeForm')[0].reset();
        $("#employeeForm").form("clear");
        $("#dd").dialog('open');

        $("#dd").dialog('setTitle', "保存");


    });
    $("#edit").click(function () {

        var dataRow = $("#dg").datagrid('getSelected');
        if (!dataRow) {
            $.messager.alert('警告', '请选择一条编辑');
            return
        }
        $("#dd").dialog('setTitle', "编辑");
        $("#employeeForm").form('clear');
        $("#dd").dialog('open');
        dataRow["department.id"] = dataRow["department"].id;
        dataRow["admin"] = dataRow["admin"] + "";

        if (dataRow["admin"] == "null") {
            dataRow["admin"] = "false";
        }
        $.ajax({
            url:"/getRoleId",
            data:{"id":dataRow.id},
            success:function (data) {
                console.log(data)
                $("#role").combobox("setValues",data);
            },
            error:function () {

            }
        })
        $("#employeeForm").form('load', dataRow);

    });

    $("#delete").click(function () {
        var dataRow = $("#dg").datagrid('getSelected');
        if (!dataRow) {
            $.messager.alert('警告', '请选择一条编辑');
            return
        }

        $.ajax({
            url:'/updateState',
            data:{"id":dataRow.id},
            success:function (data) {
                //var data = $.parseJSON(data);
                $.messager.alert("温馨提示", data.msg);


                    $("#delete").linkbutton("disable");
                    $('#dg').datagrid('reload');

            },
            error:function () {

            }

        })

    });

    $("#role").combobox({
        width:150,
        panelHeight:'auto',
        editable:false,
        url:'/getRoles',
        textField:'rname',
        valueField:'rid',
        multiple:true,
        onLoadSuccess:function (data) { /*数据加载完毕之后回调*/
            $("#role").each(function(i){
                $("#role").combobox("select",data[0].name);
                $("#role").combobox("setValue",data[0].name);

                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    })
})