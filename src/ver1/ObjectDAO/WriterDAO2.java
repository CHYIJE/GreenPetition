package ver1.ObjectDAO;

// 수정용 글쓰기

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.CheckerFrame;
import Frame.MainFrame;
import Frame.WriterFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;
import ver1.models.WriterDTO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class WriterDAO2 {
	WriterFrame mContext;
	WriterDTO dto;

	LoginDAO dao;

	UserDTO userdto;

	private int test = 0;

	MainFrame mainFrame;

	String title;
	String content;

	CheckerFrame checkerFrame;

	private LoginDAO loginDAO;

	public WriterDAO2(WriterDTO dto, WriterFrame mContext, LoginDAO loginDAO, CheckerFrame checkerFrame) {
		this.dto = dto;
		this.mContext = mContext;
		this.loginDAO = loginDAO;
		this.checkerFrame = checkerFrame;
		try {
			writer(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void writer(WriterDTO dto) throws SQLException {
		String insertQuery = " update petition set title = ?, content = ?, category = ? where id = ?";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);

			PreparedStatement psmt = conn.prepareStatement(insertQuery);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getCategory());
			psmt.setInt(4, checkerFrame.getPetitionId());

			int rowCount = psmt.executeUpdate();

			if (rowCount > 0) {
				conn.commit();
				JOptionPane.showMessageDialog(null, "수정되었습니다.");
			} else {
				conn.rollback();
			}
		}
	}
}