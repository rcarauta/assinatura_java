package com.assinatura.xades.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class ReadFile {
	
	public String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader(file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("\n");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            //stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	public String saveFileInMemory(String base64) throws IOException {
		byte[] decodedImg = Base64.getDecoder()
                .decode(base64.getBytes());
		Path destinationFile = Paths.get("src/main/resources/cert/", "mycert.pfx");
		Files.write(destinationFile, decodedImg);
		return destinationFile.toAbsolutePath().toString();
	}

}
