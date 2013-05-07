package com.algorithms.splay;

public class OptimalBSTHelper {
	/* A Dynamic Programming based function that calculates minimum cost of
	   a Binary Search Tree. */
	public static int optimalSearchTree(int keys[], int freq[])
	{
	    /* Create an auxiliary 2D matrix to store results of subproblems */
	    int cost[][] = new int[keys.length][keys.length];
	 
	    /* cost[i][j] = Optimal cost of binary search tree that can be
	       formed from keys[i] to keys[j].
	       cost[0][n-1] will store the resultant cost */
	 
	    // For a single key, cost is equal to frequency of the key
	    for (int i = 0; i < keys.length; i++)
	        cost[i][i] = freq[i];
	 
	    // Now we need to consider chains of length 2, 3, ... .
	    // L is chain length.
	    for (int L=2; L<=keys.length; L++)
	    {
	        // i is row number in cost[][]
	        for (int i=0; i<=keys.length-L; i++)
	        {
	            // Get column number j from row number i and chain length L
	            int j = i+L-1;
	            cost[i][j] = Integer.MAX_VALUE;
	 
	            // Try making all keys in interval keys[i..j] as root
	            for (int r=i; r<=j; r++)
	            {
	               // c = cost when keys[r] becomes root of this subtree
	               int c = ((r > i)? cost[i][r-1]:0) + 
	                       ((r < j)? cost[r+1][j]:0) + 
	                       sum(freq, i, j);
	               if (c < cost[i][j])
	                  cost[i][j] = c;
	            }
	        }
	    }
	    return cost[0][keys.length-1];
	}
	 
	// A utility function to get sum of array elements freq[i] to freq[j]
	private static int sum(int freq[], int i, int j)
	{
	    int s = 0;
	    for (int k = i; k <=j; k++) {
	    	System.out.println(i + " " + j);
	    	s += freq[k];
	    }
	    return s;
	}
	
	public static void main(String[] args) {
		int keys[] = {10, 12, 20};
	    int freq[] = {34, 8, 50};
	    System.out.println("Cost of Optimal BST is " + optimalSearchTree(keys, freq));
	}
}
