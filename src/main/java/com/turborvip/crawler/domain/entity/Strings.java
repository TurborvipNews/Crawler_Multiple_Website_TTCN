package com.turborvip.crawler.domain.entity;

import java.util.Arrays;

public class Strings {
    public static String repeat(char c, int count) {
        char[] arr = new char[count];
        Arrays.fill(arr, c);
        return new String(arr);
    }
}
