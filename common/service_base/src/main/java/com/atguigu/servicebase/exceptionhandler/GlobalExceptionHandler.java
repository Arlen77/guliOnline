package com.atguigu.servicebase.exceptionhandler;


import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseBody //为了返回值
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("something wrong");
    }
}
