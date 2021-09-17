package com.neathorium.selector.specificity.tuples;

import java.util.Objects;

public class WeightData {
    public final double ID_WEIGHT;
    public final double CLASS_WEIGHT;
    public final double ELEMENT_WEIGHT;
    public final double VALID_REST_WEIGHT;

    public WeightData(double idWeight, double classWeight, double elementWeight, double validRestWeight) {
        this.ID_WEIGHT = idWeight;
        this.CLASS_WEIGHT = classWeight;
        this.ELEMENT_WEIGHT = elementWeight;
        this.VALID_REST_WEIGHT = validRestWeight;
    }

    public WeightData() {
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

        final var that = (WeightData) o;
        return (
            Double.compare(that.ID_WEIGHT, ID_WEIGHT) == 0 &&
            Double.compare(that.CLASS_WEIGHT, CLASS_WEIGHT) == 0 &&
            Double.compare(that.ELEMENT_WEIGHT, ELEMENT_WEIGHT) == 0 &&
            Double.compare(that.VALID_REST_WEIGHT, VALID_REST_WEIGHT) == 0
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_WEIGHT, CLASS_WEIGHT, ELEMENT_WEIGHT, VALID_REST_WEIGHT);
    }

    @Override
    public String toString() {
        return (
            "WeightData{" +
            "ID_WEIGHT=" + ID_WEIGHT +
            ", CLASS_WEIGHT=" + CLASS_WEIGHT +
            ", ELEMENT_WEIGHT=" + ELEMENT_WEIGHT +
            ", VALID_REST_WEIGHT=" + VALID_REST_WEIGHT +
            '}'
        );
    }
}
