
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
