package org.mengnankk.comon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.Controller;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理SQL完整性约束异常
     * @param ex 异常对象
     * @return 自定义错误响应
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("SQLIntegrityConstraintViolationException: {}", ex.getMessage());
        if (ex.getMessage().contains("Doublicate entry")){
            String[] split = ex.getMessage().split("");
            String msg = split[2] + "已存在";
            return  R.error(msg);
        }
        return R.error("未知错误");
}
}
