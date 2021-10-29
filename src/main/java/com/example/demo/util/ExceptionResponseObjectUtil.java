package com.example.demo.util;

import com.example.demo.response.ResponseService;
import com.example.demo.response.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExceptionResponseObjectUtil {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    private String getMessage(String code){
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public CommonResult getExceptionResponseObj(String code){
        return responseService.getFailResult(Integer.parseInt(getMessage(code + ".code")), getMessage(code + ".msg"));
    }

}
