package com.example.demo.response.result;

import lombok.Getter;

import java.util.List;

@Getter
public class ListResult<T> extends CommonResult{
    private List<T> list;

    public ListResult(CommonResult commonResult, List<T> list){
        super(commonResult);
        this.list = list;
    }

}
