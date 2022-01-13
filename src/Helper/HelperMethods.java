package Helper;

import Business.RentCarSystem;
import Entities.Abstract.User;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author Lian La-Fey
 */
public class HelperMethods {
    
    /*
    ^ string start
    1) A-Z characters allowed
    2) a-z characters allowed
    3) 0-9 numbers allowed
    4) Additionally email may contain only underscore(_) and dot(.)
    $ string finish
    */
    public static boolean checkEmailWriting(String str) {
        return str.matches("^[A-Za-z0-9+_.]+@(.+)$");
    }
    
    public static void changePage(JPanel panel, JLayeredPane page_JLayeredPane) {
        page_JLayeredPane.removeAll();
        page_JLayeredPane.add( panel );
        page_JLayeredPane.repaint();
        page_JLayeredPane.revalidate();
    }
    
    public static void showErrorMessage(String str, String title) {
        JOptionPane.showMessageDialog(null, str, title, JOptionPane.ERROR_MESSAGE); 
    }
    
    public static void showSuccessfulMessage(String str, String title) {
        JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void hideShowPass(java.awt.event.ItemEvent evt, JToggleButton btn, JPasswordField passwordField) {
        
        URL imageUrlOpenEye = null;
        URL imageUrlCloseEye = null; 
        
        File currentDirFile = new File("");
        String helper = currentDirFile.getAbsolutePath();
        
        try {
            imageUrlOpenEye = new File(helper + "\\src\\images\\openEye.png").toURI().toURL();
            imageUrlCloseEye = new File(helper + "\\src\\images\\closeEye2.png").toURI().toURL();
        } catch (MalformedURLException ex) {
            System.out.println( ex + "");
        }
        
        if ( evt.getStateChange() == evt.SELECTED ) {
            btn.setIcon(new ImageIcon(imageUrlOpenEye) );
            passwordField.setEchoChar((char)0);
        } else if ( evt.getStateChange() == evt.DESELECTED ) {
            btn.setIcon(new ImageIcon( imageUrlCloseEye ) );
            passwordField.setEchoChar((char)'\u2022');
        }
    }
    
    public static boolean checkPasswordWriting(String password) {
        return password.matches("^(?=\\S+$).{3,}$");
    }
    
    public static void wirteOnlyNumber( java.awt.event.KeyEvent evt, JTextField textField) {
        
        if ( (evt.getKeyChar() >= '0' &&  evt.getKeyChar() <= '9') || evt.getKeyChar() == '.' ) {
            
        } else {
            evt.consume();
            
        }

        try {
            while ( textField.getText().charAt(0) == '0' ) {
                textField.setText( textField.getText().substring(1) );
            }
        } catch ( StringIndexOutOfBoundsException ex ) {
            
        }
        
        if ( textField.getText().isBlank() ||  textField.getText().isEmpty() ) {
            textField.setText("0");
        } 
        
        
        
        
    }
    
    // 513 426 1358
    public static boolean controlPhoneNum(String phoNum, String userName) {
        ArrayList<User> users = RentCarSystem.getUserList();
        // Blocking duplicate between customer and galleryOwner
        for ( User user : users ) {
            if ( user.getPhoneNumber().equals( phoNum ) && !user.getUsername().equals( userName ) ) 
                throw new IllegalArgumentException("This phone number is already in use");
        }
        
        if ( !phoNum.matches(("[1-9]\\d{2} [1-9]\\d{2} \\d{4}")) )
            throw new IllegalArgumentException("Not valid phone number! Check your style!");
        
        return true;
    }
    
    public static boolean checkHomeAddress(String homeAddress) {
        int countRegex = 0;
        for ( int i = 0; i < homeAddress.length() - 2; i++ ) {
            if ( homeAddress.substring(i, i + 2).equals(", ") )
                countRegex++;
        }
        
        if ( countRegex == 1 )
            return true;
        else
            return false;
    }
    
}
