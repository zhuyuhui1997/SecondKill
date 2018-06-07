package dao;

import entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;


/*
*
* 秒杀成功 数据访问接口
* */
public interface SuccessKilledDao {

    /*
    * 插入秒杀成功信息
    *
    * 返回1,插入成功
    * 返回0,插入失败
    *
    * */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);


    /*
    *
    * 根据秒杀id和用户手机号查询秒杀记录信息
    * */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
