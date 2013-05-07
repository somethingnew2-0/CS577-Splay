package com.algorithms.splay;

public class SplayNode extends Node {
	public SplayNode(int value) {
		super(value);
	}
	
	public SplayNode(int value, Node parent, boolean isLeftChild) {
		super(value, parent);
	}
}
