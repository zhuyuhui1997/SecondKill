package dao;

import entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


/*
*
* 秒杀产品 数据访问接口
* */
public interface SeckillDao {
    /*
    *减少库存
    * 返回1代表减少库存成功
    * 返回0代表减少库存失败
    *
    * */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);


    /*
    * 根据秒杀id查询秒杀商品信息
    *
    * */
    Seckill queryById(@Param("seckillId") long seckillId);

    /*
    * 查询所有参与秒杀的信息
    *
    * */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 使用存储过程进行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
