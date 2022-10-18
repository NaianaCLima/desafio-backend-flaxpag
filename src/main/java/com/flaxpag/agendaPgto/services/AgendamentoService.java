package com.flaxpag.agendaPgto.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.flaxpag.agendaPgto.models.AgendamentoModel;
import com.flaxpag.agendaPgto.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;

	public Page<AgendamentoModel> findAll(Pageable pageable) {
		return repository.findAll(pageable);

	}

	public Optional<AgendamentoModel> findById(Long id) {
		return repository.findById(id);

	}

	@Transactional
	public Object save(AgendamentoModel agendamentoModel) {
		return repository.save(agendamentoModel);
	}

	@Transactional
	public void delete(AgendamentoModel agendamentoModel) {
		repository.delete(agendamentoModel);
	}

}
