package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class DuplicationDAO {
	SigninFrame mContext;
	UserDTO dto;

	public DuplicationDAO(UserDTO dto, SigninFrame mContext) {
		try {
			dupliUser(dto, mContext);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void dupliUser(UserDTO dto, SigninFrame mContext) throws SQLException {
		this.mContext = mContext;

		String checkIdQuery = "SELECT acc_id FROM user where acc_id = ? ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);

			PreparedStatement checkptmt = conn.prepareStatement(checkIdQuery);
			checkptmt.setString(1, mContext.getIdField().getText());

			ResultSet rs = checkptmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "중복된 ID 입니다.");
				conn.rollback();
				mContext.getSignIn().setEnabled(false);
			} else {
				JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.");
				mContext.getSignIn().setEnabled(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
