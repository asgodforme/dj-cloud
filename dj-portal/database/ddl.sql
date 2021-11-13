/*
Navicat MySQL Data Transfer

Source Server         : jiangjie
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : dj_portal

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2021-11-14 00:27:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `display_seq` varchar(255) DEFAULT NULL,
  `display` int(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/welcome', '欢迎', 'smile', 'Welcome', null, null, '1', null);
INSERT INTO `menu` VALUES ('2', '/admin', 'admin', 'smile', 'Admin', null, null, '1', null);
INSERT INTO `menu` VALUES ('3', '/admin/sub-page', 'sub-page', '', 'Welcome', '2', '1', '1', null);
INSERT INTO `menu` VALUES ('4', '/system', '系统管理', 'smile', 'Admin', null, null, '1', null);
INSERT INTO `menu` VALUES ('5', '/system/permission', '权限管理', '', 'Admin', '4', '1', '1', null);
INSERT INTO `menu` VALUES ('6', '/system/role', '角色管理', '', 'Admin', '4', '2', '1', null);
INSERT INTO `menu` VALUES ('7', '/peron/user', '用户管理', '', 'Admin', '4', '3', '1', null);
INSERT INTO `menu` VALUES ('8', '/service', '服务管理', 'smile', 'Admin', null, null, '1', null);
INSERT INTO `menu` VALUES ('9', '/service/system', '系统管理', '', 'Admin', '8', '1', '1', null);
INSERT INTO `menu` VALUES ('10', '/resource', '资源管理', 'smile', 'Admin', null, '', '1', null);
INSERT INTO `menu` VALUES ('11', '/service/environment', '环境管理', '', 'Admin', '8', '3', '1', null);
INSERT INTO `menu` VALUES ('12', '/schedule', '调度管理', 'smile', 'Admin', null, null, '1', null);
INSERT INTO `menu` VALUES ('13', '/schedule/work', '作业管理', '', 'Admin', '12', '1', '1', null);
INSERT INTO `menu` VALUES ('14', '/schedule/execute', '作业调度', '', 'Admin', '12', '2', '1', null);
INSERT INTO `menu` VALUES ('15', '/resource/front', '前端资源', '', 'Admin', '10', '1', '1', null);
INSERT INTO `menu` VALUES ('16', '/resource/background', '后端资源', '', 'Admin', '10', '2', '1', null);
INSERT INTO `menu` VALUES ('17', '/business', '业务管理', 'smile', 'Admin', null, null, '1', '系统交互api接口管理');
INSERT INTO `menu` VALUES ('18', '/business/server', '服务端', null, 'Admin', '17', '1', '1', '作为服务端，有哪些系统请求了我的服务');
INSERT INTO `menu` VALUES ('19', '/business/client', '客户端', null, 'Admin', '17', '2', '1', '作为客户端，我请求了哪些系统的服务');
INSERT INTO `menu` VALUES ('20', '/logger', '日志查询', 'smile', 'Welcome', null, null, '1', null);
INSERT INTO `menu` VALUES ('21', '/system/menuManager', '菜单管理', null, 'Welcome', '4', '4', '1', null);
