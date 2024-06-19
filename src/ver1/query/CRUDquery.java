package ver1.query;

import lombok.Getter;

@Getter
public class CRUDquery {
	// INSERT
	public static final String INSERT_USER = " INSERT INTO user(acc_id, acc_pw, name) values ( ?, ?, ?) "; // 회원가입
	public static final String INSERT_ARTICLE = " INSERT INTO petition(title, user_id, category_id, content, date, agree, disagree) values ( ?, ?, now(), ?)";
	
	// SELECT
	public static final String VIEW_ALL = " SELECT * FROM petition ";
	public static final String VIEW_KINDS = " SELECT * FROM petition where category = ? ";
	
	
}
