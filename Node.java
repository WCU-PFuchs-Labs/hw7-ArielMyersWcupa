import java.util.Random;
/**
 * Code Author: Ariel Myers
 * Purpose: A Binary Tree class for Arithmetic evaluation
 */
public class Node implements Cloneable {
    private Node left, right;
    private Op operation;
    protected int depth = 0;

    //Constructors//
    public Node(Binop op) {
        this.operation = op;
    }

    public Node(Unop op) {
        this.operation = op;
    }

    public Op getOp() {
        return operation;
    }
    //RandomKids algorithm//
    public void addRandomKids(NodeFactory nf, int maxDepth, Random rand) {
        if (operation instanceof Unop) {
            return; 
        }

        if (depth >= maxDepth) {
            left = nf.getTerminal(rand);
            left.depth = depth + 1;

            right = nf.getTerminal(rand);
            right.depth = depth + 1;
            return;
        }

        int total = nf.getNumOps() + nf.getNumIndepVars();
        int r = rand.nextInt(total + 1);

        left = (r < nf.getNumOps()) ? nf.getOperator(rand) : nf.getTerminal(rand);
        left.depth = depth + 1;
        left.addRandomKids(nf, maxDepth, rand);

        r = rand.nextInt(total + 1);
        right = (r < nf.getNumOps()) ? nf.getOperator(rand) : nf.getTerminal(rand);
        right.depth = depth + 1;
        right.addRandomKids(nf, maxDepth, rand);
    }
