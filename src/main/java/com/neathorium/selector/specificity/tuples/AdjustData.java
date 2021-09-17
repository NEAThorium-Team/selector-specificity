package com.neathorium.selector.specificity.tuples;

import com.neathorium.selector.specificity.validators.Adjust;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class AdjustData {
    public final BiFunction<SpecificityData, Double, SpecificityData> ADJUSTER;
    public final WeightData WEIGHTS;
    public final Function<WeightData, UnaryOperator<SpecificityData>> WEIGHER;
    public final Predicate<AdjustData> VALIDATOR;

    public AdjustData(BiFunction<SpecificityData, Double, SpecificityData> adjuster, WeightData weights, Function<WeightData, UnaryOperator<SpecificityData>> weigher, Predicate<AdjustData> validator) {
        this.ADJUSTER = adjuster;
        this.WEIGHTS = weights;
        this.WEIGHER = weigher;
        this.VALIDATOR = validator;
    }

    public AdjustData(BiFunction<SpecificityData, Double, SpecificityData> adjuster) {
        this(adjuster, null, null, Adjust::validate);
    }

    public AdjustData() {
        this(null, null, null, Adjust::validate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final var that = (AdjustData) o;
        return (
            Objects.equals(ADJUSTER, that.ADJUSTER) &&
            Objects.equals(WEIGHTS, that.WEIGHTS) &&
            Objects.equals(WEIGHER, that.WEIGHER) &&
            Objects.equals(VALIDATOR, that.VALIDATOR)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(ADJUSTER, WEIGHTS, WEIGHER, VALIDATOR);
    }

    @Override
    public String toString() {
        return (
            "AdjustData{" +
            "ADJUSTER=" + ADJUSTER +
            ", WEIGHTS=" + WEIGHTS +
            ", WEIGHER=" + WEIGHER +
            ", VALIDATOR=" + VALIDATOR +
            '}'
        );
    }
}
