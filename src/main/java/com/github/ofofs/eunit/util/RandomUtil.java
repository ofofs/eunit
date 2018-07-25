package com.github.ofofs.eunit.util;

import com.github.ofofs.eunit.annotation.Rule;
import com.github.ofofs.reggen.RegexGenerator;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
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
        // 有正则表达式的时候不再使用其他规则
        if (StringUtils.isNotEmpty(rule.regex())) {
            return RegexGenerator.generate(rule.regex());
        }

        // 字符串的长度在minLength和maxLength之间随机
        Random random = new Random();
        int randomLength = random.nextInt(rule.maxLength() - rule.minLength() + 1) + rule.minLength();

        return randomString(randomLength);
    }

    /**
     * 随机生成一个Integer
     *
     * @return 返回Integer
     */
    public static Integer randomInteger() {
        Random random = new Random();
        return random.nextInt(100000);
    }

    /**
     * 按规则生成一个Integer
     *
     * @param rule 规则
     * @return 返回Integer
     */
    public static Integer randomInteger(Rule rule) {
        Random random = new Random();
        int max = rule.max();
        int min = rule.min();

        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 随机生成一个Float
     *
     * @return 返回Float
     */
    public static Float randomFloat() {
        Random random = new Random();
        float result = random.nextFloat() + randomInteger();

        BigDecimal bg = new BigDecimal(result);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 按规则生成一个Float
     *
     * @param rule 规则
     * @return 返回Float
     */
    public static Float randomFloat(Rule rule) {
        Random random = new Random();
        int max = rule.max();
        int min = rule.min();

        float result = random.nextInt(max - min) + min + random.nextFloat();

        BigDecimal bg = new BigDecimal(result);
        return bg.setScale(rule.precision(), BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 随机生成一个Byte
     *
     * @return 返回Byte
     */
    public static Byte randomByte() {
        Random random = new Random();
        return Byte.parseByte(String.valueOf(random.nextInt(127)));
    }

    /**
     * 按规则生成一个Byte
     *
     * @param rule 规则
     * @return 返回Byte
     */
    public static Byte randomByte(Rule rule) {
        Random random = new Random();
        int max = rule.max();
        int min = rule.min();

        return Byte.parseByte(String.valueOf(random.nextInt(max - min + 1) + min));
    }

    /**
     * 随机生成一个Character
     * 目前只生成：0-9 a-z A-Z
     * 对应ascii:48-57 65-90 97-122
     *
     * @return 返回Character
     */
    public static Character randomCharacter() {
        int[] factors = {48, 65, 97};
        Random random = new Random();
        int factor = factors[random.nextInt(3)];
        if (factor == factors[0]) {
            return (char) (factor + random.nextInt(10));
        }

        return (char) (factor + random.nextInt(26));
    }

    /**
     * 随机生成一个Short
     *
     * @return 返回Short
     */
    public static Short randomShort() {
        Random random = new Random();

        return Short.parseShort(String.valueOf(random.nextInt(Short.MAX_VALUE)));
    }

    /**
     * 按规则生成一个Short
     *
     * @param rule 规则
     * @return 返回Short
     */
    public static Short randomShort(Rule rule) {
        Random random = new Random();
        int max = rule.max();
        int min = rule.min();

        return Short.parseShort(String.valueOf(random.nextInt(max - min + 1) + min));
    }

    /**
     * 按规则生成一个Long
     *
     * @param rule 规则
     * @return 返回Long
     */
    public static Long randomLong(Rule rule) {
        Random random = new Random();
        int max = rule.max();
        int min = rule.min();
        return nextLong(random, max - min + 1L) + min;
    }

    private static Long nextLong(Random random, Long bound) {
        long bits, val;
        do {
            bits = (random.nextLong() << 1) >>> 1;
            val = bits % bound;
        } while (bits - val + (bound - 1) < 0L);
        return val;
    }

    /**
     * 随机生成一个Long
     *
     * @return 返回Long
     */
    public static Long randomLong() {
        Random random = new Random();
        return random.nextLong();
    }

    /**
     * 随机生成一个Boolean
     *
     * @return 返回Boolean
     */
    public static Boolean randomBoolean() {
        Random random = new Random();
        return random.nextInt(2) == 1;
    }

    /**
     * 按规则生成一个BigDecimal
     *
     * @param rule 规则
     * @return 返回BigDecimal
     */
    public static BigDecimal randomBigDecimal(Rule rule) {
        return BigDecimal.valueOf(randomFloat(rule)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 随机生成一个BigDecimal
     *
     * @return 返回BigDecimal
     */
    public static BigDecimal randomBigDecimal() {
        return BigDecimal.valueOf(randomFloat()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 随机生成一个Date
     * 以当前时间为基础的前后10年
     *
     * @return 返回Date
     */
    public static Date randomBigDate() {
        Date date = new Date();
        Random random = new Random();
        // 10年
        long offset = 10L * 365 * 24 * 60 * 60 * 1000;
        offset = nextLong(random, offset) * (randomBoolean() ? -1 : 1);
        date.setTime(date.getTime() + offset);
        return date;
    }
}
