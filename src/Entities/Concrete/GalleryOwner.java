
package Entities.Concrete;

import Business.RentCarSystem;
import Entities.Abstract.User;
import GUI.Bill;
import java.sql.SQLException;
import java.util.ArrayList;

public class GalleryOwner extends User{
    
    private int galleryOwner_id;
    private ArrayList<Gallery> galleries = new ArrayList<>();
    
    public GalleryOwner(int galleryOwner_id,
            String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress,
            String Username, String homeAddress, String Password, String resetPasswordQuestion, String resetPasswordAnswer)
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, homeAddress, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.galleryOwner_id = galleryOwner_id;
    }

    public int getGalleryOwner_id() {
        return galleryOwner_id;
    }



    public ArrayList<Gallery> getGalleries() {
        return galleries;
    }
    
    
    
    public boolean addGallery(String galleryName) throws SQLException {
        
        if ( RentCarSystem.getGalleries().get( RentCarSystem.getGalleries().size() - 1) == null )
            throw new NullPointerException();
        
        Gallery gallery = new Gallery(RentCarSystem.getGalleries().get( RentCarSystem.getGalleries().size() - 1).getId() + 1, galleryName);
        RentCarSystem.getGalleries().add( gallery );
        galleries.add( gallery );
        RentCarSystem.addGalleryToDatabase(gallery, galleryOwner_id);
        
        return true;
    }
    
    public boolean deleteGallery(int galleryID) throws SQLException {
        for ( Gallery gallery : galleries ) {
            if ( galleryID == gallery.getId() ) {
                galleries.remove( gallery );
                RentCarSystem.deleteGalleryFromDatabase( gallery );
                RentCarSystem.getGalleries().remove( gallery );
                return true;
            }
        }
        
        return false;
    }
    
    public boolean updateGallery(String oldGalleryName, String newGalleryName) throws SQLException {
        for ( Gallery gallery : galleries ) {
            if ( oldGalleryName.equalsIgnoreCase( gallery.getName() ) ) {
                gallery.setName( newGalleryName );
                RentCarSystem.updateGalleryInDatabase( gallery );
                return true;
            }
        }
        
        return false;
    }
    
    public boolean deleteCar(int carID) throws SQLException {
        Car car = RentCarSystem.getCarById(carID);
        
        if ( car == null )
            throw new NullPointerException();
        
        for ( Gallery gallery : galleries ) {
            if ( gallery.getCars().contains(car) ) {
                gallery.getCars().remove(car);
                RentCarSystem.getCars().remove(car);
                RentCarSystem.deleteCarFromDatabase(car);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean addCar(Car car) throws SQLException {
        RentCarSystem.addCarToDatabase( car );
        RentCarSystem.getGalleryById( car.getGalleryId() ).getCars().add(car);
        return RentCarSystem.getCars().add( car );
    }

    @Override
    public void printOrder(Order order) {
        Bill frameBill = new Bill( order );
        frameBill.setVisible( true );
        frameBill.setLocationRelativeTo( null );
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
