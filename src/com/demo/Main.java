package com.demo;

import com.demo.DataStructuresAndAlgorithms.Array;
import com.demo.FlightQuote.FlightService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //arrayDemo();
        flightQuoteDemo();
    }

    private static void arrayDemo() {
        var array = new Array(3);
        array.insert(10);
        array.insert(20);
        array.insert(30);
        array.print();

        System.out.println("getValue: " + array.getValue(1));
        System.out.println("indexOf: " + array.indexOf(30));

        array.removeAt(1);
        array.print();
    }

    private static void flightQuoteDemo() {
        var startTime = LocalTime.now();
        var flightService = new FlightService();
        var flightQuotes = flightService.getQuotes();

        var futureFlightQuotes = flightQuotes
                .map(futureFlightQuote -> futureFlightQuote
                        .thenAcceptAsync(result -> {
                            System.out.println(result.toString());
                        }))
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
