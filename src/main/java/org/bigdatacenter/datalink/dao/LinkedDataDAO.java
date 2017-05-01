package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.LinkedDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class LinkedDataDAO {

	@Autowired
	@Qualifier("dataSource")
	private DataSource datasource;
	
	public ArrayList<LinkedDataDTO> linkedDataView(String IRB){
		
		ArrayList<LinkedDataDTO> linkedDataListDTOs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String db_IRB = IRB.replace("-", "_");
		
		try {
			
			con = datasource.getConnection();
			String sql = "SELECT * FROM J_linkedData."+db_IRB;
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				int No = rs.getInt(1);
				String REPORT_YMD = rs.getString(2);
				int ADDRESS = rs.getInt(3);
				int GENDER = rs.getInt(4);
				String DEATH_YMD = rs.getString(5);
				int DEATH_TIME = rs.getInt(6);
				int DEATH_PLACE = rs.getInt(7);
				String DEATH_JOB = rs.getString(8);
				int MARRY = rs.getInt(9);
				int EDU = rs.getInt(10);
				String DEATH_CAU1 = rs.getString(11);
				String DEATH_CAU1_Parent = rs.getString(12);
				int DEATH_AGE = rs.getInt(13);
				
				int STND_Y = rs.getShort(14);
				int YKIHO_ID = rs.getInt(15);
				int YKIHO_GUBUN_CD = rs.getInt(16);
				int ORG_TYPE = rs.getInt(17);
				int YKIHO_SIDO = rs.getInt(18);
				int SICKBED_CNT = rs.getInt(19);
				int DR_CNT = rs.getInt(20);
				int CT_YN = rs.getInt(21);
				int MRI_YN = rs.getInt(22);
				int PET_YN = rs.getInt(23);
				
				LinkedDataDTO linkedDataListDTO = new LinkedDataDTO(No, REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE, STND_Y, YKIHO_ID, YKIHO_GUBUN_CD, ORG_TYPE, YKIHO_SIDO, SICKBED_CNT, DR_CNT, CT_YN, MRI_YN, PET_YN);
				linkedDataListDTOs.add(linkedDataListDTO);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null){ con.close();}
				if(pstmt != null){ pstmt.close();}
				if(rs != null){ rs.close();}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		return linkedDataListDTOs;
		
	}//end
	
}
