package com.lacunasoftware.pkisuite.dto;

public class UploadPdfPkiExpressDTO {

	private String base64Encode;
	private String filename;
	 
	public String getBase64Encode() {
		return base64Encode;
	}
	public String getFilename() {
		return filename;
	}
	public void setBase64Encode(String base64Encode) {
		this.base64Encode = base64Encode;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	 
}
