package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

// envia valores pro banco, mudar parte de SALVAR no ALUNOSERVICE
@Builder
@Data
public class FuncionarioRequest {
    private Long id;
    private String nome;
    private Long ra;
    private String cpf;
    private EnderecoRequest endereco;
}
