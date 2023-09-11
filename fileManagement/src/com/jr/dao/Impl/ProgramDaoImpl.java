package com.jr.dao.Impl;

import com.jr.dao.IProgramDao;
import com.jr.entity.File01;
import com.jr.entity.Program;
import com.jr.entity.ProgramAndFile;
import com.jr.test.Login;
import com.jr.util.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProgramDaoImpl implements IProgramDao {

    private BaseDao baseDao = new BaseDao();


    /**载入程序
     * 增加program 也就是添加program
     * 使用insert
     * */
    @Override
    public int insertProgram(Program program) {
        String sql = "insert into db_program(proname,propath) values(?,?)";
        Object[] objs = {program.getProname(),program.getPropath()};
        int i = 0;
        try {
            i = baseDao.updateInfo(sql,objs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**启动应用程序
     * 查询
     * 使用select
     * */
    @Override
    public Program startProg(Program program) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from db_program where proname=? ";
        Object[] objs = {program.getProid()};
        List list = null;
        list = baseDao.queryInfo(program.getClass(),sql,objs);
        return list.size()!=0? (Program) list.get(0) :null;
    }

    /**删除程序
    *删除
    * */
    @Override
    public int deleteProg(Program program) throws SQLException {
        Scanner input = new Scanner(System.in);
        String sql = "delete from db_program where proname=?";
        Object[] obj = {program.getProname()};
        int i = baseDao.updateInfo(sql, obj);
        return i;
    }


    /**程序检索
     * 按 程序名称 和 文件类型 进行程序数据检索，然后展示(程序标识、程序名称、关联文件类型、程序路径)
     * 涉及到两个表 db_program 和 db_file
     *
     * searchProgramsByProgramNameAndFileType带参数、使用普通查询方法
     * searchProgramsByProgramNameAndFileTypeC不带参数、使用改写的queryInfo01方法查询
     * */
    @Override
    public List<ProgramAndFile> searchProgramsByProgramNameAndFileType(String programName, int fileTypeId) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql ="select db.programprimarykey, p.proname, f.filetypeid, p.propath " +
        "from db_filetype f, db_progandfiletype db, db_program p " +
                "where db.filetypeprimarykey = f.filetypeid " +
                "and db.programprimarykey = p.proid " +
                "and p.proname = ? " +
                "and f.filetypeid = ?";

        Object[] objs = {programName,fileTypeId};
        List list1 = baseDao.queryInfo(ProgramAndFile.class, sql, objs);
        System.out.println(list1.isEmpty());

        return list1;
    }
    public List<LinkedHashMap<String, Object>> searchProgramsByProgramNameAndFileTypeC() throws SQLException {
        String sql ="select db.programprimarykey, p.proname, f.filetypeid, p.propath " +
                "from db_filetype f, db_progandfiletype db, db_program p " +
                "where db.filetypeprimarykey = f.filetypeid " +
                "and db.programprimarykey = p.proid " ;

        List<LinkedHashMap<String, Object>> linkedHashMaps = baseDao.queryInfo01(sql);
        System.out.println(linkedHashMaps.isEmpty());

        return linkedHashMaps;
    }


    /**
     * 关联文件类型
     * 修改用户选中的程序的关联文件类型。
     * 输入项：文件类型，关联多个可以用逗号分隔
     * 更新
     * 得到了proname程序名，文件类型
     * */

    @Override
    public int associateFileType(String proName,int fileTypeId) throws SQLException {
        String sql = "UPDATE db_progandfiletype " +
                "SET filetypeprimarykey = (" +
                "    SELECT filetypeid " +
                "    FROM db_filetype " +
                "    WHERE filetypeid = ?" +
                ") " +
                "WHERE programprimarykey = (" +
                "    SELECT proid " +
                "    FROM db_program " +
                "    WHERE proname = ?" +
                ")";
        Object[] objs = {fileTypeId,proName};
        int i = baseDao.updateInfo(sql, objs);
        return i;

    }


   /**
    * 程序管理
    * 分页程序数据，每页10条数据。
    * 展示项包括：程序标识、程序名称、关联文件类型、程序路径。
   */
    @Override
    public void showProgramInfo(int page) throws SQLException {

        String sql = "select * from db_program where 1=1 limit 10 ";
        List<LinkedHashMap<String, Object>> linkedHashMaps = baseDao.queryInfo01(sql);
        for (Map<String, Object> row : linkedHashMaps) {
            for (String columnName : row.keySet()) {
                Object value = row.get(columnName);
                System.out.print(columnName + ":\t" + value+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 对于program的操作
     * 打开 删除 加密 解密
     * */
    @Override
    public void programOperatorByProgid(int proid) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "SELECT f.* FROM db_progandfiletype pf " +
                "JOIN db_filetype ft ON pf.filetypeprimarykey = ft.filetypeid " +
                "JOIN db_file f ON ft.filetypeid = f.filetypeid " +
                "WHERE pf.programprimarykey = ?";
        Object[] objs = {proid};
        List list = baseDao.queryInfo(File01.class, sql, objs);
        if (list.isEmpty()){
            System.out.println("程序没有关联的文件");

        }else {
            for (Object o : list) {
                System.out.println(o);
            }

        }
    }
}
