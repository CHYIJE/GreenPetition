package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import ver1.DBConnectionManager;

@Data
@AllArgsConstructor
public class CommentsLoader {

	public JTable insertData() {

		JTable articleTable = new JTable();
		articleTable.setDefaultRenderer(Object.class, null);

		String query = " select comment from petition as p left join user as u on u.id = p.user_id order by id desc ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement ptmt = conn.prepareStatement(query);
				ResultSet rs = ptmt.executeQuery();) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columncount = metaData.getColumnCount();
			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			for (int i = 1; i <= columncount; i++) {
				model.addColumn(metaData.getColumnName(i));
			}

			while (rs.next()) {
				Object[] rowData = new Object[columncount];
				for (int i = 1; i <= columncount; i++) {
					rowData[i - 1] = rs.getObject(i);
				}
				model.addRow(rowData);
			}
			articleTable.setModel(model);

		} catch (Exception e) {

		}
		return articleTable;
	}

}
