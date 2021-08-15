package com.lacunasoftware.pkisuite.dto;

import org.springframework.web.bind.annotation.RequestParam;

public class PadesSignatureExpressCompleteDTO {

    private String fileToSign;
    private String transferFileId;
    private String signature;

    public String getFileToSign() {
        return fileToSign;
    }

    public void setFileToSign(String fileToSign) {
        this.fileToSign = fileToSign;
    }

    public String getTransferFileId() {
        return transferFileId;
    }

    public void setTransferFileId(String transferFileId) {
        this.transferFileId = transferFileId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
