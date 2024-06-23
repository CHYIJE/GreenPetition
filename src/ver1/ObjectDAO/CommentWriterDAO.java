package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ver1.DBConnectionManager;
import ver1.models.CommentWriterDTO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentWriterDAO {

	private CommentWriterDTO dto;
	private CheckerDAO dao;
	private int userId;
	private int patitionId;
	private String content;

	public CommentWriterDAO(int userId, int patitionId, String content) {
		dto = CommentWriterDTO.builder().build();
		this.userId = userId;
		this.patitionId = patitionId;
		this.content = content;
		try {
			writer(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writer(CommentWriterDTO dto) throws SQLException {
		String insertQuery = "INSERT INTO comment(user_id, petition_id, comment)" + " VALUES (?,?,?)";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(insertQuery);
			psmt.setInt(1, userId);
			psmt.setInt(2, patitionId);
			psmt.setString(3, content);

			int rowCount = psmt.executeUpdate();
			if (rowCount > 0) {
				conn.commit();
				JOptionPane.showMessageDialog(null, "작성되었습니다.");
			} else {
				conn.rollback();
			}
		}
	}
}