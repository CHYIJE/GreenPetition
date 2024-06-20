package ver1;

import java.util.HashMap;
import java.util.Map;

import Frame.LoginFrame;
import ver1.ObjectDAO.LoginDAO;

public class UserInfo {
	static LoginFrame frame;
	static LoginDAO dao;
	
	private static UserInfo userInfo = new UserInfo();
	private static Map<Integer, String> users = new HashMap<>();
	
	private UserInfo() {
	}
	
	public static UserInfo getInstance() {
		if(userInfo == null) {
			userInfo = new UserInfo();
		}
		
		return userInfo;
	}
	
	public static void loadUser() {
		
		if(users.isEmpty()) {
			System.out.println("empty");
			System.out.println(users.size());
			users.put(1, "temp");
			users.put(2, frame.getTextId().getText());
			users.put(3, dao.getUserAccId());
			System.out.println(users.size());
			System.out.println(users.get(1));
		}
	}
	
	
	
}
