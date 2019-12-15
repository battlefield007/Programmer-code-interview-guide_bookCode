package chapter_9_others;

public class Problem_16_PathsToNums {

	public static void pathsToNums(int[] paths) {
		if (paths == null || paths.length == 0) {
			return;
		}
		// citiesPath -> distancesArray
		pathsToDistans(paths);

		// distancesArray -> numArray
		distansToNums(paths);
	}

	public static void pathsToDistans(int[] paths) {
		int cap = 0;
		for (int i = 0; i != paths.length; i++) {
			if (paths[i] == i) {
				cap = i;
			} else if (paths[i] > -1) {
				int curI = paths[i];
				paths[i] = -1;
				int preI = i;
				while (paths[curI] != curI) {
					if (paths[curI] > -1) {
						int nextI = paths[curI];
						paths[curI] = preI;
						preI = curI;
						curI = nextI;
					} else {
						break;
					}
				}
				int value = paths[curI] == curI ? 0 : paths[curI];
				while (paths[preI] != -1) {
					int lastPreI = paths[preI];
					paths[preI] = --value;
					curI = preI;
					preI = lastPreI;
				}
				paths[preI] = --value;
			}
		}
		paths[cap] = 0;
	}

	public static void distansToNums(int[] disArr) {
		for (int i = 0; i != disArr.length; i++) {
			int index = disArr[i];
			if (index < 0) {
				disArr[i] = 0; // important
				while (true) {
					index = -index;
					if (disArr[index] > -1) {
						disArr[index]++;
						break;
					} else {
						int nextIndex = disArr[index];
						disArr[index] = 1;
						index = nextIndex;
					}
				}
			}
		}
		disArr[0] = 1;
	}

	public static void printArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] paths = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
		printArray(paths);
		pathsToNums(paths);
		printArray(paths);

	}

}
