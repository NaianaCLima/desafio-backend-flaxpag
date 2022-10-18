package com.flaxpag.agendaPgto.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flaxpag.agendaPgto.dtos.AgendamentoDto;
import com.flaxpag.agendaPgto.enums.StatusAgendamento;
import com.flaxpag.agendaPgto.models.AgendamentoModel;
import com.flaxpag.agendaPgto.services.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService service;

	@GetMapping
	public ResponseEntity<Page<AgendamentoModel>> findAll(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<AgendamentoModel> modelOptional = service.findById(id);
		if (!modelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modelOptional.get());
	}

	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody AgendamentoDto agendamentoDto) {
		var agendamentoModel = new AgendamentoModel();
		BeanUtils.copyProperties(agendamentoDto, agendamentoModel);
		agendamentoModel.setData_hora(LocalDateTime.now(ZoneId.of("UTC")));
		agendamentoModel.setStatus(StatusAgendamento.PENDING);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(agendamentoModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody AgendamentoDto agendamentoDto) {
		Optional<AgendamentoModel> agendamentoModelOptional = service.findById(id);
		if (!agendamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado.");
		}
		var agendamentoModel = new AgendamentoModel();
		BeanUtils.copyProperties(agendamentoDto, agendamentoModel);
		agendamentoModel.setId(agendamentoModelOptional.get().getId());
		agendamentoModel.setData_hora(LocalDateTime.now(ZoneId.of("UTC")));
		agendamentoModel.setStatus(agendamentoDto.getStatus());

		return ResponseEntity.status(HttpStatus.OK).body(service.save(agendamentoModel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Long id) {
		Optional<AgendamentoModel> modelOptional = service.findById(id);
		if (!modelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id NÃO encontrado!");
			
		}
		service.delete(modelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
	}
}
