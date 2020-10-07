package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

// envia valores pro banco, mudar parte de SALVAR no ALUNOSERVICE
@Data
public class EmprestarRequest {
    private Long alunoId;
    private Long funcionarioId;
    private Long livroId;
}
