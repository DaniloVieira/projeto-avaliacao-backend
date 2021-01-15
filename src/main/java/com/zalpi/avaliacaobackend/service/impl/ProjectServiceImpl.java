package com.zalpi.avaliacaobackend.service.impl;

import java.util.List;

import com.zalpi.avaliacaobackend.dao.ProjectDAO;
import com.zalpi.avaliacaobackend.dto.response.ProjectFilterDTO;
import com.zalpi.avaliacaobackend.dto.response.ResponseObject;
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
			for(Project p : resultList){
				System.out.println(p.getClientName());
			}
			return createResponse(resultList, SUCCESS_MESSAGE, filter.getPageSize(), totalSize , filter.getPage(), null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(null, ERROR_MESSAGE, e);
		}
	}
}
