package com.teamresourceful.resourcefullib.common.codecs.yabn;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapLike;
import com.teamresourceful.resourcefullib.common.yabn.base.YabnElement;
import com.teamresourceful.resourcefullib.common.yabn.base.YabnObject;
import com.teamresourceful.resourcefullib.common.yabn.base.YabnPrimitive;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class YabnObjectMapLike implements MapLike<YabnElement> {

    private final YabnObject object;

    public YabnObjectMapLike(YabnObject yabnObject) {
        this.object = yabnObject;
    }

    @Nullable
    @Override
    public YabnElement get(YabnElement key) {
        if (key instanceof YabnPrimitive primitive) return get(YabnOps.getAsString(primitive));
        throw new IllegalArgumentException("Not a string: " + key);
    }

    @Nullable
    @Override
    public YabnElement get(String key) {
        return object.elements().get(key).getOrNull();
    }

    @Override
    public Stream<Pair<YabnElement, YabnElement>> entries() {
        return object.elements().entrySet().stream().map(entry -> Pair.of(YabnPrimitive.ofString(entry.getKey()), entry.getValue()));
    }
}