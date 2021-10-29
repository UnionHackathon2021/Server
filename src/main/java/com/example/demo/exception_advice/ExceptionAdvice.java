package com.example.demo.exception_advice;

import com.example.demo.exception_advice.exception.MemberNotFoundException;
import com.example.demo.response.ResponseService;
import com.example.demo.response.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final ResponseService responseService;
    private final MessageSource messageSource;

    // code 정보에 해당하는 메시지를 조회한다.
    private String getMessage(String code){
        return getMessage(code, null);
    }

    // code 정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    // 알 수 없는 에러
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ExceptionHandler(Exception.class)
    public CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), e.getMessage());
    }

    // 사용자를 찾을 수 없습니다.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public CommonResult userNotFoundException(HttpServletRequest request, MemberNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("memberNotFound.code")), getMessage("memberNotFound.msg"));
    }

}
