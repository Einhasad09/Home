package com.jr.service.Impl;

import com.jr.dao.Impl.FileDaoImpl;
import com.jr.entity.File01;
import com.jr.service.IFileService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FileServiceImpl implements IFileService {

    FileDaoImpl fdi = new FileDaoImpl();

    @Override
    public int addFileReal(File01 file) throws SQLException {
        return fdi.addFile(file)==0?0:1;//0为假 1为真
    }

    @Override
    public void searchByName(String filename){
        File01 file01 = null;
        try {
            file01 = fdi.searchByName(filename);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(file01);

    }

    @Override
    public void showTenInfoReal(int page){
        try {
            fdi.showTenInfo(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fileOperatorByNameReal(){
        Scanner input =new Scanner(System.in);
        System.out.println("请选择文件(输入序号)：");
        int selectedFile = input.nextInt();
        System.out.println(" 1.文件加密(0加密状态、1解密解密状态) " +
                            "2.文件解密(需要fileencrypt为0的文件解密) " +
                            "3.打开文件 " +
                            "4.删除文件");
        System.out.println("请输入你的操作(输入序号)：");
        int selectedOperator = input.nextInt();
        try {
            fdi.fileOperatorByName(selectedFile,selectedOperator);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void searchByContent(){
        Scanner input =new Scanner(System.in);
        System.out.println("请输入要查询的内容(只能查询文件名和文本内容)：");
        String contentFile = input.next();

        List<File01> file01s = null;
        try {
            file01s = fdi.searchByNameAndContest(contentFile);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (File01 file01 : file01s) {
            System.out.println(file01);
        }
    }

    @Override
    public void showInfoByNameAndContestReal(int page){

        try {
            fdi.showInfoByNameAndContest(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void fileOperatorByContentReal()
    {
        Scanner input =new Scanner(System.in);
        System.out.println("请输入要查询的内容(只能查询文件名和文本内容)：");
        String contentFile = input.next();
        List<File01> file01s = null;
        try {
            file01s = fdi.searchByNameAndContest(contentFile);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (File01 file01 : file01s) {
            System.out.println(file01);
        }
        System.out.println("请选择文件(输入序号)：");
        int selectedFile = input.nextInt();
        System.out.println(" 1.文件加密(0加密状态、1解密状态i) " +
                            "2.文件解密(需要fileencrypt为0的文件解密) " +
                            "3.打开文件 " +
                            "4.删除文件");
        System.out.println("请输入你的操作(输入序号)：");
        int selectedOperator = input.nextInt();
        try {
            fdi.fileOperatroByNameAndContent(contentFile,selectedFile,selectedOperator);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
