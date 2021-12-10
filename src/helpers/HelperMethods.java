package helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
    
    
}
