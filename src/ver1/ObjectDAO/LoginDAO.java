package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.LoginFrame;
import Frame.MainFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

<<<<<<< HEAD
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginDAO {
	LoginFrame mContext;
	UserDTO dto;
	
	public LoginDAO(UserDTO dto, LoginFrame mContext){
		
		try {
			loginCheckUser(dto, mContext);
			this.mContext = mContext;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	// 회원 체크 
	public void loginCheckUser(UserDTO dto, LoginFrame mcontext) throws SQLException {
		
		this.mContext = mcontext;
		
		String idQuery = " SELECT acc_id FROM user WHERE acc_id = ? ";
		String pwQuery = " SELECT acc_pw FROM user WHERE acc_pw = ? ";
		
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ptmt = conn.prepareStatement(idQuery);
			ptmt.setString(1, mcontext.get);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
=======
public class LoginDAO {
	LoginFrame mContext;
	UserDTO dto;
	MainFrame mainFrame;
	String userId;
	String userPw;

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
					userId = rs2.getString("id");
					mainFrame = new MainFrame();

				} else {
					JOptionPane.showMessageDialog(null, "ID 또는 Password 가 일치하지 않습니다.");
					conn.rollback();
					
				} 
			}

		} 

>>>>>>> 006fd8658d269d5b86947ddbcec80efe3b2d05ce
	}

} // end of class
