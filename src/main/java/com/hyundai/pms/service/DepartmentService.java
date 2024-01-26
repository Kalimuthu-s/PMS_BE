package com.hyundai.pms.service;

import java.util.Optional;

import com.hyundai.pms.entity.DepartmentMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.webModel.PaginationWebModel;

public interface DepartmentService {

	public Response getAll(PaginationWebModel paginationWebModel);

	public Optional<DepartmentMaster> getById(int depId);

	Response adddepartment(DepartmentMaster depbody);

	Response updatedepartment(DepartmentMaster depbody);

	Response deletedepartment(int depId);

}