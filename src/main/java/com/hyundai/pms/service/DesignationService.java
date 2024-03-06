package com.hyundai.pms.service;

import java.util.Optional;

import com.hyundai.pms.entity.DesignationMaster;
import com.hyundai.pms.entity.Response;
import com.hyundai.pms.webModel.PaginationWebModel;

public interface DesignationService {

	public Response getAll(PaginationWebModel paginationWebModel);

	public Optional<DesignationMaster> getById(int desgId);

	Response adddesignation(DesignationMaster desgbody);

	Response updatedesignation(DesignationMaster desgbody);

	Response deletedesignation(int desgId);

}