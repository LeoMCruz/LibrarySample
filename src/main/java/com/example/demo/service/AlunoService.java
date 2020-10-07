package com.example.demo.service;

import com.example.demo.config.converter.AlunoResponseConverter;
import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.repository.AlunoRepository;
import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.AlunoResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final AlunoResponseConverter alunoResponseConverter;

    public AlunoService(AlunoRepository alunoRepository, AlunoResponseConverter alunoResponseConverter) {
        this.alunoRepository = alunoRepository;
        this.alunoResponseConverter = alunoResponseConverter;
    }

    public AlunoResponse salvarAluno(AlunoRequest alunoRequest) {
        var enderecoRequest = alunoRequest.getEndereco();
        var aluno = Aluno.builder()
                .id(alunoRequest.getId())
                .nome(alunoRequest.getNome())
                .ra(alunoRequest.getRa())
                .cpf(alunoRequest.getCpf())
                .endereco(enderecoRequest == null?null: Endereco.builder()
                        .id(enderecoRequest.getId())
                        .rua(enderecoRequest.getRua())
                        .bairro(enderecoRequest.getBairro())
                        .cidade(enderecoRequest.getCidade())
                        .estado(enderecoRequest.getEstado())
                        .build())
                .dataCadastro(LocalDateTime.now())
                .build();
        alunoRepository.save(aluno);
        return alunoResponseConverter.fromAluno(aluno);
    }

    public List<AlunoResponse> buscarTodos(Pageable pageable) {
        var listaAlunos = alunoRepository.findAll(pageable).stream()
                .map(aluno -> alunoResponseConverter.fromAluno(aluno)).collect(Collectors.toList());

        return listaAlunos;
    }

    public AlunoResponse buscarById(Long id) {
        return alunoRepository.findById(id).map(aluno -> alunoResponseConverter.fromAluno(aluno)).orElse(null);
    }
}
