package com.example.demo.domain.repository;

import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
