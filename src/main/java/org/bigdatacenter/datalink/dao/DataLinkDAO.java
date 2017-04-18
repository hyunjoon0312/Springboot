package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataLinkDAO {

	@Autowired
	@Qualifier("jCdcDataSource")
	private DataSource jCdcDataSource;

	public void dataLink(String IRB) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		String db_IRB = IRB.replace("-", "_");

		// 연계한 데이터 담을 테이블 생성

		System.out.println("DatalinkDAO.java");
		try {

			con = jCdcDataSource.getConnection();
			String sql = "create table J_linkedData." + db_IRB
					+ "(No int(100) auto_increment, REPORT_YMD VARCHAR(20), ADDRESS INT(20), GENDER INT(20), DEATH_YMD VARCHAR(20), DEATH_TIME INT(20), DEATH_PLACE INT(20), DEATH_JOB VARCHAR(20), MARRY INT(20), EDU INT(20), DEATH_CAU1 VARCHAR(20), DEATH_CAU1_Parent VARCHAR(20), DEATH_AGE INT(20), STND_Y INT(20),YKIHO_ID INT(20),YKIHO_GUBUN_CD INT(20),ORG_TYPE INT(20),YKIHO_SIDO INT(20),SICKBED_CNT INT(20),DR_CNT INT(20),CT_YN INT(20),MRI_YN INT(20),PET_YN INT(20), PRIMARY KEY(No))";

			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

			System.out.println("연계데이터 담을 테이블 생성");
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

		
		// 연계 데이터 저장

		try {

			con = jCdcDataSource.getConnection();
			String sql = "INSERT INTO J_linkedData."+ db_IRB + 
					"(REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE, STND_Y, YKIHO_ID, YKIHO_GUBUN_CD, ORG_TYPE, YKIHO_SIDO, SICKBED_CNT, DR_CNT, CT_YN, MRI_YN, PET_YN) "
					+ "SELECT J_linkerTakeCDC."+db_IRB+".REPORT_YMD, J_linkerTakeCDC."+db_IRB+".ADDRESS, J_linkerTakeCDC."+db_IRB+".GENDER, J_linkerTakeCDC."+db_IRB+".DEATH_YMD, J_linkerTakeCDC."+db_IRB+".DEATH_TIME, J_linkerTakeCDC."+db_IRB+".DEATH_PLACE, J_linkerTakeCDC."+db_IRB+".DEATH_JOB, J_linkerTakeCDC."+db_IRB+".MARRY, J_linkerTakeCDC."+db_IRB+".EDU, J_linkerTakeCDC."+db_IRB+".DEATH_CAU1, J_linkerTakeCDC."+db_IRB+".DEATH_CAU1_Parent, J_linkerTakeCDC."+db_IRB+".DEATH_AGE, J_linkerTakeNHIS."+db_IRB+".STND_Y, J_linkerTakeNHIS."+db_IRB+".YKIHO_ID, J_linkerTakeNHIS."+db_IRB+".YKIHO_GUBUN_CD, J_linkerTakeNHIS."+db_IRB+".ORG_TYPE, J_linkerTakeNHIS."+db_IRB+".YKIHO_SIDO, J_linkerTakeNHIS."+db_IRB+".SICKBED_CNT, J_linkerTakeNHIS."+db_IRB+".DR_CNT, J_linkerTakeNHIS."+db_IRB+".CT_YN, J_linkerTakeNHIS."+db_IRB+".MRI_YN, J_linkerTakeNHIS."+db_IRB+".PET_YN "
							+ "FROM J_linkerTakeCDC."+ db_IRB
					+ " INNER JOIN J_linkerTakeNHIS."+db_IRB+" "
							+ "ON J_linkerTakeCDC."+db_IRB+".linkID = J_linkerTakeNHIS."+db_IRB+".linkID";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

			System.out.println("연계데이터 저장");
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
		
		//연계데이터 저장함을 표시해줌.

	}// end
}
