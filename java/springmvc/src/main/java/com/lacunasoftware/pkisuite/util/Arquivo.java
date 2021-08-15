package com.lacunasoftware.pkisuite.util;

public class Arquivo {

    private String base64Encode;
    private String filename;
    private String fileId;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBase64Encode() {
        return base64Encode;
    }

    public void setBase64Encode(String base64Encode) {
        this.base64Encode = base64Encode;
    }
}
