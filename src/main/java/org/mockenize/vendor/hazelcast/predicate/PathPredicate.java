package org.mockenize.vendor.hazelcast.predicate;

import java.util.Map;

import com.hazelcast.query.Predicate;

/**
 * Created by rwatanabe on 10/02/16.
 */
public class PathPredicate implements Predicate<String, Object> {

	private static final long serialVersionUID = 4605050326503577349L;
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
