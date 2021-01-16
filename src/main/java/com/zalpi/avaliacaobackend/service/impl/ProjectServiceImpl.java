package com.zalpi.avaliacaobackend.service.impl;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.zalpi.avaliacaobackend.dao.ProjectDAO;
import com.zalpi.avaliacaobackend.dto.ProjectDTO;
import com.zalpi.avaliacaobackend.dto.filter.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Activity;
import com.zalpi.avaliacaobackend.model.Project;
import com.zalpi.avaliacaobackend.service.ProjectService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.ERROR_MESSAGE;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.SUCCESS_MESSAGE;
import static com.zalpi.avaliacaobackend.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	@NonNull
	private ProjectDAO projectDAO;

	@Override
	public ResponseObject<List<Project>> listByFilters(ProjectFilterDTO filter) {
		try {
			Integer totalSize = projectDAO.totalRecords(filter);
			List<Project> resultList = projectDAO.listByFilters(filter);

			return createResponse(resultList, SUCCESS_MESSAGE, filter.getPageSize(), totalSize , filter.getPage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}

	@Override
	public ResponseObject<List<ProjectDTO>> listDTOByFilters(ProjectFilterDTO filter) {
		try {
			Integer totalSize = projectDAO.totalRecords(filter);
			List<Project> resultList = projectDAO.listByFilters(filter);

			return createResponse(createResultSet(resultList), SUCCESS_MESSAGE, filter.getPageSize(), totalSize , filter.getPage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}

	private Set<ProjectDTO> createResultSet(List<Project> resultList){
		return resultList.stream()
			.map(p -> this.createDTO(p))
			.collect(Collectors.toSet());
	}

	private ProjectDTO createDTO(Project project){
		BigInteger totalHours = calculateTotalHours(project.getActivities());
		return ProjectDTO.builder()
			.id(project.getId())
			.clientName(project.getClientName())
			.description(project.getDescription())
			.totalHours(totalHours)
			.build();
	}

	private BigInteger hoursExpent (LocalDateTime start, LocalDateTime end){
		if(Objects.isNull(start) && Objects.isNull(end)) return BigInteger.ZERO;
		Duration duration = Duration.between(start, end);
		return BigInteger.valueOf(duration.toHours()) ;
	}

	private BigInteger calculateTotalHours(Set<Activity> activities) {
		List<BigInteger> result = activities
			.stream()
			.map(a  -> this.hoursExpent(a.getDtStart(), a.getDtEnd()))
			.collect(Collectors.toList());

		return result.stream()
			.reduce((n1, n2) -> n1.add(n2)).orElse(BigInteger.ZERO);
	}
}
