package com.trimhelp.starter.base.controller;

import cn.hutool.db.PageResult;

import java.util.List;
import java.util.Map;

public abstract class BaseControllerWrapper {


    private PageResult<Map<String, Object>> pageResult = null;

    private Map<String, Object> single = null;

    private List<Map<String, Object>> multi = null;

    public BaseControllerWrapper(Map<String, Object> single) {
        this.single = single;
    }

    public BaseControllerWrapper(List<Map<String, Object>> multi) {
        this.multi = multi;
    }



    @SuppressWarnings("unchecked")
    public <T> T wrap() {

        /**
         * 包装结果
         */
        if (single != null) {
            wrapTheMap(single);
        }
        if (multi != null) {
            for (Map<String, Object> map : multi) {
                wrapTheMap(map);
            }
        }

        /**
         * 根据请求的参数响应
         */
        if (pageResult != null) {
            return (T) pageResult;
        }
        if (single != null) {
            return (T) single;
        }
        if (multi != null) {
            return (T) multi;
        }

        return null;
    }

    protected abstract void wrapTheMap(Map<String, Object> map);
}
