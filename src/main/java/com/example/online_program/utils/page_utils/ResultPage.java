package com.example.online_program.utils.page_utils;


import java.io.Serializable;
import java.util.List;

public class ResultPage<T> implements Serializable {
    int page;//起始页
    int limit;//页数大小
    int count;//数据数量
    String code;//代码
    String msg;//信息
    List<T> data;//返回数据
    T example;//任何类型条件

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public T getExample() {
        return example;
    }

    public void setExample(T example) {
        this.example = example;
    }
}

