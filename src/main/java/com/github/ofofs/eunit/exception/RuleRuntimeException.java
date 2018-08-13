package com.github.ofofs.eunit.exception;

/**
 * <p> 规则运行时异常 </p>
 *
 * <pre> Created: 2018/8/13 上午10:37  </pre>
 * <pre> Project: eunit  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.8
 */
public class RuleRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6691533213750418930L;

    public RuleRuntimeException() {
    }

    public RuleRuntimeException(String message) {
        super(message);
    }

    public RuleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleRuntimeException(Throwable cause) {
        super(cause);
    }

    public RuleRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
