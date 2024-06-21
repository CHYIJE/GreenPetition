package ver1.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import ver1.ObjectDAO.LoginDAO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Vote {

	// 찬성반대
	// 중복투표불가 (찬성, 반대)(같은거 여러번)
	// 체크시 count +1

	UserDTO userDto;
	PatitionDTO paDto;
	LoginDAO loginDao;
	WriterFrame mContext;
	public int agreeCount;
	public int disagreeCount;

	// 중복체크
	public void checkUser(UserDTO dto, WriterFrame mContext) throws SQLException {

		this.mContext = mContext;
		String checkQuery = " SELECT * FROM user where acc_id = ? ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			PreparedStatement ptmt = conn.prepareStatement(checkQuery);
			ptmt.setString(1, loginDao.getUserName());

			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "이미 투표 하셨습니다.");
				conn.rollback();
				// 투표 막기
			} else {
				JOptionPane.showMessageDialog(null, "투표 완료 되었습니다.");
				// 투표 허용
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

} // end of class
