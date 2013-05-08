package com.algorithms.splay;

import java.util.Random;

public class TreeComparator {
	public static final int N = 250; 
	public static final int SAMPLES = 30;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		Splay splay;
		int[] keys = {};
		int[] freq = {};
		
		// Do in order lookup tests
		System.out.println("In Order Lookup Tests");
		System.out.format("%-8s%-8s%s\n", "N", "Splay", "OptimalBST");
		for (int n = 1; n <= N; n++) {
			splay = new Splay();
			keys = new int[n];
			freq = new int[n];
			for (int number = 0; number < n; number++) {
				keys[number] = number;
//				freq[number]++;
				splay.insert(number);
			}
			
			for (int lookup = 0; lookup < 100; lookup++) {
				for (int i = 0; i < n; i++) {
					freq[i]++;
					splay.find(i);
				}
			}
			
			System.out.format("%-8d%-8d%d\n", n, splay.getOperations(), OptimalBSTHelper.optimalSearchTree(keys, freq));
		}
		
		System.out.println("Distribution of Lookups for N = " + keys.length);
		for (int i = 0; i < keys.length; i++) {
			System.out.format("%-8d%d\n", keys[i], freq[i]);
		}
		
		// Do random tests
		System.out.println("Random Lookup Tests with Sampling");
		System.out.format("%-8s%-8s%s\n", "N", "SplayAvg", "OptimalBSTAvg");
		for (int n = 1; n <= N; n++) {
			int splayAvg = 0;
			int optimalBSTAvg = 0;
			for (int sample = 0; sample < SAMPLES; sample++) {
				splay = new Splay();
				keys = new int[n];
				freq = new int[n];
				for (int number = 0; number < n; number++) {
					keys[number] = number;
//					freq[number]++;
					splay.insert(number);
				}
				
				for (int lookup = 0; lookup < 100; lookup++) {
					for (int i = 0; i < n; i++) {
						int number = random.nextInt(n);
						freq[number]++;
						splay.find(number);
					}
				}
				splayAvg += splay.getOperations();
				optimalBSTAvg += OptimalBSTHelper.optimalSearchTree(keys, freq);
			}
			splayAvg /= SAMPLES;
			optimalBSTAvg /= SAMPLES;
			
			System.out.format("%-8d%-8d%d\n", n, splayAvg, optimalBSTAvg);
		}
		
		System.out.println("Distribution of Lookups for N = " + keys.length);
		for (int i = 0; i < keys.length; i++) {
			System.out.format("%-8d%d\n", keys[i], freq[i]);
		}
		
		// Do redundant lookup (high frequency tests)
		System.out.println("Redundant Lookup Tests");
		System.out.format("%-8s%-8s%s\n", "N", "Splay", "OptimalBST");
		for (int n = 1; n <= N; n++) {
			splay = new Splay();
			keys = new int[n];
			freq = new int[n];
			for (int number = 0; number < n; number++) {
				keys[number] = number;
//				freq[number]++;
				splay.insert(number);
			}
			
			for (int i = 0; i < n; i++) {
				for (int lookup = 0; lookup < 100; lookup++) {
					freq[i]++;
					splay.find(i);
				}
			}
			
			System.out.format("%-8d%-8d%d\n", n, splay.getOperations(), OptimalBSTHelper.optimalSearchTree(keys, freq));
		}
		
		System.out.println("Distribution of Lookups for N = " + keys.length);
		for (int i = 0; i < keys.length; i++) {
			System.out.format("%-8d%d\n", keys[i], freq[i]);
		}
	}

}
