package com.assinatura.xades.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.transaction.Transactional;
import javax.xml.XMLConstants;
import  javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@Service
@Transactional
public class ValidateCertificatePatternService {
	
	private String teste = "<Diploma ID=\"941ce80d-a2ff-4896-a900-8f44fdb905d8\">\n" + 
			"<aluMatricula>201163675</aluMatricula>\n" + 
			"<aluRa>140063978</aluRa>\n" + 
			"<curCurso>1635</curCurso>\n" + 
			"<curNivel>2</curNivel>\n" + 
			"<curPerIni>20082</curPerIni>\n" + 
			"<curPerFim>99999</curPerFim>\n" + 
			"<dadForSaidOpc>1</dadForSaidOpc>\n" + 
			"<dadPerSaidOpc>20182</dadPerSaidOpc>\n" + 
			"<dadPerCur>20141</dadPerCur>\n" + 
			"<livPerPeriodo>20182</livPerPeriodo>\n" + 
			"<aluNome>Mateus Manuel Rodrigues Bezerra</aluNome>\n" + 
			"<nacionalidade>brasileira,</nacionalidade>\n" + 
			"<nascimento>Distrito Federal,</nascimento>\n" + 
			"<diaNasc>21</diaNasc>\n" + 
			"<aluDocumento>CPF 052.871.771-51,</aluDocumento>\n" + 
			"<aluDocumentoAnverso>CPF 052.871.771-51</aluDocumentoAnverso>\n" + 
			"<tratamento>o</tratamento>\n" + 
			"<mesNasc>junho</mesNasc>\n" + 
			"<anoNasc>1996</anoNasc>\n" + 
			"<aluIdentidade>2591677,</aluIdentidade>\n" + 
			"<opcDenomGrau>Bacharel</opcDenomGrau>\n" + 
			"<curDenominacao>Engenharia de Software,</curDenominacao>\n" + 
			"<cursoAnverso>Engenharia de Software</cursoAnverso>\n" + 
			"<livDtFormat>13/12/2018</livDtFormat>\n" + 
			"<livDtExped>28/02/2019</livDtExped>\n" + 
			"<regDtRegistroLbl>Data de Registro:</regDtRegistroLbl>\n" + 
			"<regDtRegistro>28/02/2019</regDtRegistro>\n" + 
			"<nroProcessoLbl>Processo n:</nroProcessoLbl>\n" + 
			"<nroProcesso>___________</nroProcesso>\n" + 
			"<livReitor>Marcia Abrahao Moura</livReitor>\n" + 
			"<livLivro>Livro n: 118</livLivro>\n" + 
			"<regNroRegistro>Registro n:1515</regNroRegistro>\n" + 
			"<regFolha>Folha n: 379</regFolha>\n" + 
			"<opcOpcao>6360</opcOpcao>\n" + 
			"<opcGrau>1</opcGrau>\n" + 
			"<nacionalNatural>de nacionalidade brasileira, nascido no Distrito Federal,</nacionalNatural>\n" + 
			"<nascimentoIdentidade>no dia 21 de junho de 1996, CPF 052.871.771-51,</nascimentoIdentidade>\n" + 
			"<nomeCurso>tendo em vista a conclusao do Curso de Engenharia de Software</nomeCurso>\n" + 
			"<dataFormatura>no dia 13 de dezembro de 2018</dataFormatura>\n" + 
			"<dataExpedicao>28 de fevereiro de 2019.</dataExpedicao>\n" + 
			"<matricula>140063978</matricula>\n" + 
			"<matriculaLst>14/0063978</matriculaLst>\n" + 
			"<opcLabel>O diplomado concluiu a seguinte habilitacao: Engenharia de Software</opcLabel>\n" + 
			"<opcDenominacao>Engenharia de Software</opcDenominacao>\n" + 
			"<OpcDenomProf>Mestrado Profissionalizante em: Engenharia de Software</OpcDenomProf>\n" + 
			"<codigoValidacao>F912CD0C42DCA3D655DD0258EC9D0AE7</codigoValidacao>\n" + 
			"<nomeReitor>Marcia Abrahao Moura</nomeReitor>\n" + 
			"<regDtFormatura>13/12/2018</regDtFormatura>\n" + 
			"<pesNomeSocial>pesNomeSocial</pesNomeSocial>\n" + 
			"<dataColacao>e colacao de grau no dia 26 de fevereiro de 2019</dataColacao>\n" + 
			"<dataExpedicaoReduzida>28/02/2019</dataExpedicaoReduzida>\n" + 
			"<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" Id=\"xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b\">\n" + 
			"<ds:SignedInfo>\n" + 
			"<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" + 
			"<ds:SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#rsa-sha256\"/>\n" + 
			"<ds:Reference Id=\"xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b-ref0\" URI=\"#941ce80d-a2ff-4896-a900-8f44fdb905d8\">\n" + 
			"<ds:Transforms>\n" + 
			"<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" + 
			"</ds:Transforms>\n" + 
			"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" + 
			"<ds:DigestValue>rV/LiNMajdp/UtJ9v8CJ3nHoO4RqyiNC6OpAEy9Y3dM=</ds:DigestValue>\n" + 
			"</ds:Reference>\n" + 
			"<ds:Reference Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b-signedprops\">\n" + 
			"<ds:Transforms>\n" + 
			"<ds:Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" + 
			"</ds:Transforms>\n" + 
			"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" + 
			"<ds:DigestValue>lfT9u6W29vyjM7F2J9aWwMQsrsFDQAHm3xN5oXcYkTk=</ds:DigestValue>\n" + 
			"</ds:Reference>\n" + 
			"</ds:SignedInfo>\n" + 
			"<ds:SignatureValue Id=\"xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b-sigvalue\"> oPMvDs9EbQR9rSxk044BVUIA0gskVXmMBOPQ7mM5EtmBV+2FnJmGShIKlBGC2ij9Hv5fP0cnxGqy CdTTXGJY2tgZfbr+Fw5lAHy4jfZu1AdGO8Ahqb9LrEnBio43YHk0hRiP2lw9xGRkHCiDzoHa/JAI K08xtDT+20wK8Px7m9Bio7Lh1EVTrvOGTeABVJdrUf2zyc7oo4G+2W2Fjm1OBxIu9tcebmVKVdFm N5pJIIz/f+/FMDDZyryESPYo8OX+HvEIG69z1p4aLDxGX32s9z0xvezhSfE/kuvH91LD7uPZIQRN C3uEZKb9gtk5oeUWnnKg/Hfye0j1/Fd2UvYXHg== </ds:SignatureValue>\n" + 
			"<ds:KeyInfo>\n" + 
			"<ds:X509Data>\n" + 
			"<ds:X509Certificate> MIIDazCCAlOgAwIBAgIUdZ8U8x7nhUqY6aa9p8b7BSHyAuQwDQYJKoZIhvcNAQELBQAwRTELMAkG A1UEBhMCQVUxEzARBgNVBAgMClNvbWUtU3RhdGUxITAfBgNVBAoMGEludGVybmV0IFdpZGdpdHMg UHR5IEx0ZDAeFw0yMTAxMzAxNzMzMDNaFw0yMjAxMzAxNzMzMDNaMEUxCzAJBgNVBAYTAkFVMRMw EQYDVQQIDApTb21lLVN0YXRlMSEwHwYDVQQKDBhJbnRlcm5ldCBXaWRnaXRzIFB0eSBMdGQwggEi MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDDwnYU6OZsy4dCfvXMnSZ0lgJkZgEDfRBWhP0z MvKcDSVzHgc2T75z5UVlo298sqfLL6IFVHu6hRKTgZoB7P7vU+BjdJQ0V9b7IxGDB2KaAemEnnhu Pou/qsxtt7hw3VEnHYtV0liClVilpiA7/iT347cekGN+o8R6Zj84dTAKP5ZkZMKKZ2T07ybMyKan 5deWoJBkamUgAXbw4GU09cd2xC1Cj2hvbupO/3U8AvheZDtH+RTXmFE7w5VR6xKMvKBTAKjLzbiy PENYvpvTKWtjPp4tbw9ZAj5PL63dnp/sm78HOhBnTI2EVkpU2Vf5TILH6hsSzQ/o+wwo5oPpONhR AgMBAAGjUzBRMB0GA1UdDgQWBBQDjy1jkmSMOs1R+j+nxKWYE775fTAfBgNVHSMEGDAWgBQDjy1j kmSMOs1R+j+nxKWYE775fTAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4IBAQBosvqJ AJ68ylEN3TC0HfYzrc6eQ0ncwNmFKqkyD6GZzyVfGJCHWP/8obBdTZeXBeWY2fyfXvjbCrqIY54h h9d+64IModZ5sZFp6rsgqoZSrbkEBkuFVBDeG4HO1H76JhnrOLixWdPPc5SNMH0eVXnkfn6bmskv PWCPA8mOqQ5L2/vQOwIwGbhqHcmOYDv9zruXxNDNPvkVfys9yybY7VverD/N2SxqLxTQkDpzxy4o 9mN6UCnycEPqffS0QS0BRyVwRfpnQdupUSBexHZGNpchYeTRgr5XeMObEfp4fccCs6FQk/zzjPAs PHlBybU3BSp8hUHS08vTFCapJqTnesfg </ds:X509Certificate>\n" + 
			"<ds:X509SubjectName>O=Internet Widgits Pty Ltd,ST=Some-State,C=AU</ds:X509SubjectName>\n" + 
			"<ds:X509IssuerSerial>\n" + 
			"<ds:X509IssuerName>O=Internet Widgits Pty Ltd,ST=Some-State,C=AU</ds:X509IssuerName>\n" + 
			"<ds:X509SerialNumber>671499563648368354326316088702941664831897469668</ds:X509SerialNumber>\n" + 
			"</ds:X509IssuerSerial>\n" + 
			"</ds:X509Data>\n" + 
			"</ds:KeyInfo>\n" + 
			"<ds:Object>\n" + 
			"<xades:QualifyingProperties xmlns:xades=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:xades141=\"http://uri.etsi.org/01903/v1.4.1#\" Target=\"#xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b\">\n" + 
			"<xades:SignedProperties Id=\"xmldsig-217237e5-b318-4e36-87ae-b01e49ae136b-signedprops\">\n" + 
			"<xades:SignedSignatureProperties>\n" + 
			"<xades:SigningTime>2021-01-30T14:44:01.956-03:00</xades:SigningTime>\n" + 
			"<xades:SigningCertificate>\n" + 
			"<xades:Cert>\n" + 
			"<xades:CertDigest>\n" + 
			"<ds:DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"/>\n" + 
			"<ds:DigestValue>vEMXjgMMfe5B2U3sAH0klQ0tnLZXcDSP1JZrGe1r85E=</ds:DigestValue>\n" + 
			"</xades:CertDigest>\n" + 
			"<xades:IssuerSerial>\n" + 
			"<ds:X509IssuerName>O=Internet Widgits Pty Ltd,ST=Some-State,C=AU</ds:X509IssuerName>\n" + 
			"<ds:X509SerialNumber>671499563648368354326316088702941664831897469668</ds:X509SerialNumber>\n" + 
			"</xades:IssuerSerial>\n" + 
			"</xades:Cert>\n" + 
			"</xades:SigningCertificate>\n" + 
			"</xades:SignedSignatureProperties>\n" + 
			"</xades:SignedProperties>\n" + 
			"</xades:QualifyingProperties>\n" + 
			"</ds:Object>\n" + 
			"</ds:Signature>\n" + 
			"</Diploma>";
	
	
	public boolean validate(MultipartFile xmlString) {
	    try {
	        // Convert to input stream
	        InputStream xml = new ByteArrayInputStream(teste.getBytes());

	        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = schemaFactory.newSchema(new File("/home/renato/Downloads/desenvolvimento/cpd/git/certificado/validador/xmldsig-core-schema_v1.1.xsd"));

	        // Validate against wsdl
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));

	        // XML Message is valid
	        return true;

	    } catch (SAXException e) {
	        e.printStackTrace();
	        return false;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
