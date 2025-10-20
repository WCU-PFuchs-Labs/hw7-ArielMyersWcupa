[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=21173121&assignment_repo_type=AssignmentRepo)
# CSC 240 Computer Science III

### Homework 7 - Encapsulating Node with GPTree

[![Points badge](../../blob/badges/.github/badges/points.svg)](../../actions)

It’s time to plan ahead for the Genetic Programming project.  If we try to process more trees in our main() method, it will get unwieldy and hard to maintain.  A simplistic rule of thumb is that main() shouldn’t have more than about 20 lines of code in it.  It should make a few objects, and then tell them to "go about their business."

## Starting Out:

1. Copy the files from your completed Refactoring project to this directory

2. After you copy the files, only two of the copied files should be modified

1. `Node.java`  - to add concrete methods `traverse()`, `swapLeft(Node trunk)`,`swapRight(Node trunk)`, and `isLeaf()`.
5. `Divide.java` - to fix division by zero.

## The task

One way to add useful encapsulation is to create a GPTree class that consolidates some of the functionality of trees.  Currently, the Node class knows how to do things, but a tree class that encapsulates the root node of a tree will be useful.  This process has five steps. 


**Step 1** The `Node` class will be modified to have a `traverse(Collector c)` method that will recursively give Nodes in the Tree to the `Collector` interface to collect them. That way, a list of potential binary Nodes will be held by the `GPTree` to be randomly selected as crossover points.

**Step 2** You will add 3 new files to this project, `Collector.java`, `GPTree.java` and `TestGPTree.java` described below.

File 1: The `Collector` interface will have one method called `collect(Node node)` as shown here:

```java
public interface Collector {
    void collect(Node node);
}
``` 
   
File 2: The GPTree class will implement the `Collector` interface to collect Nodes appropriate for Crossover. You will write this implementation in Step 3. In addition to implementing `Collector`, the `GPTree` class encapsulates the Algebra Tree (`Node`) and implements a `crossover()` method: 


```java
public class GPTree implements Collector {
    private Node root;
    private ArrayList<Node> crossNodes;
    
    
    /**
     * @param - node The node to be collected.
     * TODO: implement this method
     */
    public void collect(Node node) {
        // add node to crossNodes if it is not a leaf node
        
    }
    
    
    // DO NOT EDIT code below for Homework 8. 
    // If you are doing the challenge mentioned in 
    // the comments above the crossover method
    // then you should create a second crossover
    // method above this comment with a slightly 
    // different name that handles all types
    // of crossover.
    
    
    /**
     * This initializes the crossNodes field and
     * calls the root Node's traverse method on this
     * so that this can collect the Binop Nodes.
     */
    public void traverse() {
        crossNodes = new ArrayList<Node>();
        root.traverse(this);
    }
    
    /**
     * This returns a String with all of the binop Strings
     * separated by semicolons
     */
    public String getCrossNodes() {
        StringBuilder string = new StringBuilder();
        int lastIndex = crossNodes.size() - 1;
        for(int i = 0; i < lastIndex; ++i) {
            Node node = crossNodes.get(i);
            string.append(node.toString());
            string.append(";");
        }
        string.append(crossNodes.get(lastIndex));
        return string.toString();
    }
   
    
    /**
     * this implements left child to left child
     * and right child to right child crossover.
     * Challenge: additionally implement left to 
     * right child and right to left child crossover.
     */
    public void crossover(GPTree tree, Random rand) {
        // find the points for crossover
        this.traverse();
        tree.traverse();
        int thisPoint = rand.nextInt(this.crossNodes.size());
        int treePoint = rand.nextInt(tree.crossNodes.size());
        boolean left = rand.nextBoolean();
        // get the connection points
        Node thisTrunk = crossNodes.get(thisPoint);
        Node treeTrunk = tree.crossNodes.get(treePoint);

        
        if(left) {
            thisTrunk.swapLeft(treeTrunk);
            
        } else {
            thisTrunk.swapRight(treeTrunk);
        }
        
    }

    GPTree() { 
        root = null; 
    }    
    
    public GPTree(NodeFactory n, int maxDepth, Random rand) {
        root = n.getOperator(rand);
        root.addRandomKids(n, maxDepth, rand);
    }
    
    public String toString() { 
        return root.toString(); 
    }
    
    public double eval(double[] data) { 
        return root.eval(data); 
    }
    
}
```

File 3: TestGPTree will be your test class:

```java
import java.util.*;

public class TestGPTree {
    static int numIndepVars = 3;
    static int maxDepth = 5;
    static Random rand = new Random();
    public static void main(String[] args) {
        double[] data = {3.14, 2.78, 1.0};
        Binop[] ops = {new Plus(), new Minus(), new Mult(), new Divide()};
        NodeFactory n = new NodeFactory(ops,numIndepVars);
        GPTree gpt1 = new GPTree(n, maxDepth, rand);
        System.out.println(gpt1 + " = " + gpt1.eval(data));
        GPTree gpt2 = new GPTree(n, maxDepth, rand);
        System.out.println(gpt2 + " = " + gpt2.eval(data));
        gpt1.crossover(gpt2,rand);
        System.out.println("After crossover");
        System.out.println(gpt1 + " = " + gpt1.eval(data));
        System.out.println(gpt2 + " = " + gpt2.eval(data));
    }
}
```

The `main()` method of the test class now tests the `GPTree` class as well as new versions using crossover,  and it is ready to be extended to compute a fitness and have real child `GPTree`s.

**Step 3** Implement the `collect()` method in `GPTree` so that any `Node` that is not a leaf is added to the `crossNodes` `ArrayList` for use in crossover.


**Step 4** Next, the `Node` class will need to add 4 methods:

    1. a `void traverse()` method that calls collect on itself and recursively `traverse()` through any child nodes. Traversal of a tree is defined as *visiting* each node of a tree. In our case *visiting* means having the collector collect the `Node` that is being traversed. 
    2. `boolean isLeaf()` should return true if it's a leaf node
    3. `void swapLeft(Node trunk)` should swap the nodes left child with the trunk's left child, 
    4. `void swapRight(Node trunk)`  should swap the nodes right child with the trunk's right child


## Node

In `Node`:

```java
/**
 * collect using preorder traversal.
 */
public void traverse(Collector c) {
    // collect this
    
    // traverse lChild
    
    // traverse rChild
    
}   
```

```java
/**
 * swap this left child node with trunk left child node
 */
public void swapLeft(Node trunk) {
    

}   
```

```java
/**
 * swap this right child node with trunk right child node
 */
public void swapRight(Node trunk) {
    

}   
```



```java
/**
 * return true if operation is s Unop
 */
public boolean isLeaf() {
    // return true if operation is a Unop

}   
```



**Step 5** And, finally, `Divide` will need to be changed to deal with potential `Infinity` values in `eval()`

## Divide

If you run your program many times, you might see that a GPtree that evaluates to Infinity, if a divisor in a Divide class object evaluates to zero.  To handle this case, modify the `Divide` class so that if the divisor is less than 0.0001 in absolute value, the numerical `eval()` method simply returns the value 1.0. I know this seems counter-intuitive, but just do it.


