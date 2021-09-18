package com.demo.DataStructuresAndAlgorithms;

import org.w3c.dom.Node;

public class LinkedList {
    private class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public int getValue() {
            return value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

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

        size++; // Increase by 1
    }

    public void deleteFirst() {
        if (!isEmpty()) {
            if (first == last) {
                first = last = null;
            } else {
                var second = first.getNext();
                first.next = null;
                first = second;
            }

            size--; // Decrease by 1
        }
    }

    public void deleteLast() {
        if (!isEmpty()) {       // Could also throw exception if list is empty
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
    }

    public boolean cointains(int number) {
        return indexOf(number) != -1; // O(n)
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

    public int getSize() {
        return size;
    }

    private boolean isEmpty() {
        return first == null;
    }
}
