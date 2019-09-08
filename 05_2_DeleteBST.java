package com.juk.algo.A0827;

import java.util.LinkedList;

public class DeleteBST {
    public static void main(String args[]) {
        // root = [10,5,15,3,7,null,18]
        //addNode(10);
        //addNode(5);
        //addNode(15);
        //addNode(3);
        //addNode(7);
        //addNode(18);
        
        //root = [2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46,4,null,12,24,26,30,null,null,35,39,42,44,null,48,3,9,null,14,22,null,null,27,null,null,null,null,38,null,41,null,null,null,47,49,null,null,5,null,13,15,21,23,null,28,37,null,null,null,null,null,null,null,null,8,null,null,null,17,19,null,null,null,null,null,null,null,7,null,16,null,null,20,6]
        //delete 33
//        addNode(2);
//        addNode(0);
//        addNode(33);
//        addNode(1);
//        addNode(25);
//        addNode(40);
//        addNode(11);
//        addNode(31);
//        addNode(34);
//        addNode(45);
//        addNode(10);
//        addNode(18);
//        addNode(29);
//        addNode(32);
//        addNode(36);
//        addNode(43);
//        addNode(46);
//        addNode(4);
//        addNode(12);
//        addNode(24);
//        addNode(26);
//        addNode(30);
//        addNode(35);
//        addNode(39);
//        addNode(42);
//        addNode(44);
//        addNode(48);
//        addNode(3);
//        addNode(9);
//        addNode(14);
//        addNode(22);
//        addNode(27);
//        addNode(38);
//        addNode(41);
//        addNode(47);
//        addNode(49);
//        addNode(5);
//        addNode(13);
//        addNode(15);
//        addNode(21);
//        addNode(23);
//        addNode(28);
//        addNode(37);
//        addNode(8);
//        addNode(17);
//        addNode(19);
//        addNode(7);
//        addNode(16);
//        addNode(20);
//        addNode(6);
        
        //[5,3,6,2,4,null,7]
        //delete 3
        addNode(5);
        addNode(3);
        addNode(6);
        addNode(2);
        addNode(4);
        addNode(7);
        System.out.print("printInOrderTraversal: ");
        printInOrderTraversal(root); System.out.println("");
        printLevelOrderTraversal(root);
        
        //BST(binary search tree)에서 두 range사이에 값의 합 
        int result = new DeleteBST().rangeSumBST(root, 7, 15);
        System.out.println("Range Sum result:"+result);
        
        //노드 삭제
        System.out.println("\ndelete node:");
        new DeleteBST().deleteNode(root, 3);
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
    
    //중위순회로 출력(가장 왼쪽 subTree->parent->오른쪽 subTree순으로 출력)
    private static void printInOrderTraversal(TreeNode3 node){        
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
    private static void printLevelOrderTraversal(TreeNode3 root){
        System.out.print("printLevelOrderTraversal: ");
        
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
                total += node.val;
            }
        //최소 값의 왼쪽노드(더 작은값) 이나 최대값의 오른쪽노드(더 큰값)로는 진행하지 않음
            if(node.val > L) searchTree(node.left, L, R);
            if(node.val < R) searchTree(node.right, L, R);
            //searchTree(node.left, L, R);
            //searchTree(node.right, L, R);
        }
    }
    
    public TreeNode3 deleteNode(TreeNode3 root, int key) {
        if(root==null) { return null; }
        
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
        
        //case1: 자식노드가 없는 경우
        if(current.left==null && current.right==null) {
            System.out.println("isLeftChild:"+isLeftChild);
            if(current == root) { root = null; }
            if(isLeftChild) { parent.left = null; }
            else { parent.right = null; }
        }
        //case2: 하나의 자식을 갖는경우
        else if(current.right==null) { //왼쪽 자식을 갖는경우
            if(current==root) { root = current.left; }
            else if(isLeftChild) { parent.left = current.left; }
            else { parent.right = current.left; }
        }else if(current.left==null) { //오른쪽 자식을 갖는경우
            if(current==root) { root = current.right; }
            else if(isLeftChild) { 
                parent.left = current.right; 
                //위치이동후 이웃형제노드의 empty여부를 다시 표시
                if(parent.right==null) { parent.left.emptySibling = "L"; }
                else { parent.left.emptySibling = ""; }
            }else { 
                parent.right = current.right; 
                //위치이동후 이웃형제노드의 empty여부를 다시 표시
                if(parent.left==null) { parent.right.emptySibling = "L"; }
                else { parent.right.emptySibling = ""; }
            }
        }
        else if (current.left!=null && current.right!=null) {
            //오른쪽 서브 트리의 최소값을 찾음
            TreeNode3 rightMinNode = getRightMinNode(current);
            
            //부모노드와 target노드를 연결
            if(current == root) {
                root = rightMinNode;
            }else if(isLeftChild) { parent.left = rightMinNode; }
            else { parent.right = rightMinNode; }
            //target의 왼쪽노드 연결
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
        System.out.println("1 target.val:" + target.val);
        // target의 right에 삭제할 노드의 right노드를 세팅
        while(true) {
            if(target == deleteNode.right) break; //하위 노드가 없는경우
            if(target.right == deleteNode.right) break; //하위 노드가 있는경우
            
            targetParent.left = target.right;
            //System.out.println("2 targetParent.left.val:" + targetParent.left.val);
            if(targetParent.right==null) { if(targetParent.left!=null) targetParent.left.emptySibling="R"; }
            else { if(targetParent.left!=null) targetParent.left.emptySibling=""; }
            
            target.right = deleteNode.right;
            //System.out.println("3 target.right.val:" + target.right.val);
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