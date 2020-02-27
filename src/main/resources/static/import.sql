DROP DATABASE IF EXISTS `order`;
CREATE DATABASE `order` CHARACTER SET utf8;
USE order;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
  `userid`   VARCHAR(20) NOT NULL,
  `username` VARCHAR(20) NOT NULL COMMENT '用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `identity` INT(1) NOT NULL COMMENT '身份',
   `enable`  INT(1) DEFAULT 0 COMMENT  '启用状态',
  PRIMARY KEY (`userid`),
  UNIQUE KEY(`username`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`(
  `adminid` VARCHAR(20) NOT NULL,
  `nickname` VARCHAR(50)  NOT NULL COMMENT '名称',
  `userid`    VARCHAR(20)NOT NULL UNIQUE ,
  `enable`  INT(1) DEFAULT 0 COMMENT  '启用状态',
   FOREIGN KEY(userid) REFERENCES users(userid),
  PRIMARY KEY (`adminid`)
)  ENGINE=INNODB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
`studentid` VARCHAR(20) NOT NULL,
`name`      VARCHAR(20)  NOT NULL COMMENT '姓名',
`sex`       INT(1) NOT NULL COMMENT '性别：1代表男2代表女',
`age`       INT(3) NOT NULL COMMENT '年龄',
`phone`     VARCHAR(15) NOT NULL COMMENT '手机号',
`face`      VARCHAR(150)    COMMENT '头像地址',
`creator`   VARCHAR(20)   COMMENT '创建人',
`creattime`  DATE    COMMENT '创建日期',
`enable`  INT(1) DEFAULT 0 COMMENT  '启用状态',
`userid`    VARCHAR(20)NOT NULL UNIQUE ,
 PRIMARY KEY(studentid),
 FOREIGN KEY(userid) REFERENCES users(userid),
  FOREIGN KEY(creator) REFERENCES admin(adminid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `coach`(
`coachid` VARCHAR(20) NOT NULL,
`name`      VARCHAR(20)  NOT NULL COMMENT '姓名',
`sex`       INT(1) NOT NULL COMMENT '性别：1代表男2代表女',
`age`       INT(3) NOT NULL COMMENT '年龄',
`phone`     VARCHAR(15) NOT NULL COMMENT '手机号',
`face`      VARCHAR(150)    COMMENT '头像地址',
`teachtype`   VARCHAR(6) NOT NULL COMMENT '教学类型',
`coachcar`  VARCHAR(10)  NOT NULL COMMENT '教练车',
`enable`  INT(1) DEFAULT 0 COMMENT  '启用状态',
`userid`    VARCHAR(20)NOT NULL UNIQUE ,
`creator`   VARCHAR(20)   COMMENT '创建人',
`creattime`  DATE    COMMENT '创建日期',
 PRIMARY KEY(coachid),
 FOREIGN KEY(userid) REFERENCES users(userid),
 FOREIGN KEY(creator) REFERENCES admin(adminid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `course`(
`courseid` VARCHAR(20)  NOT NULL,
`coachid`   VARCHAR(20) NOT NULL,
`studentid` VARCHAR(20),
`status`    INT(1) NOT NULL COMMENT '状态:1已通过2未通过3作废',
`licensetype`   VARCHAR(2)  COMMENT  '驾照类型',
`coursetype`    VARCHAR(10) NOT NULL COMMENT '课程类型',
`subject`   VARCHAR(10)  COMMENT '科目',
`creator`   VARCHAR(20)   COMMENT '创建人',
`creattime`  DATE    COMMENT '创建日期',
 PRIMARY KEY(courseid),
 FOREIGN KEY(studentid) REFERENCES student(studentid),
 FOREIGN KEY(coachid) REFERENCES coach(coachid),
  FOREIGN KEY(creator) REFERENCES admin(adminid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


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
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `evaluation`(
`evaluationid` VARCHAR(20) NOT NULL,
`date`      DATETIME  NOT NULL  COMMENT '评论日期',
`coachid` VARCHAR(20)  NOT NULL  COMMENT '学员id',
`studentid`   VARCHAR(20)  NOT NULL COMMENT '教练id',
`content`   VARCHAR(200) NOT NULL COMMENT '评论内容',
 PRIMARY KEY(evaluationid),
 FOREIGN KEY(coachid) REFERENCES coach(coachid),
 FOREIGN KEY(studentid) REFERENCES student(studentid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `notice`(
`noticeid`   VARCHAR(20)  NOT NULL,
`title`      VARCHAR(50)  NOT NULL  COMMENT '公告标题',
`content`    VARCHAR(2000)  NOT NULL  COMMENT '公告内容',
`creattime`  DATE         NOT NULL COMMENT '发布时间',
`creator`    VARCHAR(20)  NOT NULL COMMENT '发布人',
 PRIMARY KEY(noticeid),
 FOREIGN KEY(creator) REFERENCES admin(adminid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE subjectskill(
  id VARCHAR(20),
  category INT(1) NOT NULL,
  title     VARCHAR(50) NOT NULL,
  content  VARCHAR(500) NOT NULL,
  creator  VARCHAR(20)  NOT NULL,
  FOREIGN KEY(creator) REFERENCES admin(adminid),
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO `users` (`userid`, `username`, `password`, `identity`, `enable`) VALUES('superuser','1416993477','ICy5YqxZB1uWSwcVLSNLcA==','3','0');
INSERT INTO `admin` (`adminid`, `nickname`, `userid`, `enable`) VALUES('superadmin','超级管理员','superuser','0');

INSERT INTO `users`(`userid`,`username`,`password`,`identity`)
VALUES('default','default','ICy5YqxZB1uWSwcVLSNLcA==',1);

INSERT INTO `student`(`studentid`,`name`,`sex`,`age`,`phone`,`userid`)
VALUES('default','default',1,20,'default','default');



INSERT INTO notice (`noticeid`,`title`,`content`,`creattime`,`creator`)
VALUES
('temp001','驾校“五一劳动节”放假通知','新月驾校“劳动节”期间（2019.5.1）放假一天，
届时车辆停训，班车暂停运行，放假期间网络预约正常开放。','2020-01-07','superadmin'),
('temp002','驾校关于非法买卖驾照的公告','鉴于目前社会上有以新月驾校
名义刊登可以花钱买驾照等诈骗违法信息，新月驾校郑重声明我校一直秉承依法合规遵纪守法的经营
理念。提醒广大学员和群众按
国家政策法规办事，谨防上当受骗。','2020-01-07','superadmin'),
('temp003','关于开通网上法培教学的通知','科目一、科目四法培需要在驾
校开通在线学习功能，充值缴费后，方可在线学习！','2020-01-07','superadmin');





INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp001',2,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp002',2,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp003',2,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp004',3,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp005',3,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp006',3,'A','aaa','superadmin');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('temp007',2,'A','aaa','superadmin');



