package com.balionis.java5;

// invert a string e.g. "cat"
public class MyApp2 {

    public static void main(String[] args) {

        System.out.println("main: test(cat)=" + test("cat"));

        System.out.println("main: done");
    }

    static String test(String word) {
        if (word.length() <= 1) {
            return word;
        } else {
            return test(word.substring(1)) + word.charAt(0);
        }
    }
}

