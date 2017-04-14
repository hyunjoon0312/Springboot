package org.bigdatacenter.datalink.controllers;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.sendSecretKeyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sendSecretKeyController {

	
	@Autowired
	private sendSecretKeyDAO sendSecretKeyDAO;
	
	
	@RequestMapping("/sendSecretKey")
	public String sendSecretKey(HttpServletRequest request){
		
		System.out.println("controller.sendSecretkey()");
		
		String Rname = request.getParameter("Rname");
		String IRB = request.getParameter("IRB");
		String secretKey = request.getParameter("secretKey");
		String requestORG = request.getParameter("requestORG");
		String linkedORG = request.getParameter("linkedORG");
		
		sendSecretKeyDAO.sendSecretKey(Rname, IRB, secretKey, requestORG, linkedORG);
		
		return "sendSecretKey_result";
	}
	
}
