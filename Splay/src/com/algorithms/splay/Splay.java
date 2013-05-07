package com.algorithms.splay;

public class Splay {

	private Node root;
	private static int operations;

	public Splay() {
		operations = 0;
	}
	
	public int getOperations() {
		return operations;
	}
	
	public Node insert(int value) {
		if(root == null) {
			root = new Node(value);
			return root;
		} else {
			root = insert(value, root);
			return root;
		}
	}
	
	private static Node insert(int value, Node node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() == null) {
				Node newNode = new Node(value);
				node.setLeft(newNode);
				splay(newNode);
				return newNode;
			}
			else {
				return insert(value, node.getLeft());
			}
		} else {
			if(node.getRight() == null) {
				Node newNode = new Node(value);
				node.setRight(newNode);
				splay(newNode);
				return newNode;
			}
			else {
				return insert(value, node.getRight());
			}
		}
	}
	
	public Node find(int value) {
		root = find(value, root);
		return root;
	}
	
	private static Node find(int value, Node node) {
		operations++;
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
	
	private static void splay(Node node) {
		while(!node.isRoot()) {
			operations++;
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
	
	private static void zig(Node node) {
		Node parent = node.getParent();
		if(node.isLeft()) {
			parent.setLeft(node.getRight());
			node.setRight(parent);			
		}
		else {
			parent.setRight(node.getLeft());
			node.setLeft(parent);
		}
		node.setParent(null);
	}
	
	private static void zigZig(Node node) {
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
	
	private static void zigZag(Node node) {
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
	
	private static void parentCurrentNode(Node node, Node grandparent) {
		if(grandparent.isRoot()) {
			node.setParent(null);
		} else {
			if(grandparent.isLeft()) {
				grandparent.getParent().setLeft(node);
			} else {
				grandparent.getParent().setRight(node);
			}
		}
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
