package Entities.Concrete;

import Entities.Abstract.User;
import GUI.Bill;

public class Customer extends User {
    
    private int customer_id;
    private double totalCash;
    
    private static int total_customer = 0;
    public static int current_database_customer_id;

    public Customer(String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress, String Username, String homeAddress, String Password, String resetPasswordQuestion, String resetPasswordAnswer, double totalCash) {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, homeAddress, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.customer_id = current_database_customer_id++;
        this.totalCash = totalCash;
        total_customer++;
    }
    
    public Customer() {
        super("", "", 0, "", "", new Mail(), "", "", "", "", "");
        this.customer_id = current_database_customer_id++;
        this.totalCash = totalCash;
        total_customer++;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public static int getTotal_customer() {
        return total_customer;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Total Cash: " + totalCash + "\n" ;
    }

    @Override
    public void printOrder(Order order) {
        Bill frameBill = new Bill( order );
        frameBill.setVisible( true );
        frameBill.setLocationRelativeTo( null );
    }
    
}
