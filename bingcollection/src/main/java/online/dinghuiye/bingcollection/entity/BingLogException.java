package online.dinghuiye.bingcollection.entity;

/**
 * @author Strangeen on 2018/02/25
 */
public class BingLogException extends RuntimeException {
    public BingLogException() {
        super();
    }

    public BingLogException(String message) {
        super(message);
    }

    public BingLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BingLogException(Throwable cause) {
        super(cause);
    }

    protected BingLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
