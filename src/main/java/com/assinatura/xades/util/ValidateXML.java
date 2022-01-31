package com.assinatura.xades.util;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ValidateXML {

    public static final String XML_FILE = "/home/renato/Downloads/desenvolvimento/cpd/java/assinatura-java/diploma_sign1.xml";
    public static final String SCHEMA_FILE = "/home/renato/Downloads/desenvolvimento/cpd/java/assinatura-java/DiplomaDigital_v1.00.xsd";

    public static void main(String[] args) {
    	ValidateXML XMLValidator = new ValidateXML();
        boolean valid = XMLValidator.validate(XML_FILE, SCHEMA_FILE);

        System.out.printf("%s validation = %b.", XML_FILE, valid);
    }

    private boolean validate(String xmlFile, String schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        try {
        	File file = new File(schemaFile);
            Schema schema = schemaFactory.newSchema(new StreamSource(new File(schemaFile)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }
}
