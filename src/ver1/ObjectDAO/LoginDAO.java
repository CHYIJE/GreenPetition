package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.LoginFrame;
import Frame.MainFrame;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

@NoArgsConstructor
@AllArgsConstructor
public class LoginDAO {
	LoginFrame mContext;
	LoginDAO lgContext = this;
=======
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginDAO {
	LoginFrame mContext;
>>>>>>> 9fa56637e6aa63f5f9ce58c4b4f798cb136937c1
	UserDTO dto;
	MainFrame mainFrame;
	private int userId;
	private String userName;

	public LoginDAO(UserDTO dto, LoginFrame mContext) {
		try {
			loginUser(dto, mContext);
			this.mContext = mContext;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loginUser(UserDTO dto, LoginFrame mContext) throws SQLException {
		this.mContext = mContext;
		this.lgContext = lgContext;
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
<<<<<<< HEAD
					lgContext.userName = rs2.getString("name");
					
					mainFrame = new MainFrame();
=======
					userId = rs2.getString("acc_id");
					mainFrame = new MainFrame(this);
>>>>>>> 9fa56637e6aa63f5f9ce58c4b4f798cb136937c1

				} else {
					JOptionPane.showMessageDialog(null, "ID 또는 Password 가 일치하지 않습니다.");
					conn.rollback();

				}
			}

		}

	}
	public int getuserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
