package com.jr.entity;

import java.io.Serializable;
/**
 * File01跟数据库中db_file表一一对应
 * */
public class File01 implements Serializable {
    private int fileId;
    private String fileName;
    private int fileTypeId;
    private int fileSize;
    private int fileEncrypt;
    private String filePath;
    private String fileEncryptPath;
    private String textcontent;

    public File01(int fileId, String fileName, int fileTypeId, int fileSize,int  fileEncrypt, String filePath, String fileEncryptPath, String textcontent) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileTypeId = fileTypeId;
        this.fileSize = fileSize;
        this.fileEncrypt = fileEncrypt;
        this.filePath = filePath;
        this.fileEncryptPath = fileEncryptPath;
        this.textcontent = textcontent;
    }

    public File01() {
    }

    public File01(String fileName1, int fileTypeId1, int fileSize1, int fileSize11, int fileEncrypt1, String filePath1, String fileEncryptPath1, String textcontent1) {
    }

    @Override
    public String toString() {
        return "File01{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileTypeId=" + fileTypeId +
                ", fileSize=" + fileSize +
                ", fileEncrypt='" + fileEncrypt + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileEncryptPath='" + fileEncryptPath + '\'' +
                ", textcontent='" + textcontent + '\'' +
                '}';
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(int fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileEncrypt() {
        return fileEncrypt;
    }

    public void setFileEncrypt(int fileEncrypt) {
        this.fileEncrypt = fileEncrypt;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileEncryptPath() {
        return fileEncryptPath;
    }

    public void setFileEncryptPath(String fileEncryptPath) {
        this.fileEncryptPath = fileEncryptPath;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }

    public File01(String fileName, int fileTypeId, int fileSize, int fileEncrypt, String filePath, String fileEncryptPath, String textcontent) {
        this.fileName = fileName;
        this.fileTypeId = fileTypeId;
        this.fileSize = fileSize;
        this.fileEncrypt = fileEncrypt;
        this.filePath = filePath;
        this.fileEncryptPath = fileEncryptPath;
        this.textcontent = textcontent;
    }
}
