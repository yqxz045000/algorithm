package com.cfyj.algorithm.questions.树;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 假设一个二叉搜索树具有如下特征：
 * 
 * 节点的左子树只包含小于当前节点的数。 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 解析: 校验是否为二叉树的核心是判断是否满足二叉树构建条件,遍历树判断是否二叉树条件,通过层序遍历,
 * 比较根节点、左节点、右节点之间的关系.
 * 
 * @author chenfeng
 *
 */
public class 验证二叉搜索树 {
	
	   public static boolean isValidBST(TreeNode root) {
		    //左边全小于根，右边全大于根
		    boolean flag = true ; 
		    if(root!=null) {
		      if(root.left != null &&   root.val <= root.left.val  ) {
		        return false ;
		      }else if(root.left != null){
		    	  flag  = checkLeftTree(root.left, root.val) ;
		      }
		     
		      if(flag && root.right != null &&  root.val >= root.right.val  ){
		        return false ;
		      }else if(flag && root.right != null){
		        return checkRightTree(root.right, root.val) ;
		      }
		  }
		    return flag;
	   }
		 
		  public static boolean checkLeftTree(TreeNode node , int max){
		   
		    if(node!=null) {
		      if(node.left != null &&  ( node.val <= node.left.val  |  node.left.val >= max )) {
		        return false ;
		      }else if(node.left != null){
		         if(!checkLeftTree(node.left, max)) {
		        	return false;  
		         } 
		      }
		     
		      if(node.right != null &&  ( node.val >= node.right.val | node.right.val >= max) ){
		        return false ;
		      }else if(node.right != null){
		    	  if(!checkLeftTree(node.right, max) ) {
		    		  return false ; 
		    	  }
		      }
		    }
		    return true ;
		   
		  }
		 
		  public static boolean checkRightTree(TreeNode node , int min){
		   
		    if(node!=null) {
		      if(node.left != null &&  ( node.val <= node.left.val  |  node.left.val <= min ) ){
		        return false ;
		      }else if(node.left != null){
		    	  if(! checkRightTree(node.left, min) ) {
		    		  return false ; 
		    	  }
		      }
		     
		      if(node.right != null &&  ( node.val >= node.right.val | node.right.val <= min) ){
		        return false ;
		      }else if(node.right != null){
		    	  if(! checkRightTree(node.right, min) ) {
		    		  return false ;
		    	  }
		      }
		    }
		    return true ;  
		  }
		  
		  
//	public static boolean isValidBST2(TreeNode root, TreeNode preNode) {
//		if (root == null) {
//			return true;
//		}
//
//		if (root.left != null) {
//			if (root.val <= root.left.val) {
//				return false;
//			} else {
//				if (!isValidBST(root.left ,root )) { // 只有在下层不满足条件时才直接返回，否则将继续左右树做判断
//					return false;
//				}
//				;
//			}
//		}
//
//		if (root.right != null) {
//			if (root.val >= root.right.val) {
//				return false;
//			}else if(preNode.val < root.right.val ){
//				
//			} else {
//				if (!isValidBST(root.right,root)) {// 只有在下层不满足条件时才直接返回，否则将继续左右树做判断
//					return false;
//				}
//			}
//		}
//		return true; // 无左右节点，直接返回
//	}

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		TreeNode root = new TreeNode(3);
//		TreeNode root_left = new TreeNode(1);
//		TreeNode root_right = new TreeNode(5);
//		root.left = root_left;
//		root.right = root_right;
//
//		TreeNode root_right_left = new TreeNode(0);
//		TreeNode root_right_right = new TreeNode(2);
//		root.left.left = root_right_left;
//		root.left.right = root_right_right;
//		root.left.right.right = new TreeNode(3);
//		
//		
//		System.out.println(isValidBST(root));
		
//		System.out.println(tableSizeFor(19));

	}

	

    
    
}
