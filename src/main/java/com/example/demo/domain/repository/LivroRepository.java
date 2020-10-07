package com.example.demo.domain.repository;

import com.example.demo.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
