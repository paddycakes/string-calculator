package com.tools;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * A calculator that will perform arithmetic
 * operations on string based inputs.
 */
public class Calculator {
	
	private static final Function<String,Integer> STRING_TO_INT_TRANSFORMER = new Function<String,Integer>() { 
		@Override public Integer apply(String number) { 
        	return parseInt(number);
        }
    };
    
    /**
     * Adds the numbers contained in the input string.
     * 
     * @param Input string to sum.
     * @return Value of the summed string.
     */
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
	
    /**
     * Main method to allow the Calculator
     * to be run as an application.
     */
    public static void main(String[] args) {
    	if (args.length != 1) {
    		throw new IllegalStateException(
    				"Must specify a single input string to sum");
    	}
    	String input = args[0];
    	Calculator calculator = new Calculator();
    	System.out.println(
    			format("The sum of adding input string '%s' is %d", 
    			input, calculator.add(input)));
    }

}
