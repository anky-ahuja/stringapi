package com.anky.xoriant.stringapi.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Utility {

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
