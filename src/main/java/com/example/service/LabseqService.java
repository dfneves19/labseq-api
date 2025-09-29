package com.example.service;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.HashMap;


public class LabseqService {

    private final HashMap<Integer,BigInteger> cache = new HashMap<>();

    public LabseqService() {
        this.cache.put(0, BigInteger.ZERO);
        this.cache.put(1,BigInteger.ONE);
        this.cache.put(2,new BigInteger("1"));
        this.cache.put(3,new BigInteger("1"));
    }

    public BigInteger getSequence(int n) {

        if (n < 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } else if (n < 4) {
            return cache.get(n);
        }

        int counter = 4;

        while (counter <= n) {
            cache.put(counter++, BigInteger.ONE);
        }

        return cache.get(n-4).add(cache.get(n-3));
    }

}
