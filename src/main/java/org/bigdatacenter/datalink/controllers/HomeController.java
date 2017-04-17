package org.bigdatacenter.datalink.controllers;

import org.bigdatacenter.datalink.dao.LinkRequestDAO;
import org.bigdatacenter.datalink.dto.LinkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@Autowired
	private LinkRequestDAO linkRequestDAO;

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(){
		return "home";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	
	@RequestMapping("/cdc_main")
	public String cdc_main(){
		return "cdc_main";
	}

	
//	@RequestMapping("/linkRequestForm")
//	public String linkRequestForm(Model model){
//		
//		System.out.println("linkRequestForm()");
//		
//		return "linkRequestForm";
//	}
//	
//	@RequestMapping(value="/linkRequest", method = RequestMethod.POST )
//	public String addNewlinkRequest(@ModelAttribute LinkRequest linkRequest){
//		//IT WILL BIND OUR REQUEST WITH OUR DTO CLASS LinkRequest
//		
//		System.out.println("linkRequest()");
//				
//		linkRequestDAO.addNewLinkRequest(linkRequest.getRname(), linkRequest.getIRB(), linkRequest.getRequest_ORG(), linkRequest.getNhis(), linkRequest.getStat(), linkRequest.getCdc());
//
//		return "linkRequest_result";
//	}
}
