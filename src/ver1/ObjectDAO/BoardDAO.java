package ver1.ObjectDAO;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
=======
public class BoardDAO {
>>>>>>> e6b776f635e02e46e1846d5ca5d609be4bef3b5e

}
