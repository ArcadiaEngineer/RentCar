package Helper;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lian La-Fey
 */
public class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
     public MyCellRenderer() {
         setOpaque(true);
     }

     public Component getListCellRendererComponent(JList<?> list,
                                                   Object value,
                                                   int index,
                                                   boolean isSelected,
                                                   boolean cellHasFocus) {

         // Bazı comboboxlar bos oldugu icin NullPointerException hatasi firlatti.
         try {
             setText(value.toString());
         } catch (NullPointerException ex ) {
             
         }
         

         Color background;
         Color foreground;

         // check if this cell represents the current DnD drop location
         JList.DropLocation dropLocation = list.getDropLocation();
         if (dropLocation != null
                 && !dropLocation.isInsert()
                 && dropLocation.getIndex() == index) {

             background = new Color(51, 51, 51);
             foreground = new Color(122, 72, 255);

         // check if this cell is selected
         } else if (isSelected) {
             background = new Color(0, 0, 0);
             foreground = new Color(255, 255, 255);

         // unselected, and not the DnD drop location
         } else {
             background = new Color(51, 51, 51);
             foreground = new Color(255, 255, 255);
         };

         setBackground(background);
         setForeground(foreground);
         
         setFont( new Font("Tahoma", Font.BOLD, 12) );
         
         setHorizontalAlignment( SwingConstants.CENTER );

         return this;
     }
 }
