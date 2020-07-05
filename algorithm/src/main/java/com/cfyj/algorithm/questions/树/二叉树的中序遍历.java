package com.cfyj.algorithm.questions.树;

import java.util.LinkedList;
import java.util.List;

/**
 * 顺序: 左--> 根--> 右
 * @author chenfeng
 *
 */
public class 二叉树的中序遍历 {

	public static List<Integer> inorderTraversal(TreeNode root) {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
//		Queue<TreeTreeNode> nodeQueue = new ArrayDeque();
//		if(root==null) {
//			return list ; 
//		}else {
//			nodeQueue.add(root);
//		}
//		
//		while(!nodeQueue.isEmpty()) {
//			TreeTreeNode poll = nodeQueue.poll();
//			
//			if(poll.right!=null) {
//				nodeQueue.add(poll.right);
//			}else {}
//			
//			if(poll.left!=null) {
//				nodeQueue.add(poll.left);
//			}
//			list.add(poll.val);	
//		}
		inorderTraversal(root,list);

		return list;
	}
	
	
	public static void inorderTraversal(TreeNode root,List<Integer> list){
		if(root==null) {
			return  ; 
		}
		
		inorderTraversal(root.left, list);
		list.add(root.val);
		inorderTraversal(root.right, list);
	}
	
	
	  public static void main(String[] args) {
		  	TreeNode root = new TreeNode(10);
		    TreeNode root_left = new TreeNode(5);
		    TreeNode root_right = new TreeNode(15);
		    root.left = root_left;
		    root.right = root_right;
		    TreeNode root_left_left = new TreeNode(2);
		    TreeNode root_left_right = new TreeNode(7);
		    root.left.left = root_left_left;
		    root.left.right = root_left_right;
		    TreeNode root_right_left = new TreeNode(12);
		    TreeNode root_right_right = new TreeNode(17);
		    root.right.left = root_right_left;
		    root.right.right = root_right_right;
//		    cengxu_stack(root); //10,5,15,2,7,17
		    TreeNode left_1 = new TreeNode(1);
		    TreeNode left_2 = new TreeNode(0);
		    root.left.left.left = left_1;
		    root.left.left.left.left = left_2;
//		    qianxu_stack(root); //10,5,2,1,0,7,15,12,17
//		    qianxu_digui(root); //10,5,2,1,0,7,15,12,17
		    TreeNode right_1 = new TreeNode(8);
		    root.left.right.right = right_1;
//		    zhongxu_digui(root);
		    List<Integer> list = inorderTraversal(root);
		    System.out.println(list);
		  }
	
	
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
	@Override
	public String toString() {
		return val+""; 
	}
}