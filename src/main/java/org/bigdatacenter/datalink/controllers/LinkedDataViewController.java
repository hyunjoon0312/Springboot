package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.bigdatacenter.datalink.dao.LinkedDataDAO;
import org.bigdatacenter.datalink.dto.LinkedDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkedDataViewController {

	@Autowired
	private LinkedDataDAO linkedDataDAO;
	
	@RequestMapping("/linkedDataView")
	public String linkedDataLsit(HttpServletRequest request, Model model){
		
		System.out.println("controller.LinkedDataListController");
		
		String IRB = request.getParameter("IRB");
		String rName = request.getParameter("rName");
		String requestORG = request.getParameter("requestORG");
		String linkedORG = request.getParameter("linkedORG");
		
		model.addAttribute("rName", rName);
		model.addAttribute("requestORG", requestORG);
		model.addAttribute("linkedORG", linkedORG);

		
		ArrayList<LinkedDataDTO> linkedDataDTOs;
		linkedDataDTOs = linkedDataDAO.linkedDataView(IRB);
		System.out.println(linkedDataDTOs);
		model.addAttribute("linkedDataList", linkedDataDTOs);		
		
		
		
		return "linkedDataView";
	}
}
