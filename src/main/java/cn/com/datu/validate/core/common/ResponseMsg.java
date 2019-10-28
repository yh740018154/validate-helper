package cn.com.datu.validate.core.common;

/**
 * @author yangheng
 * @Classname ResponseMsg
 * @Description TODO
 * @Date 2019/10/28 10:48
 * @group smart video north
 */
public class ResponseMsg {

    public ResponseMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
