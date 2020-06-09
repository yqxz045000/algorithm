package com.cfyj.algorithm.questions;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产消费的阻塞队列:
 * 
 * 
 * 1.维护三个组件: 安全锁、存储队列、阻塞队列 2.当队列满时,将线程加入到set阻塞队列 3.当队列为null时,将线程加入到get阻塞队列
 * 
 * @author chenfeng
 *
 */
public class 生产消费的阻塞队列<T> {

	private int capacity = 0;

	private static final int DEFAULT_CAPACITY = 10;

	private LinkedList<T> elementDatas;

	private ReentrantLock lock;

	private Condition getWaitLockCondition;
	private Condition setWaitLockCondition;

	生产消费的阻塞队列() {
		this(DEFAULT_CAPACITY);
	}

	生产消费的阻塞队列(int capacity) {
		this.capacity = capacity;
		elementDatas = new LinkedList<>();
		lock = new ReentrantLock();
		getWaitLockCondition = lock.newCondition();
		setWaitLockCondition = lock.newCondition();
	}

	public void add(T t) throws InterruptedException {

		try {

			lock.lockInterruptibly();
			while (elementDatas.size() >= capacity) {
				setWaitLockCondition.await();
			}

			elementDatas.add(t);

		} finally {
			lock.unlock();
		}
		signalGetThread();
	}

	/**
	 * 将唤醒对方线程独立出来,提高吞吐量,类似于非公平锁思想.
	 * 
	 * @throws InterruptedException
	 */
	public void signalGetThread() throws InterruptedException {
		try {
			lock.lockInterruptibly();
			if (elementDatas.size() == 1) {
				getWaitLockCondition.signal();// 只唤醒一个即可,因为只需要了一个,无需唤醒太多队列去竞争
			} else if (elementDatas.size() > 1) {
				getWaitLockCondition.signalAll();// 元素数量大于N,可以唤醒多个消费者
			}

		} finally {
			lock.unlock();
		}
	}

	public void signalSetThread() throws InterruptedException {
		try {
			lock.lockInterruptibly();
			if (elementDatas.size() == capacity - 1) {
				setWaitLockCondition.signal();// 只唤醒一个即可,因为只需要了一个,无需唤醒太多队列去竞争
			} else if (elementDatas.size() < capacity) {
				setWaitLockCondition.signalAll();// 元素数量大于N,可以唤醒多个消费者
			}

		} finally {
			lock.unlock();
		}
	}

	public T get() throws InterruptedException {
		T t;
		try {
			lock.lockInterruptibly();
			while (elementDatas.size() <= 0) {
				getWaitLockCondition.await();
			}

			t = elementDatas.pop();
		} finally {
			lock.unlock();
		}
		signalSetThread();
		return t;
	}

	public static void main(String[] args) {
		int k = 50;
		CyclicBarrier cb1 = new CyclicBarrier(k);
		CyclicBarrier cb2 = new CyclicBarrier(k);
		AtomicInteger num = new AtomicInteger();
		生产消费的阻塞队列<Integer> queue = new 生产消费的阻塞队列<>(2);
		for (int i = 0; i < k; i++) {
			new Thread() {
				public void run() {
					try {
						cb1.await();
						for (int i = 0; i < k; i++) {
							queue.add(num.incrementAndGet());
						}
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				};
			}.start();
		}

		for (int i = 0; i < k; i++) {
			new Thread() {
				public void run() {
					try {
						cb2.await();
						for (int i = 0; i < k; i++) {
							int result = queue.get();
							System.out.println(result);
						}
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				};
			}.start();
		}

	}

}
