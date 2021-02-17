package com.zalpi.avaliacaobackend.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "activity")
//@JsonIgnoreProperties(value = {"user", "project"}, allowSetters = true)
public class Activity {

	@Id
	@Column(name = "id_activity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ds_activity")
	private String description;

	@Column(name = "ds_details")
	private String details;

	@Column(name = "dt_start")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtStart;

	@Column(name = "dt_end")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dtEnd;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user")
	private User user;

	@ManyToOne
	@JoinColumn(name = "fk_project")
	private Project project;

	//TODO if there still time, implement type of activity model

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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getDtStart() {
		return dtStart;
	}

	public void setDtStart(LocalDateTime dtStart) {
		this.dtStart = dtStart;
	}

	public LocalDateTime getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(LocalDateTime dtEnd) {
		this.dtEnd = dtEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
