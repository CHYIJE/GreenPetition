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
public class AllArticle {

	public JTable insertData() {

		JTable articleTable = new JTable();
		String query = "SELECT * FROM petition";

		try (Connection conn = DBConnectionManager.getInstance().getConnection();
				PreparedStatement ptmt = conn.prepareStatement(query);
				ResultSet rs = ptmt.executeQuery();) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columncount = metaData.getColumnCount();
			DefaultTableModel model = new DefaultTableModel();

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

