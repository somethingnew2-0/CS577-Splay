/**
 * Instructions
 * insert this into java vm command line -ea -Xss100m (go to run configurations)
 */
package com.algorithms.splay;

import java.util.Random;

public class TreeComparator {

	private static boolean thirdTest;
	private static boolean speedUp;
	private final static int TEN_THOUSAND = 10000;
	private final static int HUNDRED_THOUSAND = 100000;
	private final static int MILLION = 1000000;
	private static Splay[] sTrees;
	private static RedBlack[] rbTrees;
	private static Random rand;
	private static long startTime;
	private static long endTime;
	private static int[] array;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		thirdTest = true;
		speedUp = false;

		sTrees = new Splay[3];
		rbTrees = new RedBlack[3];
		
		for (int i = 0; i < 3; i++) {
			sTrees[i] = new Splay();
			rbTrees[i] = new RedBlack();
		}
		
		rand = new Random(17);


		// generate array to hold items to insert
		if (thirdTest) {
			array = new int[MILLION];
			for (int i = 0; i < array.length; i++) {
				array[i] = rand.nextInt();
			}
		} else {
			array = new int[HUNDRED_THOUSAND];
			for (int i = 0; i < array.length; i++) {
				array[i] = rand.nextInt();
			}
		}

		// Random Insert
		long[][] randResults_splay = new long[3][4];
		long[][] randResults_rb = new long[3][4];
		randomInsertTest(randResults_splay, randResults_rb, 0);
		// in order lookup
		inOrderLookupTest(randResults_splay, randResults_rb, 1, false);
		// random lookup from array
		randomLookupTest(randResults_splay, randResults_rb, 2, false);
		// random lookup from rand
		randomNumberLookupTest(randResults_splay, randResults_rb, 3, false);
		
		// make the splay look good
		long[][] favorSplayResults_splay = new long[3][15];
		long[][] favorSplayResults_rb = new long[3][15];
		constantLookupTest(favorSplayResults_splay, favorSplayResults_rb, 0);
		windowLookupTest(favorSplayResults_splay, favorSplayResults_rb, 1000, 1);
		windowLookupTest(favorSplayResults_splay, favorSplayResults_rb, 100, 2);
		finiteRandomLookupTest(favorSplayResults_splay, favorSplayResults_rb, 100, 3);

		
		// In-Order Insert
		long[][] inOrderResults_splay = new long[3][4];
		long[][] inOrderResults_rb = new long[3][4];
		for (int i = 0; i < 3; i++) {		// clear trees
			sTrees[i] = new Splay();
			rbTrees[i] = new RedBlack();
		}
		inOrderInsertTest(inOrderResults_splay, inOrderResults_rb, 0);
		// in order lookup
		inOrderLookupTest(inOrderResults_splay, inOrderResults_rb, 1, true);
		// random lookup from array
		randomLookupTest(inOrderResults_splay, inOrderResults_rb, 2, speedUp);
		// random lookup from rand
		randomNumberLookupTest(inOrderResults_splay, inOrderResults_rb, 3, speedUp);
		inOrderWindowLookupTest(favorSplayResults_splay, favorSplayResults_rb, 100, 4);
		
		
		

		for (int i = 0; i < 3; i++) {
			System.out.println("insert splay [" + i + "]: " + randResults_splay[i][0]);
			System.out.println("insert rb [" + i + "]: " + randResults_rb[i][0]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("inorder splay [" + i + "]: " + randResults_splay[i][1]);
			System.out.println("inorder rb [" + i + "]: " + randResults_rb[i][1]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("randy array splay [" + i + "]: " + randResults_splay[i][2]);
			System.out.println("randy array rb [" + i + "]: " + randResults_rb[i][2]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("randy splay [" + i + "]: " + randResults_splay[i][3]);
			System.out.println("randy rb [" + i + "]: " + randResults_rb[i][3]);
		}
		System.out.println();
		
		for (int i = 0; i < 3; i++) {
			System.out.println("inorder insert splay [" + i + "]: " + inOrderResults_splay[i][0]);
			System.out.println("inorder insert rb [" + i + "]: " + inOrderResults_rb[i][0]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("inorder inorder splay [" + i + "]: " + inOrderResults_splay[i][1]);
			System.out.println("inorder inorder rb [" + i + "]: " + inOrderResults_rb[i][1]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("inorder rand from array splay [" + i + "]: " + inOrderResults_splay[i][2]);
			System.out.println("inorder rand from array rb [" + i + "]: " + inOrderResults_rb[i][2]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("inorder rand splay [" + i + "]: " + inOrderResults_splay[i][3]);
			System.out.println("inorder rand rb [" + i + "]: " + inOrderResults_rb[i][3]);
		}
		System.out.println();
		
		// favor splay
		System.out.println("constant splay: [2]: " + favorSplayResults_splay[2][0]);
		System.out.println("constant rb: [2]: " + favorSplayResults_rb[2][0]);
		
		for (int i = 0; i < 3; i++) {
			System.out.println("window[1000] splay: [2]: " + favorSplayResults_splay[i][1]);
			System.out.println("window[1000] rb: [2]: " + favorSplayResults_rb[i][1]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("window[100] splay: [" + i + "]: " + favorSplayResults_splay[i][2]);
			System.out.println("window[100] [" + i + "]: " + favorSplayResults_rb[i][2]);
		}	
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("finite[100] splay: [" + i + "]: " + favorSplayResults_splay[i][3]);
			System.out.println("finite[100] rb: [" + i + "]: " + favorSplayResults_rb[i][3]);
		}
		System.out.println();
		for (int i = 0; i < 3; i++) {
			System.out.println("actual window[100] splay: [" + i + "]: " + favorSplayResults_splay[i][4]);
			System.out.println("actual window[100] rb: [" + i + "]: " + favorSplayResults_rb[i][4]);
		}

	}

	// hundred thou lookups
	// generate a set of size size of random numbers.
	// randomly pick from the set as the array index for the number to look up
	private static void finiteRandomLookupTest(long[][] favorSplayResults_splay, long[][] favorSplayResults_rb, 
			int size, int col) {
				
		int[] randomNumberSet = new int[size];
		int[] randomNumbers = new int[HUNDRED_THOUSAND];
		
		for (int i = 0; i < 3; i++) {
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));
			
			// generate random numbers ahead of time
			for (int j = 0; j < size; j++) {
				// pick random indices
				randomNumberSet[j] = rand.nextInt(endingConstant);
			}
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				// now randomly choose numbers of out the random set
				randomNumbers[j] = randomNumberSet[rand.nextInt(size)];
			}			
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				sTrees[i].find(array[randomNumbers[j]]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_splay[i][col] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				rbTrees[i].find(array[randomNumbers[j]]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_rb[i][col] = endTime - startTime;

		}
	}
	
	// hundred thousand lookups
	// only looks up numbers from a small set of array indices, chosen at random
	private static void windowLookupTest(long[][] favorSplayResults_splay, long[][] favorSplayResults_rb, 
			int window, int col) {
		
		int[] randomNumbers = new int[HUNDRED_THOUSAND];
			
		for (int i = 0; i < 3; i++) {
			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));

			// pick start of interval
			final int startOfInterval = rand.nextInt(endingConstant - window);
			// generate random numbers ahead of time
			for (int j = 0; j < randomNumbers.length; j++) {
				// define window as going from randomNum to randomNum+window
				randomNumbers[j] = rand.nextInt(window) + startOfInterval;
			}
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < randomNumbers.length; j++) {
				sTrees[i].find(array[randomNumbers[j]]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_splay[i][col] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < randomNumbers.length; j++) {
				rbTrees[i].find(array[randomNumbers[j]]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_rb[i][col] = endTime - startTime;
		}
	}
	
	// hundred thousand lookups
	// use inorder for this one
	private static void inOrderWindowLookupTest(long[][] favorSplayResults_splay, long[][] favorSplayResults_rb, 
			int window, int col) {
		int[] randomNumbers = new int[HUNDRED_THOUSAND];
		

		for (int i = 0; i < 3; i++) {

			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));

			// pick start of interval
			final int startOfInterval = rand.nextInt(endingConstant - window);
			// generate random numbers ahead of time
			for (int j = 0; j < randomNumbers.length; j++) {
				// define window as going from randomNum to randomNum+window
				randomNumbers[j] = rand.nextInt(window) + startOfInterval;
			}

			startTime = System.currentTimeMillis();
			for (int j = 0; j < randomNumbers.length; j++) {
				sTrees[i].find(randomNumbers[j]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_splay[i][col] = endTime - startTime;

			startTime = System.currentTimeMillis();
			for (int j = 0; j < randomNumbers.length; j++) {
				rbTrees[i].find(randomNumbers[j]);
			}
			endTime = System.currentTimeMillis();
			favorSplayResults_rb[i][col] = endTime - startTime;
		}
		
	}
	

	// hundred thousand lookups of some number in the trees, for largest trees only
	private static void constantLookupTest(long[][] favorSplayResults_splay, long[][] favorSplayResults_rb, int col) {
		startTime = System.currentTimeMillis();
		for (int i = 0; i < HUNDRED_THOUSAND / 3; i++) {
			sTrees[2].find(array[15]); // 15 arbitrary
		}
		for (int i = 0; i < HUNDRED_THOUSAND / 3; i++) {
			sTrees[2].find(array[56]); // index arbitrary
		}
		for (int i = 0; i < HUNDRED_THOUSAND / 3 + 1; i++) {
			sTrees[2].find(array[TEN_THOUSAND]); // index arbitrary
		}
		endTime = System.currentTimeMillis();
		favorSplayResults_splay[2][col] = endTime - startTime;
		
		startTime = System.currentTimeMillis();
		for (int i = 0; i < HUNDRED_THOUSAND / 3; i++) {
			rbTrees[2].find(array[15]); // 15 arbitrary
		}
		for (int i = 0; i < HUNDRED_THOUSAND / 3; i++) {
			rbTrees[2].find(array[56]); // index arbitrary
		}
		for (int i = 0; i < HUNDRED_THOUSAND / 3 + 1; i++) {
			rbTrees[2].find(array[TEN_THOUSAND]); // index arbitrary
		}
		endTime = System.currentTimeMillis();
		favorSplayResults_rb[2][col] = endTime - startTime;

	}
	
	// insert a million into 0, 10 mil into 1, and 100 mil into 2 (I CHANGED THIS)
	private static void randomInsertTest(long[][] randResults_splay, long[][] randResults_rb, int col) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));

			startTime = System.currentTimeMillis();
			for (int j = 0; j < endingConstant; j++) {
				sTrees[i].insert(array[j]);
			}
			endTime = System.currentTimeMillis();
			randResults_splay[i][col] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < endingConstant; j++) {
				rbTrees[i].insert(array[j]);
			}
			endTime = System.currentTimeMillis();
			randResults_rb[i][col] = endTime - startTime;
		}
	}
	
	// insert 0, 1, 2...
	public static void inOrderInsertTest(long[][] inOrderResults_splay, long[][] inOrderResults_rb, int col) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));
			
			if (i == 0 || (i == 1 && !speedUp)) {
			startTime = System.currentTimeMillis();
				for (int j = 0; j < endingConstant; j++) {
					sTrees[i].insert(j);
				}
				endTime = System.currentTimeMillis();
				inOrderResults_splay[i][col] = endTime - startTime;
			} else {
				sTrees[i].insert(0); // prevents tree from crashing on lookup
				inOrderResults_splay[i][col] = -1;
			}
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < endingConstant; j++) {
				rbTrees[i].insert(j);
			}
			endTime = System.currentTimeMillis();
			inOrderResults_rb[i][col] = endTime - startTime;
		}
	}
	
	// lookup everything in order of insertion
	private static void inOrderLookupTest(long[][] randResults_splay, long[][] randResults_rb, int col, boolean forInOrder) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));
			
			if (!forInOrder) {
				startTime = System.currentTimeMillis();
				for (int j = 0; j < endingConstant; j++) {
					sTrees[i].find(array[j]);
				}
				endTime = System.currentTimeMillis();
				randResults_splay[i][col] = endTime - startTime;

				startTime = System.currentTimeMillis();
				for (int j = 0; j < endingConstant; j++) {
					rbTrees[i].find(array[j]);
				}
				endTime = System.currentTimeMillis();
				randResults_rb[i][col] = endTime - startTime;
			} else {
				startTime = System.currentTimeMillis();
				for (int j = 0; j < endingConstant; j++) {
					sTrees[i].find(j);
				}
				endTime = System.currentTimeMillis();
				randResults_splay[i][col] = endTime - startTime;

				startTime = System.currentTimeMillis();
				for (int j = 0; j < endingConstant; j++) {
					rbTrees[i].find(j);
				}
				endTime = System.currentTimeMillis();
				randResults_rb[i][col] = endTime - startTime;
			}
		}
	}
	
	// lookup 100,000 things at random from array
	public static void randomLookupTest(long[][] randResults_splay, long[][] randResults_rb, 
			int col, boolean skipForSplay) {
		for (int i = 0; i < 3; i++) {

			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i)) - 1;

			int[] randomNumbers = new int[HUNDRED_THOUSAND];

			// generate random numbers ahead of time
			for (int j = 0; j < randomNumbers.length; j++) {
				// define window as going from randomNum to randomNum+window
				randomNumbers[j] = rand.nextInt(endingConstant);
			}
			
			if (!skipForSplay || i != 2) {
				startTime = System.currentTimeMillis();
				for (int j = 0; j < HUNDRED_THOUSAND; j++) {
					sTrees[i].find(array[randomNumbers[j]]);
				}
				endTime = System.currentTimeMillis();
				randResults_splay[i][col] = endTime - startTime;
			} else {
				randResults_splay[i][col] = -1;
			}
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				rbTrees[i].find(array[randomNumbers[j]]);
			}
			endTime = System.currentTimeMillis();
			randResults_rb[i][col] = endTime - startTime;
		}
	}
	
	// lookup 100,000 random numbers
	public static void randomNumberLookupTest(long[][] randResults_splay, long[][] randResults_rb, 
			int col, boolean skipForSplay) {
		for (int i = 0; i < 3; i++) {
			int[] randomNumbers = new int[HUNDRED_THOUSAND];

			// generate random numbers ahead of time
			for (int j = 0; j < randomNumbers.length; j++) {
				// define window as going from randomNum to randomNum+window
				randomNumbers[j] = rand.nextInt();
			}
			
			
			if (!skipForSplay || i != 2) {
				startTime = System.currentTimeMillis();
				for (int j = 0; j < HUNDRED_THOUSAND; j++) {
					sTrees[i].find(randomNumbers[j]);
				}
				endTime = System.currentTimeMillis();
				randResults_splay[i][col] = endTime - startTime;
			} else {
				randResults_splay[i][col] = -1;
			}
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				rbTrees[i].find(randomNumbers[j]);
			}
			endTime = System.currentTimeMillis();
			randResults_rb[i][col] = endTime - startTime;
		}
	}
	
}
