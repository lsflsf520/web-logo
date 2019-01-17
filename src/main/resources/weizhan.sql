CREATE TABLE `channel_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增长',
  `uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户id(用于关联用户表)',
  `channel_id` int(10) unsigned NOT NULL COMMENT '类型，对应的渠道表ID',
  `channel_uid` varchar(64) NOT NULL COMMENT '第三方用户唯一标识',
  `nick_name` varchar(32) NOT NULL COMMENT '用户昵称。',
  `sex` enum('U','F','M') DEFAULT NULL COMMENT '性别:U=未知，M=男，F=女',
  `head_img` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `invite_uid` varchar(1000) NOT NULL DEFAULT '0' COMMENT '推荐人。将推荐人的推荐链路id按层级一层层存储起来，用英文逗号分隔',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168168 DEFAULT CHARSET=utf8mb4 COMMENT '用户信息表';

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(200) NOT NULL COMMENT '手机号，RSA加密存储',
  `passowrd` varchar(32) DEFAULT NULL COMMENT '密码，md5加密存储。APP推送过来的用户，这里初始为空',
  `real_name` varchar(200) DEFAULT NULL COMMENT '姓名，加密存储',
  `head_img` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `my_code` varchar(20) DEFAULT NULL COMMENT '当前用户邀请码',
  `invite_uid` varchar(2000) DEFAULT '0' COMMENT '推荐人（或服务工程师的uid）。将推荐人的推荐链路id按层级一层层存储起来，用英文逗号分隔',
  `utype` enum('User','BUser') DEFAULT NULL COMMENT '用户类型。普通用户、合作商户',
  `status` enum('Deleted','Freezed','Normal') DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_ind` (`phone`),
  UNIQUE KEY `my_code_ind` (`my_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68168168 DEFAULT CHARSET=utf8 COMMENT '用户信息表';

create table site_info(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `name` varchar(100) comment '站点名称',
  `short_name` varchar(32) comment '站点简称',
  `contact_name` varchar(32) comment '联系人',
  `contact_phone` varchar(11) comment '联系人手机号',
  `contact_addr` varchar(200) comment '联系人地址',
  `wx_qrcode` varchar(200) comment '联系人微信二维码地址',
  `qq` varchar(32) comment '联系人QQ',
  `banner` varchar(1000) comment '站点banner图地址，多张以英文都好隔开',
  `remark` text comment '站点简介',
  `title` text comment '站点首页seo的title',
  `keyword` text comment '站点首页seo的keyword',
  `description` text comment '站点首页seo的description',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '站点信息表';

create table img_text(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `it_type` enum('Image', 'Text') NOT NULL comment '图文类型。图片、文字'
  `img` varchar(200) comment '环境图片',
  `remark` text comment '针对图片的文字描述',
  `data_time` datetime comment '数据所属时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '图文信息表';

create table prod(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `name` varchar(200) comment '产品名称',
  `short_name` varchar(10) comment '产品简称',
  `img` varchar(1000) comment '产品图片地址。最多5张',
  `status` enum('Deleted','Normal') DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '产品信息表';

create table prod_param(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `prod_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '所属产品ID',
  `param_name` varchar(200) comment '参数名称',
  `param_val` varchar(2000) comment '参数对应的值',
  `main_param` enum('N', 'Y') comment '是否作为主参数，主参数不能超过2个',
  `priority` int(10) unsigned DEFAULT 50 COMMENT '排序。建议0~100，值越小越优先展示',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '产品参数表';

create table prod_detail(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `prod_id` int(10) unsigned NOT NULL COMMENT '所属产品ID',
  `detail` text comment '产品详情',
  `serv_flow` text comment '服务流程'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '产品详情表';

create table consult_config(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `field_name` varchar(32) NOT NULL COMMENT '参数名称',
  `option_type` enum('Text', 'Select', 'Checkbox', 'Radio') NOT NULL DEFAULT 'Text' COMMENT '选项类型。文本、下拉选择、复选、单选',
  `options` varchar(2000) COMMENT '参数对应的可选项。多个以英文逗号分隔',
  `priority` int(10) unsigned DEFAULT 50 COMMENT '排序。建议0~100，值越小越优先展示',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '咨询配置表';

create table consult_log(
  `id` int primary key auto_increment,
  `buid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '合作商户id(用于关联用户表)',
  `uid` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '用户id(用于关联用户表)',
  `content` varchar(5000) NOT NULL COMMENT '咨询内容',
  `quality` enum('A','B','C','D') NOT NULL COMMENT '用户质量。A：已成交客户；B：有意向客户；C：犹豫中的客户；D：无效客户',
  `remark` varchar(500) NOT NULL COMMENT '回访内容，标注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_uptime` datetime NOT NULL COMMENT '最后修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8 COMMENT '咨询记录表';


