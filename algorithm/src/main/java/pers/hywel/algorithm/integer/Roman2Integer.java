/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package pers.hywel.algorithm.integer;

/**
 * Description:
 * 罗马数字转int
 *
 * @author zhangwei111
 * Created on 2019-12-27 15:16
 */
public class Roman2Integer {

    /**
     * 减去额外值，方法二
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum=0;
        if(s.indexOf("IV")!=-1){sum-=2;}
        if(s.indexOf("IX")!=-1){sum-=2;}
        if(s.indexOf("XL")!=-1){sum-=20;}
        if(s.indexOf("XC")!=-1){sum-=20;}
        if(s.indexOf("CD")!=-1){sum-=200;}
        if(s.indexOf("CM")!=-1){sum-=200;}

        char c[]=s.toCharArray();
        int count=0;

        for(;count<=s.length()-1;count++){
            if(c[count]=='M') sum+=1000;
            if(c[count]=='D') sum+=500;
            if(c[count]=='C') sum+=100;
            if(c[count]=='L') sum+=50;
            if(c[count]=='X') sum+=10;
            if(c[count]=='V') sum+=5;
            if(c[count]=='I') sum+=1;

        }
        return sum;
    }

    /**
     *
     * @param s
     * @return
     */
    public int romanToIntByMySelf(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'M':
                    result += 1000;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'C':
                    if (i + 1 < s.length()) {
                        if (s.charAt(i + 1) == 'M') {
                            result += 900;
                            i++;
                            break;
                        } else if (s.charAt(i + 1) == 'D') {
                            result += 400;
                            i++;
                            break;
                        }
                    }
                    result += 100;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'X':
                    if (i + 1 < s.length()) {
                        if (s.charAt(i + 1) == 'C') {
                            result += 90;
                            i++;
                            break;
                        } else if (s.charAt(i + 1) == 'L') {
                            result += 40;
                            i++;
                            break;
                        }
                    }
                    result += 10;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'I':
                    if (i + 1 < s.length()) {
                        if (s.charAt(i + 1) == 'X') {
                            result += 9;
                            i++;
                            break;
                        } else if (s.charAt(i + 1) == 'V') {
                            result += 4;
                            i++;
                            break;
                        }
                    }
                    result += 1;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Roman2Integer roman2Integer = new Roman2Integer();
        String test = "LVIII";
        System.out.println(roman2Integer.romanToInt(test));
    }
}
