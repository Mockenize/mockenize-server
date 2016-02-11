package com.mockenize.vendor.hazelcast.predicate;

import com.hazelcast.query.Predicate;

import java.util.Map;

/**
 * Created by rwatanabe on 10/02/16.
 */
public class RegexKeyPredicate implements Predicate<String, Object> {

    private final String value;

    public RegexKeyPredicate(String value) {
        this.value = value;
    }

    @Override
    public boolean apply(Map.Entry<String, Object> mapEntry) {
        return value.matches(mapEntry.getKey());
    }
}
