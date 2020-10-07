package com.example.demo.domain.repository;

import com.example.demo.domain.model.Emprestar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestarRepository extends JpaRepository<Emprestar, Long> {
}
