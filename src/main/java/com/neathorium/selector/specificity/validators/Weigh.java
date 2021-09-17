package com.neathorium.selector.specificity.validators;

import com.neathorium.selector.specificity.tuples.SpecificityData;
import com.neathorium.selector.specificity.tuples.WeightData;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public interface Weigh {
    static boolean validate(Function<WeightData, UnaryOperator<SpecificityData>> weigher, WeightData weights) {
        return (!(Objects.isNull(weigher) || Objects.isNull(weights)));
    }
}
