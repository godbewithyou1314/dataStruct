package org.hywel.algorithm.main;

import org.hywel.algorithm.binarysearch.GuessNumber;

public class BinarySearchMain {
    public static void main(String[] args) {
        GuessNumber guessGame = new GuessNumber(6);
        System.out.println(guessGame.guessNumber(10));
    }
}
