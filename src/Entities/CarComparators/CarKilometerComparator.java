package Entities.CarComparators;

import Entities.Concrete.Car;
import java.util.Comparator;

public class CarKilometerComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getKm(), o2.getKm());
    }
    
}
