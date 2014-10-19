package com.tools;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tokenizer to split input string by a 
 * standard of custom delimiter.
 */
public class Tokenizer {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_DELIMITER = "\n";
	private static final String OR = "|";
	private static final String CUSTOM_DELIMITER_PREFIX = "//";
	private static final String STANDARD_DELIMITERS_REGEX = COMMA_DELIMITER + OR + NEW_LINE_DELIMITER;	
	private static final String CUSTOM_DELIMITER_AND_NUMBER_GROUPS_REGEX = "//(.|\\[(.*)\\])\n(.*)";	
	private static final int GROUP_INDEX_ONE = 1;
	private static final int GROUP_INDEX_TWO = 2;	
	private static final int NUMBER_GROUP_INDEX = 3;
	
	private final String input;

	public Tokenizer(String input) {
		this.input = ensureInputInvariants(input);
	}

	/**
	 * Tokenizes input string.
	 * 
	 * @return String array containing the split tokens.
	 */
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
		int delimiterIndex = getDelimiterIndex(matcher);
		String customDelimiter = escapeRegexMetaCharacters(matcher.group(delimiterIndex));
		String numberSequence = matcher.group(NUMBER_GROUP_INDEX);
		return split(numberSequence, customDelimiter);
	}

	private int getDelimiterIndex(Matcher matcher) {	
		return matcher.group(GROUP_INDEX_TWO) != null ? GROUP_INDEX_TWO : GROUP_INDEX_ONE;
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
	
	private String ensureInputInvariants(String input) {
		String invariant = ensureNonNullInvariant(input);
		invariant = emptyStringEqualToZeroInvariant(input);
		return invariant;
	}
	
	private String ensureNonNullInvariant(String input) {
		return checkNotNull(input, "Input to be tokenized cannot be null.");
	}
	
	private String emptyStringEqualToZeroInvariant(String input) {
		return input.isEmpty() ? "0" : input;
	}

}
