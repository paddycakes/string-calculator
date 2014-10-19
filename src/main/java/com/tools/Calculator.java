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
	
	private static final Function<String,Integer> STRING_TO_INT_TRANSFORMER = new Function<String,Integer>() { 
		@Override public Integer apply(String number) { 
        	return parseInt(number);
        }
    };
    

	public int add(String input) {
		List<Integer> operands = parseOperands(input);
		operands = validateOperands(operands);
		return addOperands(operands);
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
		return Lists.transform(asList(numbers), STRING_TO_INT_TRANSFORMER);
	}
	
	private List<Integer> validateOperands(List<Integer> operands) {
		return new Validator(operands).validate();
	}
	
	private int addOperands(List<Integer> operands) {
		int sum = 0;
		for (Integer operand : operands) {
			sum += operand;
		}
		return sum;
	}

}
