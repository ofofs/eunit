package com.ofofs.eunit.util;

import java.util.UUID;

/**
 * @author kangyonggan
 * @since 2018/7/21 0021
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

}
