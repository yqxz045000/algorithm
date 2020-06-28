package com.cfyj.algorithm.questions.树;

import java.util.LinkedList;
import java.util.List;

public class 二叉树的后序遍历 {

	  public static List<Integer> postorderTraversal(TreeNode root) {

	    	LinkedList<Integer> list = new LinkedList<Integer>();
	    	postorderTraversal(root,list);
			return list;
	    }
	    
	    
		public static void postorderTraversal(TreeNode root,List<Integer> list){
			if(root==null) {
				return  ; 
			}
			
			postorderTraversal(root.left, list);
			postorderTraversal(root.right, list);
			list.add(root.val);
		}
		
}
