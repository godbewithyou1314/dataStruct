package pers.hywel.algorithm.String;

/**
 * Description:
 * String to Integer (atoi),越界就输入int得最大值或者最小值
 *
 * @author RobertZhang
 *

 */
public class StringToInteger {
    public static int myAtoi(String str) {
        int result = 0;
        int count = 0;
        int sign = 0;
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == 32 && count==0)
                continue;
            if (str.charAt(i) == 43 && count == 0) {
                sign = 1;
                count++;
                continue;
            }
            if (str.charAt(i) == 45 && count == 0) {
                sign = -1;
                count++;
                continue;
            }
            if (str.charAt(i) < 48 || str.charAt(i) > 57) {
                return sign==0 ? result : sign * result;
            } else {
                int newResult = result*10 + (str.charAt(i) - 48);
                if (result != 0 && (newResult / 10) != result)
                   return sign==-1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                result = result==0 ? str.charAt(i) - 48 : newResult;
                count++;
            }
        }
        return sign==0 ? result : sign * result;
    }

    public static void main(String[] args) {
        String test = "+-4";
        System.out.println(myAtoi(test));
    }
}
