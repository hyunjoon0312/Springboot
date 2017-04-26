package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.LinkedDataListDAO;
import org.bigdatacenter.datalink.dto.LinkedDataListDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkedDataListController {


	private LinkedDataListDAO linkedDataListDAO;
	
	@RequestMapping("/linkedData")
	public String linkedDataLsit(HttpServletRequest request, Model model){
		
		System.out.println("controller.LinkedDataListController");
		
		String IRB = request.getParameter("IRB");
		
		ArrayList<LinkedDataListDTO> linkedDataListDTOs;
		linkedDataListDTOs = linkedDataListDAO.linkedDataList(IRB);
		model.addAttribute("linkedDataList", linkedDataListDTOs);		
		
		return "linkedData";
	}
}
