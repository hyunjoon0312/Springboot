package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import org.bigdatacenter.datalink.dao.LinkerListDAO;
import org.bigdatacenter.datalink.dto.LinkerListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkerListController {

	@Autowired
	private LinkerListDAO linkerListDAO;
	
	@RequestMapping("/linkerList")
	public String linkerList(Model model){
		
		System.out.println("controller.linkeList");
		
		ArrayList<LinkerListDTO> linkerListDTOs = linkerListDAO.linkerList();
		model.addAttribute("linkerList", linkerListDTOs);
		
		
		return "linkerList";
	}
}
