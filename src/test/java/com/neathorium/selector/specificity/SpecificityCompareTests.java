package com.neathorium.selector.specificity;

import com.neathorium.selector.specificity.namespaces.Specificity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.neathorium.selector.specificity.enums.Strategy;
import com.neathorium.selector.specificity.tuples.SelectorSpecificsData;
import com.neathorium.selector.specificity.tuples.SpecificityData;

import java.util.stream.Stream;

public class SpecificityCompareTests {
    public static final SelectorSpecificsData left = Specificity.getSelectorSpecificity("ul#nav li.active a", Strategy.CSS_SELECTOR);
    public static final SelectorSpecificsData left2 = Specificity.getSelectorSpecificity("ul#nav li.active a", Strategy.CSS_SELECTOR);
    public static final SelectorSpecificsData idData = Specificity.getSelectorSpecificity("#thisisanid", Strategy.CSS_SELECTOR);
    public static final SelectorSpecificsData classData = Specificity.getSelectorSpecificity(".classic", Strategy.CSS_SELECTOR);
    public static final SelectorSpecificsData xpath = Specificity.getSelectorSpecificity("", Strategy.XPATH);
    public static final SelectorSpecificsData other = Specificity.getSelectorSpecificity("", Strategy.OTHER);
    public static final SelectorSpecificsData idAndClassData = Specificity.getSelectorSpecificity(".classic #ID", Strategy.CSS_SELECTOR);

    public static Stream<Arguments> compareProvider() {
        return Stream.of(
            Arguments.of("Equal To Self", left.SPECIFICS, left.SPECIFICS, 0, "SelectorSpecificData left wasn't equal to itself."),
            Arguments.of("Equal To Self(Different reference)", left.SPECIFICS, left2.SPECIFICS, 0, "SelectorSpecificData left wasn't equal to itself via different reference."),
            Arguments.of("Class smaller than Id", classData.SPECIFICS, idData.SPECIFICS, -1, "Class specifics weren't smaller than Id specifics."),
            Arguments.of("Id bigger than class", idData.SPECIFICS, classData.SPECIFICS, 1, "Id specifics weren't bigger than Class specifics."),
            Arguments.of("Id And Class bigger than Id", idAndClassData.SPECIFICS, idData.SPECIFICS, 1, "Id and Class specifics weren't bigger than Id specifics."),
            Arguments.of("Xpath smaller than Id", xpath.SPECIFICS, idData.SPECIFICS, -1, "Xpath specifics weren't smaller than Id specifics."),
            Arguments.of("Xpath smaller than Class", xpath.SPECIFICS, classData.SPECIFICS, -1, "Xpath specifics weren't smaller than Class specifics."),
            Arguments.of("Xpath smaller than Id And Class", xpath.SPECIFICS, idAndClassData.SPECIFICS, -1, "Xpath specifics weren't smaller than Id and Class specifics."),
            Arguments.of("Other smaller than Id", other.SPECIFICS, idData.SPECIFICS, -1, "Other specifics weren't smaller than Id specifics."),
            Arguments.of("Other smaller than Class", other.SPECIFICS, classData.SPECIFICS, -1, "Other specifics weren't smaller than Class specifics."),
            Arguments.of("Other smaller than Id And Class", other.SPECIFICS, idAndClassData.SPECIFICS, -1, "Other specifics weren't smaller than Id and Class specifics."),
            Arguments.of("Other smaller than Xpath", other.SPECIFICS, xpath.SPECIFICS, -1, "Other specifics weren't smaller than Xpath specifics.")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("compareProvider")
    public void comparator(String name, SpecificityData left, SpecificityData right, int expected, String message) {
        final var result = Specificity.compare(left, right);
        Assertions.assertEquals(expected, result, message);
    }
}
