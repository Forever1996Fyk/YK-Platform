/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : rm-m5e91lmw2n84e7lp36o.mysql.rds.aliyuncs.com:3306
 Source Schema         : yk-platform

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 20/06/2020 14:26:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `id` varchar(32) NOT NULL COMMENT '数据库表标识',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) DEFAULT NULL COMMENT '生成功能作者',
  `options` varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表';

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column` (
  `id` varchar(32) NOT NULL COMMENT '列名标识',
  `table_id` varchar(64) DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `pk` char(1) DEFAULT NULL COMMENT '是否主键（1是）',
  `increment` char(1) DEFAULT NULL COMMENT '是否自增（1是）',
  `required` char(1) DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '鏄惁涓烘彃鍏ュ瓧娈碉紙1鏄級',
  `edit` char(1) DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `list` char(1) DEFAULT NULL COMMENT '是否列表字段（1是）',
  `query` char(1) DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代码生成业务表字段';

-- ----------------------------
-- Table structure for tb_action_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_action_log`;
CREATE TABLE `tb_action_log` (
  `id` varchar(32) NOT NULL COMMENT '日志标识',
  `name` varchar(200) DEFAULT NULL COMMENT '日志类型名称',
  `type` varchar(200) DEFAULT NULL COMMENT '日志类型',
  `action_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `ip_addr` varchar(200) DEFAULT NULL COMMENT '操作ip',
  `action_url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `action_location` varchar(255) DEFAULT NULL COMMENT '操作地点',
  `method` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `input_param` text COMMENT '输入参数',
  `output_param` text COMMENT '输出参数',
  `exception_info` text COMMENT '异常信息',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(4) DEFAULT '1' COMMENT '状态:0  已禁用 1 正在使用',
  `action_time` datetime DEFAULT NULL COMMENT '操作时间',
  `action_user_name` varchar(50) DEFAULT NULL COMMENT '操作人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';

-- ----------------------------
-- Table structure for tb_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict_data`;
CREATE TABLE `tb_dict_data` (
  `id` varchar(32) NOT NULL COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` int(1) DEFAULT '1' COMMENT '鏄惁榛樿锛?鏄?0鍚︼級',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '状态（1正常 2停用 0删除）',
  `create_user_id` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- ----------------------------
-- Records of tb_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `tb_dict_data` VALUES ('1270239925018693632', 1, '男', '1', 'sys_user_sex', '', 'primary', 1, '性别男', 1, NULL, '2020-06-09 14:22:52', NULL, '2020-06-09 14:40:37');
INSERT INTO `tb_dict_data` VALUES ('1270244354497318912', 2, '女', '2', 'sys_user_sex', '', 'primary', 0, '性别女', 1, NULL, '2020-06-09 14:40:28', NULL, '2020-06-09 14:40:28');
INSERT INTO `tb_dict_data` VALUES ('1270244961832538112', 1, '是', '1', 'sys_yes_no', '', 'success', 1, '系统默认是', 1, NULL, '2020-06-09 14:42:53', NULL, '2020-06-09 14:42:53');
INSERT INTO `tb_dict_data` VALUES ('1270245054522462208', 2, '否', '0', 'sys_yes_no', '', 'danger', 0, '系统默认否', 1, NULL, '2020-06-09 14:43:15', NULL, '2020-06-09 14:43:15');
INSERT INTO `tb_dict_data` VALUES ('1270283189667106816', 1, '删除', '0', 'sys_common_status', '', 'danger', 0, '系统删除状态', 1, NULL, '2020-06-09 17:14:47', NULL, '2020-06-09 17:15:17');
INSERT INTO `tb_dict_data` VALUES ('1270283449168695296', 2, '正常', '1', 'sys_common_status', '', 'primary', 1, '系统正常状态', 1, NULL, '2020-06-09 17:15:49', NULL, '2020-06-09 17:15:49');
INSERT INTO `tb_dict_data` VALUES ('1270283566168805376', 3, '禁用', '2', 'sys_common_status', '', 'warning', 0, '系统禁用状态', 1, NULL, '2020-06-09 17:16:17', NULL, '2020-06-09 17:16:17');
INSERT INTO `tb_dict_data` VALUES ('1272042392521936896', 1, '本地上传', 'local', 'file_upload_type', '', 'primary', 1, '本地上传', 1, NULL, '2020-06-14 13:45:14', NULL, '2020-06-14 13:45:14');
INSERT INTO `tb_dict_data` VALUES ('1272042545542729728', 2, 'fastDFS上传', 'fastdfs', 'file_upload_type', '', 'info', 0, 'fastDFS上传', 1, NULL, '2020-06-14 13:45:50', NULL, '2020-06-14 13:45:50');
INSERT INTO `tb_dict_data` VALUES ('1272042606766985216', 3, 'OSS上传', 'oss', 'file_upload_type', '', 'success', 0, 'OSS上传', 1, NULL, '2020-06-14 13:46:05', NULL, '2020-06-14 13:46:05');
INSERT INTO `tb_dict_data` VALUES ('1272772594055122944', 1, 'YK平台', 'yk-platform', 'bucket_name', '', 'default', 1, 'YK平台', 1, NULL, '2020-06-16 14:06:48', NULL, '2020-06-16 14:06:48');
INSERT INTO `tb_dict_data` VALUES ('1272772748346789888', 1, '标准', 'Standard', 'storage_type', '', 'primary', 1, '标准存储类型', 1, NULL, '2020-06-16 14:07:24', NULL, '2020-06-16 14:07:24');
INSERT INTO `tb_dict_data` VALUES ('1272772876021403648', 2, '低频访问', 'InfrequentAccess', 'storage_type', '', 'info', 0, '低频访问存储类型', 1, NULL, '2020-06-16 14:07:55', NULL, '2020-06-16 14:07:59');
INSERT INTO `tb_dict_data` VALUES ('1272772991947771904', 3, '归档', 'Archive', 'storage_type', '', 'info', 0, '归档存储类型', 1, NULL, '2020-06-16 14:08:22', NULL, '2020-06-16 14:08:22');
INSERT INTO `tb_dict_data` VALUES ('1272773155571765248', 1, '本地冗余', 'Local', 'data_redundancy_type', '', 'primary', 1, '本地冗余', 1, NULL, '2020-06-16 14:09:01', NULL, '2020-06-16 14:09:01');
INSERT INTO `tb_dict_data` VALUES ('1272773280754962432', 2, '同城冗余', 'Region', 'data_redundancy_type', '', 'info', 0, '同城冗余', 1, NULL, '2020-06-16 14:09:31', NULL, '2020-06-16 14:09:31');
INSERT INTO `tb_dict_data` VALUES ('1272773488796635136', 1, '公共读', 'PubR', 'canned_acl', '', 'primary', 1, '公共读', 1, NULL, '2020-06-16 14:10:21', NULL, '2020-06-16 14:10:21');
INSERT INTO `tb_dict_data` VALUES ('1272773552382283776', 2, '公共读写', 'PubRW', 'canned_acl', '', 'info', 0, '公共读写', 1, NULL, '2020-06-16 14:10:36', NULL, '2020-06-16 14:10:36');
INSERT INTO `tb_dict_data` VALUES ('1272773629075132416', 3, '私有', 'Private', 'canned_acl', '', 'danger', 0, '私有', 1, NULL, '2020-06-16 14:10:54', NULL, '2020-06-16 14:10:54');
INSERT INTO `tb_dict_data` VALUES ('1273509087002329088', 1, '新增', '1', 'sys_log_type', '', 'default', NULL, '新增', 1, NULL, '2020-06-18 14:53:21', NULL, '2020-06-18 14:53:21');
INSERT INTO `tb_dict_data` VALUES ('1273509137866653696', 2, '修改', '2', 'sys_log_type', '', 'default', NULL, '修改', 1, NULL, '2020-06-18 14:53:33', NULL, '2020-06-18 14:53:33');
INSERT INTO `tb_dict_data` VALUES ('1273509202060476416', 3, '删除', '3', 'sys_log_type', '', 'default', NULL, '删除', 1, NULL, '2020-06-18 14:53:49', NULL, '2020-06-18 14:53:49');
INSERT INTO `tb_dict_data` VALUES ('1273509266229133312', 4, '授权', '4', 'sys_log_type', '', 'default', NULL, '授权', 1, NULL, '2020-06-18 14:54:04', NULL, '2020-06-18 14:54:04');
INSERT INTO `tb_dict_data` VALUES ('1273509324035031040', 5, '导出', '5', 'sys_log_type', '', 'default', NULL, '导出', 1, NULL, '2020-06-18 14:54:18', NULL, '2020-06-18 14:54:18');
INSERT INTO `tb_dict_data` VALUES ('1273509369757138944', 6, '导入', '6', 'sys_log_type', '', 'default', NULL, '导入', 1, NULL, '2020-06-18 14:54:29', NULL, '2020-06-18 14:54:29');
INSERT INTO `tb_dict_data` VALUES ('1273509445137170432', 7, '生成代码', '7', 'sys_log_type', '', 'default', NULL, '生成代码', 1, NULL, '2020-06-18 14:54:47', NULL, '2020-06-18 14:54:47');
INSERT INTO `tb_dict_data` VALUES ('1273527344232955904', 8, '其他', '0', 'sys_log_type', '', 'default', NULL, '其他', 1, NULL, '2020-06-18 16:05:54', NULL, '2020-06-18 16:05:54');
INSERT INTO `tb_dict_data` VALUES ('1273892445398024192', 9, '强退', '8', 'sys_log_type', '', 'default', NULL, '强退', 1, NULL, '2020-06-19 16:16:41', NULL, '2020-06-19 16:16:41');
COMMIT;

-- ----------------------------
-- Table structure for tb_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict_type`;
CREATE TABLE `tb_dict_type` (
  `id` varchar(32) NOT NULL COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '状态（1正常 2停用 0删除）',
  `create_user_id` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of tb_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `tb_dict_type` VALUES ('1270200810239823872', '用户性别', 'sys_user_sex', '用户性别列表', 1, NULL, '2020-06-09 11:47:27', NULL, '2020-06-09 11:47:27');
INSERT INTO `tb_dict_type` VALUES ('1270244841384710144', '系统是否', 'sys_yes_no', '系统是否列表', 1, NULL, '2020-06-09 14:42:24', NULL, '2020-06-09 14:42:24');
INSERT INTO `tb_dict_type` VALUES ('1270283069282193408', '系统通用状态', 'sys_common_status', '系统通用状态列表', 1, NULL, '2020-06-09 17:14:19', NULL, '2020-06-09 17:17:51');
INSERT INTO `tb_dict_type` VALUES ('1272042117186850816', '文件上传类型', 'file_upload_type', '文件上传类型集合', 1, NULL, '2020-06-14 13:44:08', NULL, '2020-06-14 13:44:08');
INSERT INTO `tb_dict_type` VALUES ('1272772141615550464', '存储空间名称', 'bucket_name', '存储空间名称', 1, NULL, '2020-06-16 14:05:00', NULL, '2020-06-16 14:05:00');
INSERT INTO `tb_dict_type` VALUES ('1272772199794741248', '存储类型', 'storage_type', '存储类型', 1, NULL, '2020-06-16 14:05:14', NULL, '2020-06-16 14:05:14');
INSERT INTO `tb_dict_type` VALUES ('1272772257839714304', '数据容灾类型', 'data_redundancy_type', '数据容灾类型', 1, NULL, '2020-06-16 14:05:27', NULL, '2020-06-16 14:05:27');
INSERT INTO `tb_dict_type` VALUES ('1272772309911998464', '存储空间权限', 'canned_acl', '存储空间权限', 1, NULL, '2020-06-16 14:05:40', NULL, '2020-06-16 14:05:40');
INSERT INTO `tb_dict_type` VALUES ('1273508666074562560', '日志类型', 'sys_log_type', '日志类型集合', 1, NULL, '2020-06-18 14:51:41', NULL, '2020-06-18 14:51:41');
COMMIT;

-- ----------------------------
-- Table structure for tb_doc_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_doc_attachment`;
CREATE TABLE `tb_doc_attachment` (
  `id` varchar(32) NOT NULL COMMENT '文档唯一标识',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '所属id',
  `position_type` varchar(10) DEFAULT NULL COMMENT '附件位置类型',
  `attach_attr` varchar(20) DEFAULT '' COMMENT '文档属性',
  `attach_md5` varchar(100) DEFAULT '' COMMENT '文档文件MD5',
  `attach_sha1` varchar(100) DEFAULT '' COMMENT '文档文件sha1值',
  `attach_origin_title` varchar(100) DEFAULT '' COMMENT '文档原名',
  `attach_name` varchar(100) DEFAULT '' COMMENT '文档名称',
  `attach_size` bigint(20) DEFAULT NULL COMMENT '文档大小',
  `attach_suffix` varchar(10) DEFAULT '' COMMENT '文档后缀',
  `attach_url` varchar(255) DEFAULT '' COMMENT '文档地址',
  `attach_path` varchar(255) DEFAULT '' COMMENT '文档路径',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '状态（1正常 2停用 0删除）',
  `create_user_id` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档附件表';

-- ----------------------------
-- Table structure for tb_image_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_image_attachment`;
CREATE TABLE `tb_image_attachment` (
  `id` varchar(32) NOT NULL COMMENT '图片唯一标识',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '所属id',
  `position_type` varchar(10) DEFAULT NULL COMMENT '附件位置类型',
  `attach_attr` varchar(20) DEFAULT '' COMMENT '图片属性',
  `attach_md5` varchar(100) DEFAULT '' COMMENT '图片文件MD5',
  `attach_sha1` varchar(100) DEFAULT '' COMMENT '图片文件sha1值',
  `attach_origin_title` varchar(100) DEFAULT '' COMMENT '图片原名',
  `attach_name` varchar(100) DEFAULT '' COMMENT '图片名称',
  `attach_size` bigint(20) DEFAULT NULL COMMENT '图片大小',
  `attach_suffix` varchar(10) DEFAULT '' COMMENT '图片后缀',
  `attach_url` varchar(255) DEFAULT '' COMMENT '图片地址',
  `attach_path` varchar(255) DEFAULT '' COMMENT '图片路径',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '状态（1正常 2停用 0删除）',
  `create_user_id` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片附件表';

-- ----------------------------
-- Table structure for tb_login_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_info`;
CREATE TABLE `tb_login_info` (
  `id` varchar(32) NOT NULL COMMENT '访问唯一标识',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录账号',
  `ip_addr` varchar(50) DEFAULT NULL COMMENT '登录ip地址',
  `login_location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `msg` varchar(255) DEFAULT NULL COMMENT '提示消息',
  `status` int(1) DEFAULT '1' COMMENT '登录状态（1成功 0失败）)',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_menu` VALUES ('1270596319207100416', '1');
INSERT INTO `tb_role_menu` VALUES ('1270596319207100416', '11');
INSERT INTO `tb_role_menu` VALUES ('1270596319207100416', '1270167105366528000');
INSERT INTO `tb_role_menu` VALUES ('2', '1');
INSERT INTO `tb_role_menu` VALUES ('2', '11');
INSERT INTO `tb_role_menu` VALUES ('2', '12');
INSERT INTO `tb_role_menu` VALUES ('2', '1269574531580825600');
INSERT INTO `tb_role_menu` VALUES ('2', '1270167105366528000');
INSERT INTO `tb_role_menu` VALUES ('2', '1270177081841553408');
INSERT INTO `tb_role_menu` VALUES ('2', '1270177218714275840');
INSERT INTO `tb_role_menu` VALUES ('2', '1270177703668092928');
INSERT INTO `tb_role_menu` VALUES ('2', '1270177860107243520');
INSERT INTO `tb_role_menu` VALUES ('2', '1270178367186014208');
INSERT INTO `tb_role_menu` VALUES ('2', '1270178663521980416');
INSERT INTO `tb_role_menu` VALUES ('2', '1270197410949369856');
INSERT INTO `tb_role_menu` VALUES ('2', '1270335773694103552');
INSERT INTO `tb_role_menu` VALUES ('2', '2');
INSERT INTO `tb_role_menu` VALUES ('2', '21');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu` (
  `id` varchar(32) NOT NULL COMMENT '菜单唯一标识',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父菜单id',
  `menu_sort` int(4) DEFAULT NULL COMMENT '菜单显示顺序',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `target` varchar(20) DEFAULT NULL COMMENT '打开方式(menuItem页面标签, menuBlank新窗口)',
  `menu_type` int(1) DEFAULT NULL COMMENT '菜单类型(1目录, 2菜单, 3按钮)',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `status` int(1) DEFAULT '1' COMMENT '菜单状态(0删除, 1显示, 2隐藏)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of tb_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_menu` VALUES ('1', '系统管理', '0', 1, '#', 'menuItem', 1, NULL, 'fa fa-gear', 1, NULL, '1', '2020-06-03 16:02:15', '1', '2020-06-09 10:00:49');
INSERT INTO `tb_sys_menu` VALUES ('11', '用户管理', '1', 1, '/system/user', 'menuItem', 2, 'system:user:view', 'fa fa-user-circle', 1, NULL, '1', '2020-06-05 15:46:58', '1', '2020-06-09 09:33:12');
INSERT INTO `tb_sys_menu` VALUES ('12', '菜单管理', '1', 2, '/system/menu', 'menuItem', 2, 'system:menu:view', 'fa fa-fax', 1, NULL, '1', '2020-06-07 11:33:15', '1', '2020-06-09 10:12:44');
INSERT INTO `tb_sys_menu` VALUES ('1269574531580825600', '角色管理', '1', 3, '/system/role', 'menuItem', 2, 'system:role:view', 'fa fa-user', 1, NULL, NULL, NULL, NULL, '2020-06-09 10:15:18');
INSERT INTO `tb_sys_menu` VALUES ('1269594790908006400', '测试', '1', 4, '', 'menuItem', 2, '4', '', 0, NULL, NULL, '2020-06-07 19:39:20', NULL, '2020-06-07 19:39:20');
INSERT INTO `tb_sys_menu` VALUES ('1269594791788810240', '测试', '1', 4, '', 'menuItem', 2, '4', '', 0, NULL, NULL, '2020-06-07 19:39:20', NULL, '2020-06-07 19:39:20');
INSERT INTO `tb_sys_menu` VALUES ('1269596453815324672', '测试', '1', 4, '', 'menuItem', 2, '', '', 0, NULL, NULL, '2020-06-07 19:45:57', NULL, '2020-06-07 19:45:57');
INSERT INTO `tb_sys_menu` VALUES ('1270167105366528000', '用户查询', '11', 1, '', 'menuItem', 3, 'system:user:list', '', 1, NULL, NULL, '2020-06-09 09:33:31', NULL, '2020-06-09 09:33:31');
INSERT INTO `tb_sys_menu` VALUES ('1270174120272859136', '用户新增', '11', 2, '', 'menuItem', 3, 'system:user:add', '', 1, NULL, NULL, '2020-06-09 10:01:23', NULL, '2020-06-09 10:01:23');
INSERT INTO `tb_sys_menu` VALUES ('1270175065236967424', '用户修改', '11', 3, '', 'menuItem', 3, 'system:user:edit', '', 1, NULL, NULL, '2020-06-09 10:05:08', NULL, '2020-06-09 10:05:08');
INSERT INTO `tb_sys_menu` VALUES ('1270175171822620672', '用户删除', '11', 4, '', 'menuItem', 3, 'system:user:delete', '', 1, NULL, NULL, '2020-06-09 10:05:34', NULL, '2020-06-10 14:17:50');
INSERT INTO `tb_sys_menu` VALUES ('1270175259659735040', '用户批量删除', '11', 5, '', 'menuItem', 3, 'system:user:delete', '', 1, NULL, NULL, '2020-06-09 10:05:55', NULL, '2020-06-10 14:19:34');
INSERT INTO `tb_sys_menu` VALUES ('1270177081841553408', '菜单查询', '12', 1, '', 'menuItem', 3, 'system:menu:list', '', 1, NULL, NULL, '2020-06-09 10:13:09', NULL, '2020-06-09 10:13:09');
INSERT INTO `tb_sys_menu` VALUES ('1270177148036059136', '菜单新增', '12', 2, '', 'menuItem', 3, 'system:menu:add', '', 1, NULL, NULL, '2020-06-09 10:13:25', NULL, '2020-06-09 10:13:25');
INSERT INTO `tb_sys_menu` VALUES ('1270177218714275840', '菜单修改', '12', 3, '', 'menuItem', 3, 'system:menu:edit', '', 1, NULL, NULL, '2020-06-09 10:13:42', NULL, '2020-06-09 10:13:42');
INSERT INTO `tb_sys_menu` VALUES ('1270177297202286592', '菜单删除', '12', 4, '', 'menuItem', 3, 'system:menu:delete', '', 1, NULL, NULL, '2020-06-09 10:14:01', NULL, '2020-06-09 10:14:01');
INSERT INTO `tb_sys_menu` VALUES ('1270177395713904640', '菜单批量删除', '12', 5, '', 'menuItem', 3, 'system:menu:delete', '', 1, NULL, NULL, '2020-06-09 10:14:24', NULL, '2020-06-10 14:21:15');
INSERT INTO `tb_sys_menu` VALUES ('1270177703668092928', '角色查询', '1269574531580825600', 1, '', 'menuItem', 3, 'system:role:list', '', 1, NULL, NULL, '2020-06-09 10:15:37', NULL, '2020-06-09 10:15:37');
INSERT INTO `tb_sys_menu` VALUES ('1270177768562364416', '角色新增', '1269574531580825600', 2, '', 'menuItem', 3, 'system:role:add', '', 1, NULL, NULL, '2020-06-09 10:15:53', NULL, '2020-06-09 10:15:53');
INSERT INTO `tb_sys_menu` VALUES ('1270177860107243520', '角色修改', '1269574531580825600', 3, '', 'menuItem', 3, 'system:role:edit', '', 1, NULL, NULL, '2020-06-09 10:16:15', NULL, '2020-06-09 10:16:15');
INSERT INTO `tb_sys_menu` VALUES ('1270177936716206080', '角色删除', '1269574531580825600', 4, '', 'menuItem', 3, 'system:role:delete', '', 1, NULL, NULL, '2020-06-09 10:16:33', NULL, '2020-06-09 10:16:33');
INSERT INTO `tb_sys_menu` VALUES ('1270178029481627648', '角色批量删除', '1269574531580825600', 5, '', 'menuItem', 3, 'system:role:delete', '', 1, NULL, NULL, '2020-06-09 10:16:55', NULL, '2020-06-10 14:21:31');
INSERT INTO `tb_sys_menu` VALUES ('1270178367186014208', '生成查询', '21', 1, '', 'menuItem', 3, 'tool:gen:list', '', 1, NULL, NULL, '2020-06-09 10:18:16', NULL, '2020-06-09 10:18:16');
INSERT INTO `tb_sys_menu` VALUES ('1270178459276152832', '生成修改', '21', 2, '', 'menuItem', 3, 'tool:gen:edit', '', 1, NULL, NULL, '2020-06-09 10:18:38', NULL, '2020-06-09 10:18:38');
INSERT INTO `tb_sys_menu` VALUES ('1270178549294305280', '生成删除', '21', 3, '', 'menuItem', 3, 'tool:gen:delete', '', 1, NULL, NULL, '2020-06-09 10:18:59', NULL, '2020-06-09 10:18:59');
INSERT INTO `tb_sys_menu` VALUES ('1270178663521980416', '预览代码', '21', 4, '', 'menuItem', 3, 'tool:gen:preview', '', 1, NULL, NULL, '2020-06-09 10:19:26', NULL, '2020-06-09 10:19:26');
INSERT INTO `tb_sys_menu` VALUES ('1270178739963170816', '生成代码', '21', 5, '', 'menuItem', 3, 'tool:gen:code', '', 1, NULL, NULL, '2020-06-09 10:19:45', NULL, '2020-06-09 10:19:45');
INSERT INTO `tb_sys_menu` VALUES ('1270197410949369856', '字典管理', '1', 4, '/system/dict/type', 'menuItem', 2, 'system:dict:view', 'fa fa-window-restore', 1, NULL, NULL, '2020-06-09 11:33:56', NULL, '2020-06-09 11:34:33');
INSERT INTO `tb_sys_menu` VALUES ('1270335773694103552', '字典查询', '1270197410949369856', 1, '', 'menuItem', 3, 'system:dict:ist', '', 1, NULL, NULL, '2020-06-09 20:43:44', NULL, '2020-06-09 20:43:44');
INSERT INTO `tb_sys_menu` VALUES ('1270335934029762560', '新增字典', '1270197410949369856', 2, '', 'menuItem', 3, 'system:dict:add', '', 1, NULL, NULL, '2020-06-09 20:44:23', NULL, '2020-06-09 20:44:23');
INSERT INTO `tb_sys_menu` VALUES ('1270336026841321472', '修改字典', '1270197410949369856', 3, '', 'menuItem', 3, 'system:dict:edit', '', 1, NULL, NULL, '2020-06-09 20:44:45', NULL, '2020-06-09 20:44:45');
INSERT INTO `tb_sys_menu` VALUES ('1270336133443751936', '字典删除', '1270197410949369856', 4, '', 'menuItem', 3, 'system:dict:delete', '', 1, NULL, NULL, '2020-06-09 20:45:10', NULL, '2020-06-09 20:45:10');
INSERT INTO `tb_sys_menu` VALUES ('1270336230973902848', '字典批量删除', '1270197410949369856', 5, '', 'menuItem', 3, 'system:dict:delete', '', 1, NULL, NULL, '2020-06-09 20:45:33', NULL, '2020-06-10 14:21:24');
INSERT INTO `tb_sys_menu` VALUES ('1270990168505061376', '文件管理', '0', 3, '', 'menuItem', 1, '', 'fa fa-folder', 1, NULL, NULL, '2020-06-11 16:04:04', NULL, '2020-06-11 16:04:04');
INSERT INTO `tb_sys_menu` VALUES ('1270990856337362944', '视频管理', '1270990168505061376', 1, '/attachment/video/videoAttachment', 'menuItem', 2, 'attachment:video:videoAttachment', 'fa fa-television', 1, NULL, NULL, '2020-06-11 16:06:48', NULL, '2020-06-11 16:22:31');
INSERT INTO `tb_sys_menu` VALUES ('1271340087510175744', '图片管理', '1270990168505061376', 2, '/attachment/image/imageAttachment', 'menuItem', 2, 'attachment:image:view', 'fa fa-file-image-o', 1, NULL, NULL, '2020-06-12 15:14:31', NULL, '2020-06-12 15:15:15');
INSERT INTO `tb_sys_menu` VALUES ('1271340244406505472', '图片列表', '1271340087510175744', 1, '', 'menuItem', 3, 'attachment:image:list', '', 1, NULL, NULL, '2020-06-12 15:15:09', NULL, '2020-06-12 15:15:09');
INSERT INTO `tb_sys_menu` VALUES ('1271340359024250880', '上传图片', '1271340087510175744', 2, '', 'menuItem', 3, 'attachment:image:upload', '', 1, NULL, NULL, '2020-06-12 15:15:36', NULL, '2020-06-12 15:15:36');
INSERT INTO `tb_sys_menu` VALUES ('1271340457816887296', '删除图片', '1271340087510175744', 3, '', 'menuItem', 3, 'attachment:image:delete', '', 1, NULL, NULL, '2020-06-12 15:16:00', NULL, '2020-06-12 15:16:00');
INSERT INTO `tb_sys_menu` VALUES ('1271340520072941568', '批量删除图片', '1271340087510175744', 4, '', 'menuItem', 3, 'attachment:image:delete', '', 1, NULL, NULL, '2020-06-12 15:16:15', NULL, '2020-06-12 15:16:15');
INSERT INTO `tb_sys_menu` VALUES ('1271366356067028992', '视频列表', '1270990856337362944', 1, '', 'menuItem', 3, 'attachment:video:list', '', 1, NULL, NULL, '2020-06-12 16:58:54', NULL, '2020-06-15 10:53:08');
INSERT INTO `tb_sys_menu` VALUES ('1271366427475054592', '上传视频', '0', 2, '', 'menuItem', 3, 'attachment:video:upload', '', 0, NULL, NULL, '2020-06-12 16:59:11', NULL, '2020-06-12 16:59:11');
INSERT INTO `tb_sys_menu` VALUES ('1271366592957124608', '上传视频', '1270990856337362944', 2, '', 'menuItem', 3, 'attachment:video:upload', '', 1, NULL, NULL, '2020-06-12 16:59:51', NULL, '2020-06-12 16:59:51');
INSERT INTO `tb_sys_menu` VALUES ('1271366679221374976', '删除视频', '1270990856337362944', 3, '', 'menuItem', 3, 'attachment:video:delete', '', 1, NULL, NULL, '2020-06-12 17:00:11', NULL, '2020-06-12 17:00:11');
INSERT INTO `tb_sys_menu` VALUES ('1271366760670564352', '批量删除视频', '1270990856337362944', 4, '', 'menuItem', 3, 'attachment:video:delete', '', 1, NULL, NULL, '2020-06-12 17:00:31', NULL, '2020-06-12 17:00:31');
INSERT INTO `tb_sys_menu` VALUES ('1273130290738429952', '文档管理', '1270990168505061376', 3, '/attachment/doc/docAttachment', 'menuItem', 2, 'attachment:docs:view', 'fa fa-file-pdf-o', 1, NULL, NULL, '2020-06-17 13:48:09', NULL, '2020-06-17 15:25:36');
INSERT INTO `tb_sys_menu` VALUES ('1273130956894568448', '文档列表', '1273130290738429952', 1, '', 'menuItem', 3, 'attachment:doc:list', '', 1, NULL, NULL, '2020-06-17 13:50:48', NULL, '2020-06-17 15:24:40');
INSERT INTO `tb_sys_menu` VALUES ('1273131126969401344', '上传文档', '1273130290738429952', 2, '', 'menuItem', 3, 'attachment:doc:upload', '', 1, NULL, NULL, '2020-06-17 13:51:28', NULL, '2020-06-17 15:24:46');
INSERT INTO `tb_sys_menu` VALUES ('1273131262713856000', '删除文档', '1273130290738429952', 3, '', 'menuItem', 3, 'attachment:doc:delete', '', 1, NULL, NULL, '2020-06-17 13:52:01', NULL, '2020-06-17 15:24:51');
INSERT INTO `tb_sys_menu` VALUES ('1273131382658367488', '批量删除文档', '1273130290738429952', 4, '', 'menuItem', 3, 'attachment:doc:delete', '', 1, NULL, NULL, '2020-06-17 13:52:29', NULL, '2020-06-17 15:24:57');
INSERT INTO `tb_sys_menu` VALUES ('1273466364782297088', '日志管理', '0', 4, '', 'menuItem', 1, '', 'fa fa-window-restore', 1, NULL, NULL, '2020-06-18 12:03:35', NULL, '2020-06-18 12:03:35');
INSERT INTO `tb_sys_menu` VALUES ('1273491188774715392', '登录日志', '1273466364782297088', 1, 'system/loginInfo', 'menuItem', 2, 'system:loginInfo:view', '', 1, NULL, NULL, '2020-06-18 13:42:14', NULL, '2020-06-18 13:45:17');
INSERT INTO `tb_sys_menu` VALUES ('1273508322426826752', '操作日志', '1273466364782297088', 2, '/system/actionLog', 'menuItem', 2, 'system:actionLog:view', '', 1, NULL, NULL, '2020-06-18 14:50:19', NULL, '2020-06-18 14:50:19');
INSERT INTO `tb_sys_menu` VALUES ('1273621398547988480', '系统监控', '0', 5, '', 'menuItem', 1, '', 'fa fa-eye', 1, NULL, NULL, '2020-06-18 22:19:38', NULL, '2020-06-18 22:19:38');
INSERT INTO `tb_sys_menu` VALUES ('1273621790342119424', '在线用户', '1273621398547988480', 1, '/system/userOnline', 'menuItem', 2, 'system:online:view', 'fa fa-user-o', 1, NULL, NULL, '2020-06-18 22:21:12', NULL, '2020-06-18 22:21:12');
INSERT INTO `tb_sys_menu` VALUES ('1273895569156276224', '用户强退', '1273621398547988480', 2, '', 'menuItem', 3, 'monitor:online:forceLogout', '', 1, NULL, NULL, '2020-06-19 16:29:06', NULL, '2020-06-19 16:29:06');
INSERT INTO `tb_sys_menu` VALUES ('2', '系统工具', '0', 2, '#', 'menuItem', 1, NULL, 'fa fa-gears', 1, NULL, '1', '2020-06-03 16:02:39', '1', '2020-06-09 10:20:25');
INSERT INTO `tb_sys_menu` VALUES ('21', '代码生成', '2', 1, '/tool/gen', 'menuItem', 2, 'tool:gen:view', 'fa fa-wrench', 1, NULL, '1', '2020-06-03 16:59:59', '1', '2020-06-09 10:17:52');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role` (
  `id` varchar(32) NOT NULL COMMENT '系统角色表',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色权限code',
  `data_scope` int(1) DEFAULT '1' COMMENT '数据范围(1全部数据, 2自定义数据权限, 3本部门数据权限, 4本部门及以下数据权限)',
  `role_sort` int(4) DEFAULT NULL COMMENT '显示顺序',
  `status` int(1) DEFAULT '1' COMMENT '状态(0已删除,1正常)',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_role` VALUES ('1', '超级管理员', 'admin', 1, 1, 1, '超级管理员', NULL, '2020-06-07 20:00:09', NULL, '2020-06-07 20:00:09');
INSERT INTO `tb_sys_role` VALUES ('1270596319207100416', '审核员', 'auditor', NULL, 3, 1, NULL, NULL, '2020-06-10 13:59:03', NULL, '2020-06-18 09:03:43');
INSERT INTO `tb_sys_role` VALUES ('2', '游客', 'comstor', 1, 2, 1, '游客', NULL, NULL, NULL, '2020-06-09 21:04:39');
COMMIT;

-- ----------------------------
-- Table structure for tb_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_user`;
CREATE TABLE `tb_sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户唯一标识',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `account` varchar(255) DEFAULT NULL COMMENT '登录账号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `sex` int(1) DEFAULT '1' COMMENT '用户性别(1 男, 2女)',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像路径',
  `user_type` int(2) DEFAULT NULL COMMENT '用户类型',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登录ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐加密',
  `status` int(1) DEFAULT '1' COMMENT '账号状态(0已删除, 1正常, 2停用)',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of tb_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_sys_user` VALUES ('1', NULL, 'admin', 'admin', '8aa67f442d3c9b6c62fe24c8589baa21', 'admin', 'admin', 1, '1272115528722419712', NULL, '0:0:0:0:0:0:0:1', '2020-06-19 15:02:44', '881156', 1, NULL, '2020-06-09 21:15:51', NULL, '2020-06-19 15:02:44');
INSERT INTO `tb_sys_user` VALUES ('1268822560846516224', NULL, NULL, 'test', NULL, '17856941755', 'michaelkai@aliyun.com', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '2020-06-05 16:30:46', NULL, '2020-06-05 16:30:46');
INSERT INTO `tb_sys_user` VALUES ('1268825163617669120', NULL, NULL, 'test', NULL, '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '2020-06-05 16:41:07', NULL, '2020-06-05 16:41:07');
INSERT INTO `tb_sys_user` VALUES ('1268825184324947968', NULL, NULL, '2', NULL, '2', '2', NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, '2020-06-05 16:41:12', NULL, '2020-06-05 16:41:12');
INSERT INTO `tb_sys_user` VALUES ('1270164423239143424', NULL, NULL, 'test1', 'c3785e699c6059290bdbf22bdae7d468', '17856941754', '1111@qq.com', 2, NULL, NULL, NULL, NULL, '79b317', 1, NULL, '2020-06-09 09:22:51', NULL, '2020-06-10 08:50:37');
INSERT INTO `tb_sys_user` VALUES ('1270598496080236544', NULL, 'test', 'test', '698ee6a809e7a49c673a22f9c6cbbda8', 'test', 'test', 1, NULL, NULL, NULL, NULL, 'c2ddd2', 1, NULL, '2020-06-10 14:07:42', NULL, '2020-06-10 14:07:42');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_online
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_online`;
CREATE TABLE `tb_user_online` (
  `session_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ip_addr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '1' COMMENT '在线状态 在线:on_line,离线:off_line',
  `start_time` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线用户记录';

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` VALUES ('1270343140607397888', '1');
INSERT INTO `tb_user_role` VALUES ('1269912874608889856', '1270596319207100416');
INSERT INTO `tb_user_role` VALUES ('1270598496080236544', '1270596319207100416');
COMMIT;

-- ----------------------------
-- Table structure for tb_video_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_video_attachment`;
CREATE TABLE `tb_video_attachment` (
  `id` varchar(32) NOT NULL COMMENT '视频唯一标识',
  `owner_id` varchar(32) DEFAULT NULL COMMENT '所属id',
  `position_type` varchar(10) DEFAULT NULL COMMENT '附件位置类型',
  `attach_attr` varchar(20) DEFAULT '' COMMENT '视频属性',
  `attach_md5` varchar(100) DEFAULT '' COMMENT '视频文件MD5',
  `attach_sha1` varchar(100) DEFAULT '' COMMENT '视频文件sha1值',
  `attach_origin_title` varchar(100) DEFAULT '' COMMENT '视频原名',
  `attach_name` varchar(100) DEFAULT '' COMMENT '视频名称',
  `attach_size` bigint(20) DEFAULT NULL COMMENT '视频大小',
  `attach_suffix` varchar(10) DEFAULT '' COMMENT '视频后缀',
  `attach_url` varchar(255) DEFAULT '' COMMENT '视频地址',
  `attach_path` varchar(255) DEFAULT '' COMMENT '视频路径',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT '1' COMMENT '状态（1正常 2停用 0删除）',
  `create_user_id` varchar(32) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频附件表';


SET FOREIGN_KEY_CHECKS = 1;
