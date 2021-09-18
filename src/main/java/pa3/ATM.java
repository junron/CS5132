package pa3;

import java.util.Objects;

public class ATM {
    private String location;
    private String postal;
    private double x;
    private double y;

    public ATM(String location, String postal, double x, double y){
        this.location = location;
        this.postal = postal;
        this.x = x;
        this.y = y;
    }

    public String getLocation(){ return location;}
    public String getPostal(){ return postal;}
    public double getX(){ return x; }
    public double getY(){ return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return Double.compare(atm.x, x) == 0 && Double.compare(atm.y, y) == 0
                && Objects.equals(location, atm.location) && Objects.equals(postal, atm.postal);
    }

    @Override
    public String toString(){
        return location + " S(" + postal + ")" + " at (" + x + "," + y + ")";
    }

}
