package Frame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellEditor extends DefaultTableCellRenderer{
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
    { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        //  table 이 선택되지 않았을 때의 기본값 (isRowSelected)
        if (!table.isRowSelected(row)) {	
        	// 여기에 글의 동의와 비동의 int 값을 끌고 와서 c.getBackground(Color.하고싶은색상); 하시면 됩니			
            if(table.getValueAt(row, 1).toString().indexOf("3")!=-1) { 
                c.setBackground(Color.lightGray);
            }else{
                c.setBackground(Color.white);
            }
            // table 이 선택되었을 때의 기본
        } else {
        	c.setBackground(Color.pink);
        }
        return c;
    } 
	
}
