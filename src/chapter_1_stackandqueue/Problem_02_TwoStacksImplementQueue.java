package chapter_1_stackandqueue;

import java.util.Stack;
/*
	 由两个栈组成的队列
	 【题目】
	 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。
	 【难度】 ★★☆☆
	 【 当前学习程度 】 理解了思想(
	 两个栈(jdk自带的Stack  API)，
	 一个存添加的数据，
	 一个存取出的数据，
	 每次更新添加数据栈，同时更新取出数据栈。
	 保证取出数据栈 栈顶元素始终是 最先添加的那个元素。
	 )。
 */
public class Problem_02_TwoStacksImplementQueue {

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// push栈向pop栈倒入数据
		private void pushToPop() {
			if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
		}

		public void add(int pushInt) {
			stackPush.push(pushInt);
			pushToPop();
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
//			peek()
//			查看此堆栈顶部的对象，而不从堆栈中删除它。
//			pop()
//			删除此堆栈顶部的对象，并将该对象作为此函数的值返回。
			pushToPop();
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
