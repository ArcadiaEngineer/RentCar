
package Entities.Concrete;

import Entities.Abstract.Person;

public class GalleryOwner extends Person{
    
    private int id;
    private Gallery gallery;
    
    private static int total_id = 0;

    public GalleryOwner(String galleryName, String fullName, 
            String gender, int age, String imgPath, String phoneNumber, Mail mailAdress,
            String Username, String Password, String resetPasswordQuestion, String resetPasswordAnswer)
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.id = total_id++;
        this.gallery = new Gallery(galleryName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
}
