package com.sombre.shop.utils.exceptions.exceptions;

/**
 * Created by inna on 12.03.17.
 */
public class NotAdminException extends Exception {
    public NotAdminException() {
        super();
    }

    public NotAdminException(String message) {
        super(message);
    }

    public NotAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAdminException(Throwable cause) {
        super(cause);
    }

    protected NotAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
