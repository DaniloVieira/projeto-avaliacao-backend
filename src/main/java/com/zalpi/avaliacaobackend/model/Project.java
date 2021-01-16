package com.zalpi.avaliacaobackend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
//@ToString
//@EqualsAndHashCode
@Table(name = "project")
public class Project {

	@Id
	@Column(name = "id_project")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_project")
	private String description;

	@Column(name = "nm_client")
	private String clientName;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_creation")
	private LocalDateTime dtCreation;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_start")
	private LocalDateTime dtStart;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_expected_completion")
	private LocalDateTime dtExpectedCompletion;

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_real_completion")
	private LocalDateTime dtRealCompletion;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "project_user",
		joinColumns = { @JoinColumn(name = "fk_project") },
		inverseJoinColumns = { @JoinColumn(name = "fk_user") }
	)
//	@JsonIgnore
	private Set<User> contributors = new HashSet<>();

	@OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
//	@JsonIgnore
	private Set<Activity> activities;

}
