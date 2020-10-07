package com.example.demo.service;

import com.example.demo.config.converter.EmprestarResponseConverter;
import com.example.demo.domain.model.Emprestar;
import com.example.demo.domain.repository.AlunoRepository;
import com.example.demo.domain.repository.EmprestarRepository;
import com.example.demo.domain.repository.FuncionarioRepository;
import com.example.demo.domain.repository.LivroRepository;
import com.example.demo.dto.EmprestarResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmprestimoService {
    private final EmprestarRepository emprestarRepository;
    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EmprestarResponseConverter emprestarResponseConverter;

    public EmprestimoService(EmprestarRepository emprestarRepository, AlunoRepository alunoRepository, LivroRepository livroRepository, FuncionarioRepository funcionarioRepository, EmprestarResponseConverter emprestarResponseConverter) {
        this.emprestarRepository = emprestarRepository;
        this.alunoRepository = alunoRepository;
        this.livroRepository = livroRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.emprestarResponseConverter = emprestarResponseConverter;
    }

    public EmprestarResponse novoEmprestimo(Long livroId, Long funcionarioId, Long alunoId){
        var aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno nao encontrado"));
        var livro = livroRepository.findById(livroId).orElseThrow(() -> new RuntimeException("Livro nao encontrado"));
        var funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new RuntimeException("Funcionario nao encontrado"));
        var emprestar = Emprestar.builder()
                .aluno(aluno)
                .livro(livro)
                .funcionario(funcionario)
                .dataDevolucaoPrevista(LocalDate.now().plusDays(7))
                .dataEmprestimo(LocalDateTime.now())
                .build();
        emprestarRepository.save(emprestar);
        return emprestarResponseConverter.fromEmprestar(emprestar);
    }
    public EmprestarResponse concluirEmprestimo(Long emprestimoId){
        var emprestimo = emprestarRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Emprestimo nao encontrado"));
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestarRepository.save(emprestimo);
        return emprestarResponseConverter.fromEmprestar(emprestimo);
    }
}
