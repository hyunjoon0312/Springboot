package org.bigdatacenter.datalink.controllers;

import java.sql.SQLException;
import java.util.List;

import org.bigdatacenter.datalink.dao.InfoDAO;
import org.bigdatacenter.datalink.dto.InfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NhisInfoController {
	
	@Autowired
	private InfoDAO infoDAO; // @Autowired is equals to  = new InfoDAO()

	@RequestMapping("/nhisinfo/findAll")
	public String findAllInfos() throws SQLException{
		List<InfoDTO> infoDTOs = infoDAO.findAllInfos();
		for(InfoDTO infoDTO: infoDTOs){
			System.out.println(infoDTO);
		}
		return "";
	}
}
