package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class EmprestarResponse {
    private Long id;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolucaoPrevista;
    private LocalDateTime dataEmprestimo;
    private AlunoResponse aluno;
    private FuncionarioResponse funcionario;
    private LivroResponse livro;
}
