package com.cfyj.algorithm.questions.树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）
 * @author chenfeng
 *
 */
public class 二叉树的层序遍历 {

	
	
    public static List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> result = new ArrayList();
    	LinkedList<Integer> levelResult = null;
    	
    	Queue<TreeNode> queue = new ArrayDeque();
    	
    	if (root==null) {
			return result;
		}else {
			queue.add(root);
			levelResult = new LinkedList();
			levelResult.addFirst(root.val);
			result.add(levelResult);
		}
    	
    	while(!queue.isEmpty()) {
    		
    		levelResult = new LinkedList();
    		TreeNode poll = queue.poll();
    		
    		if(poll.left!=null) {
    			queue.add(poll.left);
    			levelResult.add(poll.left.val);
    		}
    		
    		if(poll.right!=null) {
    			queue.add(poll.right);
    			levelResult.add(poll.right.val);
    		}

    		result.add(levelResult);
    		  		
    	}
    	
    	
    	
    	
    	
    	
    	return result ; 
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
		    List<List<Integer>> list = levelOrder(root);
		    System.out.println(list);
		  }
	
}
