package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class EnderecoRequest {

    private Long id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
}
