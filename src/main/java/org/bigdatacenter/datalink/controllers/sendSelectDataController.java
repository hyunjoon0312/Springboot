package org.bigdatacenter.datalink.controllers;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.sendSelectDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sendSelectDataController {

	@Autowired
	private sendSelectDataDAO selectDataDAO;

	@RequestMapping("/sendSelectData")
	public String sendSelectData(HttpServletRequest request) {

		System.out.println("controller.sendSelectData");

		String person_ID[] = request.getParameterValues("checks");
		String REPORT_YMD[] = request.getParameterValues("REPORT_YMD");
		String ADDRESS[] = request.getParameterValues("ADDRESS");
		String GENDER[] = request.getParameterValues("GENDER");
		String DEATH_YMD[] = request.getParameterValues("DEATH_YMD");
		String DEATH_TIME[] = request.getParameterValues("DEATH_TIME");
		String DEATH_PLACE[] = request.getParameterValues("DEATH_PLACE");
		String DEATH_JOB[] = request.getParameterValues("DEATH_JOB");
		String MARRY[] = request.getParameterValues("MARRY");
		String EDU[] = request.getParameterValues("EDU");
		String DEATH_CAU1[] = request.getParameterValues("DEATH_CAU1");
		String DEATH_CAU1_Parent[] = request.getParameterValues("DEATH_CAU1_Parent");
		String DEATH_AGE[] = request.getParameterValues("DEATH_AGE");
		String IRB_Num = request.getParameter("IRB_Num");

		selectDataDAO.sendSelectDataDAO(person_ID, REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE, IRB_Num);

		return "sendSelectData_result";
	}

}
