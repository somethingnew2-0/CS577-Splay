package com.algorithms.splay;

public class Splay {

	private static Node root;
	
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
		
	}
	
	private void zigZig(Node node) {
		
	}
	
	private void zigZag(Node node) {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		root = new Node();
	}

}
