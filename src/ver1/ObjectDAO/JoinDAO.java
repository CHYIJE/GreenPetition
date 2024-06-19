package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.query.CRUDquery;

public class JoinDAO {
	SigninFrame mContext;
	// 회원가입

	
	public void joinUser(JoinDTO dto) throws SQLException{
		
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			PreparedStatement ptmt = conn.prepareStatement(null);
			ptmt.setString(1, mContext.getIdField().getText());
			ptmt.setString(2, mContext.getPwField().getText());
			ptmt.setString(3, mContext.getNameField().getText());
			
			int testRow = ptmt.executeUpdate();
		
		}
	}
}
