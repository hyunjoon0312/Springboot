package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class sendSecretKeyDAO {

	@Autowired
	@Qualifier("jNhisTakeKeyDataSource")
	private DataSource jNhisTakeKeyDataSource;

	@Autowired
	@Qualifier("jCdcTakeKeyDataSource")
	private DataSource jCdcTakeKeyDataSource;

	@Autowired
	@Qualifier("jNecaDataSource")
	private DataSource jNecaDataSource;
	
	
	@Autowired
	@Qualifier("ssisRequestDataInfoDataSource")
	private DataSource ssisRequestDataInfoDataSource;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	public void sendSecretKey(String Rname, String IRB, String secretKey, String requestORG, String linkedORG){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		// 요청 기관에 전송
		
		if (requestORG.equals("권역심뇌혈관센터")) {
			
			//암호키 전송
			try {
			
				System.out.println("sendSecretKeyDAO.sendSecretKey()");
			
				con = jCdcTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_cdcTakeKey.request_secretKey VALUES(?,?)";
				
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, IRB);
				pstmt.setString(2, secretKey);

				pstmt.executeUpdate();
				System.out.println("권역심뇌혈관센터 암호키 전달");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if( con != null) {con.close();}
					if( pstmt != null) {pstmt.close();}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
				// 요청에 대한 정보 전송
				try {
					con = jCdcTakeKeyDataSource.getConnection();
					String sql = "INSERT INTO J_cdc.Info VALUES(?,?,?,?,?,?)";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, IRB);
					pstmt.setString(2, Rname);
					pstmt.setString(3, requestORG);
					pstmt.setString(4, linkedORG);
					pstmt.setInt(5, 0);
					pstmt.setInt(6, 0);
					
					pstmt.executeUpdate();
					
					System.out.println("권역심뇌혈관센터에 요청 정보 전달");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try{
					if (con != null) { con.close();}
					if (pstmt != null) { pstmt.close();}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			
		}else if (requestORG.equals("건강보험공단")) {

			//암호키 전달
			try {
				con = jNhisTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_nhisTakeKey.request_secretKey VALUES(?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, IRB);
				pstmt.setString(2, secretKey);

				pstmt.executeUpdate();
				System.out.println("건강보험공단 암호키 전달");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if (con != null) { con.close();}
					if (pstmt != null) { pstmt.close();}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
			}

			// 요청에 대한 정보 전송

			try {
				con = jNhisTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_nhis.Info VALUES(?,?,?,?,?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, IRB);
				pstmt.setString(2, Rname);
				pstmt.setString(3, requestORG);
				pstmt.setString(4, linkedORG);
				pstmt.setInt(5, 0);
				pstmt.setInt(6, 0);

				pstmt.executeUpdate();
				System.out.println("건강보험공단에 요청 정보 전달");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if (con != null) { con.close();}
					if (pstmt != null) { pstmt.close();}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		}//if end
		
		//연결기관 암호키 전달
		
		if (linkedORG.equals("건강보험공단")) {
			
			//암호키 전달
			try {
				con = jNhisTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_nhisTakeKey.linked_secretKey VALUES(?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, IRB);
				pstmt.setString(2, secretKey);

				pstmt.executeUpdate();
				System.out.println("건강보험공단 암호키 전달");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if (con != null) { con.close();}
					if (pstmt != null) { pstmt.close();}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
			}
			
			// 요청에 대한 정보 전송
			
					try {
						con = jNhisTakeKeyDataSource.getConnection();
						String sql = "INSERT INTO J_nhis.Info VALUES(?,?,?,?,?,?)";

						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, IRB);
						pstmt.setString(2, Rname);
						pstmt.setString(3, requestORG);
						pstmt.setString(4, linkedORG);
						pstmt.setInt(5, 0);
						pstmt.setInt(6, 0);

						pstmt.executeUpdate();
						System.out.println("건강보험공단에 요청 정보 전달");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try{
							if (con != null) { con.close();}
							if (pstmt != null) { pstmt.close();}
							}catch (Exception e2) {
								e2.printStackTrace();
							}
					}
			
		}//if end --------------------- 연계기관 추가시 밑으로 else if 써서 쭉 만들면됨. -------------------
		 //-------------------------------------------------------------------------------------------------
		
		
		//neca에 정보 전송
		

		try {
				con = jNecaDataSource.getConnection();
				String sql = "INSERT INTO J_neca.Info VALUES(?,?,?,?,?,?)";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, IRB);
				pstmt.setString(2, Rname);
				pstmt.setString(3, requestORG);
				pstmt.setString(4, linkedORG);
				pstmt.setInt(5, 0);
				pstmt.setInt(6, 0);

				pstmt.executeUpdate();
				System.out.println("NECA에 요청 정보 전달");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if (con != null) { con.close();}
					if (pstmt != null) { pstmt.close();}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		
		
		// 각 기관에 암호키 보냄 표시

		try {
			con = ssisRequestDataInfoDataSource.getConnection();
			String sql = "UPDATE SSIS_requestDataInfo.requestDataInfo SET sendKey = 1 WHERE IRB = '" + IRB + "'";
			pstmt = con.prepareStatement(sql);

			pstmt.executeUpdate();
			System.out.println("암호키 전달 표시");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (con != null) { con.close();}
				if (pstmt != null) { pstmt.close();}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		
		//linker에게 요청에 대한 정보 보냄
		
		// 요청에 대한 정보 전송

		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO J_linker.Info VALUES(?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, IRB);
			pstmt.setString(2, Rname);
			pstmt.setString(3, requestORG);
			pstmt.setString(4, linkedORG);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);

			pstmt.executeUpdate();
			System.out.println("연계기관에 요청 정보 전달");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (con != null) { con.close();}
				if (pstmt != null) { pstmt.close();}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
		
		
		//연계된 데이터에 대한 정보
		
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO J_linkedData.Info VALUES(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, IRB);
			pstmt.setString(2, Rname);
			pstmt.setString(3, requestORG);
			pstmt.setString(4, linkedORG);
			pstmt.setInt(5, 0);

			pstmt.executeUpdate();
			System.out.println("연계데이터에 대한 정보 전달");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if (con != null) { con.close();}
				if (pstmt != null) { pstmt.close();}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		
	}//sendSecretkey end
}
