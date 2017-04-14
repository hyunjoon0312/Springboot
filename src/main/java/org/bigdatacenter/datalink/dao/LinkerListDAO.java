package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.LinkerListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class LinkerListDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	public ArrayList<LinkerListDTO> linkerList(){
		
		
		ArrayList<LinkerListDTO> linkerListDTOs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = dataSource.getConnection();
			String sql = "SELECT * FROM J_linker.Info";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String IRB = rs.getString(1);
				String rName = rs.getString(2);
				String requestORG = rs.getString(3);
				String linkedORG = rs.getString(4);
				int linkData = rs.getInt(5);
				
				LinkerListDTO linkerListDTO = new LinkerListDTO(IRB, rName, requestORG, linkedORG, linkData);
				linkerListDTOs.add(linkerListDTO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null){con.close();}
				if(pstmt != null){pstmt.close();}
				if(rs != null){rs.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return linkerListDTOs;
	}//end
}
