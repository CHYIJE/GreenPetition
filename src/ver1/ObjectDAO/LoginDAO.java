package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Frame.LoginFrame;
import Frame.MainFrame;
import ver1.DBConnectionManager;
import ver1.UserInfo;
import ver1.models.UserDTO;

public class LoginDAO {
	LoginFrame mContext;
	UserDTO dto;
	LoginDAO dao = this;
	MainFrame mainFrame;
	UserInfo userinfo;
	private int userId = 0;
	private String useraccId = null;



	public LoginDAO(UserDTO dto, LoginFrame mContext) {
		try {
			loginUser(dto, mContext);
			this.mContext = mContext;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// LoginFrame mContext == JtextField 끌고 오기
	public void loginUser(UserDTO dto, LoginFrame mContext) throws SQLException {
		this.mContext = mContext;
		String idQuery = " SELECT * FROM user where acc_id = ? ";
		String passwordQuery = " SELECT * FROM user where acc_id = ? AND acc_pw = ? ";
		String loginQuery = " SELECT name FROM user where acc_id = ? and acc_pw = ? ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement idptmt = conn.prepareStatement(idQuery);
			idptmt.setString(1, mContext.getTextId().getText());

			ResultSet rs1 = idptmt.executeQuery();

			if (rs1.next() == false) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 ID입니다.");
			} else {

				PreparedStatement pwptmt = conn.prepareStatement(passwordQuery);

				pwptmt.setString(1, mContext.getTextId().getText());
				pwptmt.setString(2, mContext.getTextPw().getText());

				ResultSet rs2 = pwptmt.executeQuery();

				if (rs2.next()) { 
					JOptionPane.showMessageDialog(null, "로그인 성공");
					userId = rs2.getInt("id"); 
					useraccId = rs2.getString("acc_id"); 
					
					
	
					mainFrame = new MainFrame(this);

				} else {
					JOptionPane.showMessageDialog(null, "ID 또는 Password 가 일치하지 않습니다.");
					conn.rollback();

				}
			}

		}

	}

	public int getUserId() {
		return userId;
	}
	public void setUserID(int userId) {
		this.userId = userId;
	}

	public String getUserAccId() {
		return useraccId;
	}

	public void setUserName(String userName) {
		this.useraccId = userName;
	}

}
