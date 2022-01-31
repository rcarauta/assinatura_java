package com.assinatura.xades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assinatura.xades.model.Certificado;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Integer>  {

	public Certificado findByIdPessoa(Integer idPessoa);
	
}
