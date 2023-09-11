package com.jr.entity;

import java.io.Serializable;


/**
 * 这个类用来处理searchProgramsByProgramNameAndFileType这个方法中的问题
 * 以下的属性都跟数据库里program表和file表和programandfiletype表中属性对应
 * */
public class ProgramAndFile implements Serializable {
    private int programPrimaryKey; // 程序标识
    private String proName; // 程序名称
    private int fileTypeId; // 关联文件类型
    private String proPath; // 程序路径

    public ProgramAndFile(int programPrimaryKey, String proName, int fileTypeId, String proPath) {
        this.programPrimaryKey = programPrimaryKey;
        this.proName = proName;
        this.fileTypeId = fileTypeId;
        this.proPath = proPath;
    }

    public ProgramAndFile() {
    }

    @Override
    public String toString() {
        return "ProgramAndFile{" +
                "programPrimaryKey=" + programPrimaryKey +
                ", proName='" + proName + '\'' +
                ", fileTypeId=" + fileTypeId +
                ", proPath='" + proPath + '\'' +
                '}';
    }

    public int getProgramPrimaryKey() {
        return programPrimaryKey;
    }

    public void setProgramPrimaryKey(int programPrimaryKey) {
        this.programPrimaryKey = programPrimaryKey;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(int fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getProPath() {
        return proPath;
    }

    public void setProPath(String proPath) {
        this.proPath = proPath;
    }
}
