package com.zalpi.avaliacaobackend.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.zalpi.avaliacaobackend.dao.ActivityDAO;
import com.zalpi.avaliacaobackend.dao.ProjectDAO;
import com.zalpi.avaliacaobackend.dao.UserDao;
import com.zalpi.avaliacaobackend.dto.ActivityDTO;
import com.zalpi.avaliacaobackend.dto.ProjectDTO;
import com.zalpi.avaliacaobackend.dto.filter.ActivityFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
import com.zalpi.avaliacaobackend.model.Activity;
import com.zalpi.avaliacaobackend.model.Project;
import com.zalpi.avaliacaobackend.model.User;
import com.zalpi.avaliacaobackend.service.ActivityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.ERROR_MESSAGE;
import static com.zalpi.avaliacaobackend.constant.ResponseMessage.SUCCESS_MESSAGE;
import static com.zalpi.avaliacaobackend.util.misc.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

	@NonNull
	private ActivityDAO activityDAO;

	@NonNull
	private UserDao userDao;

	@NonNull
	private ProjectDAO projectDAO;

	@Override
	public ResponseObject<List<Activity>> listByFilters(ActivityFilterDTO filter) {
		try {
			return createResponse(createResultSet(activityDAO.listByFilters(filter)), SUCCESS_MESSAGE, filter.getPageSize(), activityDAO.totalRecords(filter) , filter.getPage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}

	private Set<ActivityDTO> createResultSet(List<Activity> resultList){
		return resultList.stream()
			.map(p -> this.createDTO(p))
			.collect(Collectors.toSet());
	}

	private ActivityDTO createDTO(Activity activity){
		return ActivityDTO.builder()
			.id(activity.getId())
			.details(activity.getDetails())
			.description(activity.getDescription())
			.dtStart(activity.getDtStart())
			.dtEnd(activity.getDtEnd())
			.projectId(activity.getUser().getId())
			.userId(activity.getUser().getId())
			.build();
	}

	@Override
	public ResponseObject<Activity> saveActivity(ActivityDTO activityDTO) {
		Activity activity = createActivity(activityDTO);
		try{
			activityDTO.setId(activityDAO.save(activity).getId());
			return createResponse(activityDTO, SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}

	private Activity createActivity(ActivityDTO dto) {
		Activity activity = new Activity();
		activity.setId(dto.getId());
		activity.setDetails(dto.getDetails());
		activity.setDescription(dto.getDescription());
		activity.setDtStart(dto.getDtStart());
		activity.setDtEnd(dto.getDtEnd());
		activity.setUser(getUser(dto));
		activity.setProject(getProject(dto));
		return activity;
	}

	//TODO throw exception if project doesn't exist
	private Project getProject(ActivityDTO dto) {
		return projectDAO.getOne(dto.getProjectId());
	}

	//TODO throw exception user doesn't exist
	private User getUser(ActivityDTO dto) {
//		return userDao.findById(dto.getUserId()).get();
		return userDao.getOne(dto.getUserId());
	}

}
