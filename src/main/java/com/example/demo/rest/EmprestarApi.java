package com.example.demo.rest;

import com.example.demo.domain.model.Emprestar;
import com.example.demo.dto.EmprestarRequest;
import com.example.demo.dto.EmprestarResponse;
import com.example.demo.dto.LivroRequest;
import com.example.demo.dto.LivroResponse;
import com.example.demo.service.EmprestarService;
import com.example.demo.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:2345", maxAge = 3600)
@RestController
public class EmprestarApi {
    private final EmprestarService emprestarService;

    public EmprestarApi(EmprestarService emprestarService) {
        this.emprestarService = emprestarService;
    }

    @PostMapping(value = "/api/v1/emprestar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody EmprestarRequest emprestarRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(emprestarService.salvarEmprestar(emprestarRequest));

    }
    @GetMapping(value = "/api/v1/emprestar", produces = "application/json")
    public ResponseEntity<List<EmprestarResponse>> findAll(){
        return ResponseEntity.ok(emprestarService.buscarTodos());
    }

    @GetMapping(value = "/api/v1/emprestar/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable Long id){
        var emprestarResponse = emprestarService.buscarById(id);
        if(emprestarResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(emprestarResponse);
    }
}
