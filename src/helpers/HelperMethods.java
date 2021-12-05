package helpers;

/**
 *
 * @author Lian La-Fey
 */
public class HelperMethods {
    
    public static boolean checkEmailWriting(String str) {
        return str.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    }
    
}
