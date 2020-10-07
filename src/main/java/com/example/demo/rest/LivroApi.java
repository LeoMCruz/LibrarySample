package com.example.demo.rest;

import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.dto.FuncionarioResponse;
import com.example.demo.dto.LivroRequest;
import com.example.demo.dto.LivroResponse;
import com.example.demo.service.FuncionarioService;
import com.example.demo.service.LivroService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class LivroApi {
    private final LivroService livroService;

    public LivroApi(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping(value = "/api/v1/livro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody LivroRequest livroRequest){
        //CONFERE SE O ID EXISTE E CRIA
        if(livroRequest.getId() != null)
            return ResponseEntity.status(HttpStatus.OK)
                    .body(livroService.salvarLivro(livroRequest));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(livroService.salvarLivro(livroRequest));

    }
    @GetMapping(value = "/api/v1/livro", produces = "application/json")
    public ResponseEntity<List<LivroResponse>> findAll(Pageable pageable){
        return ResponseEntity.ok(livroService.buscarTodos(pageable));
    }

    @GetMapping(value = "/api/v1/livro/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var livroResponse = livroService.buscarById(id);
        if(livroResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(livroResponse);
    }
}
