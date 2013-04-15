package com.algorithms.splay;

import java.util.Random;

public class RedBlack {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	
	public RedBlack() {
		root = null;
	}
	
	// returns null if not there
	public RedBlackNode findParent(int value) {
		RedBlackNode tmp = root;
		RedBlackNode prevPtr = null;
		while (tmp != null) {
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
			if (node.getLeft() != null) {
				return find(value, node.getLeft());
			}
		} else if (value > node.getValue()) {
			if (node.getRight() != null) {
				return find(value, node.getRight());
			}
		}
		return null;
	}
	
	
	
	public void insert(int value) {		
		// Case 1
		if (root == null) {
			root = new RedBlackNode(BLACK);
			root.setValue(value);
			return;
		}
			
		// insert node as red
		RedBlackNode tmp = findParent(value);
		RedBlackNode child = new RedBlackNode(RED);
		child.setValue(value);
		child.setParent(tmp);
		if (value < tmp.getValue()) {
			assert(tmp.getLeft() == null);
			tmp.setLeft(child);
		}
		else {
			assert(tmp.getRight() == null);
			tmp.setRight(child);
		}
		
		insert_case2(child);		// this line makes it a Red-Black tree
	}
	
	private void insert_case1(RedBlackNode n) {
		if (n.getParent() == null)
			n.setBlack();
		else
			insert_case2(n);
	}
	
	private void insert_case2(RedBlackNode n) {
		if (n.getParent().getColor() == BLACK)
			return;
		else
			insert_case3(n);
	}
	
	private void insert_case3(RedBlackNode n) {
		RedBlackNode u = uncle(n);
		RedBlackNode g;
		if (u != null && u.getColor() == RED) {
			n.getParent().setBlack();
			u.setBlack();
			g = grandparent(n);
			g.setRed();
			insert_case1(g);
		}
		else
			insert_case4(n);
	}
	
	private void insert_case4(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		if (n.isRight() && n.getParent().isLeft()) {
			rotate_left(n.getParent());
			n = n.getLeft();
		}
		else if (n.isLeft() && n.getParent().isRight()) {
			rotate_right(n.getParent());
			n = n.getRight();
		}
		insert_case5(n);
	}
	
	private void insert_case5(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		n.getParent().setBlack();
		g.setRed();
		if (n.isLeft())
			rotate_right(g);
		else
			rotate_left(g);
		
	}
	
//	private void rotate_right(RedBlackNode n) {
//		RedBlackNode p = n.getParent();
//		if(p.isLeft()) {
//			p.setLeft(n.getRight());
//			n.setRight(p);			
//		}
//		else {
//			p.setRight(n.getLeft());
//			n.setLeft(p);
//		}
//		setRoot(n);
//	}
	
//	private void rotate_right(RedBlackNode n) {
//		RedBlackNode l = n.getLeft();
//		RedBlackNode r = n.getRight();
//		RedBlackNode p = n.getParent();
//		p.setLeft(r);
//		n.setRight(p);
//
//	}
	//TODO: inconsistency in two rotate functions (see != and ==)
	//http://www.chegg.com/homework-help/questions-and-answers/psedocode-right-rotation-red-black-tree-q642414
	//http://www.cs.duke.edu/~reif/courses/alglectures/skiena.lectures/lecture10.pdf
	//TODO: possible problem is parents not always getting set (they are null when they shouldn't be)
	private void rotate_right(RedBlackNode n) {
		RedBlackNode l = n.getLeft();
		n.setLeft(l.getRight());
		if (l.getRight() != null)
			l.getRight().setParent(n);
		l.setParent(n.getParent());
		if (n.getParent() == null)
			root = l;
		else if (n.isRight())
			n.getParent().setRight(l);
		else
			n.getParent().setLeft(l);
		l.setRight(n);
		n.setParent(l);
	}
	
	private void rotate_left(RedBlackNode n) {
		RedBlackNode r = n.getRight();
		n.setRight(r.getLeft());
		if (r.getLeft() != null)
			r.getLeft().setParent(n);
		r.setParent(n.getParent());
		if (n.getParent() == null)
			root = r;
		else if (n.isLeft())
			n.getParent().setLeft(r);
		else
			n.getParent().setRight(r);
		r.setRight(n);
		n.setParent(r);
	}
	
//	private void rotate_left(RedBlackNode n) {
//		RedBlackNode r = n.getRight();
//		RedBlackNode p = n.getParent();
//		p.setLeft(r);
//		n.setRight(r.getLeft());
//		r.setLeft(n);
//	}
	
	private RedBlackNode uncle(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		if (g == null)
			return g;
		if (n.getParent().isLeft())
			return g.getRight();
		else
			return g.getLeft();
	}
	
	private RedBlackNode grandparent(RedBlackNode n) {
		if ((n != null) && (n.getParent() != null))
			return n.getParent().getParent();
		else 
			return null;
	}
	
	public static void main(String[] args) {
		final int NUM_INSERTS = 20;
		final int NUM_LOOKUPS = 12;

		int[] array = new int[NUM_INSERTS];

		RedBlack T = new RedBlack();
		Random rand = new Random();
		int toPrint;
		for (int i = 0; i < NUM_INSERTS; i++) {
			toPrint = rand.nextInt(NUM_INSERTS * 10);
			T.insert(toPrint);
			array[i] = toPrint;
			System.out.print(toPrint + " ");
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

//		Splay S = new Splay();
//		for (int i = 0; i < NUM_INSERTS; i++) {
//			toPrint = rand.nextInt(NUM_INSERTS * 10);
//			S.insert(toPrint);
//			array[i] = toPrint;
//			System.out.print(toPrint + " ");
//		}
//		System.out.println();
//		for (int i = 0; i < NUM_INSERTS; i++) {
//			System.out.print(S.find2(array[i]).getValue() + " ");
//		}
	}
	
}

