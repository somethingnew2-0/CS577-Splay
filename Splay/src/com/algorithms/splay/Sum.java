package com.algorithms.splay;

public class Sum {
	
	private Node root;
	
	public Sum(int[] numbers) {
		Node[] baseNodes = new Node[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			baseNodes[i] = new Node(numbers[i]);
		}
		root = createTree(baseNodes);
	}
	
	private static Node createTree(Node[] nodes) {
		if(nodes.length == 1) {
			return nodes[0];
		} else {
			Node[] nextLevel = new Node[nodes.length - 1];
			for (int i = 0; i < nodes.length - 1; i++) {
				Node leftChild = nodes[i];
				Node rightChild = nodes[i+1];
				nextLevel[i] = new Node(leftChild.getValue() + rightChild.getValue());
				nextLevel[i].setLeft(leftChild);
				nextLevel[i].setRight(rightChild);
			}
			return createTree(nextLevel);
		}
	}
}
