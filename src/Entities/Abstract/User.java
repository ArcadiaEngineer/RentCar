package Entities.Abstract;

import Business.RentCarSystem;
import Entities.Concrete.Mail;
import Entities.Concrete.Order;
import java.sql.SQLException;


public abstract class User extends Person {
    
    protected String gender;
    protected int age;
    protected String imgPath;
    protected String phoneNumber;
    protected Mail mailAdress ;
    protected String Username;
    protected String homeAddress;
    protected String password;
    protected String resetPasswordQuestion;
    protected String resetPasswordAnswer;

    public User(String fullName, String gender, int age, String imgPath, String phoneNumber, 
            Mail mailAdress, String Username, String homeAddress, 
            String Password, String resetPasswordQuestion, String resetPasswordAnswer) {
        super(fullName);
        this.gender = gender;
        this.age = age;
        this.imgPath = imgPath;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
        this.Username = Username;
        this.homeAddress = homeAddress;
        this.password = Password;
        this.resetPasswordQuestion = resetPasswordQuestion;
        this.resetPasswordAnswer = resetPasswordAnswer;
    }

    public boolean updateInformation(String newPhoneNum, String newImgPath, String newHomeAddress, String databaseName, Mail newMail ) throws SQLException {
        phoneNumber = newPhoneNum;
        mailAdress = newMail;
        imgPath = newImgPath;
        homeAddress = newHomeAddress;
        RentCarSystem.updateUserInDatabase(this, databaseName);
        return true;
    }
    
    public abstract void printOrder(Order order);
    

    public String getResetPasswordQuestion() {
        return resetPasswordQuestion;
    }

    public String getResetPasswordAnswer() {
        return resetPasswordAnswer;
    }

    
    public String getUsername() {
        return Username;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getFullName() {
        return fullName;
    }


    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Mail getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(Mail mailAdress) {
        this.mailAdress = mailAdress;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + "\n" +
               "Age: " + age + "\n" + 
               "Gender: " + gender + "\n" + 
               "Phone Number: " + phoneNumber + "\n" +
               mailAdress + "\n" ;
    }
    
}
