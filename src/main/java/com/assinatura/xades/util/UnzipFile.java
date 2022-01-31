package com.assinatura.xades.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Component
public class UnzipFile {
	
	@Autowired
	private ServletContext servletContext;


	public String unzip(MultipartFile file) throws IOException {
		String path = servletContext.getRealPath("")+"loteCertificado.zip";
		File tempFile = new File(path);
		boolean created = tempFile.createNewFile();
		if(created) {
		    file.transferTo(tempFile);
		    return path;
		} else if(tempFile.exists()){
			 file.transferTo(tempFile);
			 return path;
		} else {
			return "error";
		}
	}


	public String extractAllFiles(String tempFile) {
		try {
	         ZipFile zipFile = new ZipFile(tempFile);
	         String pathFiles = tempFile.replaceAll("loteCertificado.zip","");
	         zipFile.extractAll(pathFiles);
	         return pathFiles;
	    } catch (ZipException e) {
	       throw new RuntimeException();
	    }
		
	}
	

}
