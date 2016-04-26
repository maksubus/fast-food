package org.zhokha.fastfood.service.exception;

/**
 * author: maks
 * date: 17.08.15
 */
public class BusinessLogicException  extends Exception {

    public BusinessLogicException() {
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessLogicException(Throwable cause) {
        super(cause);
    }

    public BusinessLogicException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
