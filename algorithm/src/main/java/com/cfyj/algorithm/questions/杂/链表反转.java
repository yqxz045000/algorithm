package com.cfyj.algorithm.questions.杂;

/**
 * 链表反转 示例: 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * 思考:
 * 	链表反转就是将后一个元素指向前一个元素,不断执行,这就需要递归思想,并且在重置引用时会将后一个元素的后向引用丢失,所以
 * 	将后向元素的后向引用作为临时节点维护,递归时就是将元素的后向元素指向自己
 * 解:
 * 	1.递归解法:
 * 		将后向元素指向当前元素本身,并且在第一次时 当前元素和后向元素的后向元素就会一致,所以需要清空掉当前元素的后向元素,
 * 		临界值为: 当元素没有后向元素时,则说明到达来尾部,这时返回尾部元素,他就是反转后的头节点
 * 	2.双指针迭代解法:
 * 		维护两个节点: 前向节点和当前节点,不断将当前节点的next指向逻辑前向节点,维护tmp节点来保存下次迭代所需的当前节点.
 * 		核心: 就是不断维护前向节点和当前节点的关系.
 *  3.双指针迭代解法的另一种解释.
 * 	特征: 右前向指向后向,不停触发的动作为 后向指向前向,那么只需要不断维护这个关系即可,采用双指针策略,维护pre next tmp几个元素,
	pre为前向节点,next为后向节点,当next不存在时意味着前向节点没了,当后向节点存在时,先维护next.next--后面会作为next指针,将next.next指向pre--完成反转,pre就会变成原来的next,而此时的后向节点为next.next,又维护了这种关系
	
 * 
 * @author chenfeng
 *
 */
public class 链表反转 {
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;

//		node1 = reverseList(node1, node1.next);
		node1 = reverseList3(node1);
		System.out.println(node1);
	}
	
	/**
	 * 核心: 维护当前节点和前向节点的关系,使用一个临时节点来保存next节点的next节点,后续会交换为当前节点
	 * 维护三个指针,一个是当前节点,一个是前向节点,一个是当前节点的后向节点
	 * 第一次时,前向节点为头节点,当前节点为头节点的next节点,然后反转,然后重新维护当前节点和前向节点关系
	 * @param node
	 * @return
	 */
	public static ListNode reverseList2(ListNode node ) {
		if(node==null) {
			return node ; 
		}
		ListNode cur = node.next;
		ListNode pre = node ;
		ListNode tmp ; 
		while (cur !=null ) {
			tmp = cur.next;
			cur.next = pre ; 
			if(pre.next == cur) {//原先头指针维护了后向索引相同,需要清空引用.
				pre.next =null;
			}
			pre = cur;
			cur = tmp ; 
		}
		return pre ; 		
	}
	
	/**
	 * 双指针解法3
	 * @param head
	 * @return
	 */
	public static ListNode reverseList3(ListNode head ) {
		if(head==null) {
			return head ; 
		}
		ListNode pre = head ; 
		ListNode next =head.next  ; 
		head.next = null ; 
		ListNode tmp ;
		
		while(next!=null ) {
			tmp = next.next;
			next.next = pre ; 
			pre = next ; 
			next = tmp ; 			
		}
			
		return pre;
	}
	
	
	
	public static ListNode reverseList(ListNode node ,ListNode nextNode) {
		
		if(nextNode!=null) {
			ListNode tmp = nextNode.next;
			nextNode.next = node;
			if(node.next == nextNode) {
				node.next = null ; 
			}
			 node = reverseList(nextNode, tmp);
		}
		return node ;
	}
	
}


class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}
	

}
