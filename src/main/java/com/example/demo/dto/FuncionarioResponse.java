package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FuncionarioResponse {
    private Long id;
    private String nome;
    private Long ra;
    private String cpf;
    private EnderecoResponse endereco;
    private LocalDateTime dataCadastro;
}
