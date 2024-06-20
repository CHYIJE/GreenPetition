package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.LoginFrame;
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
public class WriterDAO {
	WriterFrame mContext;
	WriterDTO dto;
	LoginDAO dao;
	private int test = -1;
	MainFrame mainFrame;
	String title;
	String content;

	public WriterDAO(WriterDTO dto, WriterFrame mContext) {
		try {
			writer(dto, mContext);
			this.mContext = mContext;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void writer(WriterDTO dto, WriterFrame mContext) throws SQLException {
		this.mContext = mContext;

		String insertQuery = "INSERT INTO petition(user_id, category, title, content, date)"
				+ " VALUES (?, ?, ?, ?, current_date())";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			PreparedStatement psmt = conn.prepareStatement(insertQuery);
			test = dao.getUserId();
			psmt.setInt(1, 1);
			psmt.setString(2, mContext.getCategory());
			psmt.setString(3, mContext.getTitleField().getText());
			psmt.setString(4, mContext.getContentField().getText());

			int rowCount = psmt.executeUpdate();

			if (rowCount > 0) {
				conn.commit();
				JOptionPane.showMessageDialog(null, "test");
			} else {
				conn.rollback();
			}
		}
	}

}
