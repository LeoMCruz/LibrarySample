package com.example.demo.rest;

import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.service.FuncionarioService;
import com.example.demo.dto.FuncionarioResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class FuncionarioApi {
    private final FuncionarioService funcionarioService;

    public FuncionarioApi(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping(value = "/api/v1/funcionario", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody FuncionarioRequest funcionarioRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(funcionarioRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(funcionarioService.salvarFuncionario(funcionarioRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(funcionarioService.salvarFuncionario(funcionarioRequest));

    }
    @GetMapping(value = "/api/v1/funcionario", produces = "application/json")
    public ResponseEntity<List<FuncionarioResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(funcionarioService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/funcionario/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var funcionarioResponse = funcionarioService.buscarById(id);
        if(funcionarioResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(funcionarioResponse);
    }
}
