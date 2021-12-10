package Entities.Concrete;

public class Mail {
    
    private int mail_id;
    private String name;
    
    private static int total_mail = 0;
    
    public Mail(int mail_id, String name) {
        this.name = name;
        this.mail_id = mail_id;
        total_mail++;
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
