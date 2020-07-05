package com.cfyj.algorithm.questions.树;

import java.util.ArrayDeque;
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
 * 解析: 校验是否为二叉树的核心是判断是否满足二叉树构建条件,遍历树判断是否二叉树条件,通过层序遍历,比较根节点、左节点、右节点之间的关系.
 * 
 * @author chenfeng
 *
 */
public class 验证二叉搜索树 {

	public static boolean isValidBST(TreeNode root, TreeNode preNode) {
		if (root == null) {
			return true;
		}

		if (root.left != null) {
			if (root.val <= root.left.val) {
				return false;
			} else {
				if (!isValidBST(root.left ,root )) { // 只有在下层不满足条件时才直接返回，否则将继续左右树做判断
					return false;
				}
				;
			}
		}

		if (root.right != null) {
			if (root.val >= root.right.val) {
				return false;
			}else if(preNode.val < root.right.val ){
				
			} else {
				if (!isValidBST(root.right,root)) {// 只有在下层不满足条件时才直接返回，否则将继续左右树做判断
					return false;
				}
			}
		}
		return true; // 无左右节点，直接返回
	}

	
	
	
	
	
	public static boolean isValidBST2(TreeNode root) {
		Queue<TreeNode> queue  = new ArrayDeque<TreeNode>();
		
		if (root == null) {
			return true;
		}else {
			queue.add(root);
		}
		
		while(!queue.isEmpty()) {

			TreeNode node = queue.poll();
			
			if(node.left!=null ) {
				if (node.val <= node.left.val) {
					return false;
				} else {
					queue.add(node.left);
				}
			}
			if(node.right!=null ) {
				if (node.val >= node.right.val) {
					return false;
				} else {
					queue.add(node.right);
				}
			}
			
		}

		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(10);
		TreeNode root_left = new TreeNode(5);
		TreeNode root_right = new TreeNode(15);
		root.left = root_left;
		root.right = root_right;
//		    TreeNode root_left_left = new TreeNode(2);
//		    TreeNode root_left_right = new TreeNode(7);
//		    root.left.left = root_left_left;
//		    root.left.right = root_left_right;
		TreeNode root_right_left = new TreeNode(6);
		TreeNode root_right_right = new TreeNode(20);
		root.right.left = root_right_left;
		root.right.right = root_right_right;
//		    cengxu_stack(root); //10,5,15,2,7,17
//		    TreeNode left_1 = new TreeNode(1);
//		    TreeNode left_2 = new TreeNode(0);
//		    root.left.left.left = left_1;
//		    root.left.left.left.left = left_2;
		System.out.println(isValidBST(root));
	}

}
