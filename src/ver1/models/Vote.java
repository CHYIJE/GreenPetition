package ver1.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
	private static final String UPVOTE = "UPDATE petition SET agree = ? where id = ?";
	private static final String DOWNVOTE = "UPDATE petition SET disagree = ? where id = ?";

	UserDTO userDto;
	PatitionDTO paDto;
	LoginDAO loginDao;
	WriterFrame mContext;
	MainFrame main;
	CheckerDTO dto;
	private int petitionId;
	private int userId;
	private int agree;
	private int disagree;

	// 중복체크
	private boolean checkUser(CheckerDTO dto, WriterFrame mContext) throws SQLException {

		this.mContext = mContext;

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement ptmt = conn.prepareStatement(CHECKQUERY);
			ptmt.setInt(1, petitionId);
			ptmt.setInt(2, userId);

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

	public void upvote(int petitionId, int userId, int agree) {
		this.petitionId = petitionId;
		this.userId = userId;
		this.agree = agree;
		try {
			boolean temp = checkUser(dto, mContext);
			if (temp) {
				up();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void downvote(int petitionId, int userId, int disagree) {
		this.petitionId = petitionId;
		this.userId = userId;
		this.disagree = disagree;
		try {
			boolean temp = checkUser(dto, mContext);
			if (temp) {
				down();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void up() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ptmt = conn.prepareStatement(INSERTQUERY);
			ptmt.setInt(1, petitionId);
			ptmt.setInt(2, userId);
			ptmt.executeUpdate();

			PreparedStatement pstmt = conn.prepareStatement(UPVOTE);
			pstmt.setInt(1, agree + 1);
			pstmt.setInt(2, petitionId);
			pstmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void down() {
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ptmt = conn.prepareStatement(INSERTQUERY);
			ptmt.setInt(1, petitionId);
			ptmt.setInt(2, userId);
			ptmt.executeUpdate();

			PreparedStatement pstmt = conn.prepareStatement(DOWNVOTE);
			pstmt.setInt(1, disagree + 1);
			pstmt.setInt(2, petitionId);
			pstmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

} // end of class
