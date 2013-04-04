package com.algorithms.splay;

public class RedBlack {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	
	public RedBlack() {
		root = new RedBlackNode(BLACK);
	}
	
	public void add(int value) {
		
		RedBlackNode tmp = search(value);
		if (value < tmp.getValue()) {
			assert(tmp.getLeft() == null);
			tmp.setLeft(new Node(value));
		}
		else {
			assert(tmp.getRight() == null);
			tmp.setRight(new Node(value));
		}
		// set color
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

class RedBlackNode extends Node {
	boolean black;
	
	public RedBlackNode(boolean b) {
		super();
		black = b;
	}
	
	public void setBlack() {
		black = true;
	}
	
	public void setRed() {
		black = false;
	}

}