package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.CdcList_selectDataDTO;
import org.bigdatacenter.datalink.dto.IRBListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CdcList_selectDataDAO {

	
	@Autowired
	@Qualifier("jCdcTakeKeyDataSource")
	private DataSource jCdcTakeKeyDataSource;

	@Autowired
	@Qualifier("statDataSource")
	private DataSource statDataSource;
	
	public ArrayList<CdcList_selectDataDTO> CdcList_selectData(){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		ArrayList<CdcList_selectDataDTO> cdcListDTOs = new ArrayList<>();
		
		try {
			con = statDataSource.getConnection();
			
			String sql = "SELECT * FROM stat_data";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String PERSON_ID = rs.getString(1);
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
				
				CdcList_selectDataDTO cdcListDTO = new CdcList_selectDataDTO(PERSON_ID, REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE);
				cdcListDTOs.add(cdcListDTO);
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
		
		return cdcListDTOs;
		
	}//end
	
	
	public ArrayList<IRBListDTO> IRBList(){
		
		ArrayList<IRBListDTO> irbListDTOs = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = jCdcTakeKeyDataSource.getConnection();
			
			String sql = "SELECT IRB FROM J_cdcTakeKey.request_secretKey";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String sIRB = rs.getString(1);
				
				IRBListDTO irbListDTO = new IRBListDTO(sIRB);
				irbListDTOs.add(irbListDTO);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null){con.close();}
				if( pstmt != null){con.close();}
				if( rs != null){con.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return irbListDTOs;
	}//end
	
	
}
