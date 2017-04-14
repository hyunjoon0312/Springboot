package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.LinkRequest;
import org.bigdatacenter.datalink.svc.createSecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class LinkRequestDAO {

	@Autowired
	@Qualifier("ssisRequestDataInfoDataSource")
	private DataSource ssisRequestDataInfoDataSource;

	public void addNewLinkRequest(String Rname, String IRB, String request_ORG, String nhis, String stat, String cdc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			System.out.println("addNewLinkRequest()");

			
			nhis = nhis == "" ? "0" : "1";
			stat = stat == "" ? "0" : "1";
			cdc = cdc == "" ? "0" : "1";

			con = ssisRequestDataInfoDataSource.getConnection();

			String sql = "INSERT INTO SSIS_requestDataInfo.requestDataInfo VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, IRB);
			pstmt.setString(2, Rname);
			pstmt.setString(3, request_ORG);
			pstmt.setString(4, nhis);
			pstmt.setString(5, stat);
			pstmt.setString(6, cdc);
			pstmt.setInt(7, 0);
			pstmt.setString(8, "");
			pstmt.setInt(9, 0);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	public ArrayList<LinkRequest> findAllLinkRequest() {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<LinkRequest> linkRequests = new ArrayList<LinkRequest>();

		try {
			con = ssisRequestDataInfoDataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from SSIS_requestDataInfo.requestDataInfo");

			while (rs.next()) {
		
				String requestTime = rs.getString(1);
				String IRB = rs.getString(2);
				String Rname = rs.getString(3);
				String request_ORG = rs.getString(4);
				String nhis = rs.getString(5);
				String stat = rs.getString(6);
				String cdc = rs.getString(7);
				int createKey = rs.getInt(8);
				String secretKey = rs.getString(9);
				int sendKey = rs.getInt(10);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date_time = format.parse(requestTime);
				requestTime = (String)format.format(date_time);
				
				
				LinkRequest linkRequest = new LinkRequest(requestTime, Rname, IRB, request_ORG, nhis, stat, cdc, createKey, secretKey, sendKey);
				linkRequests.add(linkRequest);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return linkRequests;
	}
	
	
	public String createKey(String IRB){
		
			System.out.println("LinkRequestDao.createkey()");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		//secretKey 생성
		String secretKey = createSecretKey.getRandomString(25);
		System.out.println("secretKey complete");
		
		
		//사회보장정보원 암호키 저장
		
		try {
			con = ssisRequestDataInfoDataSource.getConnection();
			String sql = "UPDATE SSIS_requestDataInfo.requestDataInfo SET secretKey = '" + secretKey + "' WHERE IRB = '" + IRB + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
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
		
		System.out.println("사회보장정보원 암호키 저장");
		
		
		
		//암호키 생성 표시
		
		try {
			con = ssisRequestDataInfoDataSource.getConnection();
			String sql = "UPDATE SSIS_requestDataInfo.requestDataInfo SET createKey = 1 WHERE IRB = '" + IRB + "'";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
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
			System.out.println("암호키 생성 표시");
			
			return secretKey;
		}
		
	}


