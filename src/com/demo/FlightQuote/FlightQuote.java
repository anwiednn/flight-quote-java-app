package com.demo.FlightQuote;

public class FlightQuote {
    private final String site;
    private final double price;

    public FlightQuote(String site, double price) {
        this.site = site;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Quote{site='" + site + "', price='" + price + "'}";
    }
}
