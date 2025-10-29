import java.util.Random;
/*
* Code Author: Ariel Myers
* Purpose: A binary operator for Division
*/
public class Divide extends Binop {
    public double eval(double[] data) {
        double denom = rChild.eval(data);
        if (Math.abs(denom) < 0.0001)
            return 1.0; 
        return lChild.eval(data) / denom;
    }
    public String toString() { return "/"; }
}
