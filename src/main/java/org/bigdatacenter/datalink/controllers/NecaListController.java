package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import org.bigdatacenter.datalink.dao.necaListDAO;
import org.bigdatacenter.datalink.dto.NecaListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NecaListController {

	@Autowired
	private necaListDAO necaListDAO;
	
	
	@RequestMapping("/necaList")
	public String necaList(Model model){
		
		System.out.println("controller.necaList()");
		
		
		ArrayList<NecaListDTO> necaListDTOs = necaListDAO.necaList();
		model.addAttribute("necaList", necaListDTOs);
		
		return "necaList";
	}
	
}
