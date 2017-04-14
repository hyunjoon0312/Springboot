package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import org.bigdatacenter.datalink.dao.nhisList_sendDataDAO;
import org.bigdatacenter.datalink.dto.NhisList_sendDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NhisListController {

	@Autowired
	private nhisList_sendDataDAO nhisList_sendDataDAO;
	
	@RequestMapping("/nhisList_sendData")
	public String necaList_sendData(Model model){
		
		System.out.println("controller.necaList_sendData()");
		
		
		ArrayList<NhisList_sendDataDTO> nhisList_sendDataDTOs = nhisList_sendDataDAO.nhisList();
		model.addAttribute("nhisList_sendData", nhisList_sendDataDTOs);
		
		return "nhisList_sendData";
	}
}
