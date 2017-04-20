ALTER TABLE tb_admin MODIFY  create_time  timestamp not null DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE tb_admin MODIFY  update_time  timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE tb_site_resource MODIFY  create_time  timestamp not null DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE tb_site_resource MODIFY  update_time  timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE tb_user_permission MODIFY  create_date  timestamp not null DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE tb_user_permission MODIFY  update_time  timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


DROP TABLE IF EXISTS tb_user_grayscale_stat_variable;
CREATE TABLE tb_user_grayscale_stat_variable
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  variable_date VARCHAR(64) NOT NULL,
  variable_time VARCHAR(64) NOT NULL,
  user_vkey VARCHAR(255) NOT NULL,
  user_id INT(11) NOT NULL,
  update_time DATETIME,
  create_time DATETIME NOT NULL,
  variable_dun VARCHAR(64) DEFAULT 'cn' NOT NULL,
  variable_name VARCHAR(64) DEFAULT '' NOT NULL
)ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='用户染黑度统计变量';

DROP TABLE IF EXISTS tb_query_log;
CREATE TABLE tb_query_log
(
    lid INT(11) PRIMARY KEY NOT NULL COMMENT '日志ID' AUTO_INCREMENT,
    vkey VARCHAR(100) NOT NULL COMMENT '查询机构key',
    uname VARCHAR(250) COMMENT '被查询用户名',
    phone VARCHAR(50) NOT NULL COMMENT '被查询手机号',
    idcard VARCHAR(50) COMMENT '被查询身份证号',
    imei VARCHAR(50) COMMENT '被查询用户手机设备号',
    imsi VARCHAR(50) COMMENT '被查询用户SIM卡设备号',
    apply_date DATE COMMENT '贷款申请时间',
    query_time DATE NOT NULL COMMENT '查询时间',
    query_type INT(11) NOT NULL COMMENT '查询类型',
    query_source TINYINT(4) NOT NULL COMMENT '查询来源:0-API;1-界面',
    result_code INT(11) NOT NULL COMMENT '查询结果编码',
    elapsed_time INT(11) COMMENT '查询耗时,单位为毫秒',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间'
) ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='机构日志表';

DROP TABLE IF EXISTS tb_query_contact;
CREATE TABLE tb_query_contact
(
    cid INTEGER NOT NULL PRIMARY KEY COMMENT '联系人日志ID' AUTO_INCREMENT,
    lid INTEGER NOT NULL COMMENT '日志ID',
    cname VARCHAR(250) COMMENT '联系人姓名',
    cnumber VARCHAR(250) COMMENT '联系人电话号码',
    relation VARCHAR(50) COMMENT '与申请人的关系',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='机构查询联系人表';
ALTER TABLE tb_query_contact ADD CONSTRAINT FK_QUERY_LOG_2_CONTACT FOREIGN KEY (lid) REFERENCES tb_query_log (lid) ON UPDATE CASCADE ON DELETE RESTRICT;

DROP TABLE IF EXISTS tb_query_call_details;
CREATE TABLE tb_query_call_details
(
    cdid INTEGER NOT NULL PRIMARY KEY COMMENT '详单ID' AUTO_INCREMENT,
    lid INTEGER NOT NULL COMMENT '日志ID',
    call_time VARCHAR(100) DEFAULT NULL COMMENT '呼叫时间',
    duration INTEGER DEFAULT NULL COMMENT '呼叫时长',
    call_model VARCHAR(50) DEFAULT NULL COMMENT '呼叫模式:主叫/被叫',
    format_call_model VARCHAR(50) DEFAULT NULL COMMENT '格式化后的呼叫模式',
    contact VARCHAR(30) NOT NULL COMMENT '联系人',
    contact_type TINYINT NOT NULL COMMENT '联系人类型0-固话,1-手机',
    call_addr VARCHAR(100) DEFAULT NULL COMMENT '呼叫时机主所在地',
    contact_addr VARCHAR(100) DEFAULT NULL COMMENT '联系人归属地',
    called_type VARCHAR(100) DEFAULT NULL COMMENT '通话类型:国内/国际/...',
    cost DOUBLE DEFAULT NULL COMMENT '费用',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)ENGINE=MYISAM DEFAULT CHARSET=utf8 COMMENT='机构查询详单记录表';
ALTER TABLE tb_query_call_details ADD CONSTRAINT FK_QUERY_LOG_2_CALL_DETAILS FOREIGN KEY (lid) REFERENCES tb_query_log (lid) ON UPDATE CASCADE ON DELETE RESTRICT;