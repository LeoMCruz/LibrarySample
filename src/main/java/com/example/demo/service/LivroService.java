package com.example.demo.service;

import com.example.demo.config.converter.LivroResponseConverter;
import com.example.demo.domain.model.Livro;
import com.example.demo.domain.repository.LivroRepository;
import com.example.demo.dto.LivroRequest;
import com.example.demo.dto.LivroResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroResponseConverter livroResponseConverter;

    public LivroService(LivroRepository livroRepository, LivroResponseConverter livroResponseConverter) {
        this.livroRepository = livroRepository;
        this.livroResponseConverter = livroResponseConverter;
    }

    public LivroResponse salvarLivro(LivroRequest livroRequest) {
        var livro = Livro.builder()
                .id(livroRequest.getId())
                .titulo(livroRequest.getTitulo())
                .cod(livroRequest.getCod())
                .autor(livroRequest.getAutor())
                .quantidade(livroRequest.getQuantidade())
                .dataCadastro(LocalDateTime.now())
                .build();
        livroRepository.save(livro);
        return livroResponseConverter.fromLivro(livro);
    }

    public List<LivroResponse> buscarTodos(Pageable pageable) {
        var listaLivros = livroRepository.findAll(pageable).stream()
                .map(livro -> livroResponseConverter.fromLivro(livro)).collect(Collectors.toList());

        return listaLivros;
    }

    public LivroResponse buscarById(Long id) {
        return livroRepository.findById(id).map(livro -> livroResponseConverter.fromLivro(livro)).orElse(null);
    }
}
