package pers.hywel.algorithm.integer;

/**
 * 0 --》 0
 * 103 --》 一百零三
 * 1111 --》 一千一百一十一
 */
public class Integer2ChineseWords {

    public static String speakInt(Integer num) {
        // 1. 边界处理： 0 和负数
        if (num == 0) {
            return "零";
        }
        String sign = num < 0 ? "负" : "";
        num = Math.abs(num);

        // 2. 定义数字和单位
        String[] numStrs = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] baseStrs = new String[]{"", "十", "百", "千"};
        String[] highStrs = new String[]{"", "万", "亿"};

        StringBuilder result = new StringBuilder();
        // 位数，控制添加当前读取到哪位。千、百、十
        int turn = 0;
        while (num % 10 == 0) {
            turn++;
            num /= 10;
        }
        // 处理连续0情况
        int continueZeroCount = 0;
        while (num != 0) {
            int cur = num % 10;
            if (cur == 0) {
                continueZeroCount++;
                if (continueZeroCount == 1) {
                    result.append("零");
                }
            } else {
                continueZeroCount = 0;
                result.append(highStrs[turn / baseStrs.length]);
                result.append(baseStrs[turn % baseStrs.length]);
                result.append(numStrs[num % 10]);
            }
            num /= 10;
            turn++;
        }
        result.append(sign);
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(speakInt(-1111));
    }

}
