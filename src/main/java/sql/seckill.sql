-- 秒杀存储过程
--;
DELIMITER $$ --; 转换为 $$

--定义存储过程
--返回上一条修改类型sql(delete,insert,update)的影响行数
--  <0 sql错误,未执行修改sql, 0就是未修改数据, >0 就是修改的行数
/*参数包括秒杀id, 秒杀手机号,秒杀时间戳, 输出参数result*/
create procedure `seckill`.`execute_seckill`
(in v_seckill_id bigint, in v_phone_bigint,
in v_kill_time timestamp , out r_result int)
  begin
    declare insert_count int default  0;
    start transaction ;
      insert ignore into success_killed(seckill_id, user_phone, create_time)
       value (v_seckill_id, v_phone, v_kill_time);
      select row_count() into insert_count;
    if (insert_count = 0) then rollback ;
    set r_result = -1;
    elseif(insert_count < 0) then rollback ;
    set r_result = -2;
    else
      update seckill set number = number - 1 where seckill_id = v_seckill_id and end_time > v_kill_time and start_time <
      v_kill_time and number > 0;
      select row_count() into insert_count;
      if (insert_count = 0)
      then rollback ; set r_result = 0;
      elseif (insert_count < 0) then rollback ; set r_result = -2;
      else commit; set r_result = 1;
      end if ;
    end if;
    end;

$$

delimiter;
set @r_result = -3;
call execute_seckill(1003, 135121212, now(), @r_result);
select @r_result;

--存储过程
--存储过程 优化 事务行级锁 持有的时间
--存储过程:银行,
---简单的逻辑 可以应用存储过程,
--qps 可以达到一个商品:接近6000 qps

