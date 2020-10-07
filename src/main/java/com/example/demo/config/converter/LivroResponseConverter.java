package com.example.demo.config.converter;

import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Livro;
import com.example.demo.dto.AlunoResponse;
import com.example.demo.dto.EnderecoResponse;
import com.example.demo.dto.LivroResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LivroResponseConverter {
    public LivroResponse fromLivro(Livro livro){
        return LivroResponse.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .cod(livro.getCod())
                .autor(livro.getAutor())
                .quantidade(livro.getQuantidade())
                .dataCadastro(LocalDateTime.now())
                .build();
    }
}
