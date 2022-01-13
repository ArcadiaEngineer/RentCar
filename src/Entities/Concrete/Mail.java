<<<<<<< HEAD
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

    public Mail() {}
    
    public void setMail_id(int mail_id) {
        this.mail_id = mail_id;
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
=======

package Entities.Concrete;

public class Mail {
    private String mailAdress;
    private String name;

    public Mail(String mailAdress, String name) {
        this.mailAdress = mailAdress;
        this.name = name;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mail address: " + mailAdress + "\n";
    }
    
       
}
>>>>>>> master
