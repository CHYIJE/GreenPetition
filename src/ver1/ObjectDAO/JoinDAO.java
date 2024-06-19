package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Frame.SigninFrame;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

public class JoinDAO {
	SigninFrame mContext;
	UserDTO dto;
	// 회원가입
	
	String resultRow = null;
	
	public JoinDAO(UserDTO dto, SigninFrame mContext) {
		
		try {
			joinUser(dto, mContext);
			this.mContext = mContext;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void joinUser(UserDTO dto, SigninFrame mContext) throws SQLException{
		this.mContext = mContext;
		
		String checkIdQuery = "SELECT acc_id FROM user ";
		String insertQuery = "INSERT INTO user(name, acc_id, acc_pw) values (? ,?, ?) ";
		
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			PreparedStatement selectptmt = conn.prepareStatement(checkIdQuery);
			ResultSet rs = selectptmt.executeQuery();
			System.out.println("while 들어가기 전");
				while(rs.next()) {
					System.out.println("while 들어오긴 함");
					if(mContext.getIdField().getText().equals(rs.getString("acc_id"))) {
						System.out.println("rollback");
						conn.rollback();
					} else {
						PreparedStatement insertptmt = conn.prepareStatement(insertQuery);
						insertptmt.setString(1, mContext.getIdField().getText());
						insertptmt.setString(2, mContext.getPwField().getText());
						insertptmt.setString(3, mContext.getNameField().getText());
						
						insertptmt.addBatch();
						
						conn.commit();
						int testRow = insertptmt.executeUpdate();
						System.out.println(testRow);
						
					}
					System.out.println("break 처리 완료");
					break;
				}
			
		}
	}
}
