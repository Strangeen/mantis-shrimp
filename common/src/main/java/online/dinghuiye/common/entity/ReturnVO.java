package online.dinghuiye.common.entity;

import online.dinghuiye.common.consts.ReturnStatusConsts;

/**
 * @author Strangeen on 2018/05/25
 */
public class ReturnVO {

    private static final ReturnVO SUCCESS = new ReturnVO(ReturnStatusConsts.SUCCESS_STAT, ReturnStatusConsts.SUCCESS_MSG);

    private int status;
    private String msg;

    private ReturnVO(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static ReturnVO valueOf(int status, String msg) {
        return new ReturnVO(status, msg);
    }

    public static ReturnVO valueOfSuccess() {
        return SUCCESS;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
