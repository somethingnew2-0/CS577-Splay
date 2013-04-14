package com.algorithms.splay;

public class RedBlackNode {
	boolean black;
	private int value;
	private RedBlackNode parent, leftChild, rightChild;
	
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public RedBlackNode getParent() {
		return parent;
	}

	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}

	public RedBlackNode getLeft() {
		return leftChild;
	}

	public void setLeft(RedBlackNode left) {
		this.leftChild = left;
		if(left != null) {
			left.setParent(this);
		}
	}

	public RedBlackNode getRight() {
		return rightChild;
	}

	public void setRight(RedBlackNode right) {
		this.rightChild = right;
		if(right != null) {
			right.setParent(this);
		}
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public boolean getColor() {
		return black;
	}
	
}