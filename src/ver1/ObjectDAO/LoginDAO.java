package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class LoginDAO {

	public static void main(String[] args) {

	}

//	// 회원 추가
//	public void addUser(UserDTO dto) throws SQLException {
//
//		String query = "INSERT INTO user(name, acc_id, acc_pw) values(?, ?, ?)";
//		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, dto.getName());
//			pstmt.setString(2, dto.getAcc_id());
//			pstmt.setString(3, dto.getAcc_pw());
//			pstmt.executeUpdate();
//		}
//	} // end of addUser

//	// 회원 정보 수정하기    일단 이름으로 찾기
//	public void updateUser(String name, UserDTO dto) throws SQLException {
//		String query = " UPDATE user SET name = ?, acc_id = ?, acc_pw = ? WHERE name = ? ";
//		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, dto.getName());
//			pstmt.setString(2, dto.getAcc_id());
//			pstmt.setString(3, dto.getAcc_pw());
//			pstmt.setString(4, name);
//			pstmt.executeUpdate();
//		}
//	}

//	// 회원 삭제
//	public void deleteUser(int id) throws SQLException {
//		String query = " DELETE FROM user WHERE id = ?";
//		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
//			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, id);
//			pstmt.executeUpdate();
//		}
//	}

	// 회원 전체 조회 -> 중복 확인 로그인
	public List<UserDTO> checkUser() throws SQLException {
		List<UserDTO> list = new ArrayList<>();
		String query = " SELECT * FROM user ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				UserDTO dto = new UserDTO().builder().name(rs.getString("name")).acc_id(rs.getString("acc_id"))
						.acc_pw(rs.getString("acc_pw")).build();
				list.add(dto);
			}
		}
		return null;
	}

	// 회원 체크 
	private static boolean loginCheckUser(Connection conn, String acc_id, String acc_pw) throws SQLException {
		String query = "SELECT * FROM user WHERE acc_id = ? AND acc_pw = ? ";
		boolean result = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, acc_id);
			pstmt.setString(2, acc_pw);
			ResultSet rs = pstmt.executeQuery();

			result = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result) {
			return true;
		}

		return false;
	}

} // end of class
