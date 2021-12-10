package Entities.Concrete;

import Entities.Abstract.User;

/**
 *
 * @author Lian La-Fey
 */
public class GalleryOwner extends User {
    
    private int galleryOwner_id;
    private Gallery gallery;
    
    public GalleryOwner(String galleryName, int galleryOwner_id, Gallery gallery, 
            String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress,
            String Username, String Password, String resetPasswordQuestion, String resetPasswordAnswer)
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.galleryOwner_id = galleryOwner_id;
        this.gallery = gallery;
    }

    public int getGalleryOwner_id() {
        return galleryOwner_id;
    }
    
    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    @Override
    public String toString() {
        return super.toString() + gallery + "\n";
    }

    @Override
    public void printOrder() {
        
    }
}
