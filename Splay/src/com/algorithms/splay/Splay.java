package com.algorithms.splay;

public class Splay {

	private static Node ROOT;
	
	private void splay(Node node) {
		while(!node.isRoot()) {
			if(node.getParent().isRoot()) {
				zig(node);
			}
			else if(node.getParent().isLeft() && node.isLeft()) {
				zigZig(node);
			}
			else if(node.getParent().isRight() && node.isRight()) {
				zigZig(node);
			}
			else {
				zigZag(node);
			}
			
		}
	}
	
	private void zig(Node node) {
		Node root = node.getParent();
		node.setParent(null);
		ROOT = node;
		if(node.isLeft()) {
			root.setLeft(node.getRight());
			node.setRight(root);			
		}
		else {
			root.setRight(node.getLeft());
			node.setLeft(root);
		}
	}
	
	private void zigZig(Node node) {
		Node parent = node.getParent();
		Node grandparent = parent.getParent();
		if(grandparent.isRoot()) {
			node.setParent(null);
			ROOT = node;
		}
		if(parent.isLeft()) {
			grandparent.setLeft(parent.getRight());
			parent.setLeft(node.getRight());
			parent.setRight(grandparent);
			node.setRight(parent);
		}
		else {
			grandparent.setRight(parent.getLeft());
			parent.setRight(node.getLeft());
			parent.setLeft(grandparent);
			node.setLeft(parent);
		}
	}
	
	private void zigZag(Node node) {
		Node parent = node.getParent();
		Node grandparent = parent.getParent();
		if(grandparent.isRoot()) {
			node.setParent(null);
			ROOT = node;
		}
		if(parent.isLeft()) {
			
		}
		else {
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ROOT = new Node();
	}

}
