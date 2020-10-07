package com.example.demo.rest;

import com.example.demo.dto.*;
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
public class LivroApiTest {
    private static final String NOVO_LIVRO_TITULO = "HARRY POTTER";

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
    public void deveCriarLivro(){
        log.info("Rodando Teste De Livro");
        var livroRequest = LivroRequest.builder()
                .titulo(NOVO_LIVRO_TITULO)
                .build();
        log.info("Enviando Livro {} para o Servidor", livroRequest);
        var response =  RestAssured.given()
                .contentType("application/json")
                .body(livroRequest)
                .post("/api/v1/livro");
        Assertions.assertEquals(HttpStatus.CREATED.value(),response.statusCode());
        var responseBody = response.as(LivroResponse.class);
        log.info("Recebendo Livro {} do servidor", responseBody);
        Assertions.assertEquals(NOVO_LIVRO_TITULO, responseBody.getTitulo());
    }
}