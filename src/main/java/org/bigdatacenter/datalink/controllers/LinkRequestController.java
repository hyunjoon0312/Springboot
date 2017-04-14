package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.LinkRequestDAO;
import org.bigdatacenter.datalink.dto.LinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinkRequestController {
	
	@Autowired
	private LinkRequestDAO linkRequestDAO;

	
	@RequestMapping("/linkRequestForm")
	public String linkRequestForm(Model model){
		
		System.out.println("linkRequestForm()");
		
		return "linkRequestForm";
	}
	
	
	@RequestMapping(value="/linkRequest", method = RequestMethod.POST )
	public String addNewlinkRequest(@ModelAttribute LinkRequest linkRequest){
		//IT WILL BIND OUR REQUEST WITH OUR DTO CLASS LinkRequest
		
		System.out.println("linkRequest()");
				
		linkRequestDAO.addNewLinkRequest(linkRequest.getrName(), linkRequest.getIRB(), linkRequest.getRequest_ORG(), linkRequest.getNhis(), linkRequest.getStat(), linkRequest.getCdc());

		return "linkRequest_result";
	}
	
	@RequestMapping(value="/requestList")
	public String requestList(Model model){
		
		System.out.println("requestList()");
		
		ArrayList<LinkRequest> linkRequests = linkRequestDAO.findAllLinkRequest();
		model.addAttribute("requestList", linkRequests);
		
		
		
		return "requestList";
	}
	
	
	@RequestMapping(value="/createSecretKey", method = RequestMethod.POST)
	public String createSecretKey(HttpServletRequest request, Model model){
		
		System.out.println("createSecretKey()");
		
		String IRB = request.getParameter("IRB");
		
		String secretKey = linkRequestDAO.createKey(IRB);
		
		model.addAttribute("secretKey", secretKey);
		
		return "createSecretKey_result";
	}
	
	
}
