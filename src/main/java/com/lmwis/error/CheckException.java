package com.lmwis.error;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-03 18:54
 * @Version 1.0
 */
public class CheckException extends RuntimeException {

    private String filedName;

    private String filedValue;

    public CheckException(String filedName,String filedValue) {
        this.filedName = filedName;
        this.filedValue = filedValue;
    }


    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(String filedValue) {
        this.filedValue = filedValue;
    }

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    protected CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
