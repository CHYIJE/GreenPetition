package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ver1.DBConnectionManager;
import ver1.models.Category;
import ver1.models.PatitionDTO;

public class SearchDAO {
	//제목 기준 검색
	private static final String SEARCH_BY_TITLE = "SELECT id, user_id, category, title, content, date FROM petition WHERE title LIKE ?";
//			+ "SELECT petition.id, petition.title, user.acc_id FROM petition JOIN user ON petition.user_id = user.id WHERE petition.title LIKE ?";

    public List<PatitionDTO> titleSearch(String title) {
        List<PatitionDTO> results = new ArrayList<>();
        try (Connection conn = DBConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SEARCH_BY_TITLE)) {

            pstmt.setString(1, title + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PatitionDTO dto = new PatitionDTO();
                dto.setId(rs.getInt("id"));
                dto.setUser_id(rs.getInt("user_id"));
                dto.setCategory(Category.fromValue(rs.getString("category")));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setDate(rs.getDate("date"));
                results.add(dto);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
	

