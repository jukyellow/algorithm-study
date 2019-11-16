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
    
    //중위순회로 출력(가장 왼쪽 subTree->parent->오른쪽 subTree순으로 출력)
    public static void printInOrderTraversal(TreeNode node){  
    	
        if( node != null ){        
            if(node.left!=null) {
                if(node.right==null) node.left.emptySibling="R"; 
                printInOrderTraversal(node.left);
            }
            
            if("L".equals(node.emptySibling)) System.out.print(null + " ");
            // 왼쪽 먼저 트리를 찾아 갔다가 왼쪽이 없으면 방문하고 오른쪽을 가기 때문에 중위 순회
            System.out.print(node.val + " ");
            if("R".equals(node.emptySibling)) System.out.print(null + " ");
            
            if(node.right!=null) {
                if(node.left==null) node.right.emptySibling="L"; 
                printInOrderTraversal(node.right);
            }
        }
    }
    //층별순회
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
