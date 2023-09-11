package com.jr.entity;

import java.io.Serializable;

public class FileType implements Serializable {
    private int fileTypeId;
    private String fileType;

    public FileType(int fileTypeId, String fileType) {
        this.fileTypeId = fileTypeId;
        this.fileType = fileType;
    }

    public FileType() {
    }

}
