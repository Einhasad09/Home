package com.jr.dao.Impl;

import com.jr.dao.IFileDao;
import com.jr.entity.File01;
import com.jr.entity.Program;
import com.jr.util.BaseDao;
import com.jr.util.FileOperator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileDaoImpl implements IFileDao {
    private BaseDao baseDao = new BaseDao();
    private FileOperator fileOperator = new FileOperator();

    /**载入文件
     * 添加文件
     * 通过insert into 表 where 字段 values ?进行添加
     * */
    @Override
    public int addFile(File01 file01) throws SQLException {
        String sql = "insert into db_file(filename,filesize,fileencrypt,filepath,fileencryptpath,textcontent) values(?,?,?,?,?,?)";
        Object[] objs = {file01.getFileName(),file01.getFileSize(),file01.getFileEncrypt(),file01.getFilePath(),file01.getFileEncryptPath(),file01.getTextcontent()};
        int i = baseDao.updateInfo(sql, objs);
        return i;
    }

    /**按照文件名查询文件
     * 通过select 查询范围 from 表 where 条件 进行查询
     * */
    @Override
    public File01 searchByName(String fileName) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File01 file011 = new File01();
        String sql = "select * from db_file where filename=?";
        List list = baseDao.queryInfo02(file011.getClass(), sql, fileName);
        return list.size()!=0? (File01) list.get(0) :null;
    }

    /**
     * 展示数据
     * */
    @Override
    public void showTenInfo(int page) throws SQLException {
        String sql = "select * from db_file where 1=1 limit 10 ";

        List<LinkedHashMap<String, Object>> linkedHashMaps = baseDao.queryInfo01(sql);
        for (Map<String, Object> row : linkedHashMaps) {
            for (String columnName : row.keySet()) {
                Object value = row.get(columnName);
                System.out.print(columnName + ":\t" + value + "\t");
            }
            System.out.println();
        }
    }


    /**展示操作项
     * selectedFile:选择的文件
     * selectedOperator:选择的操作
     * 1.文件加密2.文件解密3.打开文件4.删除文件
     * */
    @Override
    public void fileOperatorByName(int selectedFile, int selectedOperator) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Scanner input = new Scanner(System.in);
        switch (selectedOperator){
            case 1:
                fileOperator.encryptedReal(selectedFile);
                break;
            case 2:
                fileOperator.decryptReal(selectedFile);
                break;
            case 3:
                fileOperator.openFileReal(selectedFile);
                break;
            case 4:
                fileOperator.deleteReal(selectedFile);
                break;
        }

    }


 /**
  * 按文件名和内容查找(相似)
  * */
    public List<File01> searchByNameAndContest(String filecontent) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from db_file where filename like ? or textcontent like ? ";
        List list = baseDao.queryInfo02(File01.class, sql, "%" + filecontent + "%", "%" + filecontent + "%");

        return list;
    }


    /**
     * 展示数据
     * */
    public void showInfoByNameAndContest(int page) throws SQLException {
        String sql = "select fileid,filename,filetypeid,filesize,fileencrypt,filepath from db_file where 1=1 limit 10 ";
        List<LinkedHashMap<String, Object>> linkedHashMaps = baseDao.queryInfo01(sql);
        for (Map<String, Object> row : linkedHashMaps) {
            for (String columnName : row.keySet()) {
                Object value = row.get(columnName);
                System.out.print(columnName + ":\t" + value + "\t");
            }
            System.out.println();
        }
    }

    /**
     * content:查找内容
     * selectedFile选择的文件
     * selectedOperator选择的操作
     * 首先选择操作 打开 删除 加密 解密
     * 然后将选择文件的序号传给fileOperator类相应的方法中进行更加具体的处理
     * */
    @Override
    public void fileOperatroByNameAndContent(String content, int selectedFile, int selectedOperator) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Scanner input = new Scanner(System.in);
        switch (selectedOperator){
            case 1:
                fileOperator.encryptedContentReal(selectedFile);
                break;
            case 2:
                fileOperator.decryptContentReal(selectedFile);
                break;
            case 3:
                fileOperator.openContentReal(selectedFile);
                break;
            case 4:
                fileOperator.deleteContentReal(selectedFile);
                break;
        }
    }


}
