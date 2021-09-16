package com.demo.DataStructuresAndAlgorithms;

public class Array {
    private int count;
    private int[] items;

    public Array(int length) {
        this.items = new int[length];
    }

    public int getValue(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }

        return items[index]; // O(1)
    }

    public int indexOf(int item) {
        int index = -1;

        // Performance O(n) -> Linear
        // Worst Case: O(n) Matching item might be at the end or not found
        // Bes Case: O(1) Matching item might be at the beginning
        // Size O(1) -> int i
        for (int i = 0; i < count; i++) {
            if (items[i] == item) {
                index = i;
                break;
            }
        }

        return index;
    }

    public void insert(int number) {
        if (count == items.length) {
            var newNumbers = new int[count * 2]; // Increase array by 1

            // Performance O(n) -> Linear
                // Worst case: O(n) Array is full
                // Best Case: O(1) Array is not full (count < numbers.length)
            // Size O(1) -> int i
            for (int i = 0; i < items.length; i++) {
                newNumbers[i] = items[i];
            }

            items = newNumbers;
        }

        items[count] = number;
        count++; // Increase by 1
    }

    public void removeAt(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }

        // Performance O(n) -> Linear
            // Worst case: O(n) index == 0; Remove first item and shift all values
            // Best case: O(1) index == numbers.length; Remove last items
        // Size O(1) -> int i
        for (int i = index; i < count; i++) {
            if (i + 1 < items.length) {
                items[i] = items[i + 1]; // Shift values
            }
        }

        count--; // Decrease by 1
    }

    public void print() {
        // Performance O(n) -> Linear
        // Size O(1) -> int i
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }
}
