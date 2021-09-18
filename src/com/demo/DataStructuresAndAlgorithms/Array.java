package com.demo.DataStructuresAndAlgorithms;

public class Array {
    private int size;
    private int[] items;

    public Array(int length) {
        this.items = new int[length];
    }

    public  int getSize() {
        return size;
    }

    public int getValue(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        return items[index]; // O(1)
    }

    public int indexOf(int item) {
        int index = -1;

        // Performance O(n) -> Linear
            // Worst Case: O(n) Matching item might be at the end or not found
            // Best Case: O(1) Matching item might be at the beginning
        // Size O(1) -> int i
        for (int i = 0; i < size; i++) {
            if (items[i] == item) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void insert(int number) {
        if (size == items.length) {
            var newItems = new int[size * 2]; // Increase array by 1

            // Performance O(n) -> Linear
                // Worst case: O(n) Array is full
                // Best Case: O(1) Array is not full (count < numbers.length)
            // Size O(1) -> int i
            for (int i = 0; i < items.length; i++) {
                newItems[i] = items[i];
            }

            items = newItems;
        }

        items[size] = number;
        size++; // Increase by 1
    }

    public void insertAt(int index, int number) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        // Performance O(n) -> Linear
            // Worst case: O(n) index == 0; Add before first item and shift all values
            // Best case: O(1) index == numbers.length; Add after last item
        // Size O(1) -> int i
        // Shift values
            // index 0 -> 1
            // index 1 -> 2
        for (int i = index; i < index; i++) {
            items[i] = items[i - 1]; // Shift values
        }

        items[index] = number;
        size++; // Increase by 1
    }

    public Array intersect(Array other) {
        var intersection = new Array(Math.max(size, other.getSize()));

        // Performance O(n) -> Linear; for items
        // Performance O(n) -> Linear; for other
            // Total Performance O(2n)
        for (int item : items)
            if (other.indexOf(item) >= 0)
                intersection.insert(item);

        return intersection;
    }

    public int max()  {
        var max = 0;

        // Performance O(n) -> Linear
        // Size O(1) -> int i
        for (var i = 0; i < size; i++) {
            if (items[i] > max) {
                max = items[i];
            }
        }

        return max;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        // Performance O(n) -> Linear
            // Worst case: O(n) index == 0; Remove first item and shift all values
            // Best case: O(1) index == numbers.length; Remove last items
        // Size O(1) -> int i
        for (int i = index; i < size; i++) {
            if (i + 1 < items.length) {
                items[i] = items[i + 1]; // Shift values
            }
        }

        size--; // Decrease by 1
    }

    public void reverse() {
        var newItems = new int[size];

        // Performance O(n) -> Linear
        // Size O(1) -> int i
        int y = size - 1;
        for (var i = 0; i < size; i++) {
            newItems[y] = items[i];
            y--;
        }

        items = newItems;
    }

    public void print() {
        // Performance O(3+n) -> Linear
        // Size O(1) -> int i
        System.out.print("["); // +1
        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);

            if ((i + 1) < size) {
                System.out.print(", "); // +1
            }
        }
        System.out.print("]\n"); // +1
    }
}
