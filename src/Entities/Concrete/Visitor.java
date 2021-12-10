package Entities.Concrete;

import Entities.Interface.Registerable;
import GUI.RegisterPanel;

/**
 *
 * @author Lian La-Fey
 */
public class Visitor implements Registerable {
    
    private String name;

    public Visitor() {
        name = "Visitor";
    }

    public String getName() {
        return name;
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
