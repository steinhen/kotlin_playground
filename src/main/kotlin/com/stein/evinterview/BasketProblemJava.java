package com.stein.evinterview;

import java.util.Arrays;
import java.util.List;

public class BasketProblemJava {
    /**
     * We have a given amount of capital, which we can spend, and a list of
     * unique offers, which have an offer price and a sales price. We can
     * use our capital to buy offers at their price and then sell them
     * at their sales price.
     *
     * The task is to write a program that selects a subset of offers
     * that results in the highest profit, given a fixed amount of capital.
     * The algorithm needs to be precise. It must reliably compute the
     * optimum, i.e. not a value only close to the optimum. Efficiency is
     * not a requirement.
     *
     * Also, we make a simplification: It is necessary only to output the
     * maximum possible profit, but not the set of offers.
     */

    private static List<Integer> offers = Arrays.asList(1, 3, 1);
    private static List<Integer> sales  = Arrays.asList(3, 5, 3);
    private static Integer capital = 3;
    public static void main(String[] args) {
        printMaximumProfit(); // prints 4
    }
    private static void printMaximumProfit() { }
}