package ver1;

import java.util.HashMap;
import java.util.Map;

import ver1.ObjectDAO.LoginDAO;

public class UserInfo {
	
	LoginDAO dao;
	
	private static final UserInfo userInfo = new UserInfo();
	private Map<Integer, String> users = new HashMap<>();
	
	private UserInfo() {
		throw new AssertionError();
	}
	
	public static UserInfo getInstance() {
		return userInfo;
	}
	
	public void loadUser() {
		
		if(users.isEmpty()) {
			this.users.put(dao.getUserId(), dao.getUserAccId());
		}
	}
	
	
	
}
