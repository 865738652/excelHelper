/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.excelHelper.core.convert;

/**
 * 严重性转换
 * 数据库： Minor，Major，Critical
 * excel： 次要    主要    严重
 *
 * @author zhangtao
 * @since 2018-04-17 下午8:42
 */
public class SeverityConvertHandler implements ConvertHandler{

    public String convert(Object object) {
        String str = object.toString();
        if(str.equals("Minor")) {
            return "次要";
        }
        else if(str.equals("Major")) {
            return "主要";
        }
        else if(str.equals("Critical")) {
            return "严重";
        }
        return "未知类型";
    }
}