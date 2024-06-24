package Frame;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ver1.DBConnectionManager;

public class CellEditor extends DefaultTableCellRenderer {

	int agree;
	int disagree;

	public void getAgree(int agree, int disargee) {

		this.agree = agree;
		this.disagree = disargee;

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		// table 이 선택되지 않았을 때의 기본값 (isRowSelected)
		if (!table.isRowSelected(row)) {
			// 이런 느낌 어때요
			// if(agree > disagree && agree + disagree > 15) { //
			if (agree > disagree && agree >= 10) { //
				c.setBackground(Color.BLUE);
				// }else if (disagree > agree && agree + disagree > 15){
			} else if (disagree > agree && disagree >= 10) {
				c.setBackground(Color.RED);
			} else {
				c.setBackground(Color.white);
			}
			// table 이 선택되었을 때의 기본
		} else {
			c.setBackground(Color.pink);
		}
		return c;
	}

}
