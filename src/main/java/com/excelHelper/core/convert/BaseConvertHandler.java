/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.excelHelper.core.convert;

/**
 * TODO zhangtao BaseConvertHandler
 *
 * @author zhangtao
 * @since 2018-04-17 下午8:57
 */
public class BaseConvertHandler implements ConvertHandler {
    public String convert(Object object) {
        if(object == null) {
            return "";
        }
        else {
            return object.toString();
        }

    }
}