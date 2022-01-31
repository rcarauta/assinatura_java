package com.assinatura.xades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Diploma")
public class Diploma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "diploma_base64", columnDefinition="TEXT")
	private String diplomaBase64;

	@Column(name = "id_pessoa")
	private Integer idPessoa;

	@Column(name = "id_aluno")
	private Integer idAluno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiplomaBase64() {
		return diplomaBase64;
	}

	public void setDiplomaBase64(String diplomaBase64) {
		this.diplomaBase64 = diplomaBase64;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

}
