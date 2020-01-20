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


