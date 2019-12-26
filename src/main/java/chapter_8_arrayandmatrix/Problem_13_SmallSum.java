package chapter_8_arrayandmatrix;

public class Problem_13_SmallSum {

	public static int getSmallSum(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return func(arr, 0, arr.length - 1);
	}

	public static int func(int[] s, int l, int r) {
		if (l == r) {
			return 0;
		}
		int mid = (l + r) / 2;
		return func(s, l, mid) + func(s, mid + 1, r) + merge(s, l, mid, r);
	}

	public static int merge(int[] s, int left, int mid, int right) {
		int[] h = new int[right - left + 1];
		int hi = 0;
		int i = left;
		int j = mid + 1;
		int smallSum = 0;
		while (i <= mid && j <= right) {
			if (s[i] <= s[j]) {
				smallSum += s[i] * (right - j + 1);
				h[hi++] = s[i++];
			} else {
				h[hi++] = s[j++];
			}
		}
		for (; (j < right + 1) || (i < mid + 1); j++, i++) {
			h[hi++] = i > mid ? s[j] : s[i];
		}
		for (int k = 0; k != h.length; k++) {
			s[left++] = h[k];
		}
		return smallSum;
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 2, 4, 6, 2, 7, 8, 1 };
		System.out.println(getSmallSum(arr));

	}

}
