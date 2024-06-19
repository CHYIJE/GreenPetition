package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class JoinDAO {
	SigninFrame mContext;
	
	public void setContext(SigninFrame context) {
        this.mContext = context;
    }
	
	// 회원가입

	
	public void joinUser(UserDTO dto) throws SQLException{
		String sql = "INSERT INTO user(name, acc_id, acc_pw) VALUES (?, ?, ?)";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			PreparedStatement ptmt = conn.prepareStatement(null);
			ptmt.setString(1, mContext.getIdField().getText());
			ptmt.setString(2, mContext.getPwField().getText());
			ptmt.setString(3, mContext.getNameField().getText());
			
			int numRowsAffected = ptmt.executeUpdate();

			
		}
	}
}
