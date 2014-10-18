package com.tools;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Add Javadoc.
 */
public class Calculator {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_DELIMITER = "\n";
	private static final String OR = "|";
	private static final String LEGAL_DELIMITERS_REGEX = COMMA_DELIMITER + OR + NEW_LINE_DELIMITER;
	
	private Function<String, Integer> stringToIntTransformer = new Function<String,Integer>() { 
        public Integer apply(String number) { 
        	return parseInt(number);
        }
    };

	public int add(String input) {
		if (input.isEmpty()) {
			return 0;
		} else {
			List<Integer> operands = parseOperands(input);
			return addOperands(operands);
		}
	}

	private int addOperands(List<Integer> operands) {
		int sum = 0;
		for (Integer operand : operands) {
			sum += operand;
		}
		return sum;
	}

	private List<Integer> parseOperands(String input) {
		String[] numbers = tokenize(input);
		List<Integer> operands = transformStringsToIntegers(numbers);
		return operands;
	}

	private List<Integer> transformStringsToIntegers(String[] numbers) {
		return Lists.transform(asList(numbers), stringToIntTransformer);
	}

	private String[] tokenize(String input) {
		return input.split(LEGAL_DELIMITERS_REGEX);
	}

}
