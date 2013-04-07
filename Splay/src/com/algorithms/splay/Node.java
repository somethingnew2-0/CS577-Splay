package com.algorithms.splay;

public class Node {
	private int value;
	private Node parent, leftChild, rightChild;
	private boolean left;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return leftChild;
	}

	public void setLeft(Node left) {
		this.leftChild = left;
		left.setLeft();
		left.setParent(this);
	}

	public Node getRight() {
		return rightChild;
	}

	public void setRight(Node right) {
		this.rightChild = right;
		right.setRight();
		right.setParent(this);
	}
	
	public boolean isRoot() {
		return parent == null;
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
	
	public Node(int value) { 
		this.value = value; 
	}
	
	public Node() {}
	
	public Node(int value, Node parent, boolean isLeftChild) {
		this(value);
		this.parent = parent;
		this.left = isLeftChild;
	}

}
