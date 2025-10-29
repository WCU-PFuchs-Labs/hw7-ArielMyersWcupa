import java.util.Random;
/*
* Code Author: Ariel Myers
* Purpose: A binary operator for Division
*/
public class Divide extends Binop {
    public double eval(double[] data) {
        double denom = right.eval(data);
        if (Math.abs(denom) < 0.0001)
            return 1.0; 
        return left.eval(data) / denom;
    }
    public String toString() { return "/"; }
}
