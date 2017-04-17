package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class sendLinkNumDAO {

	@Autowired
	@Qualifier("jnecaTakeIDDataSource")
	private DataSource jnecaTakeIDDataSource;

	@Autowired
	@Qualifier("jCdcTakeKeyDataSource")
	private DataSource jCdcTakeKeyDataSource;

	@Autowired
	@Qualifier("jNhisTakeKeyDataSource")
	private DataSource jNhisTakeKeyDataSource;

	@Autowired
	@Qualifier("jNecaDataSource")
	private DataSource jNecaDataSource;
	
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	public void sendLinkNum(String IRB, String requestORG, String linkedORG) {

		Connection con = null;
		PreparedStatement pstmt = null;

		String re_IRB = IRB.replace("_", "-");
		// 요청기관에 전송---------------------------------------------------------------------

		System.out.println(requestORG);
		if (requestORG.equals("권역심뇌혈관센터")) {

			// 권역심뇌혈관센터 테이블 생성
			try {

				con = jCdcTakeKeyDataSource.getConnection();
				String sql = "CREATE TABLE J_cdcTakeKey." + IRB
						+ "(linkID int(20), secretID VARCHAR(100), PRIMARY KEY(linkID))";

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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

			// 권역심뇌혈관센터에 연결번호 전달

			try {

				con = jCdcTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_cdcTakeKey." + IRB + " SELECT * FROM J_necaTakeID." + IRB;

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
			
			
			
			
			//권역심뇌혈관센터에 연결번호 보내줌 표시
			
			
			try {
				
				
				con = dataSource.getConnection();
				String sql = "UPDATE J_cdc.Info set takeLinkNum = 1 WHERE IRB = '"+re_IRB+"'";
				
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
			

		} else if (requestORG.equals("건강보험공단")) {

			// 건강보험공단 테이블 생성
			try {

				con = jNhisTakeKeyDataSource.getConnection();

				String sql = "CREATE TABLE J_nhisTakeKey." + IRB
						+ "(linkID int(20), secretID VARCHAR(100), PRIMARY KEY(linkID))";

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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

			// 건강보험공단에 연결번호 전달

			try {

				con = jNhisTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_nhisTakeKey." + IRB + " SELECT * FROM J_necaTakeID." + IRB;

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
			
			
			//건보공단에 연결번호 보내줌 표시
			
			try {
				con = dataSource.getConnection();
				String sql = "UPDATE J_nhis.Info set takeLinkNum = 1 WHERE IRB = '"+re_IRB+"'";
				
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

		}

		// 요청기관 추가시 else if를 이용하여 여기에 추가하면됨------------------------------

		// ----------------------------------------------------------------------------------

		// 연계기관에 연결번호 전송------------------------------------------------

		if (linkedORG.equals("건강보험공단")) {

			// 건강보험공단에 테이블 생성
			try {

				con = jNhisTakeKeyDataSource.getConnection();

				String sql = "CREATE TABLE J_nhisTakeKey." + IRB
						+ "(linkID int(20), secretID VARCHAR(100), PRIMARY KEY(linkID))";

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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

			// 건강보험공단에 연결번호 전달

			try {

				con = jNhisTakeKeyDataSource.getConnection();
				String sql = "INSERT INTO J_nhisTakeKey." + IRB + " SELECT * FROM J_necaTakeID." + IRB;

				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
			
			
			//건보공단에 연결번호 보내줌 표시
			
			try {
				con = dataSource.getConnection();
				String sql = "UPDATE J_nhis.Info set takeLinkNum = 1 WHERE IRB = '"+re_IRB+"'";
				
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
			
		}

		// neca에 연결번호 보냄 표시

		try {
			con = jNecaDataSource.getConnection();
			String sql = "UPDATE J_neca.Info SET sendLinknum = 1 WHERE IRB = '" + re_IRB + "'";
			pstmt = con.prepareStatement(sql);

			pstmt.executeUpdate();
			System.out.println("연결번호 전달 표시");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	}

}
