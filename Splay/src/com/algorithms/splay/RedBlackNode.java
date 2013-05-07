package com.algorithms.splay;


public class RedBlackNode extends Node {
	boolean black, fake;
	private int value;
	
	public RedBlackNode(boolean black) {
		this.black = black;
	}
	
	public RedBlackNode(boolean black, boolean fake) {
		this(black);
		this.fake = fake;
	}
	
	public boolean isFake() {
		return fake;
	}
	
	public void setBlack() {
		black = true;
	}
	
	public void setRed() {
		black = false;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean getColor() {
		return black;
	}
	
}