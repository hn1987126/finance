$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'pm/wages/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 35, key: true, hidden: true },
            { label: '人员姓名', name: 'userRelname', index: 'user_id', width: 60, formatter: function(value, options, row){
                return '<a href="javascript:void(0);" onclick="vm.detail('+row.id+')">'+value+'</a>';
            }},
			{ label: '职务工资', name: 'jobWage', index: 'job_wage', width: 60 }, 			
			{ label: '基本工资', name: 'basicWage', index: 'basic_wage', width: 60 }, 			
			// { label: '级别工资', name: 'levelWage', index: 'level_wage', width: 60 },
			// { label: '基础工资', name: 'basicsWage', index: 'basics_wage', width: 60 },
			{ label: '工龄工资', name: 'workingYearWage', index: 'working_year_wage', width: 60 },
			// { label: '技术等级工资', name: 'techLevelWage', index: 'tech_level_wage', width: 60 },
			// { label: '见习工资', name: 'traineeWage', index: 'trainee_wage', width: 60 },
			// { label: '活工资', name: 'liveWage', index: 'live_wage', width: 60 },
			// { label: '特区津贴', name: 'zoneAllowance', index: 'zone_allowance', width: 60 },
			// { label: '保留津贴', name: 'retainAllowance', index: 'retain_allowance', width: 60 },
			// { label: '工作性补贴(奖金)', name: 'bonus', index: 'bonus', width: 60 },
			// { label: '改革性补贴(房补)', name: 'houseSubsidy', index: 'house_subsidy', width: 60 },
			// { label: '生活性补贴(物补)', name: 'lifeSubsidy', index: 'life_subsidy', width: 60 },
			// { label: '特岗津贴', name: 'specialAllowance', index: 'special_allowance', width: 60 },
			// { label: '临岗津贴', name: 'tmpAllowance', index: 'tmp_allowance', width: 60 },
			// { label: '独生子女费', name: 'childCost', index: 'child_cost', width: 60 },
			// { label: '离退休增资全国', name: 'retireCostWhole', index: 'retire_cost_whole', width: 60 },
			// { label: '离退休增资特区', name: 'retireCostZone', index: 'retire_cost_zone', width: 60 },
			// { label: '离退休补贴经费', name: 'retireCost', index: 'retire_cost', width: 60 },
			{ label: '年度考核奖', name: 'checkYearBonus', index: 'check_year_bonus', width: 70 },
			// { label: '其他', name: 'otherBonus', index: 'other_bonus', width: 60 },
			{ label: '岗位津贴', name: 'postBonus', index: 'post_bonus', width: 60 },
			{ label: '绩效工资', name: 'meritPay', index: 'merit_pay', width: 60 }, 			
			// { label: '薪级工资', name: 'wagePay', index: 'wage_pay', width: 60 },
			// { label: '执法薪级工资', name: 'lawWagePay', index: 'law_wage_pay', width: 60 },
			// { label: '套转差额', name: 'difference', index: 'difference', width: 60 },
			// { label: '其他增补', name: 'otherSubsidy', index: 'other_subsidy', width: 60 },
			// { label: '养老补差', name: 'pensionDiff', index: 'pension_diff', width: 60 },
			// { label: '基层补贴', name: 'basicSubsidy', index: 'basic_subsidy', width: 60 },
			// { label: '临时补贴', name: 'tmpSubsidy', index: 'tmp_subsidy', width: 60 },
			{ label: '应发工资', name: 'shouldWage', index: 'should_wage', width: 60 }, 			
			// { label: '养老基数', name: 'pensionBase', index: 'pension_base', width: 60 },
			// { label: '年金合计', name: 'totalAnnuity', index: 'total_annuity', width: 60 },
			// { label: '职业年金(单位)', name: 'companyAnnuity', index: 'company_annuity', width: 60 },
			// { label: '职业年金(个人)', name: 'personalAnnuity', index: 'personal_annuity', width: 60 },
			{ label: '个人社保合计', name: 'personalSocial', index: 'personal_social', width: 80 },
			// { label: '个人医疗', name: 'personalMedical', index: 'personal_medical', width: 60 },
			// { label: '个人养老', name: 'personalPension', index: 'personal_pension', width: 60 },
			// { label: '个人统筹医疗', name: 'personalPensionCase', index: 'personal_pension_case', width: 60 },
			{ label: '所得税', name: 'incomeTax', index: 'income_tax', width: 60 }, 			
			// { label: '反税', name: 'antiTax', index: 'anti_tax', width: 60 },
			{ label: '其他应扣', name: 'otherDeduction', index: 'other_deduction', width: 60 }, 			
			{ label: '个人公积金', name: 'personalFund', index: 'personal_fund', width: 70 },
			// { label: '代缴', name: 'replacePay', index: 'replace_pay', width: 60 },
			{ label: '实发工资', name: 'actualWages', index: 'actual_wages', width: 60 }, 			
			// { label: '房改补贴', name: 'housingSubsidy', index: 'housing_subsidy', width: 60 },
			// { label: '单位公积金', name: 'companyFund', index: 'company_fund', width: 60 },
			// { label: '单位社保合计', name: 'companySocial', index: 'company_social', width: 60 },
			// { label: '单位医疗', name: 'companyMedical', index: 'company_medical', width: 60 },
			// { label: '单位养老', name: 'companyPension', index: 'company_pension', width: 60 },
            { label: '支付状态', name: 'payStatus', index: 'pay_status', width: 60, formatter: function(value, options, row){
                return value === 0 ?
                    '<span class="label label-danger">未支付</span>' :
                    '<span class="label label-success">已支付</span>';
            }},
			{ label: '导入时间', name: 'createTime', index: 'create_time', width: 120 }
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            name: null
		},
		showList: true,
		showDetail: false,
		title: null,
		wages: {},
        userinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.showDetail = false;
			vm.title = "新增";
			vm.wages = {};
		},
        detail: function(id){
            vm.showList = false;
            vm.showDetail = true;
            vm.title = "工资详单";
            vm.getInfo(id)

        },
		update: function (event) {
			var id = getSelectedRow();
			if(id == null || id == false){
				return ;
			}
			vm.showList = false;
            vm.showDetail = false;
            vm.title = "修改";
            
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.wages.id == null ? "pm/wages/save" : "pm/wages/update";

            $.ajaxFileUpload({
                type: "POST",
                url: baseURL + url,
                fileElementId: 'file',
                data: {'token':localStorage.getItem("token")},
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                },
                complete:function(r){}
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
				    url: baseURL + "pm/wages/delete",
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
			$.get(baseURL + "pm/wages/info/"+id, function(r){
                vm.wages = r.wages;
                vm.userinfo = r.userinfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		print: function () {
            $('#printDiv').jqprint({
                debug: false,
                importCSS: true,
                printContainer: true,
                operaSupport: false
            })
        }
	}
});