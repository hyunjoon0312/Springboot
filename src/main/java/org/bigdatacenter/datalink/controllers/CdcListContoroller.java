package org.bigdatacenter.datalink.controllers;

import java.util.ArrayList;

import org.bigdatacenter.datalink.dao.CdcList_selectDataDAO;
import org.bigdatacenter.datalink.dao.CdcList_sendDataDAO;
import org.bigdatacenter.datalink.dto.CdcList_selectDataDTO;
import org.bigdatacenter.datalink.dto.CdcList_sendDataDTO;
import org.bigdatacenter.datalink.dto.IRBListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CdcListContoroller {

	
	@Autowired
	private CdcList_selectDataDAO cdcList_selectDataDAO;
	
	@Autowired
	private CdcList_sendDataDAO cdcList_sendDataDAO;
	
	
	@RequestMapping("/cdcList_selectData")
	public String CdcList_selectData(Model model){
		
		System.out.println("controller.CdcList_selectData()");
		
		ArrayList<CdcList_selectDataDTO> cdcList_selectDataDTOs = cdcList_selectDataDAO.CdcList_selectData();
		model.addAttribute("cdcList_selectData", cdcList_selectDataDTOs);
		
		ArrayList<IRBListDTO> irbListDTOs = cdcList_selectDataDAO.IRBList();

		model.addAttribute("IRBList", irbListDTOs);
		
		return "cdcList_selectData";
	}
	
	
	@RequestMapping("/cdcList_sendData")
	public String cdcList_sendData(Model model){
		
		System.out.println("controller.cdcList_sendData");
		
		
		ArrayList<CdcList_sendDataDTO> cdcList_sendDataDTOs = cdcList_sendDataDAO.CdcList_sendData();
		model.addAttribute("cdcList_sendData", cdcList_sendDataDTOs);
		
		return "cdcList_sendData";
		
	}
	
	
	
	
}
