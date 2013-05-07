package com.algorithms.splay;

public class Splay {

	private Node root;
	
	public Splay() {
//		root = new Node(0);
	}
	
	public Node insert(int value) {
		if(root == null) {
			root = new Node(value);
			return root;
		} else {
			return insert(value, root);
		}
	}
	
	private Node insert(int value, Node node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() == null) {
				node.setLeft(new Node(value));
				return node.getLeft();
			}
			else {
				return insert(value, node.getLeft());
			}
		} else {
			if(node.getRight() == null) {
				node.setRight(new Node(value));
				return node.getRight();
			}
			else {
				return insert(value, node.getRight());
			}
		}
	}
	
	public Node find(int value) {
		return find(value, root);
	}
	
	private Node find(int value, Node node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() != null) {
				return find(value, node.getLeft());
			}
		} else if (value > node.getValue()) {
			if(node.getRight() != null) {
				return find(value, node.getRight());
			}
		}
		return null;
	}
	
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
		Node parent = node.getParent();
		if(node.isLeft()) {
			parent.setLeft(node.getRight());
			node.setRight(parent);			
		}
		else {
			parent.setRight(node.getLeft());
			node.setLeft(parent);
		}
		setRoot(node);
	}
	
	private void zigZig(Node node) {
		Node parent = node.getParent();
		Node grandparent = parent.getParent();
		if(parent.isLeft()) {
			parentCurrentNode(node, grandparent);

			grandparent.setLeft(parent.getRight());
			parent.setLeft(node.getRight());

			parent.setRight(grandparent);
			node.setRight(parent);
		}
		else {
			parentCurrentNode(node, grandparent);
			
			grandparent.setRight(parent.getLeft());
			parent.setRight(node.getLeft());

			parent.setLeft(grandparent);
			node.setLeft(parent);
		}
	}
	
	private void zigZag(Node node) {
		Node parent = node.getParent();
		Node grandparent = parent.getParent();
		if(parent.isLeft()) {
			parentCurrentNode(node, grandparent);
			
			grandparent.setLeft(node.getRight());
			parent.setRight(node.getLeft());
			
			node.setRight(grandparent);
			node.setLeft(parent);			
		} else {
			parentCurrentNode(node, grandparent);

			grandparent.setRight(node.getLeft());
			parent.setLeft(node.getRight());
			
			node.setLeft(grandparent);
			node.setRight(parent);
		}
	}
	
	private void parentCurrentNode(Node node, Node grandparent) {
		if(grandparent.isRoot()) {
			setRoot(node);
		} else {
			if(grandparent.isLeft()) {
				grandparent.getParent().setLeft(node);
			} else {
				grandparent.getParent().setRight(node);
			}
		}
	}
	
	private void setRoot(Node node) {
		node.setParent(null);
		root = node;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Splay splay = new Splay();
		for (int i = 1; i <= 100; i++) {
			splay.insert(i);
		}
		for (int i = 100; i >= 1; i--) {
			Node node = splay.find(i);
			System.out.println(node.getValue());
		}
		
	}

}
