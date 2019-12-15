package chapter_9_others;

import java.util.Arrays;

public class Problem_29_FindUpMedian {

	public static int getUpMedian(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
			throw new RuntimeException("Your arr is invalid!");
		}
		int start1 = 0;
		int end1 = arr1.length - 1;
		int start2 = 0;
		int end2 = arr2.length - 1;
		int mid1 = 0;
		int mid2 = 0;
		int offset = 0;
		while (start1 < end1) {
			mid1 = (start1 + end1) / 2;
			mid2 = (start2 + end2) / 2;
			// Ԫ�ظ���Ϊ������offsetΪ0��Ԫ�ظ���Ϊż����offsetΪ1��
			offset = ((end1 - start1 + 1) & 1) ^ 1;
			if (arr1[mid1] > arr2[mid2]) {
				end1 = mid1;
				start2 = mid2 + offset;
			} else if (arr1[mid1] < arr2[mid2]) {
				start1 = mid1 + offset;
				end2 = mid2;
			} else {
				return arr1[mid1];
			}
		}
		return Math.min(arr1[start1], arr2[start2]);
	}

	// For test, this method is inefficient but absolutely right
	public static int findForTest(int[] arr1, int[] arr2) {
		if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
			throw new RuntimeException("Your arr is invalid!");
		}
		int[] arrAll = new int[arr1.length + arr2.length];
		for (int i = 0; i != arr1.length; i++) {
			arrAll[i * 2] = arr1[i];
			arrAll[i * 2 + 1] = arr2[i];
		}
		Arrays.sort(arrAll);
		return arrAll[(arrAll.length / 2) - 1];
	}

	public static int[] generateSortedArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != len; i++) {
			res[i] = (int) (Math.random() * (maxValue + 1));
		}
		Arrays.sort(res);
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 10;
		int maxValue1 = 20;
		int maxValue2 = 50;
		int[] arr1 = generateSortedArray(len, maxValue1);
		int[] arr2 = generateSortedArray(len, maxValue2);
		printArray(arr1);
		printArray(arr2);
		System.out.println(getUpMedian(arr1, arr2));
		System.out.println(findForTest(arr1, arr2));

	}

}
