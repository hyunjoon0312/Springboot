package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.svc.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class sendSelectDataDAO {

	
	@Autowired
	@Qualifier("jCdcTakeKeyDataSource")
	private DataSource jCdcTakeKeyDataSource;

	@Autowired
	@Qualifier("jCdcSecretDataSource")
	private DataSource jCdcSecretDataSource;

	@Autowired
	@Qualifier("jnecaTakeIDDataSource")
	private DataSource jnecaTakeIDDataSource;
	
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	public void sendSelectDataDAO(String person_ID[], String REPORT_YMD[], String ADDRESS[], String GENDER[], String DEATH_YMD[],String DEATH_TIME[],String DEATH_PLACE[],String DEATH_JOB[],String MARRY[],String EDU[],String DEATH_CAU1[], String DEATH_CAU1_Parent[], String DEATH_AGE[], String IRB_Num){
		
		String secretKey = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		//암호키 가져오기
		try {
			
			con = jCdcTakeKeyDataSource.getConnection();
			String sql = "select * FROM J_cdcTakeKey.request_secretKey WHERE IRB = '"+IRB_Num+"'";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				secretKey = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null){con.close();}
				if( pstmt != null){pstmt.close();}
				if( rs != null){rs.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	
		
		SHA256Util sha256Util = new SHA256Util(secretKey);
		
		IRB_Num = IRB_Num.replace("-", "_");
		
		//데이터 담을 테이블 생성

		try{
			con = jCdcSecretDataSource.getConnection();
			String sql = "CREATE Table J_cdcSecretData."+IRB_Num+"(secretID VARCHAR(100), REPORT_YMD VARCHAR(20), ADDRESS INT(20), GENDER INT(20), DEATH_YMD VARCHAR(20), DEATH_TIME INT(20), DEATH_PLACE INT(20), DEATH_JOB VARCHAR(20), MARRY INT(20), EDU INT(20), DEATH_CAU1 VARCHAR(20), DEATH_CAU1_Parent VARCHAR(20), DEATH_AGE INT(20), PRIMARY KEY(secretID))";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("암호화된 주민번호 담은 테이블 생성");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
			if(con != null){con.close();}
			if(pstmt != null){pstmt.close();}
			}catch(Exception e2){
				e2.printStackTrace();
				}
			}
		

		//주민번호 암호화 후 데이터 담기

		try{
			con = jCdcSecretDataSource.getConnection();
			String sql = "INSERT INTO J_cdcSecretData."+IRB_Num+"(secretID , REPORT_YMD , ADDRESS , GENDER , DEATH_YMD , DEATH_TIME , DEATH_PLACE , DEATH_JOB , MARRY , EDU , DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			
			
			for(int i = 0 ; i < person_ID.length ; i++){
			
				System.out.println(person_ID[i]);
				pstmt.setString(1, sha256Util.SHA256Encode(person_ID[i]));
				pstmt.setString(2, REPORT_YMD[i]);
				pstmt.setInt(3, Integer.parseInt(ADDRESS[i]));
				pstmt.setInt(4, Integer.parseInt(GENDER[i]));
				pstmt.setString(5, DEATH_YMD[i]);
				pstmt.setInt(6, Integer.parseInt(DEATH_TIME[i]));
				pstmt.setInt(7, Integer.parseInt(DEATH_PLACE[i]));
				pstmt.setString(8,	DEATH_JOB[i]);
				pstmt.setInt(9, Integer.parseInt(MARRY[i]));
				pstmt.setInt(10, Integer.parseInt(EDU[i]));
				pstmt.setString(11, DEATH_CAU1[i]);
				pstmt.setString(12, DEATH_CAU1_Parent[i]);
				pstmt.setString(13, DEATH_AGE[i]);
				pstmt.addBatch();
				
			}
			int[] cnt = pstmt.executeBatch();
			System.out.println(cnt.length + "row 성공  //  데이터 입력완료");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null){con.close();}
				if(pstmt != null){pstmt.close();}
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		//요청 기관이 보내준 암호화키 저장할 테이블 생성
		try{
			con = jnecaTakeIDDataSource.getConnection();
			String sql = "CREATE Table J_necaTakeID."+IRB_Num+"(linkID int(20) auto_increment, secretID VARCHAR(100), PRIMARY KEY(linkID))";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("neca 테이블 생성");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null){con.close();}
				if(pstmt != null){pstmt.close();}
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		//요청기관에서 데이터 전송
		try {

					con = jnecaTakeIDDataSource.getConnection();

					String sql = "INSERT INTO J_necaTakeID."+IRB_Num+" VALUES(DEFAULT,?)";

					pstmt = con.prepareStatement(sql);

					for(int i = 0 ; i < person_ID.length ; i++){
						pstmt.setString(1, sha256Util.SHA256Encode(person_ID[i]));
						pstmt.addBatch();
					}
					int[] cnt = pstmt.executeBatch();
					System.out.println(cnt.length + "row 성공  //  neca 데이터 전송 완료");
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(con != null){con.close();}
				if(pstmt != null){pstmt.close();}
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		

		
		
		//cdc가 neca에게 데이터 보내줌 표시
		
		try {
			
			String IRB = IRB_Num;
			IRB = IRB_Num.replaceAll("_", "-");
					
			con = dataSource.getConnection();
			String sql = "UPDATE J_neca.Info set takePersonID = 1 WHERE IRB = '"+IRB+"'";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null ){con.close();}
				if( pstmt != null ){pstmt.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		

		
		
	}//end
}
