package online.dinghuiye.bingcollection.entity;

/**
 * @author Strangeen on 2018/02/25
 */
public class BingPullException extends RuntimeException {
    public BingPullException() {
        super();
    }

    public BingPullException(String message) {
        super(message);
    }

    public BingPullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BingPullException(Throwable cause) {
        super(cause);
    }

    protected BingPullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
