package com.assinatura.xades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.assinatura.xades.controller.AssinaturaXadesController;
import com.assinatura.xades.model.Certificado;
import com.assinatura.xades.model.Diploma;
import com.assinatura.xades.repository.CertificadoRepository;
import com.assinatura.xades.repository.DiplomaRepository;
import com.assinatura.xades.service.AssinaturaXadesService;
import com.assinatura.xades.service.ValidateCertificatePatternService;
import com.assinatura.xades.util.ReadFile;
import com.assinatura.xades.util.RestApiTemplateManager;
import com.assinatura.xades.util.UnzipFile;



@ComponentScan(basePackageClasses = {AssinaturaXadesController.class, 
		UnzipFile.class, ReadFile.class, AssinaturaXadesService.class, RestApiTemplateManager.class,
		ValidateCertificatePatternService.class})
@EnableJpaRepositories(basePackageClasses = {CertificadoRepository.class, DiplomaRepository.class})
@EntityScan(basePackageClasses = {Certificado.class, Diploma.class})
@SpringBootApplication
public class AssinaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssinaturaApplication.class, args);
	}

}
