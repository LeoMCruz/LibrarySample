package com.example.demo.rest;

import com.example.demo.dto.EmprestarResponse;
import com.example.demo.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmprestimoApi {
    private final EmprestimoService emprestimoService;

    public EmprestimoApi(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/api/v1/emprestimo/{livroId}/{funcionarioId}/{alunoId}")
    public ResponseEntity<?> criarEmprestimo(@PathVariable Long livroId, @PathVariable Long funcionarioId, @PathVariable Long alunoId){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.novoEmprestimo(livroId, funcionarioId, alunoId));
        }catch(RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
        }
    }

    @PostMapping("/api/v1/emprestimo/{emprestimoId}/concluir")
    public ResponseEntity<?> concluirEmprestimo(@PathVariable Long emprestimoId){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.concluirEmprestimo(emprestimoId));
        }catch(RuntimeException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
        }
    }
}
