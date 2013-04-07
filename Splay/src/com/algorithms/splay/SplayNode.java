package com.algorithms.splay;

public class SplayNode extends Node {
	private boolean left;
	
	public SplayNode(int value) {
		super(value);
	}
	
	public SplayNode(int value, Node parent, boolean isLeftChild) {
		super(value, parent);
		this.left = isLeftChild;
	}
	
	public void setLeft(SplayNode left) {
		super.setLeft(left);
		left.setLeft();
	}

	public void setRight(SplayNode right) {
		super.setRight(right);
		right.setRight();
	}
	
	public boolean isLeft() {
		if(!isRoot()) {
			return left;
		}
		return false;
	}
	
	public void setLeft() {
		this.left = true;
	}
	
	public boolean isRight() {
		if(!isRoot()) {
			return !left;
		}
		return false;
	}
	
	public void setRight() {
		this.left = false;
	}
}
