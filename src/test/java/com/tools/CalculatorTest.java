package com.tools;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CalculatorTest {

	private static final String EMPTY_STRING = "";


	@Test
	public void should_return_zero_for_an_empty_string() {
		Calculator calculator = new Calculator();
		
		assertThat(calculator.add(EMPTY_STRING), is(0));
	}
	
	@Test
	public void should_return_number_for_single_number_string() {
		Calculator calculator = new Calculator();
		
		assertThat(calculator.add("1"), is(1));
	}

}
