package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class JoinDAO {
	SigninFrame mContext;

	public void setContext(SigninFrame context) {
		this.mContext = context;
	}

	UserDTO dto;

	public JoinDAO(UserDTO dto, SigninFrame mContext) {

		try {
			joinUser(dto, mContext);
			this.mContext = mContext;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void joinUser(UserDTO dto, SigninFrame mContext) throws SQLException {
		this.mContext = mContext;
		String checkIdQuery = "SELECT acc_id From user";
		String insertQuery = "INSERT INTO user(name, acc_id, acc_pw) values (? ,?, ?) ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement ptmt = conn.prepareStatement(insertQuery);
			ptmt.setString(1, mContext.getIdField().getText());
			ptmt.setString(2, mContext.getPwField().getText());
			ptmt.setString(3, mContext.getNameField().getText());

			int testRow = ptmt.executeUpdate();
			System.out.println(testRow);

		}
		
		
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement ptmt = conn.prepareStatement(checkIdQuery);
			
			ResultSet rs = ptmt.executeQuery();
			rs = 
					
			
		}
	}
}
