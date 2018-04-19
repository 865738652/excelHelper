package com.excelHelper.core;

import com.excelHelper.core.convert.ConvertHandler;

/**
 * 自定义的单元格
 * 使用一个通用的格式来保存Javabean对象，然后通过该对象再转换为单元格
 *
 * @author zhangtao
 * @since 2018-04-16 上午9:49
 */
public class MyCell {

    /**
     * 该单元格的类型
     */
    private Class<?> clazz;

    /**
     * 该单元格的值
     */
    private Object object;

    private Class<? extends ConvertHandler> convertHandler;


    public MyCell(Class<?> clazz, Object object) {
        this.clazz = clazz;
        this.object = object;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<? extends ConvertHandler> getConvertHandler() {
        return convertHandler;
    }

    public void setConvertHandler(Class<? extends ConvertHandler> convertHandler) {
        this.convertHandler = convertHandler;
    }
}