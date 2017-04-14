package org.bigdatacenter.datalink.controllers;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.bigdatacenter.datalink.dao.sendDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sendDataController {

	
	@Autowired
	private sendDataDAO sendDataDAO;
	
	
	@RequestMapping("/cdc_sendData")
	public String cdc_sendData(HttpServletRequest request){
	
		System.out.println("controller.cdc_sendData");
		
		String IRB = request.getParameter("IRB");
		String requestORG = request.getParameter("requestORG");
		String linkedORG = request.getParameter("linkedORG");
		
		sendDataDAO.cdc_sendData(IRB, requestORG, linkedORG);
		
		
		return "cdc_sendData_result";
		
	}
	
	@RequestMapping("/nhis_sendData")
	public String nhis_sendData(HttpServletRequest request){
	
		System.out.println("controller.nhis_sendData");
		
		String IRB = request.getParameter("IRB");
		String requestORG = request.getParameter("requestORG");
		String linkedORG = request.getParameter("linkedORG");
		
		sendDataDAO.nhis_sendData(IRB, requestORG, linkedORG);
		
		return "nhis_sendData_result";
		
	}
	
	
	
}
