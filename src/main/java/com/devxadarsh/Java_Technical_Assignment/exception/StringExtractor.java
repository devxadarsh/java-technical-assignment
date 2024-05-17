package com.devxadarsh.Java_Technical_Assignment.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtractor {

    public static String extractString(String input, String startWord, String endWord) {
        // Construct the regular expression pattern
        String patternString = startWord + "(.*?)" + endWord;
        Pattern pattern = Pattern.compile(patternString);

        // Create a matcher with the input string
        Matcher matcher = pattern.matcher(input);

        // Find matches and return the extracted string
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null; // No match found
        }
    }
}

