package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Frame.CheckerFrame;
import ver1.DBConnectionManager;
import ver1.models.CheckerDTO;
import ver1.models.PatitionDTO;

public class DeleteDAO {
	CheckerFrame mContext;
	CheckerDTO dto;
	
	public DeleteDAO(CheckerDTO dto,CheckerFrame mContext) {
		this.dto = dto;
		this.mContext = mContext;
		
		try {
			delete(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void delete(CheckerDTO dto) throws SQLException{
		
		String insertQuery = " delete from petition where id = ? ";
		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			conn.setAutoCommit(false);

			PreparedStatement psmt = conn.prepareStatement(insertQuery);
			psmt.setInt(1, dto.getPetition_id()); 
			int rowCount = psmt.executeUpdate();

			if (rowCount > 0) {
				conn.commit();
				JOptionPane.showMessageDialog(null, "삭제되었습니다.");
				mContext.dispose();
			} else {
				conn.rollback();
			}
		}
	}
}
