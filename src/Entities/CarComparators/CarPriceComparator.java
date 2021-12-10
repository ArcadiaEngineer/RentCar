package Entities.CarComparators;

import Entities.Concrete.Car;
import java.util.Comparator;

public class CarPriceComparator implements Comparator<Car>{

    @Override
    public int compare(Car o1, Car o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
    
}
