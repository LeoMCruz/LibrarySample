package com.example.demo.rest;

import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.AlunoResponse;
import com.example.demo.service.AlunoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class AlunoApi {
    private final AlunoService alunoService;

    public AlunoApi(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    @PostMapping(value = "/api/v1/aluno", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody AlunoRequest alunoRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(alunoRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(alunoService.salvarAluno(alunoRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoService.salvarAluno(alunoRequest));

    }
    @GetMapping(value = "/api/v1/aluno", produces = "application/json")
    public ResponseEntity<List<AlunoResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(alunoService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/aluno/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var alunoResponse = alunoService.buscarById(id);
        if(alunoResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(alunoResponse);
    }
}
