package chapter_1_stackandqueue;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.*;

/*
【题目不长的题，面试考到概率大】

生成窗口最大值数组
【题目】
有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边,窗口每次向右边滑一个位置.
例如，数组为[4,3,5,4,3,3,6,7],窗口大小为3时：
[4 3 5] 4 3 3 6 7  窗口中最大值为5
4 [3 5 4] 3 3 6 7  窗口中最大值为5
4 3 [5 4 3] 3 6 7  窗口中最大值为5
4 3 5 [4 3 3] 6 7  窗口中最大值为4
4 3 5 4 [3 3 6] 7  窗口中最大值为6
4 3 5 4 3 [3 6 7]  窗口中最大值为7
如果数组长度为n，窗口大小为w,则一共产生(n-w+1)个窗口的最大值.
请实现一个函数。
•	输入：整型数组air,窗口大小为w。
•	输出：一个长度为 n-w+1 的数组res,res［i］表示每一种窗口状态下的最大值
以本题为例，结果应该返回｛5,5,5,4,6,7｝。
【难度】
尉
【收获】
guava   Collections.max(list);   min 同理


 */
public class Problem_07_SlidingWindowMaxArray_kao {

	//多次运行 发现 我的方式 比作者方式效率高。时间更短。可能得益于【guava 的性能确实很高。】
	public static int[] getMaxWindow_my(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}


		int[] res = new int[arr.length - w + 1];
//		分析
//		w=1时，窗口最大值就是自身。
		if (w==1) {
			res = arr;
		}
//		w>1时，窗口数组最大值就是窗口元素max值。 	 guava   Collections.max(list);
		if (w>1) {
			for (int i = 0; i < res.length; i++) {
				LinkedList<Integer> list = new LinkedList<Integer>();
				for (int j = i; j < i+w; j++) {
					list.addLast(arr[j]);
				}
//				Collections2.
				Integer max = Collections.max(list);
				res[i] = max;
			}
		}
		return res;
	}


	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			if (qmax.peekFirst() == i - w) {
				qmax.pollFirst();
			}
			if (i >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int[] arr = null;
		int arrLen = 10_000;
		arr = getBigArr(arrLen);


		int w = 3;
		long timeMillis = System.currentTimeMillis();
		printArray(getMaxWindow(arr, w));
		long timeMillis2 = System.currentTimeMillis();
		System.out.println(timeMillis2-timeMillis);
		printArray(getMaxWindow_my(arr, w)); //多次运行 发现 我的方式 比作者方式效率高。时间更短。可能得益于【guava 的性能确实很高。】
		long timeMillis3 = System.currentTimeMillis();
		System.out.println(timeMillis3-timeMillis2);


//		//课外   google  guava API 测试。
//		List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
//
//		List<List<Integer>> lists=Lists.partition(numList,3);
//		System.out.println(lists);//[[1, 2, 3], [4, 5, 6], [7, 8]]
//		List<List<Integer>> lists1 = Lists.cartesianProduct(lists);
//		System.out.println(lists1);
	}

	private static int[] getBigArr(int arrLen) {
		int[] arr = new int[arrLen];
		for (int i = 0; i < arrLen; i++) {
			arr[i] = i;

		}
		return arr;
	}

}
