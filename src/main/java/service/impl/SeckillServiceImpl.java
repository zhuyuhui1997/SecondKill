package service.impl;

import dao.SeckillDao;
import dao.SuccessKilledDao;
import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import entity.SuccessKilled;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import exception.SeckillException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.SeckillService;
import util.SeckillStateEnum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 秒杀业务实现类
*
* */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = Logger.getLogger(SeckillServiceImpl.class);

    private final String salt = "shouldseckill+-";  //盐值,防止md5被轻易破解

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;


    /*
    * 获取所有秒杀产品的信息
    * */
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    /*
    * 返回秒杀 相关信息
    *
    * */
    public Exposer exportSeckillUrl(long seckillId) {
       Seckill seckill = seckillDao.queryById(seckillId);

       /*
       * 如果没有这个秒杀产品
       * */
       if (seckill == null) {
           return new Exposer(false, seckillId);
       }

       Date startTime = seckill.getStartTime();
       Date endTime = seckill.getEndTime();
       Date now = new Date();
       /*
       * 如果秒杀未开启或者已经关闭
       * 返回秒杀的开启时间,当前时间,秒杀的结束时间给客户端
       *
       * */
       if (startTime.getTime() > now.getTime() || endTime.getTime() < now.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
       }

        //获取秒杀id 相关的加密md5值

       String md5 = getMD5(seckillId);

       //暴露秒杀相关信息
       return new Exposer(true, md5, seckillId);
    }


    /*
    * 执行秒杀的方法,根据客户端传来的md5值判断
    * 没有开发秒杀接口的情况下,用户无法获得产品对应的秒杀md5值
    *只有开发了秒杀接口(秒杀时间范围内) ,用户才可以获得秒杀的md5值,进行秒杀
    *
    * */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
       if (md5 == null || !md5.equals(getMD5(seckillId))) {
           throw new SeckillException("秒杀数据错误");
       }
       Date now = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, now);

            //减少库存失败
            if (updateCount <= 0){
                throw  new SeckillCloseException("秒杀已经结束");
            }
            else {

                //减少库存成功,插入秒杀成功记录到 秒杀记录表
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //插入失败,就是说明重复秒杀
                if (insertCount <= 0)
                    throw new RepeatKillException("秒杀信息重复,请勿重复秒杀");
                else {
                    //插入成功,获取秒杀成功信息
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, 1, "秒杀成功", successKilled);
                }
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (SeckillException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }

    }



    //获取 混入盐值 的秒杀id相关md5值
    //私有方法
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            byte[] bytes = messageDigest.digest(base.getBytes());
            String md5 = byteToHexString(bytes);
            return md5;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    //字节转16进制字符串
    private  String byteToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        String base = "0123456789abcdef";
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            int high = b >>> 4 &0x0000000f;
            int low = b & 0x0f;
            stringBuilder.append(base.charAt(high) + "" + base.charAt(low));

        }
        return stringBuilder.toString();

    }

    public SeckillExecution executeSeckillByProducer(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId)))
            return new SeckillExecution(seckillId, SeckillStateEnum.DATE_REWRITE);
        Date killTime = new Date();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("seckillId", seckillId);
        map.put("phone", userPhone);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            seckillDao.killByProcedure(map);
            int result = (Integer) map.get("result");
            if (result == 1) {
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
            } else {
                return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
    }
}
