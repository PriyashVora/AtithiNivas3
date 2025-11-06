package com.atithinivas.loyaltyservice.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public final class IdUtil {
    private IdUtil() {}

    public static String txnCode() {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int rnd = ThreadLocalRandom.current().nextInt(1000, 9999);
        return "PTX-" + ts + "-" + rnd;
    }
}
