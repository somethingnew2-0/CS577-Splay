package com.algorithms.splay;

public class Node {
	private Node parent, left, right;

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	public Node(Node parent) {
		this.parent = parent;
	}

}
