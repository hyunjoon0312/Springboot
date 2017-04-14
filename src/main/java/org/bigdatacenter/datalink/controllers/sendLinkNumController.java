package org.bigdatacenter.datalink.controllers;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.sendLinkNumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sendLinkNumController {
	
	@Autowired
	private sendLinkNumDAO sendLinkNumDAO;
	
	
	@RequestMapping("/sendLinknum")
	public String sendLinkNum(HttpServletRequest request){
		
		System.out.println("controller.sendlinkNum()");
		
		String IRB = request.getParameter("IRB");
		String requestORG = request.getParameter("requestORG");
		String linkedORG = request.getParameter("linkedORG");
		IRB = IRB.replace("-", "_");
		
		sendLinkNumDAO.sendLinkNum(IRB, requestORG, linkedORG);
		
		
		return "sendLinkNum_result";
		
	}

	
	
}
