package com.excelHelper;

import com.excelHelper.core.WorkbookUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 *
 * @author zhangtao
 * @since 2018-04-17 上午10:47
 */
public class TestMain {

    public static void main(String[] args) {

        List<User> users = new ArrayList<User>();
        for(int i=0;i<100;i++) {
            User user = new User("用户"+i,"123456",i,getSeverity(i));
            users.add(user);
        }

        //想再excel保留的属性需在此设置
        List<String> titles = new ArrayList<String>();
        titles.add("用户名");
        titles.add("密码");
        titles.add("年龄");
        titles.add("严重性");
        try {
            Workbook workbook = WorkbookUtil.getWorkBook(User.class,users,titles);
            WorkbookUtil.saveExcel(workbook,"/Users/zhangtao/test.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getSeverity(int i) {
        if(i%3==0) {
            return "Minor";
        }
        else if(i%3==1) {
            return "Major";
        }
        else if(i%3==2) {
            return "Critical";
        }
        return "";
    }
}