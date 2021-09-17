package com.neathorium.selector.specificity.validators;

import com.neathorium.selector.specificity.tuples.AdjustData;

import java.util.Objects;

public interface Adjust {
    static boolean validate(AdjustData data) {
        return !Objects.isNull(data);
    }
}
