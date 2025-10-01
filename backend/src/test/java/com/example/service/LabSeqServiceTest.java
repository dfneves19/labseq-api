package com.example.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class LabSeqServiceTest {

    LabseqService service;

    @BeforeEach
    void setUp() {
        service = new LabseqService();
    }

    @Test
    void testPositiveNumber() {
        int n = 30;
        BigInteger expected = new BigInteger("156");
        assertEquals(expected,service.getSequence(n));
    }

    @Test
    void testDefaultValue0() {
        int n = 0;
        BigInteger expected = BigInteger.ZERO;
        assertEquals(expected,service.getSequence(n));
    }

    @Test
    void testDefaultValue1() {
        int n = 1;
        BigInteger expected = BigInteger.ONE;
        assertEquals(expected,service.getSequence(n));
    }

    @Test
    void testDefaultValue2() {
        int n = 2;
        BigInteger expected = BigInteger.ZERO;
        assertEquals(expected,service.getSequence(n));
    }

    @Test
    void testDefaultValue3() {
        int n = 3;
        BigInteger expected = BigInteger.ONE;
        assertEquals(expected,service.getSequence(n));
    }

    @Test
    void testNegativeNumber() {
        int n = -1;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.getSequence(n));
        assertEquals("n should be greater than 0",exception.getMessage());
    }

}
