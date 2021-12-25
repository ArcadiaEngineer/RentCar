package Entities.Concrete;

import Entities.Abstract.User;
import java.util.ArrayList;

/**
 *
 * @author Lian La-Fey
 */
public class GalleryOwner extends User {
    
    private int galleryOwner_id;
    private ArrayList<Gallery> galleries = new ArrayList<>();
    
    public GalleryOwner(int galleryOwner_id,
            String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress,
            String Username, String Password, String resetPasswordQuestion, String resetPasswordAnswer)
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.galleryOwner_id = galleryOwner_id;
    }

    public int getGalleryOwner_id() {
        return galleryOwner_id;
    }

    public ArrayList<Gallery> getGalleries() {
        return galleries;
    }

    @Override
    public void printOrder() {
        
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
