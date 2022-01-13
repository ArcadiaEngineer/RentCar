<<<<<<< HEAD
package Entities.Concrete;

import java.util.ArrayList;

/**
 *
 * @author Lian La-Fey
 */
public class Gallery {
    
    private int gallery_id;
    private String name;
    private ArrayList<Car> cars = new ArrayList<>();

    private static int total_gallery = 0;
    
    public Gallery(int gallery_id, String name) {
        this.gallery_id = gallery_id;
        this.name = name;
        total_gallery++;
    }

    public int getId() {
        return gallery_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public static int getTotal_gallery() {
        return total_gallery;
    }

    @Override
    public String toString() {
        String allText = "Galery Name: " + name + "\n" ;
       
        for (Car car : cars) {
            allText += car;
        }
        
        return allText;
    }
    
    
    
    
}
=======

package Entities.Concrete;

import java.util.ArrayList;

public class Gallery {
    
    private int id;
    private String name;
    private ArrayList<Car> cars = new ArrayList<>();
    
    private static int total_id = 0;

    public Gallery(String name) {
        this.id = total_id++;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
       String allText = "Galery Name: " + name + "\n" ;
       
        for (Car car : cars) {
            allText += car;
        }
        
        return allText;
    }
    
    
    
}
>>>>>>> master
