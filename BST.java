/* BST
 * Amended for Optimizing Lab
 */
import java.util.*;
import java.lang.*;

public class BST{
    private int element;
    private BST left;
    private BST right;
    private double cost;
    private double sum;
    private double origProb;

    public BST(int elt, double cost){
	element = elt; left = null; right = null; this.cost = cost;
	origProb = cost; sum = cost;}
    
    public BST(BST other){ // make a deep copy of a BST
	element = other.getElement();
	sum = other.getSum();
	cost = other.getCost();
	origProb = other.getOrigProb();
	if (other.getLeft() != null)
	    left = new BST(other.getLeft());
	else left = null;
	if (other.getRight() != null)
	    right = new BST(other.getRight());
	else right = null;
    }

    // Binary node methods
    public BST getLeft() { return left; }
    public BST getRight() { return right; }
    public int getElement() { return element; }
    public double getCost() { return cost; }
    public double getSum() { return sum; }
    public double getOrigProb() { return origProb; }

    public boolean hasLeft() { return left != null; }
    public boolean hasRight() { return right != null; }
    public boolean hasChildren() { return hasLeft() || hasRight(); }

    public void setElement(int elt) { element = elt; }
    public void setLeft(BST l) { left = l; }
    public void setRight(BST r) { right = r; }

    public String toString() { return element + " : " + cost; }
    
    public void visit() { System.out.print(element + " "); }

    //Binary Tree methods
    //accessors
    public void inOrder() {
	if (hasLeft()) left.inOrder();
	this.visit();
	if (hasRight()) right.inOrder();
    }
    public void preOrder() {
	this.visit();
	if (hasLeft()) left.preOrder();
	if (hasRight()) right.preOrder();
    }
    public void postOrder() {
       	if (hasLeft()) left.postOrder();
	if (hasRight()) right.postOrder();
	this.visit();
    }

    /* Some BST methods removed for clarity's sake */
    /* methods for Optimizing Lab */
    public double updateCost(BST justAdded){
	double toAdd = justAdded.getCost() + justAdded.getSum();

	cost += toAdd;
	return cost;
    }


    public boolean addLeft(BST l){
	if (l.getElement() > element) return false;
	left = new BST(l);
	updateCost(left);
	sum += l.getSum();
	return true;
    }

    public boolean addRight(BST r){
	if (r.getElement() < element) return false;
	right = new BST(r);
	updateCost(right);
	sum += r.getSum();
	return true;
    }
    
}
