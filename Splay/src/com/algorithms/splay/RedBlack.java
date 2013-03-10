package com.algorithms.splay;

public class RedBlack<T> {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	
	public RedBlack() {
		root = new RedBlackNode(BLACK);
	}
	
	public void add(int value) {
		
	}
	
}

class RedBlackNode extends Node {
	boolean black;
	
	public void setBlack() {
		black = true;
	}
	
	public void setRed() {
		black = false;
	}
	
	RedBlackNode(boolean b) {
		black = b;
	}
}