
DROP TABLE USERINFO;

--用户表
CREATE TABLE userInfo(
	userID number NOT NULL PRIMARY KEY,
	loginName varchar2(20) NOT NULL,
	loginPass varchar2(20) NOT NULL
)
insert into userInfo values(1,'admin','admin');
insert into userInfo values(2,'scott','scott');
select * from USERINFO;
SELECT * FROM userinfo WHERE loginName ='admin' AND loginPass = 'admin';
--餐品表
DROP TABLE foodInfo;

CREATE TABLE foodInfo(
	foodID number(20) NOT NULL PRIMARY KEY, --主键
	foodName varchar2(100) NOT NULL,          --餐品名称
	remark varchar2(255) NOT NULL,            --评论
	foodPrice NUMBER(18, 0) NOT NULL,         --价格
	description varchar2(255) NOT NULL,       --描述
	foodImage varchar2(20) NOT NULL           --餐品图片名
)
select * from foodInfo;
insert into foodInfo values(01,'土豆丝盖饭','好好吃哦',7,'美味可口！家常小菜，既含有丰富的碳水化合物又含有丰富的维生素经济而又实惠！家常小菜，既含有丰富的碳水化合物又含有丰富的维生素，美味可口！家常小菜，既含有丰富的碳水化合物又含有丰富的维生素，而且又经济实惠哦！','500008.jpg');
insert into foodInfo values(02,'青菜肉丝粥','原料：青菜、肉丝口味清淡，清香可口！',4,'青菜肉丝粥，清淡小粥，一份温馨！','500022.jpg');
insert into foodInfo values(03,'刀削面','原料：青菜、猪肉、海带、骨头汤口味：微辣、中辣、超辣。',6,'“左手托面，右手持刀，从上往下，往汤锅里削。”这就是刀削面，简单美味，还含有丰富的维生素B哦！','500023.jpg');
insert into foodInfo values(04,'拉面','配料：牛肉、骨头汤口味：微辣、中辣、超辣。',6,'营养丰富、风味独特、经济实惠、清淡可口、方便快捷！','500024.jpg');
insert into foodInfo values(05,'皮蛋瘦肉粥','味可口！',5,'皮蛋瘦肉粥，鲜美爽口的小粥！','500047.jpg');
insert into foodInfo values(06,'西红柿打卤面','配料：西红柿、鸡蛋口味：清淡。',7,'口味独特，经济实惠，简单营养。','500025.jpg');
insert into foodInfo values(07,'木须肉盖饭','原料：木耳、猪肉、青瓜、鸡蛋。口味清淡，美味营养。',8,'这是一款不错的营养配餐，营养价值极高，而且又美味哦！强烈推荐！','500033.jpg');
insert into foodInfo values(08,'清炒时蔬','时令绿色蔬菜！',5,'绿色蔬菜含有丰富的膳食纤维，每天食少量的蔬菜对身体益处多多！','500046.jpg');
insert into foodInfo values(09,'特色炒饭','原料：蘑菇、鸡蛋、胡萝卜、青椒、绿色蔬菜。口感极好！',7,'这是一款适合不同口味的美食，口感极好！','500034.jpg');
insert into foodInfo values(10,'米粉汤','原料：米粉、骨头汤、豆腐、肉丸汤味鲜美，口感极佳！ ',8,'汤味鲜香，美味营养！','500035.jpg');
insert into foodInfo values(11,'炸酱面','京味小吃！',8,'老北京炸酱面，正宗京味小吃！','500045.jpg');
insert into foodInfo values(12,'重庆小面','正宗重庆街头特色小面！',5,'汤味醇厚，麻辣可口！','500036.jpg');
insert into foodInfo values(13,'肉丝茄子','美味可口！',10,'风味家常小炒，口味极佳！','500044.jpg');
insert into foodInfo values(14,'创意炒饭','原料：鸡蛋、胡萝卜、青豆。。。口味适中，非常爽口！ ',7,'这是一款极具家庭味的炒饭，更多的是制作人的心思，让你有种温馨的感觉！','500038.jpg');
insert into foodInfo values(15,'西红柿炒鸡蛋','经典搭配！ ',6,'金黄的鸡蛋再配以新鲜的西红柿，经典的搭配，也绝对美味！','500043.jpg');
insert into foodInfo values(16,'酸豆角炒肉末盖饭','开胃可口！ ',8,'家常风味，是道非常开胃的美食！','500041.jpg');
insert into foodInfo values(17,'香油抄手','原料：鸡蛋、胡萝卜、青豆。。。口味适中，非常爽口！ ',4,'正宗川味小吃，陷大皮薄，再配以鲜美的汤料，是一道极佳的小吃！','500042.jpg');



--订单表
DROP TABLE foodOrderInfo;

CREATE TABLE foodOrderInfo(
	orderID number NOT NULL PRIMARY KEY,     --订单ID,主键
	customerName varchar2(20) NOT NULL,       --客户名称
	address varchar2(100) NOT NULL,           --地址
	zipCode varchar2(20) ,                    --邮编
	telephone varchar2(30) NOT NULL,          --固定电话
	mobile varchar2(30)  NOT NULL,             --手机
	notice varchar2(100),                     --留言
	totalPrice number(18,0) NOT NULL          --总金额
)
select * from FOODORDERINFO;
--通过页面输入
/*
insert into foodOrderinfo values(6,'孙天','北京海淀区北大东门','100089','010-98765555','132888888888','快些送来',8);
insert into foodOrderinfo values(10,'张三','北京市海淀区东王庄十三楼302室','100084','010-88888888','13788888888','所有的菜都不要太辣，微辣就可以了！另外，汤的味道不要太淡',39);
insert into foodOrderinfo values(11,'李四','北京市朝阳区惠新东桥百旺小区6栋601室','100063','010-65435432','13611119999','菜不要放太多辣椒',30);
insert into foodOrderinfo values(27,'李小鹏','北京市海淀区西苑医院','100081','010-88822222','13877772222','请在中午12：30之前送达',60);
*/
