package com.neathorium.selector.specificity.namespaces;

import com.neathorium.selector.specificity.constants.AlgorithmData;
import com.neathorium.selector.specificity.enums.Strategy;
import com.neathorium.selector.specificity.tuples.*;
import com.neathorium.selector.specificity.validators.Execute;
import com.neathorium.selector.specificity.validators.SelectorSpecifics;
import com.neathorium.selector.specificity.validators.Weigh;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface Specificity {
    @SafeVarargs
    static <T> UnaryOperator<T> execute(UnaryOperator<T>... steps) {
        return Execute.validate(steps.length) ? data -> {
            var lData = data;
            for(var step : steps) {
                lData = step.apply(lData);
            }

            return lData;
        } : data -> data;
    }

    static SelectorSpecificsData getSelectorSpecificity(String selector, Strategy strategy) {
        var specificityData = new SelectorSpecificsData(selector, new SpecificityData());
        if (Objects.equals(strategy, Strategy.XPATH)) {
            return new SelectorSpecificsData(selector, new SpecificityData(0.0, 0.0, 0.0, 1.0));
        }

        if (Objects.equals(strategy, Strategy.OTHER)) {
            return new SelectorSpecificsData(selector, new SpecificityData(0.0, 0.0, 0.0, 0.1));
        }

        return isNotBlank(selector) ? AlgorithmData.CALCULATION_ALGORITHM_NORMALIZATIONS.apply(specificityData) : specificityData;
    }

    static UnaryOperator<SelectorSpecificsData> get(NormalizeData normalizeData, AdjustData adjustData) {
        if (!(
            normalizeData.VALIDATOR.test(normalizeData) &&
            adjustData.VALIDATOR.test(adjustData)
        )) {
            return data -> data;
        }

        return data -> {
            if (!SelectorSpecifics.validate(data)) {
                return data;
            }

            final var matcher = normalizeData.PATTERN.matcher(data.SELECTOR);
            final var length = (int)matcher.results().count();
            return length > 0 ? new SelectorSpecificsData(
                data.ORIGINAL_SELECTOR,
                normalizeData.NORMALIZER.apply(normalizeData.REPLACER, length).apply(matcher),
                adjust(adjustData, length).apply(data.SPECIFICS)
            ) : data;
        };
    }

    static UnaryOperator<SpecificityData> adjust(AdjustData adjustData, double amount) {
        return data -> execute(
            adjust(adjustData.ADJUSTER, amount),
            weigh(adjustData.WEIGHER, adjustData.WEIGHTS)
        ).apply(data);
    }

    static UnaryOperator<SpecificityData> adjust(BiFunction<SpecificityData, Double, SpecificityData> adjuster, double amount) {
        final var absAmount = Math.abs(amount);
        return absAmount > 0.0 ? (data -> !Objects.isNull(adjuster) ? adjuster.apply(data, amount) : data) : data -> data;
    }

    static UnaryOperator<SpecificityData> weigh(BiPredicate<Function<WeightData, UnaryOperator<SpecificityData>>, WeightData> validator, Function<WeightData, UnaryOperator<SpecificityData>> weigher, WeightData weights) {
        final UnaryOperator<SpecificityData> negative = data -> data;
        if (Objects.isNull(validator) || Objects.isNull(weigher) || Objects.isNull(weights)) {
            return negative;
        }

        return validator.test(weigher, weights) ? weigher.apply(weights) : negative;
    }

    static UnaryOperator<SpecificityData> weigh(Function<WeightData, UnaryOperator<SpecificityData>> weigher, WeightData weights) {
        return weigh(Weigh::validate, weigher, weights);
    }

    static UnaryOperator<SelectorSpecificsData> get(NormalizeData normalizeData) {
        return get(normalizeData, new AdjustData());
    }

    static UnaryOperator<SelectorSpecificsData> getWithAdjuster(NormalizeData normalizeData, BiFunction<SpecificityData, Double, SpecificityData> adjuster) {
        return get(normalizeData, new AdjustData(adjuster));
    }

    static UnaryOperator<SelectorSpecificsData> getWithValidRest(NormalizeData normalizeData) {
        return get(normalizeData, new AdjustData(Specificity::adjustValidRest));
    }

    static SpecificityData adjustIds(SpecificityData data, double amount) {
        final var absAmount = Math.abs(amount);
        return absAmount > 0.0 ? new SpecificityData(data.IDS_COUNT + amount, data.CLASSES_COUNT, data.ELEMENTS_COUNT, data.VALID_REST_COUNT) : data;
    }

    static SpecificityData adjustClasses(SpecificityData data, double amount) {
        final var absAmount = Math.abs(amount);
        return absAmount > 0.0 ? new SpecificityData(data.IDS_COUNT, data.CLASSES_COUNT + amount, data.ELEMENTS_COUNT, data.VALID_REST_COUNT) : data;
    }

    static SpecificityData adjustElements(SpecificityData data, double amount) {
        final var absAmount = Math.abs(amount);
        return absAmount > 0.0 ? new SpecificityData(data.IDS_COUNT, data.CLASSES_COUNT, data.ELEMENTS_COUNT + amount, data.VALID_REST_COUNT) : data;
    }

    static SpecificityData adjustValidRest(SpecificityData data, double amount) {
        final var absAmount = Math.abs(amount);
        return absAmount > 0.0 ? new SpecificityData(data.IDS_COUNT, data.CLASSES_COUNT, data.ELEMENTS_COUNT, data.VALID_REST_COUNT + amount) : data;
    }

    static SpecificityData adjust(SpecificityData data, SpecificityData amountData) {
        final var idsCount = amountData.IDS_COUNT;
        final var classesCount = amountData.CLASSES_COUNT;
        final var elementsCount = amountData.ELEMENTS_COUNT;
        final var validRestCount = amountData.VALID_REST_COUNT;

        return new SpecificityData(data.IDS_COUNT + idsCount, data.CLASSES_COUNT + classesCount, data.ELEMENTS_COUNT + elementsCount, data.VALID_REST_COUNT + validRestCount);
    }

    static SpecificityData reduce(SpecificityData... dataArray) {
        var base = new SpecificityData();
        if (!Execute.validate(dataArray.length)) {
            return base;
        }

        for (SpecificityData data : dataArray) {
            base = adjust(base, data);
        }

        return base;
    }

    static List<Double> getSpecificityValuesInOrder(SpecificityData data) {
        return Arrays.asList(data.IDS_COUNT, data.CLASSES_COUNT, data.ELEMENTS_COUNT, data.VALID_REST_COUNT);
    }

    static int compare(SpecificityData left, SpecificityData right) {
        final var leftValues = getSpecificityValuesInOrder(left);
        final var rightValues = getSpecificityValuesInOrder(right);
        final var length = leftValues.size();
        double leftValue;
        double rightValue;
        for (var index = 0; index < length; ++index) {
            leftValue = leftValues.get(index);
            rightValue = rightValues.get(index);

            if (leftValue > rightValue) {
                return 1;
            }

            if (leftValue < rightValue) {
                return -1;
            }
        }

        return 0;
    }

    static <T> T compute(SpecificityData left, SpecificityData right, BiFunction<SpecificityData, SpecificityData, T> algorithm) {
        return algorithm.apply(left, right);
    }
}
