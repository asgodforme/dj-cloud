package com.dj.cloud.common.exception;

import com.dj.cloud.common.vo.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Map<String, Object>> exceptionHandler(Exception e) throws IOException {
        System.out.println("未知异常！原因是:"+e);
        e.printStackTrace();
        try {
            CoreException coreException = (CoreException) e;
            return Result.newFailResult(coreException.getResponseCode(), coreException.getResponseMessage(), null);
        } catch (ClassCastException classCastException) {
            System.out.println(classCastException);
            return Result.newFailResult("ERROR", "系统异常，请联系管理员", null);
        }
    }
}
