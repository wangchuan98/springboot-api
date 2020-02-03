DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
  `userid`   VARCHAR(20) NOT NULL,
  `username` VARCHAR(20) NOT NULL COMMENT '用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `identity` INT(1) NOT NULL COMMENT '身份',
   `enable`  INT(1) default 0 COMMENT  '启用状态',
  PRIMARY KEY (`userid`),
  UNIQUE KEY(`username`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`(
  `adminid` VARCHAR(20) NOT NULL,
  `nickname` varchar(50)  NOT NULL COMMENT '名称',
  `userid`    VARCHAR(20)NOT NULL UNIQUE ,
  `enable`  INT(1) default 0 COMMENT  '启用状态',
   FOREIGN KEY(userid) REFERENCES users(userid)，
  PRIMARY KEY (`adminid`)
)  ENGINE=INNODB  DEFAULT CHARSET=utf8;

DROP TABLE `student`
CREATE TABLE `student`(
`studentid` VARCHAR(20) NOT NULL,
`name`      VARCHAR(20)  NOT NULL COMMENT '姓名',
`sex`       INT(1) NOT NULL COMMENT '性别：1代表男2代表女',
`age`       INT(3) NOT NULL COMMENT '年龄',
`phone`     VARCHAR(15) NOT NULL COMMENT '手机号',
`face`      VARCHAR(50)    COMMENT '头像地址',
`enable`  INT(1) default 0 COMMENT  '启用状态',
`userid`    VARCHAR(20)NOT NULL UNIQUE ,
 PRIMARY KEY(studentid),
 FOREIGN KEY(userid) REFERENCES users(userid)
) ENGINE=INNODB DEFAULT CHARSET=utf8


CREATE TABLE `coach`(
`coachid` VARCHAR(20) NOT NULL,
`name`      VARCHAR(20)  NOT NULL COMMENT '姓名',
`sex`       INT(1) NOT NULL COMMENT '性别：1代表男2代表女',
`age`       INT(3) NOT NULL COMMENT '年龄',
`phone`     VARCHAR(15) NOT NULL COMMENT '手机号',
`face`      VARCHAR(50)    COMMENT '头像地址',
`coachage`  INT(3) NOT NULL  COMMENT '教龄',
`coachcar`  VARCHAR(10)  NOT NULL COMMENT '教练车',
`enable`  INT(1) default 0 COMMENT  '启用状态',
`userid`    VARCHAR(20)NOT NULL UNIQUE ,
 PRIMARY KEY(coachid),
 FOREIGN KEY(userid) REFERENCES users(userid)
) ENGINE=INNODB DEFAULT CHARSET=utf8

CREATE TABLE `course`(
`courseid` VARCHAR(20)  NOT NULL,
`studentid` VARCHAR(20) NOT NULL,
`coachid`   VARCHAR(20) NOT NULL,
`status`    INT(1) NOT NULL COMMENT '状态:1已通过2未通过3作废',
`licensetype`   VARCHAR(2) NOT NULL COMMENT  '驾照类型',
`coursetype`    VARCHAR(10) NOT NULL COMMENT '课程类型',
`subject`   VARCHAR(10) NOT  NULL COMMENT '科目',
 PRIMARY KEY(courseid),
 FOREIGN KEY(studentid) REFERENCES student(studentid),
 FOREIGN KEY(coachid) REFERENCES coach(coachid)
) ENGINE=INNODB DEFAULT CHARSET=utf8


CREATE TABLE `trainingorder`(
`orderid` VARCHAR(30) NOT NULL,
`date`      DATE  NOT NULL  COMMENT '订单日期',
`begintime` TIME  NOT NULL  COMMENT '开始时间',
`endtime`   TIME  NOT NULL COMMENT '结束时间',
`locationid`   VARCHAR(5) NOT NULL COMMENT '坐标',
`courseid` VARCHAR(20) ,
`status`    INT(1) NOT NULL COMMENT '状态:1未预约2已预约3调休',
 PRIMARY KEY(orderid),
 FOREIGN KEY(courseid) REFERENCES course(courseid)
) ENGINE=INNODB DEFAULT CHARSET=utf8



CREATE TABLE subjectskill(
  id VARCHAR(20),
  category INT(1) NOT NULL,
  title     VARCHAR(50) NOT NULL,
  content  VARCHAR(500) NOT NULL,
  creator  VARCHAR(20)  NOT NULL,
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8

