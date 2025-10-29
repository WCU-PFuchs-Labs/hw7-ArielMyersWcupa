import java.util.*;
/*
* Code Author: Ariel Myers
*/

public class TestGPTree {
    public static void main(String[] args) {
        Random rand = new Random();
        int maxDepth = 4;
        int numVars = 3;
        double[] inputs = {3.14, 2.78, 1.0};

        Binop[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
        NodeFactory factory = new NodeFactory(ops, numVars);

        GPTree treeA = new GPTree(factory, maxDepth, rand);
        GPTree treeB = new GPTree(factory, maxDepth, rand);

        System.out.println("Tree A: " + treeA + " = " + treeA.eval(inputs));
        System.out.println("Tree B: " + treeB + " = " + treeB.eval(inputs));

        treeA.crossover(treeB, rand);

        System.out.println("Results After crossover");
        System.out.println("Tree A: " + treeA + " = " + treeA.eval(inputs));
        System.out.println("Tree B: " + treeB + " = " + treeB.eval(inputs));
    }
}
