package com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_DELIMITER = "\n";
	private static final String OR = "|";
	private static final String CUSTOM_DELIMITER_PREFIX = "//";
	private static final String STANDARD_DELIMITERS_REGEX = COMMA_DELIMITER + OR + NEW_LINE_DELIMITER;
	private static final String CUSTOM_DELIMITER_AND_NUMBER_GROUPS_REGEX = "//(.)\n(.*)";
	private static final int DELIMITER_GROUP_INDEX = 1;
	private static final int NUMBER_GROUP_INDEX = 2;
	
	private final String input;

	public Tokenizer(String input) {
		this.input = emptyStringEqualToZeroInvariant(input);
	}
	
	private String emptyStringEqualToZeroInvariant(String input) {
		return input.isEmpty() ? "0" : input;
	}

	public String[] tokenize() {
		if (haCustomDelimiter(input)) {
			return tokenizeUsingCustomDelimiter(input);
		}
		return tokenizeUsingStandardDelimiters(input);
	}
	
	private String[] tokenizeUsingStandardDelimiters(String sequence) {
		return split(sequence, STANDARD_DELIMITERS_REGEX);
	}
	
	private String[] tokenizeUsingCustomDelimiter(String sequence) {
		Matcher matcher = Pattern.compile(CUSTOM_DELIMITER_AND_NUMBER_GROUPS_REGEX).matcher(sequence);
		matcher.matches();
		String customDelimiter = escapeRegexMetaCharacters(matcher.group(DELIMITER_GROUP_INDEX));
		String numberSequence = matcher.group(NUMBER_GROUP_INDEX);
		return split(numberSequence, customDelimiter);
	}

	private String escapeRegexMetaCharacters(String sequence) {
		return Pattern.quote(sequence);
	}
	
	private String[] split(String sequence, String delimiterRegex) {
		return sequence.split(delimiterRegex);
	}
	
	private boolean haCustomDelimiter(String input) {
		return input.startsWith(CUSTOM_DELIMITER_PREFIX);
	}

}
