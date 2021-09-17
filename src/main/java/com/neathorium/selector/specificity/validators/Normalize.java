package com.neathorium.selector.specificity.validators;

import com.neathorium.selector.specificity.tuples.NormalizeData;

import java.util.Objects;

public interface Normalize {
    static boolean validate(NormalizeData data) {
        return (
            !Objects.isNull(data) &&
            !Objects.isNull(data.PATTERN) &&
            !Objects.isNull(data.NORMALIZER) &&
            !Objects.isNull(data.REPLACER)
        );
    }
}
