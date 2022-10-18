package com.flaxpag.agendaPgto.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.flaxpag.agendaPgto.enums.StatusAgendamento;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //compara obj java, pega apenas o ID
@Entity
@Table(name = "TB_AGENDAMENTO")
public class AgendamentoModel implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include //igualdade entre dois id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime data_hora;
	private StatusAgendamento status;

}
