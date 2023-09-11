package com.jr.service.Impl;

import com.jr.dao.Impl.ProgramDaoImpl;
import com.jr.entity.Program;
import com.jr.entity.ProgramAndFile;
import com.jr.service.IProgramService;
import com.jr.test.Login;
import com.jr.util.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class ProgramServiceImpl implements IProgramService {

    private ProgramDaoImpl pdi = new ProgramDaoImpl();
    private BaseDao baseDao = new BaseDao();
    private FileServiceImpl fsi = new FileServiceImpl();
    Login login = new Login();

    /**载入程序
    *增加program 也就是添加program
    *0false 1true
    * */
    @Override
    public boolean insertProgramReal(Program program) {
        return pdi.insertProgram(program)==0 ? false : true;
    }


    /**启动应用程序
     *查询
     * */
    @Override
    public Program startProgramReal(Program program) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return pdi.startProg(program);
    }


    /**删除程序
     *删除
     * */
    @Override
    public boolean deleteProgramReal(Program program) throws SQLException {
        return pdi.deleteProg(program)==0 ? false:true;
    }


    /**关联文件类型
     * 修改用户选中的程序的关联文件类型。
     * 输入项：文件类型，关联多个可以用逗号分隔
     * 更新
     */
    @Override
    public void associateFileTypeReal() throws SQLException {
        Login login = new Login();
        Scanner input = new Scanner(System.in);
        List<LinkedHashMap<String, Object>> linkedHashMaps = pdi.searchProgramsByProgramNameAndFileTypeC();
        for (LinkedHashMap<String, Object> linkedHashMap : linkedHashMaps) {
            System.out.println(linkedHashMap);
        }
        System.out.println("请选择一个程序的名字(proname)：");
        String proname = input.next();
        System.out.println("请选择一个文件类型(filetypeid)：");
        int fileTypeId = input.nextInt();
        int i = pdi.associateFileType(proname, fileTypeId);
        if(i == 0){
            System.out.println("修改失败");
            login.programSelect02();
        }else{
            System.out.println("修改成功");
            login.programSelect02();
        }
    }

    @Override
    public void showInfo(int page) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        pdi.showProgramInfo(page);
    }


    /**程序检索
     * 按 程序名称 和 文件类型 进行程序数据检索，然后展示(程序标识、程序名称、关联文件类型、程序路径)
     * 涉及到两个表 db_program 和 db_file
     *
     * 重载，同一个功能提供多种实现方法 带参数&&不带参数
     * */
    @Override
    public void searchProgramsByProgramNameAndFileType(String programName, int fileTypeId) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<ProgramAndFile> programs = pdi.searchProgramsByProgramNameAndFileType(programName, fileTypeId);
        System.out.println(programs);

    }

    @Override
    public void searchProgramsByProgramNameAndFileType() throws SQLException {
        List<LinkedHashMap<String, Object>> linkedHashMaps = pdi.searchProgramsByProgramNameAndFileTypeC();
        for (LinkedHashMap<String, Object> linkedHashMap : linkedHashMaps) {
            System.out.println(linkedHashMap);
        }
    }

    /**选择文件 输入程序列表的 程序标识选择程序，选择文件后展示操作项，操作项包括
     * 文件加密；
     * 文件解密；
     * 打开文件；
     * 删除文件：
     *
     * 然后根据程序标识在关联表里选择文件
     */
    @Override
    public void programOperator() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Scanner input= new Scanner(System.in);
        pdi.showProgramInfo(1);
        System.out.println("请输入选择的proid程序标识(请不要输入不存在的程序标识):");
        int selectedProid= input.nextInt();
        pdi.programOperatorByProgid(selectedProid);
            fsi.fileOperatorByNameReal();
    }

}
