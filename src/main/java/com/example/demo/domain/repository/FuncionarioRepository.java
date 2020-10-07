package com.example.demo.domain.repository;

import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("select a from Funcionario a where a.nome = :nome order by a.id desc") // jpql
    public Funcionario findByNome(String nome); // query by name
}
