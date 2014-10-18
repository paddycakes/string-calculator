package com.tools;

import static java.lang.Integer.parseInt;

public class Calculator {

	public int add(String operands) {
		if (operands.isEmpty()) {
			return 0;
		} else {
			return parseInt(operands);
		}
	}

}
