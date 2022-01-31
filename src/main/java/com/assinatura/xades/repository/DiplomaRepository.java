package com.assinatura.xades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assinatura.xades.model.Diploma;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, Integer> {

}
