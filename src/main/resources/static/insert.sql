<!--系统数据-->
INSERT INTO `users`(`userid`,`username`,`password`,`identity`)
VALUES('default','default','ICy5YqxZB1uWSwcVLSNLcA==',1)

INSERT INTO `student`(`studentid`,`name`,`sex`,`age`,`phone`,`userid`)
VALUES('default','default',1,20,'default','default')


INSERT INTO `users`(`userid`,`username`,`password`,`identity`)
VALUES('001','1416993477','ICy5YqxZB1uWSwcVLSNLcA==',3)
<!--admin-->
INSERT INTO admin(adminid,nickname,userid)
VALUES('a001','管理员1号','001')
<!--student-->
INSERT INTO `student`(`studentid`,`name`,`sex`,`age`,`phone`,`userid`)
VALUES('s002','周卫国',1,20,'13657905948','002')
<!--coach-->
INSERT INTO `coach`(`coachid`,`name`,`sex`,`age`,`phone`,`userid`,`coachage`,`coachcar`)
VALUES('c003','周杰伦',1,20,'13657905948','003',2,'学1024')
<!--course-->
INSERT INTO `course`(`courseid`,`studentid`,`coachid`,`status`,`licensetype`
,`coursetype`,`subject`)
VALUES('001','s002','c003',2,'C1','vip','科目二')





 INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001011400c003","2020-01-01","14:00:00","15:00:00",4,"001",2
        )    ;

         INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001021400c003","2020-01-02","14:00:00","15:00:00",4,"001",2
        )    ;

         INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001031400c003","2020-01-03","14:00:00","15:00:00",4,"001",2
        )    ;

         INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001041400c003","2020-01-04","14:00:00","15:00:00",4,"001",2
        ) ;

         INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001061400c003","2020-01-06","14:00:00","15:00:00",4,"001",2
        )  ;
          INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001071400c003","2020-01-07","14:00:00","15:00:00",4,"001",2
        )  ;
          INSERT INTO     trainingorder(`orderid`,
        `date`,`begintime`,`endtime`,`locationid`,`courseid`,`status`)
        VALUES("202001081400c003","2020-01-08","14:00:00","15:00:00",4,"001",2
        )  ;







INSERT INTO notice (`noticeid`,`title`,`content`,`createtime`,`creator`)
VALUES
('temp001','驾校“五一劳动节”放假通知','新月驾校“劳动节”期间（2019.5.1）放假一天，
届时车辆停训，班车暂停运行，放假期间网络预约正常开放。',"2020-01-07",'a001'),
('temp002','驾校关于非法买卖驾照的公告','鉴于目前社会上有以新月驾校
名义刊登可以花钱买驾照等诈骗违法信息，新月驾校郑重声明我校一直秉承依法合规遵纪守法的经营
理念。提醒广大学员和群众按
国家政策法规办事，谨防上当受骗。',"2020-01-07",'a001'),
('temp003','关于开通网上法培教学的通知','科目一、科目四法培需要在驾
校开通在线学习功能，充值缴费后，方可在线学习！',"2020-01-07",'a001')





INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('001',2,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('002',2,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('003',2,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('004',3,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('005',3,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('006',3,'A','aaa','管理员1');
INSERT INTO subjectskill(`id`,`category`,`title`,`content`,`creator`)
VALUES('007',2,'A','aaa','管理员1');


