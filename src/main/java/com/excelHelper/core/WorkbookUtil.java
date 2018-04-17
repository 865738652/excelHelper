package com.excelHelper.core;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
/**
 * WorkbookUtil
 *
 * @author zhangtao
 * @since 2018-04-13 下午8:23
 */
public class WorkbookUtil {

    /**
     * <p>
     *      1.通过titles和实体类中的注解生成以下数据结构：用Map&lt;Integer,List&lt;MyCell&gt;&gt; 表示<br/>
     *          <table>
     *              <tr> <td>用户名</td> |<td>密码</td> </tr>
     *              <tr> <td>小张</td>  |<td>123456</td> </tr>
     *              ......
     *          </table>
     * </p>
     * <P>
     *     2.将Map&lt;Integer,List&lt;MyCell&gt;&gt;结构的数据转换为 Workbook对象
     * </P>
     * @param list 要生成excel的实体对象的集合
     * @param titles 要生成excel的表头，与实体类中属性使用的@ExcelProperty中的title属性相同，是其子集
     * @return Workbook
     * @throws Exception
     */
    public static <T> Workbook getWorkBook(List<T> list,List<String> titles) throws Exception{
        Workbook workbook = null;
        //获取一下转换对象的类类型
        Class clazz = list.get(0).getClass();
        //获取一下要转换的成excel的数据结构
        Map<Integer,List<MyCell>> map = getExcelGraph(clazz,list,titles);
        //将该数据结构转换为WorkBook
        workbook = getWorkBookByMap(map);
        return workbook;
    }

    /**
     *
     * @param clazz 要转换成excel数据的实体类的Class对象
     * @param listT 要转换成excel数据的对象的集合
     * @param titles excel表格的第一行的开头，同时也是限定导出数据和顺序，注意此时填写的数据要存在于该实体类的注解中相同
     * @return Map&lt;Integer,List&lt;MyCell&gt;&gt; map 要转换为Workbook的矩形的数据结构
     * @throws Exception
     */
    private static <T> Map<Integer,List<MyCell>> getExcelGraph(Class<?> clazz,List<T> listT,List<String> titles) throws Exception{
        Map<Integer,List<MyCell>> result = new LinkedHashMap<Integer, List<MyCell>>();
        List<MyCell> titleCells = new ArrayList<MyCell>();
        MyCell cell = null;
        //设置标题
        for(String title:titles) {
            cell = new MyCell(String.class,title);
            titleCells.add(cell);
        }
        result.put(0,titleCells);
        Field[] fields = clazz.getDeclaredFields();
        for(int i=1;i<=listT.size();i++) {
            T t = listT.get(i-1);
            List<MyCell> valueCells = new ArrayList<MyCell>();
            for(String title:titles) {
                for (Field field:fields) {
                    if (field.isAnnotationPresent(ExcelMapping.class)) {
                        ExcelMapping ep = (ExcelMapping) field.getAnnotation(ExcelMapping.class);
                        if(ep.title().equals(title)) {
                            field.setAccessible(true);
                            cell = new MyCell(field.getType(),field.get(t));
                            valueCells.add(cell);
                        }
                    }
                }
            }
            if(valueCells.size() != titleCells.size()) {
                System.out.println("警告：某些属性没有对应到");
            }
            result.put(i,valueCells);

        }
        return result;
    }

    /**
     * @param map 要转为Workbook的数据结构
     * @return
     */
    private static Workbook getWorkBookByMap(Map<Integer,List<MyCell>> map) {
        //生成标题
        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        try {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("sheet1");
            List<MyCell> list = map.get(0);

            //组装标题
            row = sheet.createRow(0);
            for(int i=0;i<list.size();i++) {
                cell = row.createCell(i);
                cell.setCellValue(list.get(i).getObject().toString());
            }

            //填充数据内容
            for(int i=1;i<map.keySet().size();i++) {
                row = sheet.createRow(i);
                list = map.get(i);
                for(int j=0;j<list.size();j++) {
                    cell = row.createCell(j);
                    cell.setCellValue( (list.get(j).getObject()==null)?"":list.get(j).getObject().toString() );
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }


    /***
     * 测试方法，将workbook写到输出流保存到本地
     * web开发多数情况下均为将workbook写入到response输出流中
     * @param workbook
     */
    @Deprecated
    public static void saveExcel(Workbook workbook,String filePath) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            workbook.write(out);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}