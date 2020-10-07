package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
// RECEBE OS PARANAUE DO BANCO, MEXER NA PORRA TODA
import java.time.LocalDateTime;
@Data
@Builder
public class AlunoResponse {
    private Long id;
    private String nome;
    private Long ra;
    private String cpf;
    private EnderecoResponse endereco;
    private LocalDateTime dataCadastro;
}
