package com.assinatura.xades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "TB_Certificado")
public class Certificado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "id_pessoa")
	private Integer idPessoa;

	@Column(name = "path_certificado")
	private String pathCertificado;

	@Column(name = "certificado_base64", columnDefinition = "TEXT")
	private String certificadoBase64;

	@Transient
	private String senha;

	@Transient
	private MultipartFile certificadoFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCertificadoBase64() {
		return certificadoBase64;
	}

	public void setCertificadoBase64(String certificadoBase64) {
		this.certificadoBase64 = certificadoBase64;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getPathCertificado() {
		return pathCertificado;
	}

	public void setPathCertificado(String pathCertificado) {
		this.pathCertificado = pathCertificado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public MultipartFile getCertificadoFile() {
		return certificadoFile;
	}

	public void setCertificadoFile(MultipartFile certificadoFile) {
		this.certificadoFile = certificadoFile;
	}

}
