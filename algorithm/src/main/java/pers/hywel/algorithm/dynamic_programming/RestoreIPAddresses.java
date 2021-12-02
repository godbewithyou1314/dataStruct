
package pers.hywel.algorithm.dynamic_programming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * 查询所有可用的IP地址组合（注意：可用！10.010.100.1中010以0开头，算不可用）
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * @author RobertZhangwei
 */
public class RestoreIPAddresses {
    /**
     * 1ms implement
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        dfs(arr, 0, 0, sb, res);
        return res;
    }

    private void dfs(char[] arr, int index, int num, StringBuilder sb, List<String> res) {

        if (num == 4) {
            if (sb.length() == arr.length + 4) {
                res.add(sb.toString().substring(0, sb.length() - 1));
            }
            return;
        }

        // 一位数
        if (index < arr.length) {
            sb.append(arr[index]);
            sb.append('.');
            dfs(arr, index + 1, num + 1, sb, res);
            sb.delete(sb.length() - 2, sb.length());
        }


        // 两位数
        if (index + 1 < arr.length) {
            if (arr[index] != '0') {
                sb.append(arr[index]);
                sb.append(arr[index + 1]);
                sb.append('.');
                dfs(arr, index + 2, num + 1, sb, res);
                sb.delete(sb.length() - 3, sb.length());
            }

        }


        // 三位数
        if (index + 2 < arr.length) {
            int number = Character.getNumericValue(arr[index]) * 100 + Character.getNumericValue(arr[index + 1]) * 10 + Character.getNumericValue(arr[index + 2]);
            if (number <= 255 && arr[index] != '0') {
                sb.append(arr[index]);
                sb.append(arr[index + 1]);
                sb.append(arr[index + 2]);
                sb.append('.');
                dfs(arr, index + 3, num + 1, sb, res);
                sb.delete(sb.length() - 4, sb.length());
            }
        }

    }

    /**
     * implement from discuss
     * @param s
     * @return
     */
    public List<String> restoreIpAddressesWithDiscuss(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for(int i = 1; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }

    /**
     * 递归裁剪实现
     * @param s
     * @return
     */
    public List<String> restoreIpAddressesByMyself(String s) {
        Set<String> result = new HashSet<>();
        if (s.trim().length() > 3) {
            restoreIpAddressRecur(result, "", s, 3);
        }
        return new LinkedList<>(result);
    }

    private void restoreIpAddressRecur(Set<String> result, String pre, String s, int k) {
        if (k < 0 || s.trim().length() == 0) {
            return;
        }
        if (k == 0) {
            if (s.length() < 4 && Integer.parseInt(s) < 256) {
                if (isStartWithZero(s)) return;
                result.add(pre + s);
            }
        } else {
            if (s.length() >= 1) {
                String oneChar = s.substring(0, 1);
                if (Integer.parseInt(oneChar) < 256) {
                    restoreIpAddressRecur(result, pre + oneChar + '.', s.substring(1), k - 1);
                }
            }
            if (s.length() >= 2) {
                String twoChar = s.substring(0, 2);
                if (!isStartWithZero(twoChar) && Integer.parseInt(twoChar) < 256) {
                    restoreIpAddressRecur(result, pre + twoChar + '.', s.substring(2), k - 1);
                }
            }
            if (s.length() >= 3) {
                String threeChar = s.substring(0, 3);
                if (!isStartWithZero(threeChar) && Integer.parseInt(threeChar) < 256) {
                    restoreIpAddressRecur(result, pre + threeChar + '.', s.substring(3), k - 1);
                }
            }
        }
    }

    // 是否以0开头
    private boolean isStartWithZero(String str) {
        return str.length() > 1 && str.charAt(0) == '0';
    }

    // 移除元素前的0
    private String remove0(String str) {
        if (str.length() > 1) {
            int i = -1;
            while (i + 1 < str.length() && str.charAt(++i) == '0');
            str = str.substring(i);
        }
        return str;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        RestoreIPAddresses testClass = new RestoreIPAddresses();
        List<String> result = testClass.restoreIpAddresses("01001010");
        System.out.println(result.toString());
        Long endTime = System.currentTimeMillis();
        System.out.println("运行耗时：" + (endTime - startTime) + "ms");
    }
}
