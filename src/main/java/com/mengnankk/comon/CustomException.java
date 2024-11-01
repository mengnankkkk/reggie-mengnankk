package com.mengnankk.comon;

/**
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
public class CustomException extends RuntimeException {
    /**
     * 这个异常将会被{@link GlobalExceptionHandler}捕获，然后返回给前端。
     *
     * @param message 异常信息
     */
    public CustomException(String message) {
        super(message);
    }

}
