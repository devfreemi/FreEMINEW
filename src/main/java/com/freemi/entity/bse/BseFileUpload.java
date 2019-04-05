package com.freemi.entity.bse;

import org.springframework.web.multipart.MultipartFile;

public class BseFileUpload {

	private String filecategory;
	
	private String fileowner;
	private MultipartFile file;

	public String getFilecategory() {
		return filecategory;
	}
	
	public String getFileowner() {
		return fileowner;
	}
	public void setFileowner(String fileowner) {
		this.fileowner = fileowner;
	}

	public void setFilecategory(String filecategory) {
		this.filecategory = filecategory;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
