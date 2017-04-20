DROP TABLE IF EXISTS `tb_grayscale_variable`;
CREATE TABLE `tb_grayscale_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `variable_name` varchar(64) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MYISAM DEFAULT CHARSET=utf8;
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(1,'TTimesOut','主叫催收号码次数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(2,'TTimesIn','被催收号码呼叫次数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(3,'TNumsCon','与几个催收号码有过联系',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(4,'TNumsCon_org','涉及催收号码的总机构数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(5,'TNumsCon_bank','与几家银行机构催收号码有过联系',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(6,'TNumsCon_cf','与几家消费金融机构催收号码有过联系',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(7,'TNumsCon_f','与几家委外催收机构催收号码有过联系',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(8,'TNumsCon_if','与几家互联网金融机构催收号码有过联系',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(9,'TNumsCon_orgtype','联系机构类型总数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(10,'TNumsIn','申请人收到催收号的总个数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(11,'MaxTimesCon','申请人联系次数最大的催收号的总时长',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(12,'TDaysCon','联系催收号的总天数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(13,'AddTNumsIn_org','新增机构数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(14,'MaxDaysIn','被每个催收号呼叫的天数的最大值',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(15,'TTimesIn_nonbank','非银机构呼入的总次数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(16,'TDurCon','联系催收手机总时长',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(17,'TTimesCon','联系催收号的总次数',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(18,'TDurIn','被催收号呼叫的总时长',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(19,'TDurIn_f','被催收机构呼叫的总时长',now(),now());
INSERT into tb_grayscale_variable (id,variable_name,description,update_time,create_time) value(20,'numsCon','联系人总个数',now(),now());


DROP TABLE IF EXISTS `tb_user_grayscale_stat_variable`;
CREATE TABLE `tb_user_grayscale_stat_variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `variable_date` varchar(64) NOT NULL,
  `variable_time` varchar(64) NOT NULL,
  `variable_dun` varchar(64) NOT NULL DEFAULT 'cn',
  `user_vkey` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `variable_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MYISAM  DEFAULT CHARSET=utf8;
