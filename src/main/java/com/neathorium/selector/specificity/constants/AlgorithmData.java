package com.neathorium.selector.specificity.constants;

import com.neathorium.selector.specificity.tuples.NormalizeData;
import com.neathorium.selector.specificity.tuples.SelectorSpecificsData;
import com.neathorium.selector.specificity.namespaces.Specificity;

import java.util.function.UnaryOperator;

import static com.neathorium.selector.specificity.namespaces.Specificity.*;

public abstract class AlgorithmData {
    public static final UnaryOperator<SelectorSpecificsData> REMOVE_UNAFFECTING_NORMALIZATIONS = execute(
            get(new NormalizeData(PatternConstants.SIX_HEXADECIMAL_PATTERN)),
            get(new NormalizeData(PatternConstants.LESS_THAN_SIX_HEXADECIMAL_PATTERN)),
            get(new NormalizeData(PatternConstants.SPECIAL_CHARACTER_PATTERN))
    );

    public static final UnaryOperator<SelectorSpecificsData> MISC_NON_REPEATED_NORMALIZATIONS = execute(
            get(new NormalizeData(PatternConstants.UNIVERSAL_AND_SEPARATOR_PATTERN)),
            get(new NormalizeData(PatternConstants.STRAY_DOTS_OR_HASHES_PATTERN)),
            getWithValidRest(new NormalizeData(PatternConstants.STAR_PATTERN)),
            getWithValidRest(new NormalizeData(PatternConstants.NOT_PATTERN)),
            getWithValidRest(new NormalizeData(PatternConstants.LOCAL_PATTERN)),
            getWithValidRest(new NormalizeData(PatternConstants.GLOBAL_PATTERN)),
            get(new NormalizeData(PatternConstants.PARENTHESIS_PATTERN))
    );

    public static final UnaryOperator<SelectorSpecificsData> ADJUST_EVERYTHING_BUT_ELEMENTS_NORMALIZATIONS = execute(
            getWithAdjuster(new NormalizeData(PatternConstants.ATTRIBUTE_PATTERN), Specificity::adjustClasses),
            getWithAdjuster(new NormalizeData(PatternConstants.ID_PATTERN), Specificity::adjustIds),
            getWithAdjuster(new NormalizeData(PatternConstants.CLASS_PATTERN), Specificity::adjustClasses),
            getWithAdjuster(new NormalizeData(PatternConstants.PSEUDO_ELEMENT_PATTERN), Specificity::adjustElements),
            getWithAdjuster(new NormalizeData(PatternConstants.PSEUDO_CLASS_WITH_BRACKETS_PATTERN), Specificity::adjustClasses),
            getWithAdjuster(new NormalizeData(PatternConstants.PSEUDO_CLASS_PATTERN), Specificity::adjustClasses)
    );

    public static final UnaryOperator<SelectorSpecificsData> ELEMENT_NORMALIZATION = getWithAdjuster(new NormalizeData(PatternConstants.ELEMENT_PATTERN), Specificity::adjustElements);

    public static final UnaryOperator<SelectorSpecificsData> CALCULATION_ALGORITHM_NORMALIZATIONS = execute(
            REMOVE_UNAFFECTING_NORMALIZATIONS,
            ADJUST_EVERYTHING_BUT_ELEMENTS_NORMALIZATIONS,
            MISC_NON_REPEATED_NORMALIZATIONS,
            ELEMENT_NORMALIZATION
    );
}
