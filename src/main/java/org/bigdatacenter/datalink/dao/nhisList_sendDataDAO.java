package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.NhisList_sendDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class nhisList_sendDataDAO {

	@Autowired
	@Qualifier("jNHISDataSource")
	private DataSource jNHISDataSource;

	public ArrayList<NhisList_sendDataDTO> nhisList() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<NhisList_sendDataDTO> nhisListDTOs = new ArrayList<>();

		try {
			con = jNHISDataSource.getConnection();
			String sql = "SELECT * FROM J_nhis.Info";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String IRB = rs.getString(1);
				String rName = rs.getString(2);
				String requestORG = rs.getString(3);
				String linkedORG = rs.getString(4);
				int sendData = rs.getInt(5);
				int takeLinkNum = rs.getInt(6);

				NhisList_sendDataDTO nhisListDTO = new NhisList_sendDataDTO(IRB, rName, requestORG, linkedORG, sendData, takeLinkNum);
				nhisListDTOs.add(nhisListDTO);
			}

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
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return nhisListDTOs;

	}// end
}
