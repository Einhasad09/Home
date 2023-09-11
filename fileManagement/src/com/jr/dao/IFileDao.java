package com.jr.dao;

import com.jr.entity.File01;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IFileDao {
    /**
     * 载入文件——添加文件
     * */
    int addFile(File01 file) throws SQLException;

    /**按照文件名查询文件。
     * 输入项：文件名。
     * 展示项包括：文件标识、文件名称、文件类型、文件大小、加密状态、文件路径。
     * 选择文件后展示操作项，操作项包括：
     * 文件加密；
     * 文件解密；
     * 打开文件；
     * 删除文件。
     * */

    /**
     * 按文件名查询文件
     * */
    File01 searchByName(String fileName) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * 分页展示10条数据
     * */
   void showTenInfo(int page) throws SQLException;
   /**
    * 展示操作项
    * */
    void fileOperatorByName(int selectedFile,int selectedOperator) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;


    /**
     * 以下是按内容检索
     * */

    /**
     * 按内容查询文件
     */
    List<File01> searchByNameAndContest(String filecontent) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    /**
     * 展示10条数据
     */
    void showInfoByNameAndContest(int page) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    /**
     * 展示操作项
     */
    void fileOperatroByNameAndContent(String content,int selectedFile,int selectedOperator) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}
