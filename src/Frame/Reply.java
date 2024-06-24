package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ver1.DBConnectionManager;

public class Reply {

	public JTable insertReply(int petitionid) {

		JTable replyTable = new JTable();
		String query = "select u.name, c.comment from comment as c join user as u on u.id = c.user_id where c.petition_id = ? ";
//		String query = "select user_id, comment from comment ";

		try (Connection conn = DBConnectionManager.getInstance().getConnection()) {
			PreparedStatement ptmt = conn.prepareStatement(query);
			ptmt.setInt(1, petitionid);
			ResultSet rs = ptmt.executeQuery();
			
//			ptmt.setInt(1, petitionid);

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			for (int i = 1; i <= columnCount; i++) {
				model.addColumn(metaData.getColumnName(i));
			}

			while (rs.next()) {
				Object[] rowData = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					rowData[i - 1] = rs.getObject(i);
				}
				model.addRow(rowData);
			}
			replyTable.setModel(model);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return replyTable;
	}

}
