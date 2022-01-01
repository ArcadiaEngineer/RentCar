package Entities.Concrete;

import Business.RentCarSystem;
import Entities.Abstract.User;
import java.sql.SQLException;


/**
 *
 * @author Lian La-Fey
 */
public final class Administrator {
    
    private static final String ADMIN_MAIL = "admin@protonmail.com";
    private static final String ADMIN_PASS = "123456";
    
    public static void addGalleryOwner(GalleryOwner galleryOwner) throws SQLException {
        if ( RentCarSystem.addUserToDatabase( galleryOwner ) )
            RentCarSystem.getUserList().add( galleryOwner );
    }
    
    public static int findTotalOrder(User user) {
        int totalOrder = 0;
        for ( Order order : RentCarSystem.getOrders() ) {
            if ( user instanceof Customer ) {
                if ( ((Customer)user).getCustomerId() == order.getCustomerId() )
                    totalOrder++;
            }
            
            if ( user instanceof GalleryOwner ) {
                if ( ((GalleryOwner)user).getGalleries().contains( RentCarSystem.getGalleryById( order.getCarId() ) ) )
                    totalOrder++;
            }
        }
        
        return totalOrder;
    }

    public static String getAdminMail() {
        return ADMIN_MAIL;
    }

    public static String getAdminPass() {
        return ADMIN_PASS;
    }
    
}
