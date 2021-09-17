package com.neathorium.selector.specificity.validators;

import com.neathorium.selector.specificity.tuples.SelectorSpecificsData;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public interface SelectorSpecifics {
    static boolean validate(SelectorSpecificsData data) {
        return (!(
            Objects.isNull(data) ||
            Objects.isNull(data.SPECIFICS) ||
            isBlank(data.SELECTOR)
        ));
    }
}
