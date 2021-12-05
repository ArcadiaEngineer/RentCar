package Entities.Concrete;


public class Mail {
    
    private int mail_id;
    private String name;
    
    private static int total_mail;
    
    public Mail(String name) {
        this.name = name;
        mail_id = total_mail++;
    }

    public int getMail_id() {
        return mail_id;
    }

    public String getName() {
        return name;
    }

    public static int getTotal_mail() {
        return total_mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
}
