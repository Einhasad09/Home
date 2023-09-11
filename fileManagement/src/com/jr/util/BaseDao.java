
////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                  //
////////////////////////////////////////////////////////////////////


package com.jr.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class BaseDao {
    /**
     * 连接数据库
     */
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/filemanage?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static final String USERNAME= "root";
    private static final String PASSWORD= "root";

    /**
     * 加载驱动类
     * */
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提取相同方法 优化第二步：连接对象获得方法
     */
    private Connection getCon(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    /**
     * 提取相同方法
     * 增加 删除 修改
     * */
    public int updateInfo(String sql,Object...obj) throws SQLException {
        int i;
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        for (int j = 0; j < obj.length ; j++) {
            ps.setObject(j+1,obj[j]);
        }
        i = ps.executeUpdate();

        closeAll(con,ps,null);
        return i;
    }

    /**提取相同方法 查询：
     * BaseDao里有两种方法queryInfo和updateInfo
     * 把数据(属性)从数据库拿出来，然后赋给对象，然后遍历list集合
     * */
    public List queryInfo(Class cla,String sql,Object...obj) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList list = new ArrayList();
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        for (int j = 0;j < obj.length;j++){
            ps.setObject(j+1,obj[j]);
        }
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Constructor c = cla.getConstructor();
            Object objs = c.newInstance();

            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(objs,rs.getObject(field.getName()));
            }
            list.add(objs);
        }
        closeAll(con,ps,rs);
        return list;
    }


    /**
     * 改写queryInfo方法，只需要SQL语句就能进行查询
     * 返回了一个包含查询结果的List<Map<String, Object>>类型对象。
     * 每个Map对象都代表一行数据，其中键名是列名，对应的值是每个字段的取值。
     *
     * 从数据库中查询数据并将查询结果映射为一个List<LinkedHashMap<String, Object>>
     * 每个行都被映射为一个LinkedHashMap，其中键是列名，值是对应的数据
     * */

    public List<LinkedHashMap<String, Object>> queryInfo01(String sql) throws SQLException {
        List<LinkedHashMap<String, Object>> resultList = new ArrayList<>();
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ResultSetMetaData metaData = rs.getMetaData();//获取结果集的元数据。元数据包含了结果集中的列的信息
        int columnCount = metaData.getColumnCount();//获取结果集中的列的数量

        while (rs.next()) {//遍历结果集中的每一行数据
            //为当前的结果集 行 初始化一个LinkedHashMap，其中键是列的名称，值是列的数据
            LinkedHashMap<String, Object> row = new LinkedHashMap<>();
            //遍历当前行的所有列，并将列的名称和值放入LinkedHashMap中
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = rs.getObject(i);
                row.put(columnName, value);
            }
            resultList.add(row);
        }
        closeAll(con, ps, rs);
        return resultList;
    }


    /**
     * 针对于searchByName改写queryInfo
     * */
    public List queryInfo02(Class cla, String sql, Object... obj) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ArrayList list = new ArrayList();//初始化一个新的ArrayList，用于存储查询结果。
        Connection con = getCon();
        PreparedStatement ps = con.prepareStatement(sql);
        for (int j = 0; j < obj.length; j++) {
            ps.setObject(j + 1, obj[j]);
        }
        ResultSet rs = ps.executeQuery();//执行SQL查询获取结果集

        while (rs.next()) {
            Constructor c = cla.getConstructor();
            Object objs = c.newInstance();

            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // 设置私有字段可访问
                field.set(objs, rs.getObject(field.getName()));
            }
            list.add(objs);
        }
        closeAll(con, ps, rs);
        return list;
    }




    /**
     * 提取相同方法 优化第七步：关闭
     * */
    private static  void closeAll(Connection con, PreparedStatement ps, ResultSet rs){
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
}
