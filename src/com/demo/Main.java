package com.demo;

import com.demo.DataStructuresAndAlgorithms.Array;
import com.demo.DataStructuresAndAlgorithms.LinkedList;
import com.demo.FlightQuote.FlightService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //arrayDemo();
        linkedListDemo();
        //flightQuoteDemo();
    }

    private static void arrayDemo() {
        var array = new Array(3);
        array.insert(10);
        array.insert(20);
        array.insert(50);
        array.insert(50);
        array.insert(30);
        System.out.print("insert: ");
        array.print();

        System.out.println("getSize: " + array.getSize());
        System.out.println("getValue: " + array.getValue(1));
        System.out.println("indexOf: " + array.indexOf(30));
        System.out.println("indexOf: " + array.indexOf(100));
        System.out.println("max: " + array.max());

        array.insertAt(2, 44);
        System.out.print("insertAt: ");
        array.print();

        array.removeAt(1);
        System.out.print("removeAt: ");
        array.print();

        var other = new Array(5);
        other.insert(0);
        other.insert(60);
        other.insert(30);
        other.insert(30);
        other.insert(50);
        var intersect = array.intersect(other);
        System.out.print("intersect: ");
        intersect.print();

        array.reverse();
        System.out.print("reverse: ");
        array.print();
    }

    private static void linkedListDemo() {
        var linkedList = new LinkedList();

        linkedList.addLast(10);
        linkedList.addLast(20);
        linkedList.addLast(30);
        System.out.print("addLast: ");
        linkedList.print();

        linkedList.addFirst(5);
        System.out.print("addFirst: ");
        linkedList.print();

        System.out.println("indexOf: " + linkedList.indexOf(20));
        System.out.println("indexOf: " + linkedList.indexOf(40));
        System.out.println("cointains: " + linkedList.cointains(20));
        System.out.println("cointains: " + linkedList.cointains(40));

        linkedList.deleteFirst();
        System.out.print("deleteFirst: ");
        linkedList.print();

        linkedList.deleteLast();
        System.out.print("deleteLast: ");
        linkedList.print();
    }

    private static void flightQuoteDemo() {
        var startTime = LocalTime.now();
        var flightService = new FlightService();
        var flightQuotes = flightService.getQuotes();

        var futureFlightQuotes = flightQuotes
                .map(futureFlightQuote -> futureFlightQuote
                        .thenAcceptAsync(result -> System.out.println(result.toString())))
                .collect(Collectors.toList());

        CompletableFuture
                .allOf(futureFlightQuotes.toArray(new CompletableFuture[0]))
                .thenRun(() -> {
                    var endTime = LocalTime.now();
                    var duration = Duration.between(startTime, endTime);
                    System.out.println("All completed");
                    System.out.println("Retrieved all quotes in " + (duration.toMillis()) + " msec.");
                })
                .join();
    }
}
