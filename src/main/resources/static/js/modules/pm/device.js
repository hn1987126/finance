$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pm/device/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true },
            { label: '设备号', name: 'deviceNum', index: 'device_num', width: 80 },
            { label: '设备名称', name: 'name', index: 'name', width: 80 },
            { label: '型号', name: 'deviceModel', index: 'device_model', width: 80 },
            { label: '设备mac', name: 'mac', index: 'mac', width: 80 },
            { label: '网关url', name: 'gatewayUrl', index: 'gateway_url', width: 80 },
            { label: '所在楼层', name: 'floor', index: 'floor', width: 80 },
            { label: '所属分组', name: 'groupName', index: 'group_id', width: 80 },
            { label: '所属商场', name: 'mallName', index: 'mall_id', width: 80 },
            { label: '标签', name: 'tags', index: 'tags', width: 80 },
            { label: '添加时间', name: 'createTime', index: 'create_time', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });

    // 商场弹出层的数据
    $("#jqGridMall").jqGrid({
        url: baseURL + 'pm/mall/list',
        datatype: "json",
        colModel: [
            { label: '商场名称', name: 'name', index: 'name', width: 155 },
            { label: '联系人', name: 'relname', index: 'relname', width: 80 },
            { label: '地址', name: 'address', index: 'address', width: 300 }
        ],
        viewrecords: true,
        height: 260,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: false,
        rownumWidth: 25,
        autowidth:true,
        // 以下两属性是 控制单选
        multiselect: true,
        multiboxonly:true,
        beforeSelectRow: function () {
            $(this).jqGrid('resetSelection');
            return(true);
        },
        pager: "#jqGridMallPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            // 隐藏 全选复选框
            $("#cb_jqGridMall").hide();
            //隐藏grid底部滚动条
            $(this).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});



//分组结构树
var group_ztree;
var group_setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            name: null
        },
        showList: true,
        title: null,
        device: {
            groupId:1,
            groupName:null,
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.device = {groupName:null,groupId:null,mallId:null,mallName:null};

            vm.getGroupTree();
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)

            vm.getGroupTree();
        },
        saveOrUpdate: function (event) {
            var url = vm.device.id == null ? "pm/device/save" : "pm/device/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.device),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "pm/device/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(id){
            $.get(baseURL + "pm/device/info/"+id, function(r){
                vm.device = r.device;

                vm.getGroupTree();
            });
        },
        getGroupTree: function(){
            //加载分组树
            $.get(baseURL + "pm/basicdata/list/wifi_group", function(r){
                group_ztree = $.fn.zTree.init($("#groupTree"), group_setting, r);
                var node = group_ztree.getNodeByParam("id", vm.device.groupId);
                if(node != null){
                    group_ztree.selectNode(node);
                    vm.device.groupName = node.name;
                }
            })
        },
        groupTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择分组",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#groupLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = group_ztree.getSelectedNodes();
                    //选择上级部门
                    vm.device.groupId = node[0].id;
                    vm.device.groupName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
        },
        selectMall:function () {
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择商场",
                area: ['600px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#mallLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var grid = $("#jqGridMall");
                    var rowId = grid.getGridParam("selrow");
                    if(!rowId){
                        alert("请选择商场");
                        return ;
                    }
                    var rowData = $("#jqGridMall").jqGrid("getRowData",rowId);
                    vm.device.mallId = rowId;
                    vm.device.mallName = rowData.name;

                    // 关闭层
                    layer.close(index);
                }
            });
        }
    }
});