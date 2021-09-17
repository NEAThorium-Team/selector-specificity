package com.neathorium.selector.specificity.tuples;

import com.neathorium.selector.specificity.constants.PatternConstants;
import com.neathorium.selector.specificity.namespaces.Normalizer;
import com.neathorium.selector.specificity.validators.Normalize;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalizeData {
    public final Pattern PATTERN;
    public final String REPLACER;
    public final BiFunction<String, Integer, Function<Matcher, String>> NORMALIZER;
    public final Predicate<NormalizeData> VALIDATOR;

    public NormalizeData(Pattern pattern, String replacer, BiFunction<String, Integer, Function<Matcher, String>> normalizer, Predicate<NormalizeData> validator) {
        this.PATTERN = pattern;
        this.REPLACER = replacer;
        this.NORMALIZER = normalizer;
        this.VALIDATOR = validator;
    }

    public NormalizeData(Pattern pattern, String replacer, BiFunction<String, Integer, Function<Matcher, String>> normalizer) {
        this(pattern, replacer, normalizer, Normalize::validate);
    }

    public NormalizeData(Pattern pattern, BiFunction<String, Integer, Function<Matcher, String>> normalizer, Predicate<NormalizeData> validator) {
        this(pattern, PatternConstants.MATCH_SINGLE_SPACE_REPLACE, normalizer, validator);
    }

    public NormalizeData(Pattern pattern, BiFunction<String, Integer, Function<Matcher, String>> normalizer) {
        this(pattern, PatternConstants.MATCH_SINGLE_SPACE_REPLACE, normalizer, Normalize::validate);
    }

    public NormalizeData(Pattern pattern, String replacer) {
        this(pattern, replacer, Normalizer::normalizeAllMatches, Normalize::validate);
    }

    public NormalizeData(Pattern pattern) {
        this(pattern, PatternConstants.MATCH_SINGLE_SPACE_REPLACE, Normalizer::normalizeAllMatches);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var that = (NormalizeData) o;
        return (
            Objects.equals(PATTERN, that.PATTERN) &&
            Objects.equals(REPLACER, that.REPLACER) &&
            Objects.equals(NORMALIZER, that.NORMALIZER) &&
            Objects.equals(VALIDATOR, that.VALIDATOR)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(PATTERN, REPLACER, NORMALIZER, VALIDATOR);
    }

    @Override
    public String toString() {
        return "NormalizeData{" +
                "PATTERN=" + PATTERN +
                ", REPLACER='" + REPLACER + '\'' +
                ", NORMALIZER=" + NORMALIZER +
                ", VALIDATOR=" + VALIDATOR +
                '}';
    }
}
