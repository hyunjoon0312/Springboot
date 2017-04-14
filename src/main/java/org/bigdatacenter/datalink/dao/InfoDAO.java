package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.InfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class InfoDAO {

	@Autowired
	@Qualifier("jNHISDataSource")
	private DataSource jNHICDataSource;
	
	public List<InfoDTO> findAllInfos() throws SQLException{
		Connection con = jNHICDataSource.getConnection();
		
		Statement statement= con.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM Info");
		
		List<InfoDTO> infoDTOs = new ArrayList<>();
		while(rs.next()){
			InfoDTO infoDTO = new InfoDTO();
			infoDTO.setIRB(rs.getString("IRB"));
			infoDTO.setRname(rs.getString("Rname"));
			infoDTO.setRequestORG(rs.getString("requestORG"));
			infoDTO.setLinkedORG(rs.getString("linkedORG"));
			infoDTOs.add(infoDTO);
		}
		return infoDTOs;
	}
}
