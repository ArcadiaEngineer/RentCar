package Entities.Abstract;

import Entities.Concrete.Mail;

public class Person {
    
    private String fullName;
    private String gender;
    private int age;
    private String imgPath;
    private String phoneNumber;
    private Mail mailAdress ;
    private String Username;
    private String Password;
    private String resetPasswordQuestion;
    private String resetPasswordAnswer;

    public Person(String fullName, String gender, int age, 
            String imgPath, String phoneNumber, Mail mailAdress, 
            String Username, String Password, 
            String resetPasswordQuestion, String resetPasswordAnswer) 
    {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.imgPath = imgPath;
        this.phoneNumber = phoneNumber;
        this.mailAdress = mailAdress;
        this.Username = Username;
        this.Password = Password;
        this.resetPasswordQuestion = resetPasswordQuestion;
        this.resetPasswordAnswer = resetPasswordAnswer;
    }
    
    

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
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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
