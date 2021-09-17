package com.neathorium.selector.specificity.namespaces;

import com.neathorium.selector.specificity.tuples.SpecificityData;
import com.neathorium.selector.specificity.tuples.WeightData;

import java.util.function.Function;

public interface Weight {
    static Function<SpecificityData, SpecificityData> weigh(WeightData weights) {
        return data -> new SpecificityData(
            weights.ID_WEIGHT * data.IDS_COUNT,
            weights.CLASS_WEIGHT * data.CLASSES_COUNT,
            weights.ELEMENT_WEIGHT * data.ELEMENTS_COUNT,
            weights.VALID_REST_WEIGHT * data.VALID_REST_COUNT
        );
    }

    static Function<SpecificityData, SpecificityData> noop(WeightData weights) {
        return data -> data;
    }
}
