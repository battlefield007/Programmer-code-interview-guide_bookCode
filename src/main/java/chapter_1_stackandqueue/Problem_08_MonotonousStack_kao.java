package chapter_1_stackandqueue;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
/*
to do

构造数组的MaxTree
【题目】
定义二叉树节点如下：
public class Node {
	public int value;
	public Node left;
	public Node right;
	public Node(int data){
		this.value - data;
	}
}
—个数组的MaxTree定义如下.
•	数组必须没有重复元索。
•	MaxTree是一棵二又树，数组的每一个元素对应一个二又树节点。
•	包括MaxTree树在内且在其中的每一棵子树上，值最大的节点都是树的头。
给定一个没有重复元素的数组arr 写出生成这个数组的MaxTree的函数，
要求如果数组长度为N则时间复杂度为O(N)、额外空间复杂度为O(N).
【难度】
校★★★☆


 */
public class Problem_08_MonotonousStack_kao {

	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int popIndex = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
				res[popIndex][0] = leftLessIndex;
				res[popIndex][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int popIndex = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
			res[popIndex][0] = leftLessIndex;
			res[popIndex][1] = -1;
		}
		return res;
	}

	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				List<Integer> popIs = stack.pop();
				// 取位于下面位置的列表中，最晚加入的那个
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
						stack.peek().size() - 1);
				for (Integer popi : popIs) {
					res[popi][0] = leftLessIndex;
					res[popi][1] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popIs = stack.pop();
			// 取位于下面位置的列表中，最晚加入的那个
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
					stack.peek().size() - 1);
			for (Integer popi : popIs) {
				res[popi][0] = leftLessIndex;
				res[popi][1] = -1;
			}
		}
		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
				System.out.println("Oops!");
				printArray(arr1);
				break;
			}
			if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
				System.out.println("Oops!");
				printArray(arr2);
				break;
			}
		}
	}
}
