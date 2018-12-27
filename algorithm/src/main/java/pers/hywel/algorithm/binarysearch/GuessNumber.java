package pers.hywel.algorithm.binarysearch;

public class GuessNumber {
    private int goalNum ;
    public GuessNumber(int goalNum){
        this.goalNum = goalNum;
    }

    private int guess(int guess){
        return Integer.compare(guess,goalNum);
    }

    public int guessNumber(int n){
        int left = 0;
        int right = n;
        int mid;
        if(n<this.goalNum) return -1;
        while (true) {
            mid = (left + right) / 2;
            if(guess(mid) == 0) return mid;
            else if (guess(mid) == -1) {
                left = mid + 1;
            } else if (guess(mid) == 1) {
                right = mid - 1;
            }
        }
    }
}
