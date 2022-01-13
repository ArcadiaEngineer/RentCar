
package Helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;


public class HeaderColor extends DefaultTableCellRenderer {

    public HeaderColor() {
        setOpaque( true );
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        setBackground( new Color(99, 82, 255) );
        setFont( new Font("Tahoma", Font.BOLD, 11) );
        setForeground(new Color(255, 255, 255));
        setHorizontalAlignment(CENTER);
        
        
        return this;
    }
    
}

