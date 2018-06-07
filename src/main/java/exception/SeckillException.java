package exception;

import entity.Seckill;
import entity.SuccessKilled;

/*
* 秒杀业务 根异常
* */
public class SeckillException extends  RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}
