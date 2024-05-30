package uk.co.argon.argon.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import uk.co.argon.argon.datastructures.hashtable.HashTable;
import uk.co.argon.argon.datastructures.hashtable.HashTableSeparateChaining;
import uk.co.argon.argon.datastructures.heap.Heap;
import uk.co.argon.argon.datastructures.heap.MinHeap;
import uk.co.argon.argon.datastructures.heap.MinHeapImpl;
import uk.co.argon.argon.datastructures.linkedlist.LinkedList;
import uk.co.argon.argon.datastructures.linkedlist.SinglyLinkedList;
import uk.co.argon.argon.datastructures.queue.Queue;
import uk.co.argon.argon.datastructures.stack.Stack;
import uk.co.argon.argon.datastructures.tree.BinarySearchTree;
import uk.co.argon.argon.datastructures.tree.Tree;

public class MainClass {

	public static void main(String[] args) {
		//quicktest();
		//linkedList();
		//stack();
		//queue();
		//heap();
		//hashTable();
		trees();
	}
	
	private static void trees() {
		Tree<Integer> t = new BinarySearchTree<>();
		Arrays.asList(13,6,14,4,10,17,5,8,12,16,20,7,9,11,19).forEach(e -> t.insert(e));
		System.out.println("\n=============== In Order ===============");
		t.printInOrder();
		System.out.println("========================================\n");
		System.out.println("\n=============== Post Order =============");
		t.printPostOrder();
		System.out.println("========================================\n");
		System.out.println("\n=============== Pre Order ==============");
		t.printPreOrder();
		System.out.println("========================================\n");
		System.out.println("\n=============== Level Order ============");
		t.printLevelOrder();
		System.out.println("========================================\n");
		
		System.out.println("Height: " + t.height());
		
		t.remove(4);
		t.printLevelOrder();
		
		t.remove(20);
		t.printLevelOrder();
		
		t.remove(16);
		t.printLevelOrder();
		
		t.remove(1);
		t.printLevelOrder();
		
		
		System.out.println("\nContains 5: " + t.contains(5));
		System.out.println("Contains 15: " + t.contains(15));
		System.out.println("isEmpty(): " + t.isEmpty());
		System.out.println("size(): " + t.size());
		t.clear();
		System.out.println("isEmpty(): " + t.isEmpty());
		System.out.println("size(): " + t.size());
		t.printLevelOrder();
	}

	private static void quicktest() {
		System.out.println((-15 & Integer.MAX_VALUE)%11);
		System.out.println((15 & Integer.MAX_VALUE));
		System.out.println(-15%11);
	}
	
	private static void hashTable() {
		HashTable<Integer, String> ht = new HashTableSeparateChaining<>();
		System.out.println(ht);
		ht.put(15, "Quachett");
		ht.put(1, "RvP");
		ht.put(5, "Carrick");
		ht.put(515, "Bruno");
		ht.put(89, "Martial");
		ht.put(12, "Evans");
		ht.put(2, "Mainoo");
		ht.put(85, "Garnacho");
		ht.put(28, "Rashford");
		ht.put(19, "McTominay");
		ht.put(91, "Welbeck");
		ht.put(95, "Kwambala");
		ht.put(3, "Shaw");
		ht.put(3, "Shaw");
		System.out.println(ht.size());
		System.out.println(ht);
		
		System.out.println("Get: " + ht.get(15));
		
		System.out.println("Replace Evans: " + ht.replace(12, "Smalling"));
		System.out.println(ht);

		System.out.println("Remove Welbeck: " + ht.remove(91));
		System.out.println(ht);

		System.out.println("Remove None: " + ht.remove(9));
		System.out.println(ht);
		
		System.out.println(ht.containsKey(151));
		
		System.out.println(ht.getEntry(15));
		
		System.out.println(ht.getKeys());
		
		System.out.println(ht.getValues());
		System.out.println(ht.isEmpty());
		
		ht.clear();
		System.out.println(ht);
		System.out.println(ht.isEmpty());
	}
	
	private static void heap() {
		//Heap<Integer> heap = new MinHeap<>(Arrays.asList(5,6,12,8,7,14,19,13,12,11));
		Heap<Integer> heap = new MinHeapImpl<>(Arrays.asList(5,6,12,8,7,14,19,13,12,11));
		System.out.println("Heap size: " + heap.size());
		heap.print();
		
		heap.add(1);
		heap.print();
		
		heap.add(13);
		heap.print();
		
		heap.add(4);
		heap.print();
		
		heap.add(0);
		heap.print();
		
		heap.add(10);
		heap.print();
		System.out.println("\n=================================================\n\n");

		//heap = new MinHeap<>(Arrays.asList(1,5,1,8,6,2,2,13,12,11,7,2,15,3,10));
		heap = new MinHeapImpl<>(Arrays.asList(1,5,1,8,6,2,2,13,12,11,7,2,15,3,10));
		System.out.println("Heap size: " + heap.size());
		heap.print();
		
		System.out.println("poll(): " + heap.poll());
		heap.print();
		
		System.out.println("remove index 8: " + heap.remove(8));
		heap.print();
		
		System.out.println("Remove Object 3: " + heap.remove((Object)3));
		heap.print();
		
		System.out.println("poll(): " + heap.poll());
		heap.print();
		
		System.out.println("remove index 8: " + heap.remove(4));
		heap.print();
		
	}
	
	private static void queue() {
		Queue<Integer> queue = new Queue<Integer>(1);
		System.out.println(queue.isEmpty());
		
		
		Arrays.asList(15,21,85,777,96,75,3,4,6,8,3,9,11).forEach(e -> queue.offer(e));
		queue.print();
		
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		queue.print();
		
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
		//queue.clear();
		
		queue.print();
		System.out.println(queue.poll());
	}
	
	private static void stack() {
		Stack<Integer> stack = new Stack<Integer>(null);
		System.out.println(stack.isEmpty());
		
		
		Arrays.asList(15,21,85,777,96,75,3,4,6,8,3,9,11).forEach(e -> stack.push(e));
		stack.print();
		
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		stack.print();
		
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());
		//stack.clear();
		
		stack.print();
		System.out.println(stack.pop());
	}
	 
	private static void linkedList() {
		LinkedList<Integer> ll = new SinglyLinkedList<>(20);
		//LinkedList<Integer> ll = new DoublyLinkedList<>(20);
		ll.add(1);
		System.out.println("Get first: " + ll.getFirst());
		System.out.println("Get Last: " + ll.getLast());
		ll.addLast(888);
		ll.add(7);
		ll.print();
		
		Iterator<Integer> itr1 = ll.iterator();
		
		for(Integer i: ll)
			System.out.println("\t=> i: " + i);
		
		while(itr1.hasNext())
			System.out.println(itr1.next());
		
		System.out.println("Laast: " + ll.getLast());
		
		Arrays.asList(15,21,85,96,75,3,4,6,8,3,9,11).forEach(e -> ll.add(e));
		
		System.out.println("Size: " + ll.size());
		
		System.out.println(ll.get(2));
		
		ll.print();
		
		ll.printReverse();
		
		//ll.clear();
		ll.add(4, 1515);
		
		ll.print();
		
		ll.addLast(753);
		
		ll.print();
		
		System.out.println(ll.contains(3));
		
		System.out.println(ll.indexOf(3));
		
		System.out.println(ll.lastIndexOf(3));
		
		System.out.println("index of 1515: " + ll.indexOf(1515));
		
		System.out.println("Removing index 3: " + ll.remove(3));
		
		ll.print();
		
		System.out.println("Removing 3: " + ll.remove((Object) 3));
		
		ll.print();
		
		System.out.println("Polling: " + ll.poll());
		
		ll.print();
		
		ll.remove();
		
		ll.print();
		
		System.out.println("rev: " + ll.remove(ll.size()-3));
		
		ll.print();
		
		System.out.println(ll.remove((Object)11));
		
		System.out.println(ll.contains(15));
		
		System.out.println("Pushing 777: " + ll.push(777));
		
		System.out.println("popping: " + ll.pop());
		
		ll.print();
		
		Iterator<Integer> itr = ll.iterator();
		
		while(itr.hasNext())
			System.out.println(itr.next());
		
		ll.clear();
		
		ll.print();
		
		ll.printReverse();
	}

}
