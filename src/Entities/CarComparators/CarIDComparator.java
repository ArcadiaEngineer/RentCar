/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.CarComparators;

import Entities.Concrete.Car;
import java.util.Comparator;

/**
 *
 * @author Lian La-Fey
 */
public class CarIDComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        return Integer.compare(((Car)o1).getId(), ((Car)o2).getId());
    }
    
}
