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
      root.addRandomKids(nf, maxDepth, Random rand) {
  }
