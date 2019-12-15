package chapter_7_bitoperation;
/*
不用额外变量交换两个整数的值
【题目】
如何不用任何额外变量交换两个整数的值?
【难度】 ★☆☆☆
【 当前学习程度 】 理解了思想：三次位运算。
 */
public class Problem_01_SwapWithoutTmp {

	public static void main(String[] args) {
		int a = 16;
		int b = 111;
		System.out.println(a);
		System.out.println(b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}

}
