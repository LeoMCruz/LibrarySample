package com.example.demo.rest;

import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.AlunoResponse;
import com.example.demo.dto.EnderecoRequest;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoApiTest {
    private static final String NOVO_ALUNO_NOME = "Roberto";
    private static final String NOVO_ALUNO_NOME_RUA = "Rua das oliveiras";

    @LocalServerPort
    private int port;

    @AfterEach
    public void afterEach() {

    }
    @BeforeEach
    public void beforeEach() {
        RestAssured.port = port;
    }
    @Test
    public void deveCriarAlunoComEndereco(){
        log.info("Rodando Teste Com Endereço");
        var enderecoRequest = EnderecoRequest.builder()
                .rua(NOVO_ALUNO_NOME_RUA)
                .build();
        var alunoRequest = AlunoRequest.builder()
                .nome(NOVO_ALUNO_NOME)
                .endereco(enderecoRequest)
                .build();
        log.info("Enviando Aluno {} para o Servidor", alunoRequest);
        var response =  RestAssured.given()
                .contentType("application/json")
                .body(alunoRequest)
                .post("/api/v1/aluno");
        Assertions.assertEquals(HttpStatus.CREATED.value(),response.statusCode());
        var responseBody = response.as(AlunoResponse.class);
        log.info("Recebendo Aluno {} do servidor", responseBody);
        Assertions.assertEquals(NOVO_ALUNO_NOME, responseBody.getNome());
        Assertions.assertEquals(NOVO_ALUNO_NOME_RUA, responseBody.getEndereco().getRua());
    }
}