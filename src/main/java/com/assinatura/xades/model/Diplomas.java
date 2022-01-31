package com.assinatura.xades.model;

import java.io.File;

public class Diplomas {

	private String pathFile;

	private File[] folder;

	public Diplomas(String pathFile, File[] folder) {
		this.pathFile = pathFile;
		this.folder = folder;
	}

	public String getPathFile() {
		return pathFile;
	}

	public File[] getFolder() {
		return folder;
	}

}
