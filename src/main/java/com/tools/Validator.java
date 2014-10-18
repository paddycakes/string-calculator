package com.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class Validator {
	
    private static final Predicate<Integer> IS_NEGATIVE = new Predicate<Integer>() {
		@Override public boolean apply(Integer number) {
			return number < 0;
		}
	};
	
    private static final Predicate<Integer> IS_1000_OR_LESS = new Predicate<Integer>() {
		@Override public boolean apply(Integer number) {
			return number <= 1000;
		}
	};
	
	private final List<Integer> values;

	public Validator(List<Integer> values) {
		this.values = values;
	}
	
	public List<Integer> validate() {
		validateNonNegativeInvariant(values);
		List<Integer> validated = validateAllOperandsEqualOrLessThan1000(values);
		return validated;		
	}

	private void validateNonNegativeInvariant(List<Integer> operands) {
		Collection<Integer> filteredOperands = Collections2.filter(operands, IS_NEGATIVE);
		if (hasNegative(filteredOperands)) {
			throw new IllegalArgumentException(
					buildNegativeOperandExceptionMessage(filteredOperands));
		}
	}

	private boolean hasNegative(Collection<Integer> operands) {
		return operands.size() > 0;
	}

	private List<Integer> validateAllOperandsEqualOrLessThan1000(List<Integer> operands) {
		Collection<Integer> filtered = Collections2.filter(operands, IS_1000_OR_LESS);
		return new ArrayList<Integer>(filtered);
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
