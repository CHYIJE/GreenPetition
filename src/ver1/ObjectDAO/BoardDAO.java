package ver1.ObjectDAO;

<<<<<<< HEAD
public class BoardDAO {
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ver1.DBConnectionManager;

public class BoardDAO {
	
	// 글 쓰기
	public void addStudent(StudentDTO dto) throws SQLException {

		String query = "INSERT INTO student(name, age, email) values(?, ?, ?)";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getEmail());
			pstmt.executeUpdate();
		}
	}

>>>>>>> 2fd70c60d3633e97aa83650b04fe28c55ce0ef87

}
