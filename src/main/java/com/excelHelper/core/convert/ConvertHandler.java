package com.excelHelper.core.convert;

/**
 * 单元格数据转换的类
 * 例如javaBean中的有一个属性为整形，0代表男，1代表女。
 * 当MyCell对象需要转换为excel单元格的值时，则需要转换
 *
 * @author zhangtao
 * @since 2018-04-17 下午8:35
 */
public interface ConvertHandler {
    String convert(Object object);
}