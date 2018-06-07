package service;

import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * @Author: kl
 * @Date Created in 19:43 2018/5/17
 * @MOdified by:
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class SeckillServiceTest {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private SeckillService seckillService;


    @Test
    public void getSeckillList() {
        List<Seckill> seckills = seckillService.getSeckillList();
        System.out.println(seckills);
    }

    @Test
    public void getById() {
        long seckillId = 1000;
        Seckill seckill = seckillService.getById(seckillId);
        System.out.println(seckill);
    }

    /**
     * 暴露地址测试
     *
     */
    @Test
    public void exportSeckillUrl() {
        long seckillId = 1004;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer);
    }

    /**
     * 秒杀测试
     *
     */
    @Test
    public void executeSeckill() {
        long seckillId = 1004;
        long userphone = 134567896;
        String md5 = seckillService.exportSeckillUrl(seckillId).getMd5();
        SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userphone, md5);
    }

    /**
     * 存储过程秒杀测试
     */
    @Test
    public void executeSeckillByProcedure() {
        long seckillId = 1001;
        long phone = 12345678912l;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillByProducer(seckillId, phone, md5);
            logger.info(execution.getStateInfo());
        }
    }
}