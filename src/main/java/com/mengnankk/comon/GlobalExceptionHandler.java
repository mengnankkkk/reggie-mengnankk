package com.mengnankk.comon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

        // 修正拼写错误 "Doublicate" -> "Duplicate"
        if (ex.getMessage().contains("Duplicate entry")) {
            // 使用空格分割错误信息以提取重复的值
            String[] split = ex.getMessage().split(" ");

            // 提取重复的值信息，通常在第2个索引位置
            String msg = split[2] + "已存在";
            return R.error(msg);
        }

        return R.error("未知错误");
    }
    @ExceptionHandler(CustomException.class)
    public R<String> execpttionHander(CustomException ex){
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
