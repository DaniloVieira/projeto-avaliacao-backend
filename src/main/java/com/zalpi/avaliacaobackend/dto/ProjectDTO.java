package com.zalpi.avaliacaobackend.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {

	private Long id;
	private String description;
	private String clientName;
	private BigInteger totalHours;


}
