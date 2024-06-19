package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public void joinUser(UserDTO dto, SigninFrame mContext) {
=======
	public void joinUser(UserDTO dto, SigninFrame mContext) throws SQLException {
>>>>>>> 3e2dde5a7a1f1aa14fbc3591f757a6f13a019978
		this.mContext = mContext;

		String insertQuery = "INSERT INTO user(name, acc_id, acc_pw) values (? ,?, ?) ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			List<UserDTO> idlist = new ArrayList<>();
			String checkIdQuery = "SELECT acc_id FROM user ";
			PreparedStatement selectptmt = conn.prepareStatement(checkIdQuery);
			ResultSet rs = selectptmt.executeQuery();

			while (rs.next()) {
				UserDTO dto1 = new UserDTO().builder().acc_id(rs.getString("acc_id")).build();
				idlist.add(dto1);
				System.out.println("while 들어오긴 함");
				if (idlist.equals(mContext.getIdField().getText())) {
					PreparedStatement insertptmt = conn.prepareStatement(insertQuery);
					insertptmt.setString(1, mContext.getIdField().getText());
					insertptmt.setString(2, mContext.getPwField().getText());
					insertptmt.setString(3, mContext.getNameField().getText());

					System.out.println(rs.getString("acc_id"));
					int testRow = insertptmt.executeUpdate();

					conn.commit();
					System.out.println(testRow);
				} else {
					conn.rollback();
				}
<<<<<<< HEAD
				System.out.println("break 처리 완료");
				break;
			}

		} catch (SQLException e) {
=======
>>>>>>> 3e2dde5a7a1f1aa14fbc3591f757a6f13a019978

		}
	}
}
