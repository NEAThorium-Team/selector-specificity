package com.neathorium.selector.specificity;

import com.neathorium.selector.specificity.namespaces.Specificity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.neathorium.selector.specificity.enums.Strategy;
import com.neathorium.selector.specificity.tuples.SpecificityData;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Stream;

public class SpecificityTests {
    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("#thisisanid", new SpecificityData(1, 0, 0, 0)),
            Arguments.of(".classic", new SpecificityData(0, 1, 0, 0)),
            Arguments.of(".classic #ID", new SpecificityData(1, 1, 0, 0)),
            Arguments.of("\\000031", new SpecificityData(0, 0, 0, 0)),
            Arguments.of("***:not:local:global:*[rel='stuff']", new SpecificityData(0, 2, 0, 3)),
            Arguments.of("#a[target=\"#a #b #almaalma .dezso\"] .a g  this is some free text", new SpecificityData(1,2,6,0)),

            // http://css-tricks.com/specifics-on-css-specificity/
            Arguments.of("ul#nav li.active a", new SpecificityData(1, 1, 3, 0)),
            Arguments.of("body.ie7 .col_3 h2 ~ h2", new SpecificityData(0, 2, 3, 0)),
            Arguments.of("#footer *:not(nav) li", new SpecificityData(1,0,2,1)),
            Arguments.of("ul > li ul li ol li:first-letter", new SpecificityData(0,0,7,0)),

            // http://reference.sitepoint.com/css/specificity
            Arguments.of("body#home div#warning p.message", new SpecificityData(2,1,3,0)),
            Arguments.of("* body#home>div#warning p.message", new SpecificityData(2,1,3,0)),
            Arguments.of("#home #warning p.message", new SpecificityData(2,1,1,0)),
            Arguments.of("#warning p.message", new SpecificityData(1,1,1,0)),
            Arguments.of("#warning p", new SpecificityData(1,0,1,0)),
            Arguments.of("p.message", new SpecificityData(0,1,1,0)),
            Arguments.of("p", new SpecificityData(0,0,1,0)),

            // Test pseudo-element with uppertestCase letters
            Arguments.of("li:bEfoRE", new SpecificityData(0,0,2,0)),
            Arguments.of("p:BEFORE", new SpecificityData(0,0,2,0)),

            Arguments.of("li:first-child+p", new SpecificityData(0,1,2,0)),
            Arguments.of("li:nth-child(even)+p", new SpecificityData(0,1,2,0)),
            Arguments.of("li:nth-child(2n+1)+p", new SpecificityData(0,1,2,0)),
            Arguments.of("li:nth-child( 2n + 1 )+p", new SpecificityData(0,1,2,0)),
            Arguments.of("li:nth-child(2n-1)+p", new SpecificityData(0,1,2,0)),
            Arguments.of("li:nth-child(2n-1) p", new SpecificityData(0,1,2,0)),
            Arguments.of(":lang(nl-be)", new SpecificityData(0,1,0,0)),

            // Tests with CSS escape sequences
            // Anyone doing or ending up with such selectors, I implore you to reconsider.
            // https://mathiasbynens.be/notes/css-escapes and https://mathiasbynens.be/demo/crazy-class
            Arguments.of(".\\\\3A -\\\\)", new SpecificityData(0,0,1,0)),             /* <p class=":-)"></p> */
            Arguments.of(".\\\\3A \\\\`\\\\(", new SpecificityData(0,0,1,0)),           /* <p class=":`("></p> */
            Arguments.of(".\\\\3A .\\\\`\\\\(", new SpecificityData(0,0,1,0)),          /* <p class=": `("></p> */
            Arguments.of(".\\\\31 a2b3c", new SpecificityData(0,0,1,0)),            /* <p class="1a2b3c"></p> */
            Arguments.of(".\\\\000031a2b3c", new SpecificityData(0,0,1,0)),         /* <p class="1a2b3c"></p> */
            Arguments.of(".\\\\000031 a2b3c", new SpecificityData(0,0,1,0)),        /* <p class="1a2b3c"></p> */
            Arguments.of("#\\\\#fake-id", new SpecificityData(1,0,0,0)),            /* <p id="#fake-id"></p> */
            Arguments.of(".\\\\#fake-id", new SpecificityData(1,0,0,0)),            /* <p class="#fake-id"></p> */
            Arguments.of("#\\\\<p\\\\>", new SpecificityData(0,0,1,0)),               /* <p id="<p>"></p> */
            Arguments.of(".\\\\#\\\\.\\\\#\\\\.\\\\#", new SpecificityData(0,0,0,0)),       /* <p class="#.#.#"></p> */
            Arguments.of(".foo\\\\.bar", new SpecificityData(0,2,0,0)),             /* <p class="foo.bar"></p> */
            Arguments.of("foo\\\\.bar", new SpecificityData(0,1,1,0)),             /* <p class="foo.bar"></p> */
            Arguments.of(".\\\\:hover\\\\:active", new SpecificityData(0,2,0,0)),     /* <p class=":hover:active"></p> */
            Arguments.of(".\\\\3A hover\\\\3A active", new SpecificityData(0,0,2,0)), /* <p class=":hover:active"></p> */
            Arguments.of(".\\\\000031  p", new SpecificityData(0,0,1,0)),           /* <p class="1"><p></p></p>" */
            Arguments.of(".\\\\3A \\\\`\\\\( .another", new SpecificityData(0,1,1,0)),  /* <p class=":`("><p class="another"></p></p> */
            Arguments.of(".\\\\--cool", new SpecificityData(0,0,1,0)),              /* <p class="--cool"></p> */
            Arguments.of("#home .\\\\[page\\\\]", new SpecificityData(1,1,0,0))      /* <p id="home"><p class="[page]"></p></p> */
        );
    }

    @ParameterizedTest
    @MethodSource("provider")
    public void calculateSpecificity(String selector, SpecificityData data) {
        final var result = Specificity.getSelectorSpecificity(selector, Strategy.CSS_SELECTOR);
        Assertions.assertEquals(data, result.SPECIFICS, result.SPECIFICS.toString());
    }
}
