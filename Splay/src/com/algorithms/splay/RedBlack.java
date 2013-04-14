package com.algorithms.splay;

public class RedBlack {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	
	public RedBlack() {
		root = null;
	}
	
	public void insert(int value) {		
		// Case 1
//		if (root == null) {
//			root = new RedBlackNode(BLACK);
//			root.setValue(value);
//			return;
//		}
			
		// insert node as red
		RedBlackNode tmp = search(value);
		if (value < tmp.getValue()) {
			assert(tmp.getLeft() == null);
			tmp.setLeft(new RedBlackNode(RED));
		}
		else {
			assert(tmp.getRight() == null);
			tmp.setRight(new RedBlackNode(RED));
		}
		tmp.setValue(value);
		
		insert_case2(tmp);
		// set color
	}
	
	void insert_case1(RedBlackNode n) {
		if (n.getParent().getColor() == BLACK)
			n.setBlack();
		else
			insert_case2(n);
	}
	
	void insert_case2(RedBlackNode n) {
		if (n.getParent().getColor() == BLACK)
			return;
		else
			insert_case3(n);
	}
	
	void insert_case3(RedBlackNode n) {
		RedBlackNode u = uncle(n);
		RedBlackNode g;
		if ((u != null) && (u.getColor() == RED)) {
			n.getParent().setBlack();
			g = grandparent(n);
			g.setRed();
			insert_case1(g);
		}
		else
			insert_case4(n);
	}
	
	void insert_case4(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		if (n == n.getParent().getRight() && n.getParent() == g.getLeft()) {
			rotate_left(n.getParent())
			n = n.getLeft();
		}
		else if (n == n.getParent().getLeft() && n.getParent() == g.getRight()) {
			rotate_right(n.getParent());
			n = n.getRight();
		}
		insert_case5(n);
	}
	
	void insert_case5(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		n.getParent().setBlack();
		g.setRed();
		if (n == n.getParent().getLeft())
			rotate_right(g);
		else
			rotate_left(g);
		
	}
	
	RedBlackNode uncle(RedBlackNode n) {
		RedBlackNode g = grandparent(n);
		if (g == null)
			return g;
		if (n.getParent() == g.getLeft())
			return g.getRight();
		else
			return g.getLeft();
	}
	
	RedBlackNode grandparent(RedBlackNode n) {
		if ((n != null) && (n.getParent() != null))
			return n.getParent().getParent();
		else 
			return null;
	}
	
	private RedBlackNode search(int value) {
		RedBlackNode tmp = root;
		while (tmp != null) {
			if (value < tmp.getValue())
				tmp = (RedBlackNode) tmp.getLeft();
			else
				tmp = (RedBlackNode) tmp.getRight();
		}
		return tmp;
	}
}

