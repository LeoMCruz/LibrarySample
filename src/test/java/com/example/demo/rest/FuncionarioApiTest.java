package com.example.demo.rest;

import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.FuncionarioResponse;
import com.example.demo.dto.EnderecoRequest;
import com.example.demo.dto.FuncionarioRequest;
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
public class FuncionarioApiTest {
    private static final String NOVO_FUNCIONARIO_NOME = "Roberto";
    private static final String NOVO_FUNCIONARIO_NOME_RUA = "Rua das oliveiras";

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
    public void deveCriarFuncionarioComEndereco(){
        log.info("Rodando Teste Com Endereço");
        var enderecoRequest = EnderecoRequest.builder()
                .rua(NOVO_FUNCIONARIO_NOME_RUA)
                .build();
        var funcionarioRequest = FuncionarioRequest.builder()
                .nome(NOVO_FUNCIONARIO_NOME)
                .endereco(enderecoRequest)
                .build();
        log.info("Enviando Funcionario {} para o Servidor", funcionarioRequest);
        var response =  RestAssured.given()
                .contentType("application/json")
                .body(funcionarioRequest)
                .post("/api/v1/livro");
        Assertions.assertEquals(HttpStatus.CREATED.value(),response.statusCode());
        var responseBody = response.as(FuncionarioResponse.class);
        log.info("Recebendo Livro {} do servidor", responseBody);
        Assertions.assertEquals(NOVO_FUNCIONARIO_NOME, responseBody.getNome());
        Assertions.assertEquals(NOVO_FUNCIONARIO_NOME_RUA, responseBody.getEndereco().getRua());
    }
}