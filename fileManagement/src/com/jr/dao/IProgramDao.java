package com.jr.dao;

import com.jr.entity.Program;
import com.jr.entity.ProgramAndFile;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public interface IProgramDao {
    /**
     * 增加program 也就是添加program
     */

    int insertProgram(Program program);

    /**按 程序名称 和 文件类型 进行程序数据检索，然后展示(程序标识、程序名称、关联文件类型、程序路径)
    * 涉及到两个表 db_program 和 db_file
    * 分页展示
    * */
    /**关联文件类型
     * 修改用户选中的程序的关联文件类型。
     * 输入项：文件类型，关联多个程序可以用逗号分隔
     * */
    List<ProgramAndFile> searchProgramsByProgramNameAndFileType(String programName, int fileTypeId) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    //针对于 修改用户选中的程序的关联文件类型 做输出操作
    List<LinkedHashMap<String, Object>> searchProgramsByProgramNameAndFileTypeC() throws SQLException;
    /*启动应用程序 查询*/
    Program startProg(Program program) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    /**
     * 删除程序  删除
     * */
    int deleteProg(Program program) throws SQLException;

    /**
     * 修改用户选中的程序的关联文件类型。
     * 输入项：文件类型，关联多个可以用逗号分隔
     * 更新
     * */
    int associateFileType(String proName,int fileTypeId) throws SQLException;


    /**
     * 程序管理
     * 分页程序数据，每页10条数据。
     * 展示项包括：程序标识、程序名称、关联文件类型、程序路径。
     * */
    void showProgramInfo(int page) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    /**
     * 输入程序列表的 程序标识选择程序，选择文件后展示操作项，操作项包括：
     *文件加密；文件解密；打开文件；删除文件：
     **/
    void programOperatorByProgid(int proid) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;




}
