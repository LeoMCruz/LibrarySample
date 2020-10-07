package com.example.demo.service;

import com.example.demo.config.converter.EmprestarResponseConverter;
import com.example.demo.domain.model.Emprestar;
import com.example.demo.domain.repository.EmprestarRepository;
import com.example.demo.dto.EmprestarRequest;
import com.example.demo.dto.EmprestarResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestarService {
    private final EmprestarRepository emprestarRepository;
    private final EmprestarResponseConverter emprestarResponseConverter;
    private final EmprestimoService emprestimoService;

    public EmprestarService(EmprestarRepository emprestarRepository, EmprestarResponseConverter emprestarResponseConverter, EmprestimoService emprestimoService) {

        this.emprestarRepository = emprestarRepository;
        this.emprestarResponseConverter = emprestarResponseConverter;
        this.emprestimoService = emprestimoService;
    }

    public EmprestarResponse salvarEmprestar(EmprestarRequest emprestarRequest) {

        return emprestimoService.novoEmprestimo(emprestarRequest.getLivroId(), emprestarRequest.getFuncionarioId(), emprestarRequest.getAlunoId());
    }

    public List<EmprestarResponse> buscarTodos() {
        var listaEmprestar = emprestarRepository.findAll().stream()
                .map(emprestar -> emprestarResponseConverter.fromEmprestar(emprestar)).collect(Collectors.toList());

        return listaEmprestar;
    }

    public EmprestarResponse buscarById(Long id) {
        return emprestarRepository.findById(id).map(emprestar -> emprestarResponseConverter.fromEmprestar(emprestar) ).orElse(null);
    }
}
