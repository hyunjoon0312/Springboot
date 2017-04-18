package org.bigdatacenter.datalink.controllers;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.DataLinkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataLinkController {

	
	@Autowired
	private DataLinkDAO DataLinkDAO;
	
	@RequestMapping("/linkData")
	public String linkData(HttpServletRequest request){
		
		System.out.println("DataLinkController.java");
		
		
		String IRB = request.getParameter("IRB");
		DataLinkDAO.dataLink(IRB);
		
		return "linkData_result";
	}
}
