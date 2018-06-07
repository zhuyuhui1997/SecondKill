package dto;

/**
 * @Author: kl
 * @Date Created in 21:14 2018/5/17
 * @MOdified by:
 * @Description:
 */

/*
* 点击秒杀的最终结果,返回给客户端
*
* */
public class SeckillResult<T> {
    private boolean success; //是否秒杀成功
    private T data;  //返回的数据
    private  String error; //错误信息

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
