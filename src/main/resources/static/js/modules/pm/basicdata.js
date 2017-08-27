var setting = {
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
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        basicdata:{
            parentName:null,
            parentId:0,
            orderNum:0
        }
    },
    methods: {
        getBasicData: function(){
            //加载基础数据树
            $.get(baseURL + "pm/basicdata/select", function(r){
                ztree = $.fn.zTree.init($("#basicdataTree"), setting, r.basicDataList);
                var node = ztree.getNodeByParam("id", vm.basicdata.parentId);
                ztree.selectNode(node);

                vm.basicdata.parentName = node ? node.name : '';
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.basicdata = {parentName:null,parentId:0,orderNum:0};
            vm.getBasicData();
        },
        update: function () {
            var id = getTableRowId();
            if(id == null || id == false){
                return ;
            }

            $.get(baseURL + "pm/basicdata/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.basicdata = r.basicData;

                vm.getBasicData();
            });
        },
        del: function () {
            var id = getTableRowId();
            if(id == null || id == false){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "pm/basicdata/delete",
                    data: "id=" + id,
                    success: function(r){
                        if(r.code === 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.basicdata.id == null ? "pm/basicdata/save" : "pm/basicdata/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.basicdata),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        basicdataTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择数据",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#basicdataLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级数据
                    vm.basicdata.parentId = node[0].id;
                    vm.basicdata.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            Basicdata.table.refresh();
        }
    }
});

var Basicdata = {
    id: "basicdataTable",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Basicdata.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '基数数据', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '标识', field: 'ename', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级数据', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
    return columns;
};


function getTableRowId () {
    var selected = $('#basicdataTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("请选择一条记录");
        return false;
    } else {
        return selected[0].id;
    }
}


$(function () {
    $.get(baseURL + "pm/basicdata/info", function(r){
        var colunms = Basicdata.initColumn();
        var table = new TreeTable(Basicdata.id, baseURL + "pm/basicdata/list", colunms);
        table.setRootCodeValue(r.id);
        table.setExpandColumn(2);
        table.setIdField("id");
        table.setCodeField("id");
        table.setParentCodeField("parentId");
        table.setExpandAll(false);
        table.init();
        Basicdata.table = table;
    });
});
