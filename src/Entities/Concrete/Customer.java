package Entities.Concrete;

import Entities.Abstract.Person;

public class Customer extends Person{
    
    private int CustomerId;
    private double totalCash;
    
    private static int total_id = 0;

    public Customer(double totalCash, 
            String fullName, String gender, int age, String imgPath, 
            String phoneNumber, Mail mailAdress, String Username, String Password, 
            String resetPasswordQuestion, String resetPasswordAnswer) 
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.CustomerId = total_id++;
        this.totalCash = totalCash;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Total Cash: " + totalCash + "\n" ;
    }
    
    
}
