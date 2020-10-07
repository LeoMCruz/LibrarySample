package com.example.demo.config.converter;

import com.example.demo.domain.model.Funcionario;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.FuncionarioResponse;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioResponseConverter {
    public FuncionarioResponse fromFuncionario(Funcionario funcionario){
        var endereco = funcionario.getEndereco();
        return FuncionarioResponse.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .cpf(funcionario.getCpf())
                .endereco(funcionario == null?null: EnderecoResponse.builder()
                        .id(endereco.getId())
                        .rua(endereco.getRua())
                        .bairro(endereco.getBairro())
                        .cidade(endereco.getCidade())
                        .estado(endereco.getEstado())
                        .build())
                .dataCadastro(funcionario.getDataCadastro())
                .build();
    }
}
