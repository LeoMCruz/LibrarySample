package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // avisa o banco que a classe é uma tabela cheia dos paranaue
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO INCREMENT DE BURGUES
    @Column(name = "ID") // muda o nome da coluna no banco, para n assumir o nome da variavel
    private Long id;
    @Column(nullable = false) // NOT NULL
    private String titulo;
    @Column
    private Long cod;
    @Column
    private String autor;
    @Column
    private Long quantidade;
    @Column
    private LocalDateTime dataCadastro;
    @Column
    private StatusEnum status;
}
