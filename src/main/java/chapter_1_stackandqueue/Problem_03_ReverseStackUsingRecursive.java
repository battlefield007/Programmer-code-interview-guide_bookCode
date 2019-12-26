package chapter_1_stackandqueue;

import java.util.Stack;
/*
	如何仅用递归函数和栈操作逆序一个栈
	【题目】
	一个栈依次压入 1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
	将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，
	但是只能用递归函数来实现，不能用其他数据结构。
	【难度】  ★★☆☆
	 【 当前学习程度 】 理解了思想(
	 一个栈(jdk自带的Stack  API)，
	 2个递归，实现reverse栈元素。

	递归函数一：将栈stack的栈底元素返回并移除。
    递归函数二：判断当前栈是否到达栈顶元素（即是否已经清空，清空说明递归函数一已经返回了原来的栈顶元素，开始压入栈底，实现reverse）
	 )。
 */
public class Problem_03_ReverseStackUsingRecursive {


	/*
	递归函数一：将栈stack的栈底元素返回并移除。
    递归函数二：判断当前栈是否到达栈顶元素（即是否已经清空，清空说明递归函数一已经返回了原来的栈顶元素，开始压入栈底，实现reverse）
     */
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastElement(stack); //最后一次reverse,栈底元素就是最后一个元素，原来的栈顶元素。    依次压栈，向上递归。
		reverse(stack);
		//(判断当前栈是否到达栈顶元素)递归条件结束，开始压栈。
		stack.push(i);
		//===》【递归条件结束(reverse(stack);)后，执行的所有操作 都是一样的。】
	}

	/*
	递归函数一：将栈stack的栈底元素返回并移除。
	 */
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {//栈底元素返回, getAndRemoveLastElement 递归结束条件
			return result;
		} else {
			int last = getAndRemoveLastElement(stack); //不管多少层递归，最终getAndRemoveLastElement 递归结束返回的都是stack的栈底元素
			//栈底元素(last)取出，不压栈。
			//栈底元素除外的元素(result)取出，重新压栈。
			stack.push(result);
			return last;
			//===》【递归条件结束(getAndRemoveLastElement(stack);)后，执行的所有操作 都是一样的。】
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
