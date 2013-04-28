/**
 * Instructions
 * insert this into java vm command line -ea -Xss100m (go to run configurations)
 */

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
	private static int randnum;
	private static long startTime;
	private static long endTime;
	private static int[] array;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		thirdTest = true;
		speedUp = true;

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
		RandomInsertTest(randResults_splay, randResults_rb, 0);
		// in order lookup
		InOrderLookupTest(randResults_splay, randResults_rb, 1);
		// random lookup from array
		RandomLookupTest(randResults_splay, randResults_rb, 2);
		// random lookup from rand
		RandomNumberLookupTest(randResults_splay, randResults_rb, 3);
		
		// In-Order Insert
		long[][] inOrderResults_splay = new long[3][4];
		long[][] inOrderResults_rb = new long[3][4];
		for (int i = 0; i < 3; i++) {		// clear trees
			sTrees[i] = new Splay();
			rbTrees[i] = new RedBlack();
		}
		InOrderInsertTest(inOrderResults_splay, inOrderResults_rb, 0);
		// in order lookup
		InOrderLookupTest(inOrderResults_splay, inOrderResults_rb, 1);
		// random lookup from array
		RandomLookupTest(inOrderResults_splay, inOrderResults_rb, 2);
		// random lookup from rand
		RandomNumberLookupTest(inOrderResults_splay, inOrderResults_rb, 3);
		

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
	}

	// insert a million into 0, 10 mil into 1, and 100 mil into 2 (I CHANGED THIS)
	private static void RandomInsertTest(long[][] randResults_splay, long[][] randResults_rb, int col) {
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
	public static void InOrderInsertTest(long[][] inOrderResults_splay, long[][] inOrderResults_rb, int col) {
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
	private static void InOrderLookupTest(long[][] randResults_splay, long[][] randResults_rb, int col) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			final int endingConstant = (int) (TEN_THOUSAND * Math.pow(10, i));
			
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
		}
	}
	
	// lookup 100,000 things at random from array
	public static void RandomLookupTest(long[][] randResults_splay, long[][] randResults_rb, int col) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			final int constant = (int) (TEN_THOUSAND * Math.pow(10, i)) - 1;
			randnum = rand.nextInt(constant);
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				sTrees[i].find(array[randnum]);
			}
			endTime = System.currentTimeMillis();
			randResults_splay[i][col] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				rbTrees[i].find(array[randnum]);
			}
			endTime = System.currentTimeMillis();
			randResults_rb[i][col] = endTime - startTime;
		}
	}
	
	// lookup 100,000 random numbers
	public static void RandomNumberLookupTest(long[][] randResults_splay, long[][] randResults_rb, int col) {
		for (int i = 0; i < 3; i++) {
			if (!thirdTest && i == 2)
				continue;
			
			randnum = rand.nextInt();
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				sTrees[i].find(randnum);
			}
			endTime = System.currentTimeMillis();
			randResults_splay[i][col] = endTime - startTime;
			
			startTime = System.currentTimeMillis();
			for (int j = 0; j < HUNDRED_THOUSAND; j++) {
				rbTrees[i].find(randnum);
			}
			endTime = System.currentTimeMillis();
			randResults_rb[i][col] = endTime - startTime;
		}
	}
	
}
