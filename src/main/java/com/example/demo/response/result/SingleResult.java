package com.example.demo.response.result;

import lombok.Getter;

@Getter
public class SingleResult<T> extends CommonResult{
    private T data;

    public SingleResult(CommonResult commonResult, T data){
        super(commonResult);
        this.data = data;
    }
}
