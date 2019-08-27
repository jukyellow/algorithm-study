package com.juk.algo.A0827;

import java.util.LinkedList;

public class RangeSumOfBST01_Recursive {
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
		
    	int result = new RangeSumOfBST01_Recursive().rangeSumBST(root, 7, 15);
    	System.out.println("result:"+result);
    }
	
	private static TreeNode root = null;
	private static void addNode(int val) {
		if(root==null) {
			root = new TreeNode(val);
			return ; 
		}

		TreeNode current = root;
		TreeNode parent = null;
		while(true) {
			parent = current;
			if(val < current.val) {
				current = current.left;
				if(current==null) {
					parent.left = new TreeNode(val);
					return ;
				}
			}else {
				current = current.right;
				if(current==null) {
					parent.right = new TreeNode(val);
					return ;
				}
			}
		}
	}
	
	//������ȸ�� ���(���� ���� subTree->parent->������ subTree������ ���)
	private static void printInOrderTraversal(TreeNode node){
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
	private static void printLevelOrderTraversal(TreeNode root){
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			TreeNode curr = queue.pop();
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
    public int rangeSumBST(TreeNode root, int L, int R) {
    	searchTree(root, L, R);	
		return total;
    }
    
    private void searchTree(TreeNode node, int L, int R) {
    	if(node!=null) {
    		if(node.val>=L && node.val<=R) {
    			System.out.println("val:"+node.val);
        		total += node.val;
    		}
    		if(node.val > L) searchTree(node.left, L, R);
    		if(node.val < R) searchTree(node.right, L, R);
    		//searchTree(node.left, L, R);
    		//searchTree(node.right, L, R);
    	}
    }
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    String emptySibling; //L:left, R:right
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
