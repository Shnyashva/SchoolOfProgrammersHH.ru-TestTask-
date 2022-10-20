package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void calculateSumOfSalaries_Qqg() {
        int[] one = new int[] {1, 3};
        int[] two = new int[] {1, 1};
        assertEquals(3, Main.calculateSumOfSalaries(3, one, two,
                2, 2));
    }
}