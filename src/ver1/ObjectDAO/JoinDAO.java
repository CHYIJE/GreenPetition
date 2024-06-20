package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class JoinDAO {
	SigninFrame mContext;

	public void setContext(SigninFrame context) {
		this.mContext = context;
	}

	UserDTO dto;

	// 회원가입

	String resultRow = null;

	public JoinDAO(UserDTO dto, SigninFrame mContext) {

		try {
			joinUser(dto, mContext);
			this.mContext = mContext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
<<<<<<< HEAD

=======
	
	// 회원가입
>>>>>>> 2406dc6d81dbfc598db08477ac661e204de9cce8
	public void joinUser(UserDTO dto, SigninFrame mContext) throws SQLException {

		this.mContext = mContext;

		String insertQuery = "INSERT INTO user(name, acc_id, acc_pw) values (? ,?, ?) ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement insertptmt = conn.prepareStatement(insertQuery);
			insertptmt.setString(1, mContext.getIdField().getText());
			insertptmt.setString(2, mContext.getPwField().getText());
			insertptmt.setString(3, mContext.getNameField().getText());

			int rowCount = insertptmt.executeUpdate();

<<<<<<< HEAD
					System.out.println(rs.getString("acc_id"));
					int testRow = insertptmt.executeUpdate();

					conn.commit();
					System.out.println(testRow);
				} else {
					conn.rollback();
				}

				System.out.println("break 처리 완료");
				break;
			}

		} catch (SQLException e) {
=======
			if(rowCount > 0) {
				conn.commit();
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			} else {
				conn.rollback();
			}
>>>>>>> 2406dc6d81dbfc598db08477ac661e204de9cce8
		}
	}
}
