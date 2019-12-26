package chapter_1_stackandqueue;

import java.util.Stack;
/*
用栈来求解汉诺塔问题
【题目】
汉诺塔问题比较经典，这里修改一下游戏规则：现在限制不能从最左側的塔直接移动
到最右侧，也不能从最右側直接移动到最左侧，而是必须经过中间.求当塔有N层的时候，
打印最优移动过程和最优移动总步数.
例如，当塔数为两层时，量上层的塔记为1,最下层的塔记为2,则打印：
Move 1 from left to mid
Move 1 from mid to right
Move 2 from left to mid
Hove 1 from right to mid
Move 1 from mid to left
Move 2 from mid to right
Move 1 frail left to mid
Move 1 frcan mid to right
It will move 8 steps.
注意：关于汉诺塔游戏的更多讨论，将在本书通归与动态规划的章节中继续.
【要求】
用以下两种方法解决.
•	方法一：递归的方法；
•	方法二：非递归的方法，用栈来模拟汉诺塔的三个塔。
【难度】
校★★★☆
【进度】
没看，感觉不实用
【解答】
方法一：递归的方法。
首先,如果只剩最上层的塔需要移动，则有如下处理:
1.	如果希望从“左”移到“中”，打印"Move 1 from left to mid"・
2.	如果希望从“中”移到“左”，打印“Move 1 from mid to left"・
3.	如果希望从“中”移到“右”，打印“Move 1 from mid to right".
4.	如果希望从“右”移到"中”，打印“Move 1 from right to mid” .
5.如果希望从“左”移到“右”，打印“Move 1 from left to mid"和“Move 1 from
mid to right”.
6.如果希望从“右”移到“左”，打印“Move 1 from right to mid"和“Move 1 from
mid to left"。
以上过程就是递归的终止条件，也就是只剰上层塔时的打印过程.
接下来，我们分析剩下多层塔的情况。
如果剰下N层塔，从曇上到景下依次为1~N,则有如下判断：
1.如果剰下的N层塔都在“左”，希望全部移到“中”，则有三个步鼻.
1）	将l~N-l层塔先全部从“左”移到“右明显交给递归过程.
2）	将第N层塔从“左”移到“中，
3）再将I〜Ml层塔全部从“右”移到“中”，明显交给递归过程。
2.	如果把剩下的N层塔从“中”移到“左”，从“中”移到“右"，从“右”移到“中”，
过程与情况1同理，一样是分解为三步，在此不再详述。
3.	如果剩下的N层塔都在“左”，希望全部移到“右”，则有五个步骤：
1）	将1~M1层塔先全部从“左”移到“右”，明显交给递归过程。
2）	将第N层塔从“左”移到“中”。
3）	将l~N-l层塔全部从“右”移到“左”，明显交给递归过程。
4）	将第N层塔从“中”移到“右
5）	最后将1-JV-1层塔全部从“左”移到“右”，明显交给递归过程.
4.	如果剩下的N层塔都在“右”，希望全部移到“左"，过程与情况3同理，一样是
分解为五步，在此不再详述。
以上递归过程经过逻辑化简之后的代码请参看如下代码中的hanoiProbleml方法.

 */
public class Problem_06_HanoiStack {

	public static int hanoiProblem1(int num, String left, String mid,
			String right) {
		if (num < 1) {
			return 0;
		}
		return process(num, left, mid, right, left, right);
	}

	public static int process(int num, String left, String mid, String right,
			String from, String to) {
		if (num == 1) {
			if (from.equals(mid) || to.equals(mid)) {
				System.out.println("Move 1 from " + from + " to " + to);
				return 1;
			} else {
				System.out.println("Move 1 from " + from + " to " + mid);
				System.out.println("Move 1 from " + mid + " to " + to);
				return 2;
			}
		}
		if (from.equals(mid) || to.equals(mid)) {
			String another = (from.equals(left) || to.equals(left)) ? right : left;
			int part1 = process(num - 1, left, mid, right, from, another);
			int part2 = 1;
			System.out.println("Move " + num + " from " + from + " to " + to);
			int part3 = process(num - 1, left, mid, right, another, to);
			return part1 + part2 + part3;
		} else {
			int part1 = process(num - 1, left, mid, right, from, to);
			int part2 = 1;
			System.out.println("Move " + num + " from " + from + " to " + mid);
			int part3 = process(num - 1, left, mid, right, to, from);
			int part4 = 1;
			System.out.println("Move " + num + " from " + mid + " to " + to);
			int part5 = process(num - 1, left, mid, right, from, to);
			return part1 + part2 + part3 + part4 + part5;
		}
	}

	public static enum Action {
		No, LToM, MToL, MToR, RToM
	}

	public static int hanoiProblem2(int num, String left, String mid, String right) {
		Stack<Integer> lS = new Stack<Integer>();
		Stack<Integer> mS = new Stack<Integer>();
		Stack<Integer> rS = new Stack<Integer>();
		lS.push(Integer.MAX_VALUE);
		mS.push(Integer.MAX_VALUE);
		rS.push(Integer.MAX_VALUE);
		for (int i = num; i > 0; i--) {
			lS.push(i);
		}
		Action[] record = { Action.No };
		int step = 0;
		while (rS.size() != num + 1) {
			step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
			step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
			step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
			step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
		}
		return step;
	}

	public static int fStackTotStack(Action[] record, Action preNoAct,
			Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
			String from, String to) {
		if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
			tStack.push(fStack.pop());
			System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
			record[0] = nowAct;
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		int num = 4;

		// solution 1
		int steps1 = hanoiProblem1(num, "left", "mid", "right");
		System.out.println("It will move " + steps1 + " steps.");
		System.out.println("===================================");

		// solution 2
		int steps2 = hanoiProblem2(num, "left", "mid", "right");
		System.out.println("It will move " + steps2 + " steps.");
		System.out.println("===================================");

	}

}