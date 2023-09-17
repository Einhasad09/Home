package com.jr.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接数据库工具类：
 */
public class BaseDao {

    private static final String URL="jdbc:mysql://127.0.0.1:3306/studz10b?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    //提取第一步：
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("jdbc.jar 没有导入，不能正常使用！");
        }
    }

    //提取第二步：
    public Connection getCon(){
        Connection con=null;
        try {
            con= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("连接数据库的 地址，用户名，密码有错，导致连接失败");
        }
        return con;
    }

    //提取4，5步
    //【增，删，改功能：】
    public int updateInfo(String sql,Object...objs){
        int i=0;
        Connection con=getCon();
        PreparedStatement ps=null;
        try {
            ps= con.prepareStatement(sql);
            for (int j = 0; j < objs.length; j++) {
                ps.setObject(j + 1, objs[j]);
            }
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行对象在 执行sql语句的时候，出现异常！");
        } finally {
            closeAll(con,ps,null);
        }
        return i;
    }

    //【查询功能】
    public List queryInfo(Class cla,String sql,Object...objs){
        List list=new ArrayList();
        Connection con=getCon();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            rs= ps.executeQuery();
            while (rs.next()) {
                Constructor c = cla.getConstructor();
                Object o = c.newInstance();
                Field[] fields = cla.getDeclaredFields();
                for (Field f : fields) {
                    Object value;
                    try {
                       value= rs.getObject(f.getName());
                    }catch (SQLException e){
                        continue;
                    }
                    f.setAccessible(true);

                    //处理java中类型LocalDate  于 数据库中date类型不匹配问题
     /*               if(value.getClass().toString().equals("class java.sql.Date")){
                        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        value=LocalDate.parse(value.toString(),dtf);
                    }*/
                    f.set(o,value);
                }
                list.add(o);
            }
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,ps,rs);
        }
        return list;
    }


    //【查询共条数功能】
    public int queryInfoCount(String sql,Object...objs){
        int count=0;
        Connection con=getCon();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i + 1, objs[i]);
            }
            rs= ps.executeQuery();
            if (rs.next()) {
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(con,ps,rs);
        }
        return count;
    }

    //提取第七步:
    public  void  closeAll(Connection con, PreparedStatement ps, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

/*    public List<String> getjons(String sql){

    }*/




}
