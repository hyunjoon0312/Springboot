package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.CdcList_sendDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CdcList_sendDataDAO {

	
	@Autowired
	@Qualifier("jCdcDataSource")
	private DataSource jCdcDataSource;
	
	
	
	
	public ArrayList<CdcList_sendDataDTO> CdcList_sendData(){
		
		ArrayList<CdcList_sendDataDTO> cdcList_sendDataDTOs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = jCdcDataSource.getConnection();
			String sql = "SELECT * FROM J_cdc.Info";
				
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String IRB = rs.getString(1);
				String rName = rs.getString(2);
				String requestORG = rs.getString(3);
				String linkedORG = rs.getString(4);
				int sendData = rs.getInt(5);
				
				CdcList_sendDataDTO cdcList_sendDataDTO = new CdcList_sendDataDTO(IRB, rName, requestORG, linkedORG, sendData);
				cdcList_sendDataDTOs.add(cdcList_sendDataDTO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(con!=null){con.close();}
				if(pstmt != null){pstmt.close();}
				if(rs != null){rs.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return cdcList_sendDataDTOs;
		
	}//end
	
}
