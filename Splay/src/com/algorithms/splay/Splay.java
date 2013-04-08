package com.algorithms.splay;

public class Splay {

	private SplayNode root;
	
	public Splay() {
//		root = new SplayNode(0);
	}
	
	public SplayNode insert(int value) {
		if(root == null) {
			root = new SplayNode(value);
			return root;
		} else {
			return insert(value, root);
		}
	}
	
	private SplayNode insert(int value, SplayNode node) {
		if(node.getValue() == value) {
			splay(node);
			return node;
		} else if (value < node.getValue()) {
			if(node.getLeft() == null) {
				node.setLeft(new SplayNode(value));
				return (SplayNode)node.getLeft();
			}
			else {
				return insert(value, (SplayNode)node.getLeft());
			}
		} else {
			if(node.getRight() == null) {
				node.setRight(new SplayNode(value));
				return (SplayNode)node.getRight();
			}
			else {
				return insert(value, (SplayNode)node.getRight());
			}
		}
	}
	
	public SplayNode find(int value) {
		return find(value, root);
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
		if(node.isLeft()) {
			parent.setLeft((SplayNode)node.getRight());
			node.setRight(parent);			
		}
		else {
			parent.setRight((SplayNode)node.getLeft());
			node.setLeft(parent);
		}
		setRoot(node);
	}
	
	private void zigZig(SplayNode node) {
		SplayNode parent = (SplayNode)node.getParent();
		SplayNode grandparent = (SplayNode)parent.getParent();
		if(parent.isLeft()) {
			parentCurrentNode(node, grandparent);

			grandparent.setLeft((SplayNode)parent.getRight());
			parent.setLeft((SplayNode)node.getRight());

			parent.setRight(grandparent);
			node.setRight(parent);
		}
		else {
			parentCurrentNode(node, grandparent);
			
			grandparent.setRight((SplayNode)parent.getLeft());
			parent.setRight((SplayNode)node.getLeft());

			parent.setLeft(grandparent);
			node.setLeft(parent);
		}
	}
	
	private void zigZag(SplayNode node) {
		SplayNode parent = (SplayNode)node.getParent();
		SplayNode grandparent = (SplayNode)parent.getParent();
		if(parent.isLeft()) {
			parentCurrentNode(node, grandparent);
			
			grandparent.setLeft((SplayNode)node.getRight());
			parent.setRight((SplayNode)node.getLeft());
			
			node.setRight(grandparent);
			node.setLeft(parent);			
		} else {
			parentCurrentNode(node, grandparent);

			grandparent.setRight((SplayNode)node.getLeft());
			parent.setLeft((SplayNode)node.getRight());
			
			node.setLeft(grandparent);
			node.setRight(parent);
		}
	}
	
	private void parentCurrentNode(SplayNode node, SplayNode grandparent) {
		if(grandparent.isRoot()) {
			setRoot(node);
		} else {
			if(grandparent.isLeft()) {
				((SplayNode)grandparent.getParent()).setLeft(node);
			} else {
				((SplayNode)grandparent.getParent()).setRight(node);
			}
		}
	}
	
	private void setRoot(SplayNode node) {
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
			SplayNode node = splay.find(i);
			System.out.println(node.getValue());
		}
		
	}

}
