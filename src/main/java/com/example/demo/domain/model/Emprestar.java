package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // avisa o banco que a classe é uma tabela cheia dos paranaue
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO INCREMENT DE BURGUES
    @Column(name = "ID") // muda o nome da coluna no banco, para n assumir o nome da variavel
    private Long id;
    @Column// NOT NULL
    private LocalDate dataDevolucao;
    @Column(nullable = false)
    private LocalDate dataDevolucaoPrevista;
    @Column(nullable = false)
    private LocalDateTime dataEmprestimo;
    @Column
    private StatusEnum status;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // se nao existir o endereço, ele ja cria
    @JoinColumn(name = "alunoId")
    private Aluno aluno;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // se nao existir o endereço, ele ja cria
    @JoinColumn(name = "livroId")
    private Livro livro;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // se nao existir o endereço, ele ja cria
    @JoinColumn(name = "funcionarioId")
    private Funcionario funcionario;
}
