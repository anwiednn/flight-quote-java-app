package com.demo.DataStructuresAndAlgorithms;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList {
    private Node first;
    private Node last;
    private int size;

    public void addFirst(int value) {
        var node = new Node(value);

        // Performance O(2)
            // O(1) -> First needs to be set
            // O(1) -> Check and set last
        if (isEmpty()) {
            first = last = node;
        } else {
            // Set current first as next value for new node
            // Therefore it becomes the second item
            node.setNext(first);
            first = node;
        }

        size++; // Increase by 1
    }

    public void addLast(int number) {
        addLast(number, false);
    }

    public void addLast(int number, boolean linkToFirst) {
        var node = new Node(number);

        // Performance O(2)
            // O(1) -> Last needs to be set
            // O(1) -> Check and set first
        if (isEmpty()) {
            first = last = node;
        } else {
            // Set current last next value to new node
            // Therefore it becomes the second to last item
            last.setNext(node);
            last = node;
        }

        if (linkToFirst) {
            last.setNext(first);
        }

        size++; // Increase by 1
    }

    public void deleteFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (first == last) {
            first = last = null;
        } else {
            var second = first.getNext();
            first.next = null;
            first = second;
        }

        size--; // Decrease by 1
    }

    public void deleteLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        var currentNode = first;

        // Performance O(n-1) -> Linear; Traverse to second to last item
        while (currentNode != null) {
            if (currentNode.getNext() == last) {
                currentNode.setNext(null);
                last = currentNode;
                break;
            }

            currentNode = currentNode.getNext();
        }

        size--; // Decrease by 1
    }

    public boolean cointains(int number) {
        return indexOf(number) != -1; // O(n)
    }

    public int getSize() {
        return size;
    }

    public int getKthFromTheEnd(int k) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else if (k < 0 || k > size) {
            throw new InvalidParameterException();
        }

        // [10, 20, 30, 40, 50]
        //          3   2   1      3rd item from end
        //          *1      *2      *1 == first pointer; *2 == second pointer; 2 nodes ahead
        var firstPointer = first;
        var secondPointer = first;

        // Size O(3) ->
            // O(1) -> i
            // O(2) -> firstPointer, secondPointer
        for (var i = 0; i < k - 1; i++) {         // Move second pointer ahead by (k - 1)
            secondPointer = secondPointer.getNext();
        }

        // Performance O(n) -> Traverse entire list
        while (secondPointer != last) {
            firstPointer = firstPointer.getNext();
            secondPointer = secondPointer.getNext();
        }

        return firstPointer.getValue();
    }

    public int[] getMiddle() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // Uneven node size
            //Number of Nodes       Middle Node
            //      1                   1
            //      3                   2
            //      5                   3
            //      6                   4
        // Even node size
            //      2                  1,2
            //      4                  2,3
            //      6                  3,4

        var middlePointer = first;
        var lastPointer = first;

        // Performance O(n) -> Traverse entire list
        while (lastPointer != last && lastPointer.getNext() != last) {
            middlePointer = middlePointer.getNext();                // Move ahead by one step
            lastPointer = lastPointer.getNext().getNext();          // Move ahead by two steps
        }

        return (size % 2 == 0)
            ? List.of(middlePointer.getValue(), middlePointer.getNext().getValue()).stream().mapToInt(i -> i).toArray()
            : List.of(middlePointer.getValue()).stream().mapToInt(i -> i).toArray();
    }

    public boolean hasLoop() {
        var slowPointer = first;
        var fastPointer = first;

        // Performance O(n) -> Traverse entire list
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;             // Move ahead by one step
            fastPointer = fastPointer.next.next;        // Move ahead by two steps

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }

    public int indexOf(int number) {
        var currentNode = first;
        var index = 0;

        // Performance O(n) -> Linear
            // Worst case: O(n) number at last node item or not found
            // Best case: O(1) number at first node item
        // Size O(2) ->
            // O(1) -> currentNode
            // O(1) -> index
        while (currentNode != null) {
            if (currentNode.getValue() == number) {
                return index;
            }

            currentNode = currentNode.getNext();
            index++;
        }

        return -1;
    }

    public void print() {
        var currentNode = first;

        // Performance O(3+n) -> Linear
        // Size O(1) -> int i
        if (currentNode != null) {
            System.out.print("["); // +1

            while (currentNode != null) {
                System.out.print(currentNode.getValue());
                currentNode = currentNode.getNext();

                if (currentNode != null) {
                    System.out.print(", "); // +1
                }
            }

            System.out.print("]\n"); // +1
        }
    }

    public void reverse() {
        // 1: [10 -> 20 -> 30]
        //     p     c     n
        // 2: [10 <- 20    30]
        //           p     c     n
        // 3: [10 <- 20 <- 30]
        //                 p     c     n  == Complete loop

        if (!isEmpty()) {
            // Performance O(n) -> Traverse entire list
            var previous = first;
            var current = first.next;

            while (current != null) {
                var next = current.getNext();
                current.setNext(previous);
                previous = current;
                current = next;
            }

            last = first;           // Current first node becomes last
            last.setNext(null);     // Set next to null
            first = previous;       // Last previous becomes firat

            /*
            My implementation: O(2n)

            var index = 0;
            var items = new int[size];
            var current = first;

            // Performance O(n) -> Traverse entire list
            while (current != null) {
               items[(size-1)-index] = current.getValue();
               current = current.getNext();
               index++; // Increase by 1
            }

            // Performance O(n) -> Traverse entire items array
            for (var i = 0; i < items.length; i++) {
                addLast(items[i]);  // O(1)
                deleteFirst();      // O(1)
            }*/
        }
    }

    public int[] toArray() {
        var index = 0;
        var items = new int[size];
        var current = first;

        // Performance O(n) -> Traverse entire list
        // Size O(1+n) ->
            // O(1) -> index
            // O(n) -> items; n = size
        while (current != null) {
            items[index++] = current.getValue();
            current = current.getNext();
        }

        return items;
    }

    private boolean isEmpty() {
        return first == null;
    }

    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }
    }
}
