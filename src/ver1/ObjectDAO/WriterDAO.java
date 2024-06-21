package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

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
	UserDTO userdto;
	private int test = 0;
	MainFrame mainFrame;
	String title;
	String content;

<<<<<<< HEAD
=======
	public WriterDAO(WriterDTO dto, WriterFrame mContext, LoginDAO dao) {
		try {
			writer(dto, mContext, dao);
			this.mContext = mContext;
			this.dao = dao;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void writer(WriterDTO dto, WriterFrame mContext, LoginDAO dao)  throws SQLException {
		this.mContext = mContext;
		this.dao = dao;

		String insertQuery = "INSERT INTO petition(user_id, category, title, content, date)"
				+ " VALUES (?, ?, ?, ?, current_date())";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {

			conn.setAutoCommit(false);
			PreparedStatement psmt = conn.prepareStatement(insertQuery);
			psmt.setInt(1, dao.getUserId());
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
>>>>>>> c1c3b51f584b1209562fb97ca773d6a36b27c9fe
	
    
    private LoginDAO loginDAO;

    public WriterDAO(WriterDTO dto, WriterFrame mContext, LoginDAO loginDAO) {
        this.dto = dto;
        this.mContext = mContext;
        this.loginDAO = loginDAO;
        try {
            writer(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writer(WriterDTO dto) throws SQLException {
        String insertQuery = "INSERT INTO petition(user_id, category, title, content, date)"
                + " VALUES (?, ?, ?, ?, current_date())";

        try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement psmt = conn.prepareStatement(insertQuery);
            psmt.setInt(1, loginDAO.getUserId()); // 사용자 ID를 LoginDAO 가져옴.
            psmt.setString(2, dto.getCategory());
            psmt.setString(3, dto.getTitle());
            psmt.setString(4, dto.getContent());

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