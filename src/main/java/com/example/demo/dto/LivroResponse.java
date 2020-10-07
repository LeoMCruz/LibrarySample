package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LivroResponse {
    private Long id;
    private String titulo;
    private Long cod;
    private String autor;
    private Long quantidade;
    private LocalDateTime dataCadastro;
}
