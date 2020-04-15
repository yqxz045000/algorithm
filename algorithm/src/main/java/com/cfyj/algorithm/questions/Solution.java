package com.cfyj.algorithm.questions;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807 Definition
 * for singly-linked list. struct ListNode { int val; ListNode *next;
 * ListNode(int x) : val(x), next(NULL) {} };
 * 
 */






public class Solution {
	
	
	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode node = s.addTwoNumbers(s.getListNodeByInt(102), s.getListNodeByInt(102));
		System.out.println(s.resolvingListNode(node));
		
	}
	
	public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		/**
		 * 1.逆序输出，如果为尾节点为0则该值等于0 2.将逆序后的数据转为int值，得出计算结构后，构建新的数据链表
		 */
		
		if(l1!=null && l2!=null) {
			int i1 = resolvingListNode(l1);
			int i2 = resolvingListNode(l2);
			int sum = i1+i2 ; 
			return getListNodeByInt(sum);
		}
		return null;
	}
	
	
	public ListNode getListNodeByInt(int sum) {

		String[] sumA = new StringBuilder().append(sum).reverse().toString().split("");
		if(sumA!=null && sumA.length>0) {
			ListNode node = new ListNode(new Integer(sumA[0]));
			ListNode tmp = node;
			for(int i =1 ;i < sumA.length;i++) {
				tmp.next = new ListNode(new Integer(sumA[i]));
				tmp = tmp.next;
			}
			return node;
		}
			
		return null;
	}
	
	public int resolvingListNode(ListNode node) {
		StringBuilder sb1 = resolvingListNode(node,null);
		if(sb1.toString().endsWith("0")) {
			return 0 ;
		}else {
			return new Integer(sb1.reverse().toString());
		}	
	}
	
	public StringBuilder resolvingListNode(ListNode node,StringBuilder sb) {
		
		StringBuilder sbb = sb!=null? sb:new StringBuilder();
		if(node!=null) {
			sbb.append(node.val);
			return resolvingListNode( node.next, sbb);
		}
		return sbb;
	}
	
	
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
