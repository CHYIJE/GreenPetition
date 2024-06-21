package ver1.models;

import java.awt.Button;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.LoginFrame;
import Frame.MainFrame;
import Frame.WriterFrame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ver1.DBConnectionManager;
import ver1.ObjectDAO.LoginDAO;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Vote {

	private static final String INSERTQUERY = "INSERT INTO vote(petition_id, user_id) values(?, ?)";
	private static final String CHECKQUERY = "SELECT * FROM vote where petition_id = ? and user_id = ?";

	UserDTO userDto;
	PatitionDTO paDto;
	LoginDAO loginDao;
	WriterFrame mContext;
	MainFrame main;

	// 중복체크
	public boolean checkUser(UserDTO dto, WriterFrame mContext) throws SQLException {

		this.mContext = mContext;

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ptmt = conn.prepareStatement(CHECKQUERY);
//			ptmt.setInt(1, main.getPetitionId());
//			ptmt.setInt(2, loginDao.getUserName());
			ptmt.setInt(1, 1);
			ptmt.setInt(2, 1);

			ResultSet rs = ptmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "이미 투표 하셨습니다.");
				// 투표 막기
				return false;

			} else {
				JOptionPane.showMessageDialog(null, "투표 완료 되었습니다.");
				// 투표 허용
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public void upvote() {
		try {
			boolean temp = checkUser(userDto, mContext);
			if (temp) {
				up();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void up() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ptmt = conn.prepareStatement(INSERTQUERY);
//			ptmt.setInt(1, main.getPetitionId());
//			ptmt.setInt(2, loginDao.getUserName());
			ptmt.setInt(1, 1);
			ptmt.setInt(2, 1);
			
			int rs = ptmt.executeUpdate();
			System.out.println(rs);
		} catch (Exception e) {

		}
	}

	public void down() {

	}

} // end of class
