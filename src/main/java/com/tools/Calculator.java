package com.tools;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	private static final String COMMA_DELIMITER = ",";

	public int add(String input) {
		if (input.isEmpty()) {
			return 0;
		} else {
			List<Integer> operands = parseOperands(input);
			int result = 0;
			for (Integer operand : operands) {
				result += operand;
			}
			return result;
		}
	}

	private List<Integer> parseOperands(String input) {
		String[] numbers = input.split(COMMA_DELIMITER);
		List<Integer> operands = new ArrayList<>(numbers.length);
		for (int i=0; i<numbers.length; i++) {
			operands.add(parseInt(numbers[i]));
		}
		return operands;
	}

}
