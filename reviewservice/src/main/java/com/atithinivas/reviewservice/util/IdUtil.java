package com.atithinivas.reviewservice.util;

import java.util.UUID;

public final class IdUtil {
    private IdUtil() {}

    // If you ever want to create external string IDs (like RV-xxxx), use this
    public static String randomExternalId() {
        return "RV-" + UUID.randomUUID().toString().substring(0, 8);
    }
}