package com.tools;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Add Javadoc.
 */
public class Calculator {
	
	private static final Function<String, Integer> STRING_TO_INT_TRANSFORMER = new Function<String,Integer>() { 
		@Override public Integer apply(String number) { 
        	return parseInt(number);
        }
    };
    
    private static final Predicate<Integer> IS_NEGATIVE = new Predicate<Integer>() {
		@Override public boolean apply(Integer number) {
			return number < 0;
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
		validateNonNegativeInvariant(operands);
		return operands;
	}

	private String[] tokenize(String sequence) {
		return new Tokenizer(sequence).tokenize();
	}

	private List<Integer> transformStringsToIntegers(String[] numbers) {
		return Lists.transform(asList(numbers), STRING_TO_INT_TRANSFORMER);
	}
	
	private int addOperands(List<Integer> operands) {
		int sum = 0;
		for (Integer operand : operands) {
			sum += operand;
		}
		return sum;
	}
	
	private void validateNonNegativeInvariant(List<Integer> operands) {
		Collection<Integer> negativeOperands = Collections2.filter(operands, IS_NEGATIVE);
		if (negativeOperands.size() > 0) {
			throw new IllegalArgumentException(
					buildNegativeOperandExceptionMessage(negativeOperands));
		}
	}

	private String buildNegativeOperandExceptionMessage(Collection<Integer> negativeOperands) {
		StringBuilder sb = new StringBuilder("Negatives now allowed:");
		for (Integer negativeOperand : negativeOperands) {
			sb.append(" ");
			sb.append(negativeOperand);
		}
		return sb.toString();
	}

}
