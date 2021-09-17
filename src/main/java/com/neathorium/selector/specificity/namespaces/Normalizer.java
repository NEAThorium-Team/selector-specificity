package com.neathorium.selector.specificity.namespaces;

import java.util.function.Function;
import java.util.regex.Matcher;

public interface Normalizer {
    static Function<Matcher, String> normalizeAllMatches(String replacer, int count) {
        return matcher -> matcher.replaceAll(replacer);
    }
}
