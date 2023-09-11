package com.jr.service;

import com.jr.entity.File01;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IFileService {
    //载入文件——添加文件
    int addFileReal(File01 file) throws SQLException;

    //按文件名查询文件
    void searchByName(String filename) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

    //展示文件，每页10条数据
    void showTenInfoReal(int page) throws SQLException;

    void fileOperatorByNameReal() throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;


    void searchByContent() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    void fileOperatorByContentReal() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    void showInfoByNameAndContestReal(int page) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}
