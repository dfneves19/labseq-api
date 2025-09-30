package com.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.HashMap;

@ApplicationScoped
public class LabseqService {

    private final HashMap<Integer,BigInteger> cache = new HashMap<>();

    public LabseqService() {
        this.cache.put(0, BigInteger.ZERO);
        this.cache.put(1,BigInteger.ONE);
        this.cache.put(2,BigInteger.ZERO);
        this.cache.put(3,BigInteger.ONE);
    }

    public BigInteger getSequence(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n should be greater than 0");
        } else if (n < 4) {
            return cache.get(n);
        }

        int counter = cache.size();

        while (counter <= n) {
            BigInteger valueToAdd = cache.get(counter-4).add(cache.get(counter-3));
            cache.put(counter, valueToAdd);
            counter++;
        }

        return cache.get(n);
    }

}
