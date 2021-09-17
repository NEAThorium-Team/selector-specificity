# selector-specificity
A Java module for calculating [CSS selector specificity](https://www.w3.org/TR/selectors-3/#specificity)., with a side of added exclusion logic, and weights.

The module is primarily for CSS Selectors Level 3, and is not a CSS validator - Invalid selectors are going to return consistent, but incorrect results. For example, the [negation pseudo-class](https://www.w3.org/TR/selectors-3/#negation) takes a simple selector as the only argument. A psuedo-element or combinator as the argument for `:not()` is invalid CSS, thus this module returns an incorrect result.

## Data structure
Following CSS Specificity rules, and because this is not a validator, some data was eliminated. Anything stronger than an ID is excluded. This reduces the Specificity tuple to 3 elements. However, there are also some other exclusions, data excluded that can help decide which selector is better, given a pair. Because of this, there is an added tuple member below Elements, and the structure is almost similar to regularly expected specificity data. Anything that's excluded, or the selector not being CSS(current stand-in value is XPATH), is counted there.

## Comparing two selectors
There is a regular Comparator implementation (sans the interface contract) for selectors.

