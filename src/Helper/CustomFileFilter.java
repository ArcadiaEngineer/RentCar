/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Lian La-Fey
 */


// https://stackoverflow.com/questions/19302029/filter-file-types-with-jfilechooser
public class CustomFileFilter extends FileFilter {

    @Override
    public String getDescription() {
        return "PNG Images (*.png)";
    }
    
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
           return true;
       } else {
           String filename = f.getName().toLowerCase();
           return filename.endsWith(".png");
       }
    }
}
