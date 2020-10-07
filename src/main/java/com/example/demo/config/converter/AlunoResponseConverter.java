package com.example.demo.config.converter;

import com.example.demo.domain.model.Aluno;
import com.example.demo.dto.AlunoResponse;
import com.example.demo.dto.EnderecoResponse;
import org.springframework.stereotype.Component;

@Component
public class AlunoResponseConverter {
    public AlunoResponse fromAluno(Aluno aluno){
        var endereco = aluno.getEndereco();
        return AlunoResponse.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .ra(aluno.getRa())
                .cpf(aluno.getCpf())
                .endereco(endereco == null?null: EnderecoResponse.builder()
                        .id(endereco.getId())
                        .rua(endereco.getRua())
                        .bairro(endereco.getBairro())
                        .cidade(endereco.getCidade())
                        .estado(endereco.getEstado())
                        .build())
                .dataCadastro(aluno.getDataCadastro())
                .build();
    }
}
