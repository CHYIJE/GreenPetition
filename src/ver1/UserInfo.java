package ver1;

import java.util.HashMap;
import java.util.Map;

import Frame.LoginFrame;
import ver1.ObjectDAO.LoginDAO;

public class UserInfo {
	LoginFrame frame;
	LoginDAO dao;
	
	private static UserInfo userInfo = new UserInfo();
	private Map<Integer, String> users = new HashMap<>();
	
	private UserInfo() {
	}
	
	public static UserInfo getInstance() {
		if(userInfo == null) {
			userInfo = new UserInfo();
		}
		
		return userInfo;
	}
	
	public void loadUser() {
		
		if(users.isEmpty()) {
			System.out.println("empty");
			System.out.println(users.size());
			users.put(1, "temp");
			users.put(dao.getUserId(), dao.getUserAccId());
			System.out.println(users.size());
			System.out.println(users.get(1));
		}
	}
	public Map<Integer, String> inputUser() {
		
		if(users.isEmpty()) {
			System.out.println("inputUser");
			users.put(dao.getUserId(), dao.getUserAccId());
			System.out.println(users.size());
			return users;
		}
		return users;
	}
	
	
	
}