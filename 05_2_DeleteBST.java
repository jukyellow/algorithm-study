package com.juk.algo.A0827;

import java.util.LinkedList;

public class DeleteBST {
	public static void main(String args[]) {
    	// root = [10,5,15,3,7,null,18]
		addNode(10);
		addNode(5);
		addNode(15);
		addNode(3);
		addNode(7);
		addNode(18);
		printInOrderTraversal(root); System.out.println("");
		printLevelOrderTraversal(root);
		
	    //BST(binary search tree)���� �� range���̿� ���� �� 
    	int result = new DeleteBST().rangeSumBST(root, 7, 15);
    	System.out.println("Range Sum result:"+result);
    	
    	//��� ����
    	System.out.println("\ndelete node:");
    	new DeleteBST().deleteNode(root, 5);
    	printLevelOrderTraversal(root);
    }
	
	private static TreeNode3 root = null;
	private static void addNode(int val) {
		if(root==null) {
			root = new TreeNode3(val);
			return ; 
		}

		TreeNode3 current = root;
		TreeNode3 parent = null;
		while(true) {
			parent = current;
			if(val < current.val) {
				current = current.left;
				if(current==null) {
					parent.left = new TreeNode3(val);
					return ;
				}
			}else {
				current = current.right;
				if(current==null) {
					parent.right = new TreeNode3(val);
					return ;
				}
			}
		}
	}
	
	//������ȸ�� ���(���� ���� subTree->parent->������ subTree������ ���)
	private static void printInOrderTraversal(TreeNode3 node){
	    if( node != null ){        
	    	if(node.left!=null) {
	    		if(node.right==null) node.left.emptySibling="R"; 
	    		printInOrderTraversal(node.left);
	    	}
	    	
	    	if("L".equals(node.emptySibling)) System.out.print(null + " ");
	    	// ���� ���� Ʈ���� ã�� ���ٰ� ������ ������ �湮�ϰ� �������� ���� ������ ���� ��ȸ
	    	System.out.print(node.val + " ");
			if("R".equals(node.emptySibling)) System.out.print(null + " ");
			
	        if(node.right!=null) {
	        	if(node.left==null) node.right.emptySibling="L"; 
	        	printInOrderTraversal(node.right);
	        }
	    }
	}
	//������ȸ
	private static void printLevelOrderTraversal(TreeNode3 root){
		LinkedList<TreeNode3> queue = new LinkedList<TreeNode3>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode3 curr = queue.pop();
			if( curr != null ){
				if("L".equals(curr.emptySibling)) System.out.print(null + " ");
				System.out.print(curr.val + " ");
				if("R".equals(curr.emptySibling)) System.out.print(null + " ");
				
				if(curr.left!=null) {
					if(curr.right==null) curr.left.emptySibling="R"; 
					queue.add(curr.left);
				}
				if(curr.right!=null) {
					if(curr.left==null) curr.right.emptySibling="L"; 
					queue.add(curr.right);
				}
		    }
		}
		System.out.println("");
	}
	
	private int total = 0;
    public int rangeSumBST(TreeNode3 root, int L, int R) {
    	searchTree(root, L, R);	
		return total;
    }
    
    private void searchTree(TreeNode3 node, int L, int R) {
    	if(node!=null) {
    		if(node.val>=L && node.val<=R) {
    			System.out.println("val:"+node.val);
        		total += node.val;
    		}
		//�ּ� ���� ���ʳ��(�� ������) �̳� �ִ밪�� �����ʳ��(�� ū��)�δ� �������� ����
    		if(node.val > L) searchTree(node.left, L, R);
    		if(node.val < R) searchTree(node.right, L, R);
    		//searchTree(node.left, L, R);
    		//searchTree(node.right, L, R);
    	}
    }
    
    public TreeNode3 deleteNode(TreeNode3 root, int key) {
    	TreeNode3 parent = root;
    	TreeNode3 current = root;
    	boolean isLeftChild = false;
    	while(current.val != key) {
    		parent = current;
    		if(current.val>key) {
    			isLeftChild = true;
    			current = current.left;
    		}else {
    			isLeftChild = false;
    			current = current.right;
    		}
    		if(current == null) {
    			return root;
    		}
    	}
    	System.out.println("find node:" + current.val);
        
    	//case1: �ڽĳ�尡 ���� ���
    	if(current.left==null && current.right==null) {
    		System.out.println("isLeftChild:"+isLeftChild);
    		if(current == root) { root = null; }
    		if(isLeftChild) { parent.left = null; }
    		else { parent.right = null; }
    	}
    	//case2: �ϳ��� �ڽ��� ���°��
    	else if(current.right==null) { //���� �ڽ��� ���°��
    		if(current==root) { root = current.left; }
    		else if(isLeftChild) { parent.left = current.left; }
    		else { parent.right = current.left; }
    	}else if(current.left==null) { //������ �ڽ��� ���°��
    		if(current==root) { root = current.right; }
    		else if(isLeftChild) { 
    			parent.left = current.right; 
    			//��ġ�̵��� �̿���������� empty���θ� �ٽ� ǥ��
    			if(parent.right==null) { parent.left.emptySibling = "L"; }
    			else { parent.left.emptySibling = ""; }
    		}else { 
    			parent.right = current.right; 
    			//��ġ�̵��� �̿���������� empty���θ� �ٽ� ǥ��
    			if(parent.left==null) { parent.right.emptySibling = "L"; }
    			else { parent.right.emptySibling = ""; }
    		}
    	}
    	else if (current.left!=null && current.right!=null) {
    		//������ ���� Ʈ���� �ּҰ��� ã��
    		TreeNode3 rightMinNode = getRightMinNode(current);
    		
    		//�θ���� target��带 ����
    		if(current == root) {
    			root = rightMinNode;
    		}else if(isLeftChild) { parent.left = rightMinNode; }
    		else { parent.right = rightMinNode; }
    		//target�� ���ʳ�� ����
    		rightMinNode.left = current.left;
    	}
    	
    	return root;
    } 
    
    private TreeNode3 getRightMinNode(TreeNode3 deleteNode) {
    	TreeNode3 target = null;
    	TreeNode3 targetParent = null;
    	TreeNode3 current = deleteNode.right;
    	
    	//find target Node
    	while(current != null) {
    		targetParent = target;
    		target = current;
    		current = current.left;
    	}
    	// target�� right�� ������ ����� right��带 ����
    	while(target != deleteNode.right) {
    		targetParent.left = null; //target.right ??
    		target.right = deleteNode.right;
    	}
    	
    	return target;
    }
}

//Definition for a binary tree node.
class TreeNode3 {
    int val;
    String emptySibling; //L:left, R:right
    TreeNode3 left;
    TreeNode3 right;
    TreeNode3(int x) { val = x; }
}