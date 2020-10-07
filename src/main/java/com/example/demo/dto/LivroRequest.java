package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

// envia valores pro banco, mudar parte de SALVAR no ALUNOSERVICE
@Data
@Builder
public class LivroRequest {
    private Long id;
    private String titulo;
    private Long cod;
    private String autor;
    private Long quantidade;
}
