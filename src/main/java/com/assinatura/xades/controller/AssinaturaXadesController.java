package com.assinatura.xades.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assinatura.xades.model.Certificado;
import com.assinatura.xades.model.Diplomas;
import com.assinatura.xades.service.AssinaturaXadesService;
import com.assinatura.xades.service.ValidateCertificatePatternService;
import com.assinatura.xades.util.ReadFile;
import com.assinatura.xades.util.UnzipFile;

@RestController
@RequestMapping("/sign")
public class AssinaturaXadesController {
	
	@Autowired
	private UnzipFile unzipFile;
	@Autowired
	private AssinaturaXadesService assinarutaXadesService;
	@Autowired
	private ValidateCertificatePatternService validateCertificatePatternService;
	@Autowired
	private ReadFile readFile;
	
	private Diplomas diplomas; 

	@PostMapping("/xades")
	public String signXades(@RequestPart("unzipfile") MultipartFile file, 
					@RequestPart("senha")String password, @RequestPart("idPessoa") String idPessoa,
					@RequestPart("idAluno") String idAluno) throws Exception {
        
		readFiles(file);
		
        Certificado cert = recuperarCertificado(Integer.parseInt(idPessoa));
        
        String pathCert = readFile.saveFileInMemory(cert.getCertificadoBase64());
        
        System.out.println(cert);
        
        for(int i = 0; i < listFiles.length; i++){
            String filename = listFiles[i].getName();
            if(filename.endsWith(".xml")||filename.endsWith(".XML")) {
            	assinarutaXadesService.sign(pathFiles+""+filename, i, password, pathCert, Integer.parseInt(idPessoa), Integer.parseInt(idAluno));
            }
        }
        
        File fileDelete = new File(pathCert); 
        FileUtils.forceDelete(fileDelete);
        
        System.out.println("Assinou tudo =))");
        
        return "Retornou!!";
	}
	
	private void readFiles(MultipartFile file) throws Exception {
	    String tempFile = unzipFile.unzip(file);
        String pathFiles = unzipFile.extractAllFiles(tempFile);
        File folder = new File(pathFiles);
        diplomas = new Diplomas(pathFiles, folder.listFiles()); 
	}
	
	
	@PostMapping("/xades/secutiry")
	public String signXadesSecurity( @RequestParam("senha")String password, @RequestParam("idPessoa") String idPessoa,
					@RequestParam("idAluno") String idAluno) throws Exception {
		System.out.println("Iniciou a assinatura !!!");
        String tempFile = unzipFile.unzip(converter());
        String pathFiles = unzipFile.extractAllFiles(tempFile);
        File folder = new File(pathFiles);
        File[] listFiles = folder.listFiles();
        File folderPerm = new File(listFiles[0].getAbsolutePath());
        
        Certificado cert = recuperarCertificado(Integer.parseInt(idPessoa));
        
        String pathCert = readFile.saveFileInMemory(cert.getCertificadoBase64());
        
        System.out.println(cert);
        
        for(int i = 0; i < listFiles.length; i++){
            String filename = listFiles[i].getName();
            if(filename.endsWith(".xml")||filename.endsWith(".XML")) {
            	assinarutaXadesService.sign(pathFiles+""+filename, i, password, pathCert, Integer.parseInt(idPessoa), Integer.parseInt(idAluno));
            }
        }
        
        File fileDelete = new File(pathCert); 
        FileUtils.forceDelete(fileDelete);
        
        System.out.println("Assinou tudo =))");
        
        return "Retornou!!";
	}
	
	
	private MultipartFile converter() throws Exception {
		 File file = new File("/home/renato/Downloads/desenvolvimento/cursos/locust/projetos/venv/locustscripts/loteCertificado.zip");
		 FileInputStream fileInputStream = new FileInputStream(file);
		 MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
		 ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
		 return multipartFile;
	}
	
	
	@GetMapping("/certificado/id")
	public Certificado recuperarCertificado(@RequestParam("idPessoa") Integer idPessoa) {
		return assinarutaXadesService.selectCertificado(idPessoa);
	}
	
	@GetMapping("/validate/id")
	public String validateDiploma(@RequestParam("idDiploma")Integer idDiploma) {
		this.validateCertificatePatternService.validate(null);
		return "validate";
	}
	
	@GetMapping("/validate/xml")
	public String validateDiploma(@RequestPart("diploma") MultipartFile diplomaXML) {
		this.validateCertificatePatternService.validate(diplomaXML);
		return "validate";
	}
	
	
	
}
