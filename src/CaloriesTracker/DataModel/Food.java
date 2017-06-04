package CaloriesTracker.DataModel;

/**
 * Created by lamkeong on 6/1/2017.
 * Basic food class with 3 variables
 */
public class Food {
    private String name;
    private double cal;
    private double quantity;
    private double totalcal;

    public Food(String name, double quantity, double cal){
        this.name = name;
        this.cal = cal;
        this.quantity = quantity;
        this.totalcal = quantity * cal;
    }

    public Food(String name, double cal){
        this.name = name;
        this.cal = cal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    public double getTotalcal() {
        return totalcal;
    }

    public void setTotalcal(double totalcal) {
        this.totalcal = totalcal;
    }

    public Food(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {

        return name;
    }

    public double getCal() {
        return cal;
    }

    public double getQuantity() {
        return quantity;
    }

//    @Override
//    public String toString() {
//        return this.getName();
//    }

    public String getDescription(){
        return ("Consumed " + quantity + " " + name + ": " + totalcal + " Total calories consumed");
    }

//    @Override
//    public int hashCode() {
//        int code = name.hashCode();
//        return code;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        Food foodObj = (Food) obj;
//        if(foodObj.name.equalsIgnoreCase(this.name)){
//            return true;
//        }else{
//            return false;
//        }
//    }
}
