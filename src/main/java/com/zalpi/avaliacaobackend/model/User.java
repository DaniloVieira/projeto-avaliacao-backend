package com.zalpi.avaliacaobackend.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

//@Getter
//@Setter
@Entity
//@ToString
//@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nm_user_first")
//	@Size(min = 1, message = "{Size.userDto.firstName}")
	private String firstName;

	@Column(name = "nm_user_last")
//	@Size(min = 1, message = "{Size.userDto.lastName}")
	private String lastName;

	private String password;

	@Column(name = "ds_username", unique = true)
	private String username;

	@Column(name = "ds_roles")
	private String roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_creation")
	private LocalDateTime dtcreation;

	@ManyToMany(mappedBy = "contributors")
	@JsonIgnore
	private Set<Project> projects = new HashSet<>();

	@Transient
	private String token;

	@Transient
	public List<String> getRolesList (){
		return Stream.of(roles.split(",", -1)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public LocalDateTime getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(LocalDateTime dtcreation) {
		this.dtcreation = dtcreation;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
