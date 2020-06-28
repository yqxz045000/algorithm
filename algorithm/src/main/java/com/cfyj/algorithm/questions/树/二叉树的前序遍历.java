package com.cfyj.algorithm.questions.树;

import java.util.LinkedList;
import java.util.List;

/**
 * 顺序: 根--> 左 --> 右
 * @author chenfeng
 *
 */
public class 二叉树的前序遍历 {

    public static List<Integer> preorderTraversal(TreeNode root) {

    	LinkedList<Integer> list = new LinkedList<Integer>();
    	preorderTraversal(root,list);
		return list;
    }
    
    
	public static void preorderTraversal(TreeNode root,List<Integer> list){
		if(root==null) {
			return  ; 
		}
		
		list.add(root.val);
		preorderTraversal(root.left, list);
		preorderTraversal(root.right, list);
	}
    
}
