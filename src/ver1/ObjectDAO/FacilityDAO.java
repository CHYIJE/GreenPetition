package ver1.ObjectDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ver1.DBConnectionManager;

public class FacilityDAO {
	public JTable insertData() {
		JTable articleTable = new JTable();
//		String query = "select p.id, p.title, u.acc_id, p.category, p.date from petition as p left join user as u on u.id = p.user_id WHERE P.category = 'facility' order by id desc";
		String query = "select p.id, p.title, u.acc_id, p.category, p.agree, p.disagree, p.date from petition as p left join user as u on u.id = p.user_id WHERE p.category = 'facility' order by id desc";

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
