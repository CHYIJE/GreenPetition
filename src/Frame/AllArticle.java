package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import ver1.DBConnectionManager;
@Data
@NoArgsConstructor
public class AllArticle {
    
    public JTable insertData() {

        JTable articleTable = new JTable();
        String query = "SELECT * FROM petition";

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
            

            for (int i = 1; i < columncount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            while (rs.next()) {
                Object[] rowData = new Object[columncount];
                for (int i = 1; i < columncount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
           articleTable.setModel(model);     
//           articleTable.setModel(new DefaultTableModel());
        } catch (Exception e) {
            
        }
        return articleTable;
    }
    
}