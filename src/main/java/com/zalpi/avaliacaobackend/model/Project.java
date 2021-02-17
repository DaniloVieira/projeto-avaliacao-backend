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

//@Getter
//@Setter
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

	@ManyToMany(fetch = FetchType.LAZY,
		cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
		}
	)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDateTime getDtCreation() {
		return dtCreation;
	}

	public void setDtCreation(LocalDateTime dtCreation) {
		this.dtCreation = dtCreation;
	}

	public LocalDateTime getDtStart() {
		return dtStart;
	}

	public void setDtStart(LocalDateTime dtStart) {
		this.dtStart = dtStart;
	}

	public LocalDateTime getDtExpectedCompletion() {
		return dtExpectedCompletion;
	}

	public void setDtExpectedCompletion(LocalDateTime dtExpectedCompletion) {
		this.dtExpectedCompletion = dtExpectedCompletion;
	}

	public LocalDateTime getDtRealCompletion() {
		return dtRealCompletion;
	}

	public void setDtRealCompletion(LocalDateTime dtRealCompletion) {
		this.dtRealCompletion = dtRealCompletion;
	}

	public Set<User> getContributors() {
		return contributors;
	}

	public void setContributors(Set<User> contributors) {
		this.contributors = contributors;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}
}
