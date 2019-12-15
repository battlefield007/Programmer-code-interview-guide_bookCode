package chapter_8_arrayandmatrix;

public class Problem_22_MultiplyExceptOwn {

	public static int[] product1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return null;
		}
		int count = 0;
		int all = 1;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] != 0) {
				all *= arr[i];
			} else {
				count++;
			}
		}
		int[] res = new int[arr.length];
		if (count == 0) {
			for (int i = 0; i != arr.length; i++) {
				res[i] = all / arr[i];
			}
		}
		if (count == 1) {
			for (int i = 0; i != arr.length; i++) {
				if (arr[i] == 0) {
					res[i] = all;
				}
			}
		}
		return res;
	}

	public static int[] product2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return null;
		}
		int[] res = new int[arr.length];
		res[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			res[i] = res[i - 1] * arr[i];
		}
		int tmp = 1;
		for (int i = arr.length - 1; i > 0; i--) {
			res[i] = res[i - 1] * tmp;
			tmp *= arr[i];
		}
		res[0] = tmp;
		return res;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4 };
		int[] res1 = product1(arr);
		printArray(res1);
		int[] res2 = product2(arr);
		printArray(res2);

	}
}
