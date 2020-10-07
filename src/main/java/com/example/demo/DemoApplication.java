package com.example.demo;

import java.util.Date;
import com.example.demo.dto.AlunoRequest;
import com.example.demo.dto.EmprestarRequest;
import com.example.demo.dto.FuncionarioRequest;
import com.example.demo.dto.LivroRequest;
import com.example.demo.service.AlunoService;
import com.example.demo.service.EmprestarService;
import com.example.demo.service.FuncionarioService;
import com.example.demo.service.LivroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
	private final EmprestarService emprestarService;

	public DemoApplication(EmprestarService emprestarService) {
		this.emprestarService = emprestarService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		var qlqr = new EmprestarRequest();
//		qlqr.setDataDevolucaoPrevista("10/01/2020");
//		var salvar = emprestarService.salvarEmprestar(qlqr);
//		log.info("salvado !{}", salvar);
	}
}
