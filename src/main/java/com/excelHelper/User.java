package com.excelHelper;


import com.excelHelper.core.ExcelMapping;

/**
 * 测试的实体类
 *
 * @author zhangtao
 * @since 2018-04-12 下午7:22
 */
public class User {

    @ExcelMapping(title = "用户名")
    private String userName;

    @ExcelMapping(title = "密码")
    private String passWord;

    @ExcelMapping(title = "年龄")
    private Integer age;

    public User(String userName, String passWord, Integer age) {
        this.userName = userName;
        this.passWord = passWord;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}