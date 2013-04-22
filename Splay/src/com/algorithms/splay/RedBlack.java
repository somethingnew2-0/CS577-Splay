package com.algorithms.splay;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class RedBlack {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	final int FAKE = 1;
	
	public RedBlack() {
		root = null;
	}
	
	// returns null if not there
	public RedBlackNode findParent(int value) {
		RedBlackNode tmp = root;
		RedBlackNode prevPtr = null;
		while (!tmp.isFake()) {
			prevPtr = tmp;
			if (value < tmp.getValue())
				tmp = tmp.getLeft();
			else
				tmp = tmp.getRight();
		}
		return prevPtr;
	}
	
	public RedBlackNode find(int value) {
		return find(value, root);
	}
	
	private	RedBlackNode find(int value, RedBlackNode node) {
		if (node.getValue() == value) {
			return node;
		} else if (value < node.getValue()) {
			if (!node.getLeft().isFake()) {
				return find(value, node.getLeft());
			}
		} else if (value > node.getValue()) {
			if (!node.getRight().isFake()) {
				return find(value, node.getRight());
			}
		}
		assert(1 == 0);
		return null;
	}
	
	public void insert(int value) {		
		// Case 1
		if (root == null) {
			root = new RedBlackNode(BLACK);
			root.setValue(value);
			//insert fake leaves
			root.setRight(new RedBlackNode(FAKE));
			root.setLeft(new RedBlackNode(FAKE));
			return;
		}
			
		// insert node as red
		RedBlackNode tmp = findParent(value);
		RedBlackNode child = new RedBlackNode(RED);
		child.setValue(value);
		child.setParent(tmp);
		if (value < tmp.getValue()) {
			assert(tmp.getLeft().isFake());
			tmp.setLeft(child);
		}
		else {
			assert(tmp.getRight().isFake());
			tmp.setRight(child);
		}
		
		//insert fake leaves
		child.setRight(new RedBlackNode(FAKE));
		child.setLeft(new RedBlackNode(FAKE));
		
		RedBlackNode x = child;
		while ( (x != root) && (x.getParent().getColor() == RED)) {
			if (x.getParent().isLeft()) {
				RedBlackNode gParent = grandparent(x);
				RedBlackNode uncle = gParent.getRight();
				if (uncle.getColor() == RED) {
					child.getParent().setBlack();
					uncle.setBlack();
					gParent.setRed();
					x = gParent; // move x up the tree
				}
				else { 
					if (x.isRight()) {
						// move x up and rotate
						x = x.getParent();
						rotate_left(x);
					}
					x.getParent().setBlack();
					grandparent(x).setRed();
					rotate_right(grandparent(x));
				}
			}
			else { // parent is right child
				RedBlackNode gParent = grandparent(x);
				RedBlackNode uncle = gParent.getLeft();
				if (uncle.getColor() == RED) {
					child.getParent().setBlack();
					uncle.setBlack();
					gParent.setRed();
					x = gParent; // move x up the tree
				}
				else { 
					if (x.isLeft()) {
						// move x up and rotate
						x = x.getParent();
						rotate_right(x);
					}
					x.getParent().setBlack();
					grandparent(x).setRed();
					rotate_left(grandparent(x));
				}
			}
		}
		root.setBlack();
	}

	private void rotate_right(RedBlackNode x) {
		RedBlackNode y = x.getLeft();
		x.setLeft(y.getRight());
		if (!y.getRight().isFake()) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null)
			root = y;
		else {
			if (x.isRight()) {
				x.getParent().setRight(y);
			}
			else
				x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);
	}
	
	private void rotate_left(RedBlackNode x) {
		RedBlackNode y = x.getRight();
		x.setRight(y.getLeft());
		if (!y.getLeft().isFake()) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null)
			root = y;
		else {
			if (x.isLeft()) {
				x.getParent().setLeft(y);
			}
			else
				x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}
	
	private RedBlackNode grandparent(RedBlackNode n) {
		if ((n != null) && (n.getParent() != null))
			return n.getParent().getParent();
		else 
			return null;
	}
	
	public static void main(String[] args) {
		final int NUM_INSERTS = 150;

		int[] array = new int[NUM_INSERTS];

		CopyOfRedBlack_FINAL T = new CopyOfRedBlack_FINAL();
		Random rand = new Random();
		int toPrint;
		for (int i = 0; i < NUM_INSERTS; i++) {
			toPrint = rand.nextInt(NUM_INSERTS * 10);
			T.insert(toPrint);
			array[i] = toPrint;
			System.out.print(toPrint + " ");
		//	T.DFS();
			T.byLevel();
		}
		System.out.println();
		for (int i = 0; i < NUM_INSERTS; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < NUM_INSERTS; i++) {
			RedBlackNode tmp = T.find(array[i]);
			if (tmp == null)
				System.out.print("xx ");
			else
				System.out.print(tmp.getValue() +  " ");
			// T.find(rand.nextInt(NUM_INSERTS * 10));
		}
		
		System.out.println("done");
	}

	public void byLevel() {
		Queue<RedBlackNode> level = new LinkedList<RedBlackNode>();
		level.add(root);
		System.out.println();
		System.out.print("byLevel: ");
		while (!level.isEmpty()) {
			RedBlackNode node = level.poll();
			char c = node.getColor() ? 'b' : 'r';
			System.out.print(node.getValue() + "" + c + " ");
			if (!node.getLeft().isFake())
				level.add(node.getLeft());
			if (!node.getRight().isFake())
				level.add(node.getRight());
		}
		System.out.println();
	}

	public void DFS() {
		System.out.println();
		System.out.print("Tree Printout: ");
		search(root);
		System.out.println();
	}
	
	private void search(RedBlackNode n) {
		if (n.isFake())
			return;
		char c = n.getColor()? 'b':'r';
		System.out.print(n.getValue() + c + " ");
		search(n.getLeft());
		search(n.getRight());
	}
	
}

