package com.neathorium.selector.specificity.tuples;

import java.util.Objects;

public class WeightedSpecificityData {
    public final double ID_VALUE;
    public final double CLASS_VALUE;
    public final double ELEMENT_VALUE;
    public final double VALID_REST_VALUE;

    public WeightedSpecificityData(double idValue, double classValue, double elementValue, double validRestValue) {
        this.ID_VALUE = idValue;
        this.CLASS_VALUE = classValue;
        this.ELEMENT_VALUE = elementValue;
        this.VALID_REST_VALUE = validRestValue;
    }

    public WeightedSpecificityData() {
        this(0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var that = (WeightedSpecificityData) o;
        return (
            (Double.compare(that.ID_VALUE, ID_VALUE) == 0) &&
            (Double.compare(that.CLASS_VALUE, CLASS_VALUE) == 0) &&
            (Double.compare(that.ELEMENT_VALUE, ELEMENT_VALUE) == 0) &&
            (Double.compare(that.VALID_REST_VALUE, VALID_REST_VALUE) == 0)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_VALUE, CLASS_VALUE, ELEMENT_VALUE, VALID_REST_VALUE);
    }

    @Override
    public String toString() {
        return (
            "WeightedSpecificityData{" +
            "ID_VALUE=" + ID_VALUE +
            ", CLASS_VALUE=" + CLASS_VALUE +
            ", ELEMENT_VALUE=" + ELEMENT_VALUE +
            ", VALID_REST_VALUE=" + VALID_REST_VALUE +
            '}'
        );
    }
}
