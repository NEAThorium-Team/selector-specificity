package com.neathorium.selector.specificity.tuples;

import java.util.Objects;

public class SpecificityData {
    public final double IDS_COUNT;
    public final double CLASSES_COUNT;
    public final double ELEMENTS_COUNT;
    public final double VALID_REST_COUNT;

    public SpecificityData() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    public SpecificityData(double idsCount, double classesCount, double elementsCount, double validRestCount) {
        this.IDS_COUNT = Math.max(Math.abs(idsCount), 0.0);
        this.CLASSES_COUNT = Math.max(Math.abs(classesCount), 0.0);
        this.ELEMENTS_COUNT = Math.max(Math.abs(elementsCount), 0.0);
        this.VALID_REST_COUNT = Math.max(Math.abs(validRestCount), 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final var that = (SpecificityData) o;
        return (
            (Double.compare(that.IDS_COUNT, IDS_COUNT) == 0) &&
            (Double.compare(that.CLASSES_COUNT, CLASSES_COUNT) == 0) &&
            (Double.compare(that.ELEMENTS_COUNT, ELEMENTS_COUNT) == 0) &&
            (Double.compare(that.VALID_REST_COUNT, VALID_REST_COUNT) == 0)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDS_COUNT, CLASSES_COUNT, ELEMENTS_COUNT);
    }

    @Override
    public String toString() {
        return (
            "SpecificityData{" +
            "idsCount=" + IDS_COUNT +
            ", classesCount=" + CLASSES_COUNT +
            ", elementsCount=" + ELEMENTS_COUNT +
            ", validRestCount=" + VALID_REST_COUNT +
            '}'
        );
    }
}
