package dto;


/*
* 暴露秒杀地址
*
* */
public class Exposer {
    private  boolean exposed;//是否暴露
    private String md5; //秒杀地址加密
    private long seckillId; //秒杀产品id
    private long now ; //当前时间戳
    private long start; //秒杀的开启时间
    private long end ;// 秒杀的结束时间



    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Exposer{" +
                "exposed=" + exposed +
                ", seckillId=" + seckillId);
        if (getMd5() != null)
            stringBuilder.append(", md5=" + getMd5());
        else {
            stringBuilder.append(", now=" + getNow()  + ", start=" + getStart() + ", end=" + getEnd());

        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}

