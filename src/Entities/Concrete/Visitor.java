package Entities.Concrete;

import Entities.Interface.Registerable;
import Entities.Abstract.Person;
import GUI.RegisterPanel;

/**
 *
 * @author Lian La-Fey
 */
public class Visitor extends Person implements Registerable {
    
    
    public Visitor(String fullName) {
        super(fullName);
    }

    @Override
    public void register() {
        RegisterPanel registerPanel = new RegisterPanel();
        registerPanel.setVisible( true );
        registerPanel.setLocationRelativeTo( null );
    }
    
    @Override
    public String toString() {
        return "Visitor";
    }
    
}
