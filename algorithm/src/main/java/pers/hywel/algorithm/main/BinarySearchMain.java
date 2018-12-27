package pers.hywel.algorithm.main;

import pers.hywel.algorithm.binarysearch.GuessNumber;

import java.util.HashSet;

public class BinarySearchMain {
    public static void main(String[] args) {
        GuessNumber guessGame = new GuessNumber(6);
        System.out.println(guessGame.guessNumber(10));

        HashSet hashSet = new HashSet();
    }
}
