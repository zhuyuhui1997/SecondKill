package dao;

import entity.Seckill;
import entity.SuccessKilled;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest extends TestCase {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() {
        long seckillId = 1001;
        long userphone = 123456289l;
        int insertCount = successKilledDao.insertSuccessKilled(seckillId, userphone);
        System.out.println("insertCount= " + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() {
        long seckillId = 1001;
        long userPhone = 123456749l;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        if (successKilled != null) {
            System.out.println(successKilled.getSeckill());
            System.out.println(successKilled);
        }


    }
}