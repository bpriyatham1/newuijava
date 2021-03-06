package com.qa.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static final long PAGE_LOAD_TIMEOUT = 15;
    public static final long IMPLICIT_WAIT = 10;

    public static String generateRandomName(int length) {
        String rn = RandomStringUtils.randomAlphabetic(length);
        return rn;
    }

}

