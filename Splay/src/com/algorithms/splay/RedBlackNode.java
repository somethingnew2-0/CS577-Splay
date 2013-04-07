package com.algorithms.splay;

public class RedBlackNode extends Node {
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