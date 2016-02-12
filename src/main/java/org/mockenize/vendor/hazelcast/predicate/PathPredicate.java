package org.mockenize.vendor.hazelcast.predicate;

import com.hazelcast.query.Predicate;

import java.util.Map;

/**
 * Created by rwatanabe on 10/02/16.
 */
public class PathPredicate implements Predicate<String, Object> {

    private final String value;

    public PathPredicate(String value) {
        this.value = value.replaceAll("/", "_");
    }

    public PathPredicate(String... segments) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String segment : segments) {
            stringBuilder.append(segment);
        }
        this.value = stringBuilder.toString().replaceAll("/", "_");
    }

    @Override
    public boolean apply(Map.Entry<String, Object> mapEntry) {
        return value.matches(mapEntry.getKey().replace("*", "[^/]+"));
    }
}
