package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Frame.LoginFrame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ver1.DBConnectionManager;
import ver1.models.UserDTO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginDAO {
	LoginFrame mContext;
	UserDTO dto;
	
	public LoginDAO(UserDTO dto, LoginFrame mContext){
		
		try {
			loginCheckUser(dto, mContext);
			this.mContext = mContext;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	// 회원 체크 
	public void loginCheckUser(UserDTO dto, LoginFrame mcontext) throws SQLException {
		
		this.mContext = mcontext;
		
		String idQuery = " SELECT acc_id FROM user WHERE acc_id = ? ";
		String pwQuery = " SELECT acc_pw FROM user WHERE acc_pw = ? ";
		
		try (Connection conn = DBConnectionManager.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			PreparedStatement ptmt = conn.prepareStatement(idQuery);
			ptmt.setString(1, mcontext.get);


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

} // end of class
