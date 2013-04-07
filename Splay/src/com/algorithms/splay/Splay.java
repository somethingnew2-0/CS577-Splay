package com.algorithms.splay;

public class Splay {

	private SplayNode root;
	
	public Splay() {
		root = new SplayNode(0);
	}
	
	public void insert(int value) {
		root = insert(value, root);
	}
	
	private SplayNode insert(int value, SplayNode node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() != null) {
				return insert(value, (SplayNode)node.getLeft());
			}
			else {
				node.setLeft(new SplayNode(value));
				return (SplayNode)node.getLeft();
			}
		} else {
			if(node.getRight() != null) {
				return insert(value, (SplayNode)node.getRight());
			}
			else {
				node.setRight(new SplayNode(value));
				return (SplayNode)node.getRight();
			}
		}
	}
	
	public SplayNode find(int value) {
		root = find(value, root);
		return root;
	}
	
	private SplayNode find(int value, SplayNode node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() != null) {
				return find(value, (SplayNode)node.getLeft());
			}
		} else if (value > node.getValue()) {
			if(node.getRight() != null) {
				return find(value, (SplayNode)node.getRight());
			}
		}
		return null;
	}
	
	private void splay(SplayNode node) {
		while(!node.isRoot()) {
			if(node.getParent().isRoot()) {
				zig(node);
			}
			else if(((SplayNode)node.getParent()).isLeft() && node.isLeft()) {
				zigZig(node);
			}
			else if(((SplayNode)node.getParent()).isRight() && node.isRight()) {
				zigZig(node);
			}
			else {
				zigZag(node);
			}
			
		}
	}
	
	private void zig(SplayNode node) {
		SplayNode parent = (SplayNode)node.getParent();
		node.setParent(null);
//		ROOT = node;
		if(node.isLeft()) {
			parent.setLeft(node.getRight());
			node.setRight(parent);			
		}
		else {
			parent.setRight(node.getLeft());
			node.setLeft(parent);
		}
	}
	
	private void zigZig(SplayNode node) {
		SplayNode parent = (SplayNode)node.getParent();
		SplayNode grandparent = (SplayNode)parent.getParent();
		if(grandparent.isRoot()) {
			node.setParent(null);
//			ROOT = node;
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
	
	private void zigZag(SplayNode node) {
		SplayNode parent = (SplayNode)node.getParent();
		SplayNode grandparent = (SplayNode)parent.getParent();
		if(grandparent.isRoot()) {
			node.setParent(null);
//			ROOT = node;
		}
		if(parent.isLeft()) {
			grandparent.setLeft(node.getRight());
			parent.setRight(node.getLeft());
			node.setRight(grandparent);
			node.setLeft(parent);			
		}
		else {
			grandparent.setRight(node.getLeft());
			parent.setLeft(node.getRight());
			node.setLeft(grandparent);
			node.setRight(parent);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Splay splay = new Splay();
		for (int i = 1; i < 100; i++) {
			splay.insert(i);
		}
		for (int i = 100; i >= 0; i--) {
			SplayNode node = splay.find(i);
			System.out.println(node.getValue());
		}
		
	}

}
