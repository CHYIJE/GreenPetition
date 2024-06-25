package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ver1.DBConnectionManager;
import ver1.models.Category;
import ver1.models.PatitionDTO;

public class SearchDAO {
	// 제목 기준 검색
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	private static final String SEARCH_BY_TITLE = "select p.id, p.title, u.acc_id, p.category, p.date, p.agree, p.disagree from petition as p left join user as u on u.id = p.user_id WHERE p.title LIKE ? order by id desc";
//			+ "SELECT petition.id, petition.title, user.acc_id FROM petition JOIN user ON petition.user_id = user.id WHERE petition.title LIKE ?";

	public List<PatitionDTO> titleSearch(String title) {
		List<PatitionDTO> results = new ArrayList<>();
		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_TITLE)) {

			pstmt.setString(1, "%" + title + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				PatitionDTO dto = new PatitionDTO();
				dto.setId(rs.getInt("id"));
				dto.setAcc_id(rs.getString("acc_id"));
				dto.setCategory(Category.fromValue(rs.getString("Category")));
				dto.setTitle(rs.getString("title"));
				dto.setAgree(rs.getInt("agree"));
				dto.setDisagree(rs.getInt("disagree"));
				dto.setDate(rs.getTimestamp("date"));
				results.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
