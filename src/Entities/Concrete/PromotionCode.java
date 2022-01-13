package Entities.Concrete;


public class PromotionCode {
    
    private int promotionCode_id;
    private String promotionCode_str;
    private double discount;  // 0.35, 0.12i
    private boolean isUsed;

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
    
    private static int total_promotionCode = 0;

    public PromotionCode(int promotionCode_id, String promotionCode_str, double discount, boolean isUsed) {
        this.promotionCode_id = promotionCode_id;
        this.promotionCode_str = promotionCode_str;
        this.discount = discount;
        this.isUsed = isUsed;
        total_promotionCode++;
    }

    public double getDiscount() {
        return discount;
    }

    public int getPromotionCode_id() {
        return promotionCode_id;
    }

    public String getPromotionCode_str() {
        return promotionCode_str;
    }

    public static int getTotal_promotionCode() {
        return total_promotionCode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
