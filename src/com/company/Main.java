package com.company;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
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
