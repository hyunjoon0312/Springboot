package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import org.bigdatacenter.datalink.dao.LinkedData_List_DAO;
import org.bigdatacenter.datalink.dto.LinkedData_List_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkedDataListController {

	@Autowired
	private LinkedData_List_DAO linkedData_List_DAO;
	
	@RequestMapping("/linkedDataList")
	public String linkedDataList(Model model){
		
		System.out.println("controller.LinkedDataListController");
		
		ArrayList<LinkedData_List_DTO> linkedData_List_DTOs;
		linkedData_List_DTOs = linkedData_List_DAO.LinkedDataList();
		model.addAttribute("linkedDataList", linkedData_List_DTOs);
		
		System.out.println(linkedData_List_DTOs);
		return "linkedData_List";
		
	}
}
