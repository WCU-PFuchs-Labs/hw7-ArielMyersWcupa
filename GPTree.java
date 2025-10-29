import java.util.*;
/*
* Code Author: Ariel Myers
*/

public class GPTree implements Collector {
    private Node root;
    private ArrayList<Node> crossNodes;

  GPTree() {
     root = null;
  }

  public GPTree(NodeFactory nf, int maxDepth, Random rand) { //For the constructor to build a tree randomly//
      root = nf.getOperator(rand);
      root.addRandomKids(nf, maxDepth, rand);
  }

  public void collect(Node n) {
      if (!n.isLeaf()) crossNodes.add(n); //Only collect nodes that are specifically NOT leaves//
    }

    
  public void traverse() {
      crossNodes = new ArrayList<>(); //To create a list and traverse it//
      root.traverse(this);
    }

    
  public String getCrossNodes() {
      StringJoiner sj = new StringJoiner(";");
      for (Node n : crossNodes) sj.add(n.toString()); //String of nodes that are separated by semicolons//
      return sj.toString();
    }

    
  public void crossover(GPTree other, Random rand) { //This is to swap random subtrees within the GPTrees//
        this.traverse();
        other.traverse();

        Node n1 = crossNodes.get(rand.nextInt(crossNodes.size()));
        Node n2 = other.crossNodes.get(rand.nextInt(other.crossNodes.size()));

        if (rand.nextBoolean()) n1.swapLeft(n2);
        else n1.swapRight(n2);
    }

  public String toString() {
      return root.toString();
    }

  public double eval(double[] data) {
      return root.eval(data);
    }
}
