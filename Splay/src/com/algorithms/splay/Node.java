package com.algorithms.splay;

public class Node {
	private Node parent, leftChild, rightChild;
	private boolean left, right;

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
		this.right = false;
	}
	
	public boolean isRight() {
		if(!isRoot()) {
			return right;
		}
		return false;
	}
	
	public void setRight() {
		this.right = true;
		this.left = false;
	}
	
	public Node() { }
	
	public Node(Node parent, boolean isLeftChild) {
		this.parent = parent;
		this.left = isLeftChild;
		this.right = !isLeftChild;
	}

}
