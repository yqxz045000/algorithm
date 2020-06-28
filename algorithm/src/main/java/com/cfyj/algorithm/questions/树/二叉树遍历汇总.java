package com.cfyj.algorithm.questions.树;

import java.util.ArrayDeque;
import java.util.Deque;

/**
* 层序、前序、中序、后序：
*
* 层序--广度优先遍历： 根--> 左层 --> 右层 --> 前序-先序： 根--> 左子树 --> 右子树  中序： 左子树--> 根 --> 右子树
* 后序： 左子树--> 右子树 --> 根
*
* 可以看到前中后 三种排序针对是其实根节点顺序去划分，具体举例如下： 有一颗树 9 ,  7 , 11 , 5, 8, 10, 15, 3 -- 按堆结构去看
* 层序： 9, 7, 11, 5, 8, 10, 15, 3 前序： 9, 7, 5, 3, 8, 11, 10, 15 中序: 3, 5,  7, 8,
* 9, 10,11,15 后续： 3,5, 8, 7, 10, 15, 11, 9
*/
public class 二叉树遍历汇总 {

	/**
	 * 层序遍历： 逐层输出。 遍历树的根节点，将根节点的元素添加到队列中，队列会保持先进先出的顺序，不断遍历队列将节点的左右节点放置到队列中然后
	 * 输出自身，就实现了先进先出顺序逐层输出树中所有节点 核心： 按照层序逐层加入队列，然后不断遍历队列输出
	 *
	 * @param node
	 */
	public static void cengxu_stack(Node node) {
		Deque<Node> queue = new ArrayDeque(3);
		if (node == null) {
			return;
		} else {
			queue.push(node);
		}
		while (!queue.isEmpty()) {
			Node pop = queue.pop();
			System.out.print(pop + ",");
			if (pop.left != null) {
				queue.add(pop.left);
			}
			if (pop.right != null) {
				queue.add(pop.right);
			}
		}
	}

	/**
	 * 前序遍历： 遍历顺序为 根节点-->左子树-->右子树 核心： 要按照左、右顺序去输出元素，那就按照右 --> 左
	 * 顺序加入到队列头部，那么左节点一定会在右节点之前，然后不断pop弹出头节点，当无左节点时 才开始输出右节点。 解决： 1.堆栈方式； 2.递归方式
	 *
	 * @param node
	 */
	public static void qianxu_stack(Node node) {
		Deque<Node> queue = new ArrayDeque(3);
		if (node == null) {
			return;
		} else {
			queue.push(node);
		}
		while (!queue.isEmpty()) {
			Node pop = queue.pop();
			System.out.print(pop + ",");
			if (pop.right != null) {
				queue.addFirst(pop.right);
			}
			if (pop.left != null) {
				queue.addFirst(pop.left);
			}
		}
	}

	/**
	 * 前序遍历： 递归方式 核心： 不断去维护遍历节点，从左节点开始维护，左节点维护完成后维护右节点
	 *
	 * @param node
	 */
	public static void qianxu_digui(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node + ",");
		qianxu_digui(node.left);
		qianxu_digui(node.right);
	}

	/**
	 * 中序遍历： 左子树--> 根 --> 右子树 核心： 不断向下递归左节点，当不存在左节点时，自身就是最后一个左节点(子根)，输出自身，然后遍历右节点
	 *
	 * @param node
	 */
	public static void zhongxu_digui(Node node) {
		if (node == null) {
			return;
		}
		zhongxu_digui(node.left);
		System.out.print(node + ","); // 此时自己就是左节点，并且也作为一个子根
		zhongxu_digui(node.right); // 找右子树的左节点
	}

	/**
	 * 后序遍历 后序遍历：左子树 ---> 右子树 ---> 根结点
	 *
	 * @param t
	 */
	public static void houxu_digui(Node t) {
		if (t != null) {
			houxu_digui(t.left);
			houxu_digui(t.right);
			System.out.print(t.value + " "); // 访问玩左右访问当前节点
		}
	}

	public static void main(String[] args) {
		Node root = new Node(10);
		Node root_left = new Node(5);
		Node root_right = new Node(15);
		root.left = root_left;
		root.right = root_right;
		Node root_left_left = new Node(2);
		Node root_left_right = new Node(7);
		root.left.left = root_left_left;
		root.left.right = root_left_right;
		Node root_right_left = new Node(12);
		Node root_right_right = new Node(17);
		root.right.left = root_right_left;
		root.right.right = root_right_right;
//	    cengxu_stack(root); //10,5,15,2,7,17
		Node left_1 = new Node(1);
		Node left_2 = new Node(0);
		root.left.left.left = left_1;
		root.left.left.left.left = left_2;
//	    qianxu_stack(root); //10,5,2,1,0,7,15,12,17
//	    qianxu_digui(root); //10,5,2,1,0,7,15,12,17
		Node right_1 = new Node(8);
		root.left.right.right = right_1;
//	    zhongxu_digui(root);
		houxu_digui(root);
	}
}

class Node {
	Integer value;
	Node left;
	Node right;

	public Node(Integer value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
