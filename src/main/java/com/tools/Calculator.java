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

	private List<Integer> parseOperands(String input) {
		String[] numbers = tokenize(input);
		List<Integer> operands = transformStringsToIntegers(numbers);
		return operands;
	}

	private String[] tokenize(String sequence) {
		return new Tokenizer(sequence).tokenize();
	}

	private List<Integer> transformStringsToIntegers(String[] numbers) {
		return Lists.transform(asList(numbers), stringToIntTransformer);
	}
	
	private int addOperands(List<Integer> operands) {
		int sum = 0;
		for (Integer operand : operands) {
			sum += operand;
		}
		return sum;
	}

}
