package entity;

import java.util.Date;

/*
* 成功秒杀记录表的实体类
*
* */
public class SuccessKilled {
    private long seckillId;  //秒杀的id
    private long userphone;   //秒杀的用户手机号码
    private short state;  //秒杀的状态
    private Date createTime;  //秒杀的创建时间
    private Seckill seckill;  //秒杀记录对应的秒杀实体  多对一

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserphone() {
        return userphone;
    }

    public void setUserphone(long userphone) {
        this.userphone = userphone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userphone=" + userphone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
