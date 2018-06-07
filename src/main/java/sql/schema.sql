
create DATABASE seckill;

use seckill;
create table seckill(
`seckill_id` bigint not null auto_increment comment '商品库存id',
`name` varchar(120) not null comment '商品名称',
`number` int not null comment '库存数量',
`start_time` timestamp not null comment '秒杀开启时间',
`end_time` timestamp not null comment '秒杀结束时间',
`create_time` timestamp not null default current_timestamp comment '创建时间',
primary key (`seckill_id`),
key idx_start_time(`start_time`),
key idx_end_time(`end_time`),
key idx_create_time(`create_time`)

)engine=InnoDB auto_increment=1000 default charset=utf8 comment='秒杀库存表';

insert into seckill(name,number,start_time,end_time)
values('2200元秒杀小米6',100,'2017-04-09 00:00:00', '2017-04-10 01:00:00'),
      ('500元秒杀红米note5',200,'2017-04-02 00:00:00', '2017-04-02 01:00:00'),
      ('300元秒杀小米手环3',100,'2018-05-15 00:00:00', '2018-05-15 01:00:00');

create table success_killed(
`seckill_id` bigint not null comment '秒杀商品id',
`user_phone` bigint not null comment '用户手机号',
`state` tinyint not null default 0 comment '状态标志:-1:无效, 0:成功 1:已付款',
`create_time` timestamp not null comment '创建时间',
primary key(seckill_id,user_phone)  comment '联合主键',
key idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment='秒杀成功明细表';


