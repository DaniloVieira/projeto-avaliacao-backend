package com.zalpi.avaliacaobackend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

	SUCCESS_MESSAGE("Ação executada com sucesso"),
	ERROR_MESSAGE("Falha ao executar a ação");

	private String msg;

}
