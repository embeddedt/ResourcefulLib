package com.teamresourceful.resourcefullib.common.yabn.base;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public sealed interface YabnElement permits YabnObject, YabnPrimitive, YabnArray {

    byte EOD = 0x00; // end of data

    /**
     * @param key nullable if is not part a key value pair;
     */
    byte[] toData(@Nullable String key);

    default byte[] toData() {
        return toData(null);
    }

    static byte[] key(byte id, String string) {
        return ArrayUtils.addAll(new byte[]{id}, cString(string));
    }

    static byte[] cString(String string) {
        return ArrayUtils.add(string.getBytes(StandardCharsets.UTF_8), YabnElement.EOD);
    }
}