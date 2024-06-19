package ver1.query;

public class CRUDquery {
	// INSERT
	private static final String INSERT_USER = " INSERT INTO user(name, acc_id, acc_pw) values ( ?, ?, ? ) "; // 회원가입
	private static final String INSERT_ARTICLE = " INSERT INTO petition(title, user_id, category_id, content, date, agree, disagree) values ( ?, ?, now(), ?, ? )";
	
	// SELECT
	private static final String VIEW_ALL = " SELECT * FROM petition ";
	private static final String VIEW_KINDS = " SELECT * FROM petition where category = ? ";
	
	
}
