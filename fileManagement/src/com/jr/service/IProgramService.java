package com.jr.service;

import com.jr.entity.Program;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IProgramService {
    boolean insertProgramReal(Program program);

    void searchProgramsByProgramNameAndFileType(String programName, int fileTypeId) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    void searchProgramsByProgramNameAndFileType() throws SQLException;

    Program startProgramReal(Program program) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    boolean deleteProgramReal(Program program) throws SQLException;

    void associateFileTypeReal() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    void showInfo(int page) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    void programOperator() throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}
