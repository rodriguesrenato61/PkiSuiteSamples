package com.lacunasoftware.pkisuite.dto;

public class PadesSignatureExpressStartDTO {

    private String fileToSign;
    private String certThumb;
    private String certContent;

    public String getCertThumb() {
        return certThumb;
    }

    public void setCertThumb(String certThumb) {
        this.certThumb = certThumb;
    }

    public String getCertContent() {
        return certContent;
    }

    public void setCertContent(String certContent) {
        this.certContent = certContent;
    }

    public String getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(String fileToSign) {
        this.fileToSign = fileToSign;
    }
}
