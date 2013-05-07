package com.algorithms.splay;



public class RedBlack {
	RedBlackNode root;
	final boolean BLACK = true;
	final boolean RED = false;
	final int FAKE = 1;
	
	public RedBlack() {
		root = null;
	}
	
	// returns null if not there
	private RedBlackNode findParent(int value) {
		RedBlackNode tmp = root;
		RedBlackNode prevPtr = null;
		while (!tmp.isFake()) {
			prevPtr = tmp;
			if (value < tmp.getValue())
				tmp = (RedBlackNode) tmp.getLeft();
			else
				tmp = (RedBlackNode) tmp.getRight();
		}
		return prevPtr;
	}
	
	// returns a node if value found, else returns null
	// if nothing has been inserted then this crashes
	public RedBlackNode find(int value) {
		return find(value, root);
	}
	
	private	RedBlackNode find(int value, RedBlackNode node) {
		if (node.getValue() == value) {
			return node;
		} else if (value < node.getValue()) {
			if (!((RedBlackNode) node.getLeft()).isFake()) {
				return find(value, (RedBlackNode) node.getLeft());
			}
		} else if (value > node.getValue()) {
			if (!((RedBlackNode) node.getRight()).isFake()) {
				return find(value, (RedBlackNode) node.getRight());
			}
		}
		
		// value not in tree
		return null;
	}
	
	public void insert(int value) {		
		// Case 1
		if (root == null) {
			root = new RedBlackNode(BLACK);
			root.setValue(value);
			//insert fake leaves
			root.setRight(new RedBlackNode(true, true));
			root.setLeft(new RedBlackNode(true, true));
			return;
		}
			
		// insert node as red
		RedBlackNode tmp = findParent(value);
		RedBlackNode child = new RedBlackNode(RED);
		child.setValue(value);
		child.setParent(tmp);
		if (value < tmp.getValue()) {
			tmp.setLeft(child);
		}
		else {
			tmp.setRight(child);
		}
		
		//insert fake leaves
		child.setRight(new RedBlackNode(true, true));
		child.setLeft(new RedBlackNode(true, true));
		
		RedBlackNode x = child;
		while ( (x != root) && (((RedBlackNode) x.getParent()).getColor() == RED)) {
			if (x.getParent().isLeft()) {
				RedBlackNode gParent = grandparent(x);
				RedBlackNode uncle = (RedBlackNode) gParent.getRight();
				if (uncle.getColor() == RED) {
					((RedBlackNode) child.getParent()).setBlack();
					uncle.setBlack();
					gParent.setRed();
					x = gParent; // move x up the tree
				}
				else { 
					if (x.isRight()) {
						// move x up and rotate
						x = (RedBlackNode) x.getParent();
						rotate_left(x);
					}
					((RedBlackNode) x.getParent()).setBlack();
					grandparent(x).setRed();
					rotate_right(grandparent(x));
				}
			}
			else { // parent is right child
				RedBlackNode gParent = grandparent(x);
				RedBlackNode uncle = (RedBlackNode) gParent.getLeft();
				if (uncle.getColor() == RED) {
					((RedBlackNode) child.getParent()).setBlack();
					uncle.setBlack();
					gParent.setRed();
					x = gParent; // move x up the tree
				}
				else { 
					if (x.isLeft()) {
						// move x up and rotate
						x = (RedBlackNode) x.getParent();
						rotate_right(x);
					}
					((RedBlackNode) x.getParent()).setBlack();
					grandparent(x).setRed();
					rotate_left(grandparent(x));
				}
			}
		}
		root.setBlack();
	}

	private void rotate_right(RedBlackNode x) {
		RedBlackNode y = (RedBlackNode) x.getLeft();
		x.setLeft(y.getRight());
		if (!((RedBlackNode) y.getRight()).isFake()) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null)
			root = y;
		else {
			if (x.isRight()) {
				x.getParent().setRight(y);
			}
			else
				x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);
	}
	
	private void rotate_left(RedBlackNode x) {
		RedBlackNode y = (RedBlackNode) x.getRight();
		x.setRight(y.getLeft());
		if (!((RedBlackNode) y.getLeft()).isFake()) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == null)
			root = y;
		else {
			if (x.isLeft()) {
				x.getParent().setLeft(y);
			}
			else
				x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}
	
	private RedBlackNode grandparent(RedBlackNode n) {
		if ((n != null) && (n.getParent() != null))
			return (RedBlackNode) n.getParent().getParent();
		else 
			return null;
	}
	
/*	public static void main(String[] args) {
		final int NUM_INSERTS = 150;

		int[] array = new int[NUM_INSERTS];

		CopyOfRedBlack_FINAL T = new CopyOfRedBlack_FINAL();
		Random rand = new Random();
		int toPrint;
		for (int i = 0; i < NUM_INSERTS; i++) {
			toPrint = rand.nextInt(NUM_INSERTS * 10);
			T.insert(toPrint);
			array[i] = toPrint;
			System.out.print(toPrint + " ");
			// T.DFS();
			T.byLevel();
		}
		System.out.println();
		for (int i = 0; i < NUM_INSERTS; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < NUM_INSERTS; i++) {
			RedBlackNode tmp = T.find(array[i]);
			if (tmp == null)
				System.out.print("xx ");
			else
				System.out.print(tmp.getValue() + " ");
			// T.find(rand.nextInt(NUM_INSERTS * 10));
		}

		System.out.println("done");
	}

	public void byLevel() {
		Queue<RedBlackNode> level = new LinkedList<RedBlackNode>();
		level.add(root);
		System.out.println();
		System.out.print("byLevel: ");
		while (!level.isEmpty()) {
			RedBlackNode node = level.poll();
			char c = node.getColor() ? 'b' : 'r';
			System.out.print(node.getValue() + "" + c + " ");
			if (!node.getLeft().isFake())
				level.add(node.getLeft());
			if (!node.getRight().isFake())
				level.add(node.getRight());
		}
		System.out.println();
	}

	public void DFS() {
		System.out.println();
		System.out.print("Tree Printout: ");
		search(root);
		System.out.println();
	}

	private void search(RedBlackNode n) {
		if (n.isFake())
			return;
		char c = n.getColor() ? 'b' : 'r';
		System.out.print(n.getValue() + c + " ");
		search(n.getLeft());
		search(n.getRight());
	}*/
	
}

