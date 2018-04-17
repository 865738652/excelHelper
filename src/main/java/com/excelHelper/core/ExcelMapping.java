package com.excelHelper.core;

import java.lang.annotation.*;

/**
 * <p>使用方式：例如实体类User中有userName属性，导出userName<p/>
 * <p>
 *      @ExcelProperty(excelTitle="用户名",fieldName="userName")
 *      private String userName;
 *      此时前台传送过来的参数为userName 与fieldName的中的值对应
 *      此时仅仅导出该字段，且该字段的表头为excelTitle中的内容对应
 * <p/>
 *
 * @author zhangtao
 * @since 2018-04-12 下午7:23
 */
@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMapping {

    /**
     * 注解信息为该字段对应的excel的表头
     */
    String title() default "未知表头";

    /**
     * 注解信息为该字段对应的前台传输过来的字段信息，目前并没有什么用
     */
    String property() default "未知字段";

}