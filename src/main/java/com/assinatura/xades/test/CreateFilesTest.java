package com.assinatura.xades.test;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.assinatura.xades.util.ReadFile;

public class CreateFilesTest {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Iniciou a geração de arquivos de teste !!");
		
		ReadFile readFile = new ReadFile();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(readFile.readFile("/home/renato/Downloads/certificado/loteCertificado/original.xml"))));
		
		for (int i = 0; i < 10000; i++) {
			TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        File fileSign = new File("/home/renato/Downloads/certificado/loteCertificado/original_"+i+".xml");
	        StreamResult result = new StreamResult(fileSign);
	        transformer.transform(source, result);
		}
		
		System.out.println("Terminou a geração de arquivos para teste !!");
		  	
	}
	
}
