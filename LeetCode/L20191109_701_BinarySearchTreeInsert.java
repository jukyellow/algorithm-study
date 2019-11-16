//https://leetcode.com/problems/insert-into-a-binary-search-tree/
//701
package leetcode;

import java.util.LinkedList;

public class L20191109_701_BinarySearchTreeInsert {

	public static void main(String[] args) {
		// root = [4,2,7,1,3]
		// 5
		Solution s = new Solution();
		s.root = s.insertIntoBST(s.root, 4);
		s.root = s.insertIntoBST(s.root, 2);
		s.root = s.insertIntoBST(s.root, 7);
		s.root = s.insertIntoBST(s.root, 1);
		s.root = s.insertIntoBST(s.root, 3);
		
		//add
		s.root = s.insertIntoBST(s.root, 5);

		System.out.print("printLevelOrderTraversal: ");
        s.printLevelOrderTraversal(s.root);
		  
    	System.out.print("printInOrderTraversal: ");
		s.printInOrderTraversal(s.root);
	}
}

class TreeNode {
    int val;
    String emptySibling; //L:left, R:right
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
	public static TreeNode root = null;
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	if(root==null) {
            root = new TreeNode(val);
            return root; 
        }

        TreeNode current = root;
        TreeNode parent = null;
        while(true) {
            parent = current;
            if(val < current.val) {
                current = current.left;
                if(current==null) {
                    parent.left = new TreeNode(val);
                    return root;
                }
            }else {
                current = current.right;
                if(current==null) {
                    parent.right = new TreeNode(val);
                    return root;
                }
            }
        }
    }
    
    //������ȸ�� ���(���� ���� subTree->parent->������ subTree������ ���)
    public static void printInOrderTraversal(TreeNode node){  
    	
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
    public static void printLevelOrderTraversal(TreeNode root){
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
}
