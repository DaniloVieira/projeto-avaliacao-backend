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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
	private String userName;

	@Column(name = "ds_roles")
	private String roles;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "dt_creation")
	private LocalDateTime dtcreation;

	@ManyToMany(mappedBy = "contributors")
	@JsonIgnore
	private Set<Project> projects = new HashSet<>();

	@JsonIgnore
	public List<String> getRolesList (){
		return Stream.of(roles.split(",", -1)).collect(Collectors.toList());
	}

}
