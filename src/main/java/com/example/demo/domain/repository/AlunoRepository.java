package com.example.demo.domain.repository;

import com.example.demo.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("select a from Aluno a where a.nome = :nome order by a.id desc") // jpql
    public Aluno findByNome(String nome); // query by name

}
