package com.lacunasoftware.pkisuite.dto;

public class PadesSignatureExpressStartResponseDTO {

    private String certThumb;
    private String certContent;
    private String toSignHash;
    private String transferFile;
    private String digestAlgorithm;

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

    public String getToSignHash() {
        return toSignHash;
    }

    public void setToSignHash(String toSignHash) {
        this.toSignHash = toSignHash;
    }

    public String getTransferFile() {
        return transferFile;
    }

    public void setTransferFile(String transferFile) {
        this.transferFile = transferFile;
    }

    public String getDigestAlgorithm() {
        return digestAlgorithm;
    }

    public void setDigestAlgorithm(String digestAlgorithm) {
        this.digestAlgorithm = digestAlgorithm;
    }
}
