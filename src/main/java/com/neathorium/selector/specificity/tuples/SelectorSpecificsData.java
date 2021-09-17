package com.neathorium.selector.specificity.tuples;

import java.util.Objects;

public class SelectorSpecificsData {
    public final String ORIGINAL_SELECTOR;
    public final String SELECTOR;
    public final SpecificityData SPECIFICS;

    public SelectorSpecificsData(String originalSelector, String selector, SpecificityData specifics) {
        this.ORIGINAL_SELECTOR = originalSelector;
        this.SELECTOR = selector;
        this.SPECIFICS = specifics;
    }

    public SelectorSpecificsData(String selector, SpecificityData specifics) {
        this(selector, selector, specifics);
    }

    public SelectorSpecificsData(String selector) {
        this(selector, new SpecificityData());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var that = (SelectorSpecificsData) o;
        return (
            Objects.equals(SELECTOR, that.SELECTOR) &&
            Objects.equals(SPECIFICS, that.SPECIFICS)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(SELECTOR, SPECIFICS);
    }

    @Override
    public String toString() {
        return (
            "SelectorSpecificsData{" +
            "ORIGINAL_SELECTOR='" + ORIGINAL_SELECTOR + '\'' +
            ", SELECTOR='" + SELECTOR + '\'' +
            ", SPECIFICS=" + SPECIFICS +
            '}'
        );
    }
}
