package com.demo.FlightQuote;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FlightService {
    public Stream<CompletableFuture<FlightQuote>> getQuotes() {
        String[] sites = { "site1", "site2", "site3" };

        return Arrays
            .stream(sites)
            .map(this::getQuote);
    }

    public CompletableFuture<FlightQuote> getQuote(String site) {
        return CompletableFuture
            .supplyAsync(() -> {
                System.out.println("Getting quote from " + site);
                threadSleep();
                return new FlightQuote(site, getRandomPrice());
            });
    }

    private int getRandomPrice() {
        var random = new Random();
        return 100 + random.nextInt(10);
    }

    private void threadSleep() {
        var random = new Random();

        try {
            Thread.sleep(1000 + random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
