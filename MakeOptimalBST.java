//Brandon and Matt
import java.util.*;
import java.io.*;
public class MakeOptimalBST {
	
	static ArrayList<ArrayList<BST>> allOptimals;
	static int numKeys;
	
	public static ArrayList<BST> makeSingletons(ArrayList<Integer> keys, ArrayList<Double> probs){
		/* make the singleton BSTs out of the keys and probabilites arrays */
		int size = keys.size();
		ArrayList<BST> singletons = new ArrayList<BST>(size);
		for (int i = 0; i < size; i++) {
			singletons.add(new BST(keys.get(i), probs.get(i)));
		}
		return singletons;
	}

	
	private static BST getOneOptimal(int size, int startPos) { 
		/* gets the optimal tree containing size number of keys, 
		 * starting with the key at position startPos
		 * returns that tree which gets added to optimals list
		 */
		BST root;
		int leftSize;
		int rightSize;
		double cost;   
		double minCost = Integer.MAX_VALUE;
		BST optimalTree = null;
		
		for (int i = startPos; i < startPos + size; i++) {
			root = new BST(i, allOptimals.get(0).get(i).getCost());
			leftSize = i - startPos;
			rightSize = startPos + size - i - 1;
			if (leftSize != 0) {
				root.addLeft(allOptimals.get(leftSize - 1).get(startPos));
			}
			if (rightSize != 0) {
				root.addRight(allOptimals.get(rightSize - 1).get(i + 1));	
			}
			cost = root.getCost();
			if (cost < minCost) {
				minCost = cost;
				optimalTree = root;
			}	
		}
		return optimalTree;
	}
	
	private static ArrayList<BST> getAllOptOfSize(int size) { 
		/* gets all optimal of one size
		 * calling getOneOptimal at each valid start point
		 */
		ArrayList<BST> optimalsOfSize = new ArrayList<BST>();
		int i = 0;
		System.out.println("A");
		while ((i + size - 1) < numKeys) {
			optimalsOfSize.add(getOneOptimal(size, i));
			System.out.println("Size=" + size);
			System.out.print("Root=" + optimalsOfSize.get(i).getElement() + ": ");
			System.out.println("Cost=" + optimalsOfSize.get(i).getCost());
			System.out.println("\n");
			i++;
		}
		return optimalsOfSize;
	}
	
	
	public static BST getOptimalTree(ArrayList<Integer> keys, ArrayList<Double> probs) {
		/* gets the optimal tree by calling getAllOptOfSize for 
		 * each valid size including the final one 
		 * and returns the final tree containing all keys
		 */
		numKeys = keys.size();
		allOptimals = new ArrayList<ArrayList<BST>>(numKeys);
		ArrayList<BST> singletons = makeSingletons(keys, probs);
		allOptimals.add(singletons);
		
		for (int i = 2; i <= numKeys; i++) {
			allOptimals.add(getAllOptOfSize(i));
		}
		return allOptimals.get(numKeys - 1).get(0);
	}
	
	/* Do not alter */	
	private static ArrayList<Integer> readInKeys(String fileName){
		ArrayList<Integer> keys = new ArrayList<Integer>();
		try {
			String keyText = "";
			Scanner in = new Scanner(new File(fileName));
			keyText = in.nextLine();
			String[] keyStAr = keyText.split(", ");
			keyStAr[0] = keyStAr[0].substring(1);
			int numKeys = keyStAr.length;
			int len = keyStAr[numKeys - 1].length();
			keyStAr[numKeys - 1] = keyStAr[numKeys - 1].substring(0, len - 1);
			for (int i = 0; i < keyStAr.length; i++) {
				keys.add(Integer.valueOf(keyStAr[i]));
			}
			System.out.println(keys);
		}
		catch(Exception e) { System.out.println(e); }
		return keys;
	}
	/* Do not alter */	
	private static ArrayList<Double> readInProbs(String fileName){
		ArrayList<Double> probs = new ArrayList<Double>();
		try {
			String probText = "";
			Scanner in = new Scanner(new File(fileName));
			probText = in.nextLine();
			String[] probStAr = probText.split(", ");
			probStAr[0] = probStAr[0].substring(1);
			int numKeys = probStAr.length;
			int len = probStAr[numKeys - 1].length();
			probStAr[numKeys - 1] = probStAr[numKeys - 1].substring(0, len - 1);
			for (int i = 0; i < probStAr.length; i++) {
				probs.add(Double.valueOf(probStAr[i]));
			}
			System.out.println(probs);
		}
		catch(Exception e) { System.out.println(e); }
		return probs;
	}
			
	/* Do not alter */	
	public static void main(String[] args) {
		BST opt = getOptimalTree(readInKeys("Keys0.txt"), readInProbs("Probs0.txt"));
		opt.preOrder();
		System.out.println();
		System.out.println(opt.getCost());

	}
	

}
