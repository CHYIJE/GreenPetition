package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ver1.DBConnectionManager;

public class JoinDAO {
	// 회원가입
	public void joinUser(JoinDTO dto) throws SQLException{
		String query = "";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
