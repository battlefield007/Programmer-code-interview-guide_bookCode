package chapter_4_recursionanddp;

import java.util.HashMap;

public class Problem_20_LongestConsecutive {

	public static int longestConsecutive(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = 1;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
				if (map.containsKey(arr[i] - 1)) {
					max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
				}
				if (map.containsKey(arr[i] + 1)) {
					max = Math.max(max, merge(map, arr[i], arr[i] + 1));
				}
			}
		}
		return max;
	}

	public static int merge(HashMap<Integer, Integer> map, int less, int more) {
		int left = less - map.get(less) + 1;
		int right = more + map.get(more) - 1;
		int len = right - left + 1;
		map.put(left, len);
		map.put(right, len);
		return len;
	}

	public static void main(String[] args) {
		int[] arr = { 100, 4, 200, 1, 3, 2 };
		System.out.println(longestConsecutive(arr));

	}

}
