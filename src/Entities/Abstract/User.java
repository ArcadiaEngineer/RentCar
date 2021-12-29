package Entities.Abstract;

import Entities.Concrete.Mail;


public abstract class User extends Person {
    
    protected String gender;
    protected int age;
    protected String imgPath;
    protected String phoneNumber;
    protected Mail mailAdress ;
    protected String Username;
    protected String password;
    protected String resetPasswordQuestion;
    protected String resetPasswordAnswer;

    public User(String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress, String Username, String Password, String resetPasswordQuestion, String resetPasswordAnswer) {
        super(fullName);
        this.gender = gender;
        this.age = age;
        this.imgPath = imgPath;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
        this.Username = Username;
        this.password = Password;
        this.resetPasswordQuestion = resetPasswordQuestion;
        this.resetPasswordAnswer = resetPasswordAnswer;
    }

    
    public abstract void printOrder();
    

    public String getResetPasswordQuestion() {
        return resetPasswordQuestion;
    }

    public void setResetPasswordQuestion(String resetPasswordQuestion) {
        this.resetPasswordQuestion = resetPasswordQuestion;
    }

    public String getResetPasswordAnswer() {
        return resetPasswordAnswer;
    }

    public void setResetPasswordAnswer(String resetPasswordAnswer) {
        this.resetPasswordAnswer = resetPasswordAnswer;
    }

    
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
