package chapter_9_others;

import java.util.HashMap;

public class Problem_27_ReceiveAndPrintOrderLine {

	// �򵥵ĵ�����ڵ�ṹ
	public static class Node {
		public int num;
		public Node next;

		public Node(int num) {
			this.num = num;
		}
	}

	public static class MessageBox {
		private HashMap<Integer, Node> headMap;
		private HashMap<Integer, Node> tailMap;
		private int lastPrint;

		public MessageBox() {
			headMap = new HashMap<Integer, Node>();
			tailMap = new HashMap<Integer, Node>();
			lastPrint = 0;
		}

		public void receive(int num) {
			if (num < 1) {
				return;
			}
			Node cur = new Node(num);
			headMap.put(num, cur);
			tailMap.put(num, cur);
			if (tailMap.containsKey(num - 1)) {
				tailMap.get(num - 1).next = cur;
				tailMap.remove(num - 1);
				headMap.remove(num);
			}
			if (headMap.containsKey(num + 1)) {
				cur.next = headMap.get(num + 1);
				tailMap.remove(num);
				headMap.remove(num + 1);
			}
			if (headMap.containsKey(lastPrint + 1)) {
				print();
			}
		}

		private void print() {
			Node node = headMap.get(++lastPrint);
			headMap.remove(lastPrint);
			while (node != null) {
				System.out.print(node.num + " ");
				node = node.next;
				lastPrint++;
			}
			tailMap.remove(--lastPrint);
			System.out.println();
		}

	}

	public static void main(String[] args) {
		// MessageBox only receive 1~N
		MessageBox box = new MessageBox();

		box.receive(2); // - 2
		box.receive(1); // 1 2 -> print, trigger is 1

		box.receive(4); // - 4
		box.receive(5); // - 4 5
		box.receive(7); // - 4 5 - 7
		box.receive(8); // - 4 5 - 7 8
		box.receive(6); // - 4 5 6 7 8
		box.receive(3); // 3 4 5 6 7 8 -> print, trigger is 3

		box.receive(9); // 9 -> print, trigger is 9

		box.receive(10); // 10 -> print, trigger is 10

		box.receive(12); // - 12
		box.receive(13); // - 12 13
		box.receive(11); // 11 12 13 -> print, trigger is 11

	}
}
