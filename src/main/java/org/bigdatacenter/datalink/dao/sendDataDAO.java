package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.nhisRawDataDTO;
import org.bigdatacenter.datalink.svc.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class sendDataDAO {

	@Autowired
	@Qualifier("jLinkerTakeNhisDataSource")
	private DataSource jLinkerTakeNhisDataSource;
	
	@Autowired
	@Qualifier("jLinkerTakeCdcDataSource")
	private DataSource jLinkerTakeCdcDataSource;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	
	
	public void cdc_sendData(String IRB, String requestORG, String linkedORG){
		
		Connection con = null;
		PreparedStatement pstmt = null;

		String db_IRB = IRB.replace("-", "_");
		
		//cdc에서 linker에게 전송한 데이터 받을 테이블 생성
		
		try {
			con = jLinkerTakeCdcDataSource.getConnection();
			String sql = "create table J_linkerTakeCDC."+ db_IRB +"(linkID int(10), REPORT_YMD VARCHAR(20), ADDRESS INT(20), GENDER INT(20), DEATH_YMD VARCHAR(20), DEATH_TIME INT(20), DEATH_PLACE INT(20), DEATH_JOB VARCHAR(20), MARRY INT(20), EDU INT(20), DEATH_CAU1 VARCHAR(20), DEATH_CAU1_Parent VARCHAR(20), DEATH_AGE INT(20), PRIMARY KEY(linkID))";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("linkertakecdc 데이터 받을 테이블 생성완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		//cdc에서 linker에게 데이터 전송 
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO J_linkerTakeCDC."+ db_IRB + 
					"(linkID, REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE) "
					+ "SELECT J_cdcTakeKey."+db_IRB+".linkID, J_cdcSecretData."+db_IRB+".REPORT_YMD, J_cdcSecretData."+db_IRB+".ADDRESS, J_cdcSecretData."+db_IRB+".GENDER, J_cdcSecretData."+db_IRB+".DEATH_YMD, J_cdcSecretData."+db_IRB+".DEATH_TIME, J_cdcSecretData."+db_IRB+".DEATH_PLACE, J_cdcSecretData."+db_IRB+".DEATH_JOB, J_cdcSecretData."+db_IRB+".MARRY, J_cdcSecretData."+db_IRB+".EDU, J_cdcSecretData."+db_IRB+".DEATH_CAU1, J_cdcSecretData."+db_IRB+".DEATH_CAU1_Parent, J_cdcSecretData."+db_IRB+".DEATH_AGE "
					+ "FROM J_cdcSecretData."+ db_IRB +" INNER JOIN J_cdcTakeKey."+db_IRB+" "
							+ "ON J_cdcTakeKey."+db_IRB+".secretID = J_cdcSecretData."+db_IRB+".secretID";			
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("cdc에서 linker에게 데이터 전송완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		//cdc가 linker에게 데이터 보내줌 표시
		
		try {
			con = dataSource.getConnection();
			String sql = "UPDATE J_cdc.Info set sendData = 1 WHERE IRB = '"+IRB+"'";
			
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
	
	
	
	public void nhis_sendData(String IRB, String requestORG, String linkedORG){
		
		System.out.println("dao.nhis_sendData()");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String db_IRB = IRB.replace("-", "_");
		
		//nhis가 가지고 있는 데이터 암호화해서 담을 테이블 생성
		
		try {
			con = dataSource.getConnection();
			String sql = "create table J_nhisSecretData."+db_IRB+"(secretID VARCHAR(100),STND_Y INT(20),YKIHO_ID INT(20),YKIHO_GUBUN_CD INT(20),ORG_TYPE INT(20),YKIHO_SIDO INT(20),SICKBED_CNT INT(20),DR_CNT INT(20),CT_YN INT(20),MRI_YN INT(20),PET_YN INT(20),PRIMARY KEY (secretID))";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			System.out.println("nhis 암호화한 데이터 담을 테이블 생성");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null){con.close();}
				if( pstmt != null){pstmt.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//nhis가 가지고 있는 데이터 읽어오기
		
		ArrayList<nhisRawDataDTO> nhisRawDataDTOs = new ArrayList<>();

		try{
			con = dataSource.getConnection();
			String sql = "select * FROM nhis.nhis_data";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				String PERSON_ID = rs.getString(1);
				int STND_Y = rs.getInt(2);
				int YKIHO_ID = rs.getInt(3);
				int YKIHO_GUBUN_CD = rs.getInt(4);
				int ORG_TYPE = rs.getInt(5);
				int YKIHO_SIDO = rs.getInt(6);
				int SICKBED_CNT = rs.getInt(7);
				int DR_CNT = rs.getInt(8);
				int CT_YN = rs.getInt(9);
				int MRI_YN = rs.getInt(10);
				int PET_YN = rs.getInt(11);
				
				nhisRawDataDTO nhisRawDataDTO = new nhisRawDataDTO(PERSON_ID, YKIHO_ID, YKIHO_GUBUN_CD, ORG_TYPE, YKIHO_SIDO, SICKBED_CNT, DR_CNT, CT_YN, MRI_YN, PET_YN);
				nhisRawDataDTOs.add(nhisRawDataDTO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null){con.close();}
				if( pstmt != null){pstmt.close();}
				if( rs!= null){rs.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		
		
		//nhis 데이터 암호화할 암호화 키 가져오기
		
		String secretKey = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select secretKey FROM J_nhisTakeKey.linked_secretKey";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				secretKey = rs.getString("secretKey");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( con != null ){con.close();}
				if( pstmt != null ){pstmt.close();}
				if( rs != null ){rs.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
		//nhis가 가지고 있는 데이터 암호화
		
		SHA256Util sha256Util = new SHA256Util(secretKey);
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO J_nhisSecretData."+db_IRB+" values(?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			for(int i = 0; i < nhisRawDataDTOs.size() ; i++){
				
				pstmt.setString(1, sha256Util.SHA256Encode(nhisRawDataDTOs.get(i).getPERSON_ID()));
				pstmt.setInt(2, nhisRawDataDTOs.get(i).getSTND_Y());
				pstmt.setInt(3, nhisRawDataDTOs.get(i).getYKIHO_ID());
				pstmt.setInt(4, nhisRawDataDTOs.get(i).getYKIHO_GUBUN_CD());
				pstmt.setInt(5, nhisRawDataDTOs.get(i).getORG_TYPE());
				pstmt.setInt(6, nhisRawDataDTOs.get(i).getYKIHO_SIDO());
				pstmt.setInt(7, nhisRawDataDTOs.get(i).getSICKBED_CNT());
				pstmt.setInt(8, nhisRawDataDTOs.get(i).getDR_CNT());
				pstmt.setInt(9, nhisRawDataDTOs.get(i).getCT_YN());
				pstmt.setInt(10, nhisRawDataDTOs.get(i).getMRI_YN());
				pstmt.setInt(11, nhisRawDataDTOs.get(i).getPET_YN());
				pstmt.addBatch();
			}
			int[] cnt = pstmt.executeBatch();
			System.out.println(cnt.length + "row 성공 // nhis가 가지고 있는 데이터 암호화해서 입력완료");
			
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
		
		
		//nhis가 linker에게 전송
		
		//nhis에서 linker에게 전송한 데이터 받을 테이블 생성
		
				try {
					con = dataSource.getConnection();
					String sql = "create table J_linkerTakeNHIS."+ db_IRB +"(linkID int(10), STND_Y INT(20),YKIHO_ID INT(20),YKIHO_GUBUN_CD INT(20),ORG_TYPE INT(20),YKIHO_SIDO INT(20),SICKBED_CNT INT(20),DR_CNT INT(20),CT_YN INT(20),MRI_YN INT(20),PET_YN INT(20),PRIMARY KEY (linkID))";
					
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					System.out.println("linkertakeNHIS 데이터 받을 테이블 생성완료");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (con != null) {
							con.close();
						}
						if (pstmt != null) {
							pstmt.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
				
				
				//nhis에서 linker에게 데이터 전송 
				
				try {
					con = dataSource.getConnection();
					String sql = "INSERT INTO J_linkerTakeNHIS."+ db_IRB + 
							"(linkID, STND_Y, YKIHO_ID, YKIHO_GUBUN_CD, ORG_TYPE, YKIHO_SIDO, SICKBED_CNT, DR_CNT, CT_YN, MRI_YN, PET_YN) "
							+ "SELECT J_nhisTakeKey."+db_IRB+".linkID, J_nhisSecretData."+db_IRB+".STND_Y, J_nhisSecretData."+db_IRB+".YKIHO_ID, J_nhisSecretData."+db_IRB+".YKIHO_GUBUN_CD, J_nhisSecretData."+db_IRB+".ORG_TYPE, J_nhisSecretData."+db_IRB+".YKIHO_SIDO, J_nhisSecretData."+db_IRB+".SICKBED_CNT, J_nhisSecretData."+db_IRB+".DR_CNT, J_nhisSecretData."+db_IRB+".CT_YN, J_nhisSecretData."+db_IRB+".MRI_YN, J_nhisSecretData."+db_IRB+".PET_YN "
							+ "FROM J_nhisSecretData."+ db_IRB +" INNER JOIN J_nhisTakeKey."+db_IRB+" "
									+ "ON J_nhisTakeKey."+db_IRB+".secretID = J_nhisSecretData."+db_IRB+".secretID";			
					
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					System.out.println("nhis에서 linker에게 데이터 전송완료");
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if (con != null) {
							con.close();
						}
						if (pstmt != null) {
							pstmt.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
				
				
				//nhis가 linker에게 데이터 보내줌 표시
				
				try {
					con = dataSource.getConnection();
					String sql = "UPDATE J_nhis.Info set sendData = 1 WHERE IRB = '"+IRB+"'";
					
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
				
				
				//nhis 전부 암호화한 DB 삭제
				
				
				try {
					con = dataSource.getConnection();
					String sql = "DROP TABLE J_nhisSecretData."+db_IRB;
					
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					System.out.println("nhis 전부 암호화한 DB 테이블 삭제");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if ( con != null ){ con.close();}
						if ( pstmt != null){ pstmt.close();}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		
		
	}//end
	
	
	
}
