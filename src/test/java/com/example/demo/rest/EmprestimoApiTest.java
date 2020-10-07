package com.example.demo.rest;

import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Funcionario;
import com.example.demo.domain.model.Livro;
import com.example.demo.domain.repository.AlunoRepository;
import com.example.demo.domain.repository.EmprestarRepository;
import com.example.demo.domain.repository.FuncionarioRepository;
import com.example.demo.domain.repository.LivroRepository;
import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.AlunoResponse;
import com.example.demo.dto.EmprestarResponse;
import com.example.demo.dto.EnderecoRequest;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmprestimoApiTest {
    private static final String NOVO_ALUNO_NOME = "Roberto";
    private static final String NOVO_FUNCIONARIO_NOME = "LEo";
    private static final String NOVO_LIVRO_TITULO = "HARYY PORRA";


    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EmprestarRepository emprestarRepository;

    @LocalServerPort
    private int port;

    private Aluno aluno;
    private Livro livro;
    private Funcionario funcionario;

    @AfterEach
    public void afterEach() {
        emprestarRepository.deleteAll();
        funcionarioRepository.deleteAll();
        alunoRepository.deleteAll();
        livroRepository.deleteAll();
    }
    @BeforeEach
    public void beforeEach() {
        RestAssured.port = port;
        funcionario = funcionarioRepository.save(Funcionario.builder()
                .nome(NOVO_FUNCIONARIO_NOME)
                .build());

        aluno = alunoRepository.save(Aluno.builder()
                .nome(NOVO_ALUNO_NOME)
                .build());
        Aluno filtro = Aluno.builder()
                .nome("Rob")
                .build();
        var matcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withIgnoreCase();
        var example = Example.of(filtro, matcher);
        alunoRepository.findAll(example);
        livro = livroRepository.save(Livro.builder()
                .titulo(NOVO_LIVRO_TITULO)
                .build());
    }
    @Test
    public void deveCriarEmprestimo(){
        var response =  RestAssured.post(String.format("/api/v1/emprestimo/%d/%d/%d", livro.getId(), funcionario.getId(), aluno.getId()));
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        var responseBody = response.as(EmprestarResponse.class);
        var responseGet = RestAssured.get(String.format("/api/v1/emprestar/%d", responseBody.getId())).as(EmprestarResponse.class);
        Assertions.assertEquals(responseGet.getId(),responseBody.getId());
        Assertions.assertEquals(responseGet.getDataDevolucao(),responseBody.getDataDevolucao());
        Assertions.assertEquals(responseGet.getDataEmprestimo(),responseBody.getDataEmprestimo());
        Assertions.assertEquals(responseGet.getDataDevolucaoPrevista(),responseBody.getDataDevolucaoPrevista());
        Assertions.assertNull(responseBody.getDataDevolucao());
        Assertions.assertEquals(responseBody.getDataEmprestimo().plusDays(7).toLocalDate(), responseBody.getDataDevolucaoPrevista());

    }
    @Test
    public void deveConcluirEmprestimo(){
        var response =  RestAssured.post(String.format("/api/v1/emprestimo/%d/%d/%d", livro.getId(), funcionario.getId(), aluno.getId()));
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        var responseBody = response.as(EmprestarResponse.class);
        response = RestAssured.post(String.format("/api/v1/emprestimo/%d/concluir", responseBody.getId()));
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        responseBody = response.as(EmprestarResponse.class);
        Assertions.assertNotNull(responseBody.getDataDevolucao());
    }

}