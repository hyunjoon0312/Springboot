package org.bigdatacenter.datalink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bigdatacenter.datalink.dto.NecaListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class necaListDAO {

	@Autowired
	@Qualifier("jNecaDataSource")
	private DataSource jNecaDataSource;

	public ArrayList<NecaListDTO> necaList() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<NecaListDTO> necaListDTOs = new ArrayList<>();

		try {
			con = jNecaDataSource.getConnection();
			String sql = "SELECT * FROM J_neca.Info";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String IRB = rs.getString(1);
				String rName = rs.getString(2);
				String requestORG = rs.getString(3);
				String linkedORG = rs.getString(4);
				int sendLinknum = rs.getInt(5);
				int takePersonID = rs.getInt(6);

				NecaListDTO necaListDTO = new NecaListDTO(IRB, rName, requestORG, linkedORG, sendLinknum, takePersonID);
				necaListDTOs.add(necaListDTO);
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
		return necaListDTOs;

	}// end

}
