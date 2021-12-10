package Entities.CarComparators;

import Entities.Concrete.Car;
import java.util.Comparator;

public class CarYearComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        return Integer.compare(o1.getYear(), o2.getYear());
    }
    
}
