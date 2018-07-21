package com.ofofs.eunit.util;

import com.ofofs.eunit.annotation.Rule;

import java.util.Random;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public final class RandomUtil {

    private static final char[] DATA = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private RandomUtil() {
    }

    /**
     * 随机生成一个字符串,长度在1~20之间随机
     *
     * @return 返回字符串
     */
    public static String randomString() {
        return randomString(new Random().nextInt(20) + 1);
    }

    /**
     * 随机生成一个指定长度的字符串
     *
     * @param length 长度
     * @return 返回字符串
     */
    public static String randomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(DATA[random.nextInt(DATA.length)]);
        }

        return sb.toString();
    }

    /**
     * 按规则生成一个字符串
     *
     * @param rule 规则
     * @return 返回字符串
     */
    public static String randomString(Rule rule) {
        // 字符串的长度在minLength和maxLength之间随机
        Random random = new Random();
        int randomLength = random.nextInt(rule.maxLength() - rule.minLength() + 1) + rule.minLength();

        return randomString(randomLength);
    }
}
