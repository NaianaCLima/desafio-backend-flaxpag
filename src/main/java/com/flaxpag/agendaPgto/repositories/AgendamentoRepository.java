package com.flaxpag.agendaPgto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flaxpag.agendaPgto.models.AgendamentoModel;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Long>{

}
