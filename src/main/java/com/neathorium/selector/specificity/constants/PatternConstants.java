package com.neathorium.selector.specificity.constants;

import java.util.regex.Pattern;

public abstract class PatternConstants {
    public static final String ID_CLASS_PATTERN_FRAGMENT = "\\s\\+>~\\.\\[:\\)]+)";
    public static final String ELEMENT_PATTERN_UNGROUPED_FRAGMENT = "[^\\s\\+>~\\.\\[:]+";
    public static final String GLOBAL_LOCAL_NOT_FRAGMENT = "(:(?!not|global|local)";

    public static final String UNIVERSAL_AND_SEPARATOR_STRING = "[\\*\\s\\+>~]";
    public static final String STRAY_DOTS_OR_HASHES_STRING = "[#\\.]";
    public static final String STAR_STRING = "[*]";
    public static final String SIX_HEXADECIMAL_STRING = "\\\\[0-9A-Fa-f]{6}\\s?";
    public static final String LESS_THAN_SIX_HEXADECIMAL_STRING = "\\\\[0-9A-Fa-f]{1,5}\\s";
    public static final String SPECIAL_CHARACTER_STRING = "\\\\.";
    public static final String ATTRIBUTE_STRING = "(\\[[^\\]]+\\])";
    public static final String ID_STRING = "(#[^\\#" + ID_CLASS_PATTERN_FRAGMENT;
    public static final String CLASS_STRING = "(\\.[^" + ID_CLASS_PATTERN_FRAGMENT;
    public static final String PSEUDO_CLASS_STRING = GLOBAL_LOCAL_NOT_FRAGMENT + "[^\\s\\+>~\\.\\[:]+)";
    public static final String ELEMENT_STRING = "(" + ELEMENT_PATTERN_UNGROUPED_FRAGMENT  + ")";
    public static final String NOT_STRING = ":not";
    public static final String LOCAL_STRING = ":local";
    public static final String GLOBAL_STRING = ":global";
    public static final String PARENTHESIS_STRING = "[\\(\\)]";


    public static final String PSEUDO_CLASS_WITH_BRACKETS_STRING = GLOBAL_LOCAL_NOT_FRAGMENT + "[\\w-]+\\([^\\)]*\\))";
    public static final String PSEUDO_ELEMENT_STRING = "(::[^\\s\\+>~\\.\\[:]+|:first-line|:first-letter|:before|:after)";

    public static final Pattern SIX_HEXADECIMAL_PATTERN = Pattern.compile(SIX_HEXADECIMAL_STRING);
    public static final Pattern LESS_THAN_SIX_HEXADECIMAL_PATTERN = Pattern.compile(LESS_THAN_SIX_HEXADECIMAL_STRING);
    public static final Pattern SPECIAL_CHARACTER_PATTERN = Pattern.compile(SPECIAL_CHARACTER_STRING);
    public static final Pattern ATTRIBUTE_PATTERN = Pattern.compile(ATTRIBUTE_STRING);
    public static final Pattern ID_PATTERN = Pattern.compile(ID_STRING);
    public static final Pattern CLASS_PATTERN = Pattern.compile(CLASS_STRING);
    public static final Pattern PSEUDO_CLASS_PATTERN = Pattern.compile(PSEUDO_CLASS_STRING);
    public static final Pattern ELEMENT_PATTERN = Pattern.compile(ELEMENT_STRING);
    public static final Pattern UNIVERSAL_AND_SEPARATOR_PATTERN = Pattern.compile(UNIVERSAL_AND_SEPARATOR_STRING);
    public static final Pattern STRAY_DOTS_OR_HASHES_PATTERN = Pattern.compile(STRAY_DOTS_OR_HASHES_STRING);
    public static final Pattern STAR_PATTERN = Pattern.compile(STAR_STRING);
    public static final Pattern NOT_PATTERN = Pattern.compile(NOT_STRING);
    public static final Pattern LOCAL_PATTERN = Pattern.compile(LOCAL_STRING);
    public static final Pattern GLOBAL_PATTERN = Pattern.compile(GLOBAL_STRING);
    public static final Pattern PARENTHESIS_PATTERN = Pattern.compile(PARENTHESIS_STRING);

    public static final Pattern PSEUDO_CLASS_WITH_BRACKETS_PATTERN = Pattern.compile(PSEUDO_CLASS_WITH_BRACKETS_STRING, Pattern.CASE_INSENSITIVE);
    public static final Pattern PSEUDO_ELEMENT_PATTERN = Pattern.compile(PSEUDO_ELEMENT_STRING, Pattern.CASE_INSENSITIVE);

    public static final String MATCH_SINGLE_SPACE_REPLACE = " ";
}
