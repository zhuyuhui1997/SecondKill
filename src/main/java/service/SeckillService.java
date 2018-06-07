package service;

import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;

import java.util.List;

/*
 *
 * 秒杀业务接口
 * */
public interface SeckillService {

    /*
     * 获取秒杀产品列表
     * */
    List<Seckill> getSeckillList();

    /*
    *
    * 查询某个秒杀产品详细信息
    * */
    Seckill getById(long seckillId);


    /*
    *
    * 根据秒杀id获取秒杀相关信息
    * */

    Exposer exportSeckillUrl(long seckillId);


    /*
    *
    * 执行秒杀
    *
    * */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws
            SeckillCloseException, RepeatKillException, SeckillException;

    /**
     * 使用存储过程秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckillByProducer(long seckillId, long userPhone, String md5);
}
