drop database jhs_finance;
CREATE DATABASE IF NOT EXISTS jhs_finance default charset utf8 COLLATE utf8_general_ci;
use jhs_finance;

-- 菜单
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) COMMENT '菜单名称',
  `url` varchar(200) COMMENT '菜单URL',
  `perms` varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) COMMENT '菜单图标',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- 部门
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) COMMENT '部门名称',
  `order_num` int COMMENT '排序',
  `del_flag` tinyint DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- 系统用户
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) COMMENT '密码',
  `salt` varchar(20) COMMENT '盐',
  `email` varchar(100) COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` tinyint COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) COMMENT '部门ID',
  `create_time` datetime COMMENT '创建时间',
  `id_number` varchar(50) COMMENT '身份证号',
  `card_number` varchar(50) COMMENT '工资卡号',
  `relname` varchar(50) COMMENT '真实姓名',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';


-- 系统用户Token
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- 角色
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COMMENT '角色名称',
  `remark` varchar(100) COMMENT '备注',
  `dept_id` bigint(20) COMMENT '部门ID',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- 角色与部门对应关系
CREATE TABLE `sys_role_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `dept_id` bigint COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- 系统配置信息
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `key` varchar(50) COMMENT 'key',
  `value` varchar(2000) COMMENT 'value',
  `status` tinyint DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`key`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='系统配置信息表';

-- 系统日志
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COMMENT '用户名',
  `operation` varchar(50) COMMENT '用户操作',
  `method` varchar(200) COMMENT '请求方法',
  `params` varchar(5000) COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) COMMENT 'IP地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='系统日志';

-- 初始数据
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_time`) VALUES ('1', 'admin', '7e0ffd097b418c6433c4a3a2090a0b902d5b2e37c396730dac06cc8641bf4e0b', 'YzcmCZNvbXocrsz9dm8e', 'hn1987@126.com', '18610556658', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('1', '0', '系统管理', NULL, NULL, '0', 'fa fa-cog', '10');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', NULL, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('3', '1', '角色管理', 'modules/sys/role.html', NULL, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', NULL, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('15', '2', '查看', NULL, 'sys:user:list,sys:user:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('16', '2', '新增', NULL, 'sys:user:save,sys:role:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('17', '2', '修改', NULL, 'sys:user:update,sys:role:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('18', '2', '删除', NULL, 'sys:user:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('19', '3', '查看', NULL, 'sys:role:list,sys:role:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('20', '3', '新增', NULL, 'sys:role:save,sys:menu:perms', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('21', '3', '修改', NULL, 'sys:role:update,sys:menu:perms', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('22', '3', '删除', NULL, 'sys:role:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('23', '4', '查看', NULL, 'sys:menu:list,sys:menu:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('24', '4', '新增', NULL, 'sys:menu:save,sys:menu:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('25', '4', '修改', NULL, 'sys:menu:update,sys:menu:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('26', '4', '删除', NULL, 'sys:menu:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', NULL, '1', 'fa fa-codepen', '1');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('32', '31', '查看', NULL, 'sys:dept:list,sys:dept:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('33', '31', '新增', NULL, 'sys:dept:save,sys:dept:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('34', '31', '修改', NULL, 'sys:dept:update,sys:dept:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('35', '31', '删除', NULL, 'sys:dept:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('500', '0', '业务管理', NULL, NULL, '0', 'fa fa-opencart', '1');

insert into sys_role values(1, '工资导入', '', 0, '2017-08-30 12:12:12');
insert into sys_role values(2, '工资查询', '', 0, '2017-08-30 12:12:12');
insert into sys_role_menu values(1, 1, 500);
insert into sys_role_menu values(2, 1, 506);
insert into sys_role_menu values(3, 1, 507);
insert into sys_role_menu values(4, 1, 508);
insert into sys_role_menu values(5, 1, 1);
insert into sys_role_menu values(6, 1, 2);
insert into sys_role_menu values(7, 1, 15);
insert into sys_role_menu values(8, 1, 16);
insert into sys_role_menu values(9, 1, 17);
insert into sys_role_menu values(10, 1, 18);
insert into sys_role_menu values(11, 2, 500);
insert into sys_role_menu values(12, 2, 506);
insert into sys_role_menu values(13, 2, 507);
insert into sys_dept values(1, 0, '一级部门', 0, 0);


-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('500', '基础数据', 'modules/pm/basicdata.html', NULL, '1', 'fa fa-window-restore', '100');
set @parentId = @@identity;
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '查看', null, 'pm:basicdata:list,pm:basicdata:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '新增', null, 'pm:basicdata:save,pm:basicdata:select', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '修改', null, 'pm:basicdata:update,pm:basicdata:select', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '删除', null, 'pm:basicdata:delete', '2', null, '6';



-- 基础数据
DROP TABLE IF EXISTS `tbl_basic_data`;
CREATE TABLE IF NOT EXISTS `tbl_basic_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父ID，顶级为0',
  `ename` varchar(128) COMMENT '标识',
  `name` varchar(128) COMMENT '基础数据名称',
  `order_num` int COMMENT '排序',
  `create_time` datetime COMMENT '添加时间',
  `create_adminid` bigint DEFAULT 0 COMMENT '添加人',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT='基础数据';

insert into tbl_basic_data values (1, 0, 'wifi_group', '探针分组', 1, '2017-08-25 17:10:10', 1);



-- 工资
DROP TABLE IF EXISTS `tbl_wages`;
CREATE TABLE IF NOT EXISTS `tbl_wages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT 0 COMMENT '员工',
  `job_wage` double DEFAULT 0 COMMENT '职务工资',
  `basic_wage` double DEFAULT 0 COMMENT '基本工资',
  `level_wage` double DEFAULT 0 COMMENT '级别工资',
  `basics_wage` double DEFAULT 0 COMMENT '基础工资',
  `working_year_wage` double DEFAULT 0 COMMENT '工龄工资',
  `tech_level_wage` double DEFAULT 0 COMMENT '技术等级工资',
  `trainee_wage` double DEFAULT 0 COMMENT '见习工资',
  `live_wage` double DEFAULT 0 COMMENT '活工资',
  `zone_allowance` double DEFAULT 0 COMMENT '特区津贴',
  `retain_allowance` double DEFAULT 0 COMMENT '保留津贴',
  `bonus` double DEFAULT 0 COMMENT '工作性补贴(奖金)',
  `house_subsidy` double DEFAULT 0 COMMENT '改革性补贴(房补)',
  `life_subsidy` double DEFAULT 0 COMMENT '生活性补贴(物补)',
  `special_allowance` double DEFAULT 0 COMMENT '特岗津贴',
  `tmp_allowance` double DEFAULT 0 COMMENT '临岗津贴',
  `child_cost` double DEFAULT 0 COMMENT '独生子女费',
  `retire_cost_whole` double DEFAULT 0 COMMENT '离退休增资全国',
  `retire_cost_zone` double DEFAULT 0 COMMENT '离退休增资特区',
  `retire_cost` double DEFAULT 0 COMMENT '离退休补贴经费',
  `check_year_bonus` double DEFAULT 0 COMMENT '年度考核奖',
  `other_bonus` double DEFAULT 0 COMMENT '其他',
  `post_bonus` double DEFAULT 0 COMMENT '岗位津贴',
  `merit_pay` double DEFAULT 0 COMMENT '绩效工资',
  `wage_pay` double DEFAULT 0 COMMENT '薪级工资',
  `law_wage_pay` double DEFAULT 0 COMMENT '执法薪级工资',
  `difference` double DEFAULT 0 COMMENT '套转差额',
  `other_subsidy` double DEFAULT 0 COMMENT '其他增补',
  `pension_diff` double DEFAULT 0 COMMENT '养老补差',
  `basic_subsidy` double DEFAULT 0 COMMENT '基层补贴',
  `tmp_subsidy` double DEFAULT 0 COMMENT '临时补贴',
  `should_wage` double DEFAULT 0 COMMENT '应发工资',
  `pension_base` double DEFAULT 0 COMMENT '养老基数',
  `total_annuity` double DEFAULT 0 COMMENT '年金合计',
  `company_annuity` double DEFAULT 0 COMMENT '职业年金(单位)',
  `personal_annuity` double DEFAULT 0 COMMENT '职业年金(个人)',
  `personal_social` double DEFAULT 0 COMMENT '个人社保合计',
  `personal_medical` double DEFAULT 0 COMMENT '个人医疗',
  `personal_pension` double DEFAULT 0 COMMENT '个人养老',
  `personal_pension_case` double DEFAULT 0 COMMENT '个人统筹医疗',
  `income_tax` double DEFAULT 0 COMMENT '所得税',
  `anti_tax` double DEFAULT 0 COMMENT '反税',
  `other_deduction` double DEFAULT 0 COMMENT '其他应扣',
  `personal_fund` double DEFAULT 0 COMMENT '个人公积金',
  `replace_pay` double DEFAULT 0 COMMENT '代缴',
  `actual_wages` double DEFAULT 0 COMMENT '实发工资',
  `housing_subsidy` double DEFAULT 0 COMMENT '房改补贴',
  `company_fund` double DEFAULT 0 COMMENT '单位公积金',
  `company_social` double DEFAULT 0 COMMENT '单位社保合计',
  `company_medical` double DEFAULT 0 COMMENT '单位医疗',
  `company_pension` double DEFAULT 0 COMMENT '单位养老',
  `pay_status` tinyint DEFAULT 1 COMMENT '支付状态,0未支付,1已支付',
  `create_time` datetime COMMENT '添加时间',
  `create_adminid` bigint DEFAULT 0 COMMENT '添加人',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT='工资';


-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('500', '工资', 'modules/pm/wages.html', NULL, '1', 'fa fa-calculator', '7');
set @parentId = @@identity;
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '查看', null, 'pm:wages:list,pm:wages:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) SELECT @parentId, '导入', null, 'pm:wages:save', '2', null, '6';
