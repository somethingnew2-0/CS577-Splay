package com.algorithms.splay;

public class Node {
	private int value;
	private Node parent, leftChild, rightChild;

	public Node() {}
	
	public Node(int value) { 
		this.value = value; 
	}
	
	public Node(int value, Node parent) {
		this(value);
		this.parent = parent;
	}
	
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
		if(left != null) {
			left.setParent(this);
		}
	}

	public Node getRight() {
		return rightChild;
	}

	public void setRight(Node right) {
		this.rightChild = right;
		if(right != null) {
			right.setParent(this);
		}
	}
	
	public boolean isRoot() {
		return parent == null;
	}
}
