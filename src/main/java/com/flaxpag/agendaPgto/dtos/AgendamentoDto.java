package com.flaxpag.agendaPgto.dtos;

import java.time.LocalDateTime;

import com.flaxpag.agendaPgto.enums.StatusAgendamento;

import lombok.Data;

@Data
public class AgendamentoDto {
	
	private Long id;	
	private LocalDateTime data_hora;
	private StatusAgendamento status;
	
	
}
