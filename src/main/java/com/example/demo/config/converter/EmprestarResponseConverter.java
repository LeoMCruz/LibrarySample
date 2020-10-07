package com.example.demo.config.converter;

import com.example.demo.domain.model.Emprestar;
import com.example.demo.dto.EmprestarResponse;
import org.springframework.stereotype.Component;

@Component
public class EmprestarResponseConverter {
    private final AlunoResponseConverter alunoResponseConverter;
    private final FuncionarioResponseConverter funcionarioResponseConverter;
    private final LivroResponseConverter livroResponseConverter;

    public EmprestarResponseConverter(AlunoResponseConverter alunoResponseConverter, FuncionarioResponseConverter funcionarioResponseConverter, LivroResponseConverter livroResponseConverter) {
        this.alunoResponseConverter = alunoResponseConverter;
        this.funcionarioResponseConverter = funcionarioResponseConverter;
        this.livroResponseConverter = livroResponseConverter;
    }

    public EmprestarResponse fromEmprestar(Emprestar emprestar){
        return EmprestarResponse.builder()
                .id(emprestar.getId())
                .dataDevolucao(emprestar.getDataDevolucao())
                .dataDevolucaoPrevista(emprestar.getDataDevolucaoPrevista())
                .dataEmprestimo(emprestar.getDataEmprestimo())
                .aluno(alunoResponseConverter.fromAluno(emprestar.getAluno()))
                .funcionario(funcionarioResponseConverter.fromFuncionario(emprestar.getFuncionario()))
                .livro(livroResponseConverter.fromLivro(emprestar.getLivro()))
                .build();
    }
}
