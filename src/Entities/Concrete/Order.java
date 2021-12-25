package Entities.Concrete;

import java.util.Date;

public class Order {
    
    private Customer customer;
    private Car car;
    private Gallery gallery;
    private PromotionCode promotionCode;
    private int order_id;
    
    private String pick_upDate;
    private String returnDate;
    private double charge;
    
    private static int  total_order = 0;

    public Order(Customer customer, Car car, Gallery gallery, PromotionCode promotionCode, int order_id, String pick_upDate, String returnDate) {
        this.customer = customer;
        this.car = car;
        this.gallery = gallery;
        this.promotionCode = promotionCode;
        this.order_id = order_id;
        this.pick_upDate = pick_upDate;
        this.returnDate = returnDate;
        total_order++;
    }

    
    
    public void calculateCharge() {
        charge = car.getPrice() * findTotalDay();
        charge = charge - charge * promotionCode.getDiscount();
    }
    
    private int findTotalDay() {
        
        int totalDay = 0;
        
        String[] tokens = pick_upDate.split(" ");
        int year = Integer.parseInt( tokens[2] );
        int day = Integer.parseInt( tokens[0] );
        Date pick_up = new Date(year, findMonth( tokens[1] ), day);
        
        tokens = returnDate.split(" ");
        year = Integer.parseInt( tokens[2] );
        day = Integer.parseInt( tokens[0] );
        Date returnDt = new Date(year, findMonth( tokens[1] ), day);
        
        totalDay = (int)(returnDt.getTime() - pick_up.getTime()) / 86400000;
        
        return totalDay;
    }
    
    private int findMonth(String month) {
        int monthNum = 0;
        
        switch (month) {
            case "Oca" : monthNum = 1; break;
            case "Şub" : monthNum = 2; break;
            case "Mar" : monthNum = 3; break;
            case "Nis" : monthNum = 4; break;
            case "May" : monthNum = 5; break;
            case "Haz" : monthNum = 6; break;
            case "Tem" : monthNum = 7; break;
            case "Ağu" : monthNum = 8; break;
            case "Eyl" : monthNum = 9; break;
            case "Eki" : monthNum = 10; break;
            case "Kas" : monthNum = 11; break;
            case "Ara" : monthNum = 12; break;
            default: monthNum = 0;
        }
        
        return monthNum;
    }

    
    
    
    
    
    
}
