package com.assinatura.xades.service;

import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.assinatura.xades.model.Certificado;
import com.assinatura.xades.model.Diploma;
import com.assinatura.xades.repository.CertificadoRepository;
import com.assinatura.xades.repository.DiplomaRepository;
import com.assinatura.xades.util.DirectPasswordProvider;
import com.assinatura.xades.util.FirstCertificateSelector;
import com.assinatura.xades.util.ReadFile;

import xades4j.XAdES4jException;
import xades4j.algorithms.EnvelopedSignatureTransform;
import xades4j.production.DataObjectReference;
import xades4j.production.SignedDataObjects;
import xades4j.production.XadesBesSigningProfile;
import xades4j.production.XadesSignatureResult;
import xades4j.production.XadesSigner;
import xades4j.production.XadesTSigningProfile;
import xades4j.properties.DataObjectDesc;
import xades4j.providers.KeyingDataProvider;
import xades4j.providers.impl.FileSystemKeyStoreKeyingDataProvider;

@Service
@Transactional
public class AssinaturaXadesService {
	
	@Autowired
	private ReadFile readFile;
	@Autowired
	private CertificadoRepository certificadoRepository;
	@Autowired
	private DiplomaRepository diplomaRepository;
	
	//retirar isso aqui 
    private static final String SIGNED      = "/home/renato/Downloads/certificado/diploma/diploma_sign"; 

	public void sign(String file, Integer numero, String password, String certificado, Integer idPessoa, Integer idAluno) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(readFile.readFile(file))));
		
        Element elem = doc.getDocumentElement();
        
        //sign first element DadosDiploma
        KeyingDataProvider kdp = new FileSystemKeyStoreKeyingDataProvider(
                "pkcs12",
                certificado,
                new FirstCertificateSelector(),
                new DirectPasswordProvider(password),
                new DirectPasswordProvider(password),
                true);
        
        elem.getElementsByTagName("DadosDiploma");  
        
        //elem.setIdAttribute("ID", true);
        DataObjectDesc obj = new DataObjectReference(elem.getAttribute("id"))
                .withTransform(new EnvelopedSignatureTransform());
        SignedDataObjects dataObjs = new SignedDataObjects().withSignedDataObject(obj);

        XadesSigner signer = new XadesTSigningProfile(kdp).newSigner();          
        XadesSignatureResult xadesResult = signer.sign(dataObjs, elem);
        doc.getDocumentElement().getFirstChild().getFirstChild().appendChild(xadesResult.getSignature().getElement());
        
        
        //find secound element DadosRegistro
        elem.getElementsByTagName("DadosRegistro");  
        
        //elem.setIdAttribute("ID", true);
        obj = new DataObjectReference(elem.getAttribute("id"))
                .withTransform(new EnvelopedSignatureTransform());
        dataObjs = new SignedDataObjects().withSignedDataObject(obj);
        signer = new XadesTSigningProfile(kdp).newSigner();         
        xadesResult = signer.sign(dataObjs, elem);  
        doc.getDocumentElement().getFirstChild().getFirstChild().getNextSibling().appendChild(xadesResult.getSignature().getElement());
       
       
        //find geral element Diploma
        elem.getElementsByTagName("Diploma");  
        
        //elem.setIdAttribute("ID", true);
        obj = new DataObjectReference("")
                .withTransform(new EnvelopedSignatureTransform());
        dataObjs = new SignedDataObjects().withSignedDataObject(obj);
        signer = new XadesTSigningProfile(kdp).newSigner();           
        xadesResult = signer.sign(dataObjs, elem);
       
        
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File fileSign = new File(SIGNED+numero+".xml");
        StreamResult result = new StreamResult(fileSign);
        transformer.transform(source, result);
        
        System.out.println("Assinou diploma "+numero);
        
        Diploma diploma = new Diploma();
        diploma.setIdPessoa(idPessoa);
        diploma.setIdAluno(idAluno);
        diploma.setDiplomaBase64(Base64.getEncoder().encodeToString(Files.readAllBytes(fileSign.toPath())));
        
        this.salvarDiploma(diploma);
        System.out.println("Salvou no banco de dados "+numero);
        
    }
	
	
	public Diploma salvarDiploma(Diploma d) {
		return diplomaRepository.save(d);
	}
	
	public Certificado selectCertificado(Integer idPessoa) {
		return certificadoRepository.findByIdPessoa(idPessoa);
	}


	
}
