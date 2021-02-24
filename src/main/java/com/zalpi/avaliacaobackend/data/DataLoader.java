package com.zalpi.avaliacaobackend.data;

import java.time.LocalDateTime;
import java.util.Set;
import javax.transaction.Transactional;

import com.zalpi.avaliacaobackend.dao.ActivityDAO;
import com.zalpi.avaliacaobackend.dao.ProjectDAO;
import com.zalpi.avaliacaobackend.model.Activity;
import com.zalpi.avaliacaobackend.model.Project;
import com.zalpi.avaliacaobackend.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

	@NonNull
	private ProjectDAO projectDAO;
	@NonNull
	private ActivityDAO activityDAO;

	@Value("${spring.jpa.hibernate.ddl-auto: none}")
	private String ddlCreate;

	@Transactional
	public void run(ApplicationArguments args) {
		if(!ddlCreate.contains("create")) return;
		User u1 = createUser("davieira", "Danilo", "Vieira", "B0katika#", "ROLE_USER,ROLE_ADMIN");
		User u2 = createUser("thamanda", "Amanda", "Thiesen", "B4naninh@", "ROLE_ADMIN");
		User u3 = createUser("mapietro", "Pietro", "Maximov", "W4ndinh@", "ROLE_USER");
		User u4 = createUser("wapietro", "Wanda", "Maximov", "V1sion#", "ROLE_USER");
		Project p1 = createProject("XPTO-01", "Mangdruva", u1, u2, u3, u4);
		Project p2 = createProject("XYZ-001", "Marvel", u2, u4);
		projectDAO.save(p1);
		projectDAO.save(p2);
		activityDAO.save(createActivity("Start project", "initial activity", u1, p1,LocalDateTime.now(), LocalDateTime.now().plusHours(4)));
		activityDAO.save(createActivity("Start project", "initial activity", u1, p2,LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(8)));
		activityDAO.save(createActivity("Incrementing somethings", null, u3, p1,LocalDateTime.now(), LocalDateTime.now().plusHours(4)));
		activityDAO.save(createActivity("Improvising workaround", "doing some hard work", u4, p2,LocalDateTime.now(), LocalDateTime.now().plusHours(6)));
	}

	private User createUser(String userName, String firstName, String lastName, String password, String roles){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		;
		User user = new User();
		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(encoder.encode(password));
		user.setRoles(roles);
		user.setDtcreation(LocalDateTime.now());
		return user;
	}

	private Project createProject(String description, String clientName, User... users) {
		Project project = new Project();
		project.setDescription(description);
		project.setClientName(clientName);
		project.getContributors().addAll(Set.of(users));
		project.setDtCreation(LocalDateTime.now());
		project.setDtExpectedCompletion(LocalDateTime.now().plusMonths(6));
		return project;
	}

	private Activity createActivity(String description, String details, User user, Project project, LocalDateTime start, LocalDateTime end){
		Activity activity = new Activity();
		activity.setDescription(description);
		activity.setDetails(details);
		activity.setUser(user);
		activity.setProject(project);
		activity.setDtStart(start);
		activity.setDtEnd(end);
		return activity;
	}
}
