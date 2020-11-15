package com.fh.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileLoad {
    public static void downLoadDerive(HttpServletResponse response, List<Object> list , String[] tatleArr, String[] fieldArr){
        try {
            if(list!=null &&list.size()>0){
                // 创建 存放的表格
                HSSFWorkbook wb =new HSSFWorkbook();
                //创建表格的第一页 如果需要很多页 在用wb.createSheet继续创建
                HSSFSheet sheet = wb.createSheet();
                HSSFRow row = sheet.createRow(0);
                //循环标题 tatleArr  创建标题行
                for (int i = 0; i < tatleArr.length; i++) {
                    String s = tatleArr[i];
                    //通过标题的长度循环创建row
                    HSSFCell cell = row.createCell(i);
                    //cell的值就是数值的值
                    cell.setCellValue(s);

                }
                for (int i = 0; i < list.size(); i++) {
                    Object obj =list.get(i);
                    //反射
                    //通过集合对象获取class
                    Class<?> aClass = obj.getClass();
                    //根据循环集合创建row 行 根据i+1  因为第一行的标题已经定义 需要在行的基础上加1
                    row = sheet.createRow(i + 1);

                    //循环属性集合
                    for (int j = 0; j < fieldArr.length; j++) {
                        //创建列 根据 j
                        HSSFCell cell = row.createCell(j);
                        String fileName = fieldArr[j];
                        //通过反射的class 获取全部属性
                        Field Field = aClass.getDeclaredField(fileName);
                        //对所有属性设置访问权限  当类中的成员变量为private时 必须设置此项
                        Field.setAccessible(true);
                        //根据 list集合中定义属性  获取属性的值
                        Object value = Field.get(obj);
                        cell.setCellValue(valueDate(value));
                    }
                }

                //关闭流
                outputStream(response,wb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //转换类型的方法
    public static  String valueDate(Object obj) {
    //判断object的对象不能为空  如果不判断会报空指针异常
        if (obj != null) {

            //instanceof 严格来说是Java中的一个双目运算符，用来测试一个对象是否为一个类的实例
            if (obj instanceof Date) {
                //如果是data类型 通过SimpleDateFormat 进行转换
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                return sim.format(obj);
            } else {
                //如果不是data类型 都转换为string 类型
                return obj.toString();
            }
        }
        return "";
    }

    //关闭输出流
    private static  void outputStream(HttpServletResponse response, HSSFWorkbook wb){
        //设置文件名
        //UUID.randomUUID() uuid 随机生成的随机数
        response.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID().toString() + ".xlsx");
        //设置
        response.setContentType("application/octet-stream;charset=UTF-8");

        ServletOutputStream out=null;
        try {
            out = response.getOutputStream();
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
