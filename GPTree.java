import java.util.*;
/*
* Code Author: Ariel Myers
*/

public class GPTree implements Collector {
    private Node root;
    private ArrayList<Node> crossNodes;

    //Constructor//
    GPTree() {
        root = null;
        crossNodes = new ArrayList<>();
    }

    // Constructor that will randomly build a tree//
    public GPTree(NodeFactory nf, int maxDepth, Random rand) {
        root = nf.getOperator(rand);
        root.addRandomKids(nf, maxDepth, rand);
        crossNodes = new ArrayList<>();
    }

    //Called by Node.traverse() for each node//
    @Override
    public void collect(Node node) {
        if (node != null && node.getOp() instanceof Binop) {
            crossNodes.add(node);
        }
    }

    //Begins a traversal and collects all Binops//
    public void traverse() {
        if (root == null) return;
        // Always clear before collecting new nodes
        crossNodes.clear();
        root.traverse(this);
    }

    //Returns a string of collected nodes separated by semicolons//
    public String getCrossNodes() {
        StringJoiner sj = new StringJoiner(";");
        for (Node n : crossNodes) {
            sj.add(n.toString());
        }
        return sj.toString();
    }

    //Subtree crossover//
    public void crossover(GPTree other, Random rand) {
        this.traverse();
        other.traverse();

        if (crossNodes.isEmpty() || other.crossNodes.isEmpty()) return;

        Node n1 = crossNodes.get(rand.nextInt(crossNodes.size()));
        Node n2 = other.crossNodes.get(rand.nextInt(other.crossNodes.size()));

        if (rand.nextBoolean()) n1.swapLeft(n2);
        else n1.swapRight(n2);
    }

    public String toString() {
        return (root == null) ? "" : root.toString();
    }

    public double eval(double[] data) {
        return root.eval(data);
    }
}
