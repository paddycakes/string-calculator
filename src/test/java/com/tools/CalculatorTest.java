package com.tools;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private static final String EMPTY_STRING = "";
	
	private Calculator calculator;
	
	@Before
	public void setUp() {
		calculator = new Calculator();
	}

	@Test
	public void should_return_zero_for_an_empty_string() {
		assertThat(calculator.add(EMPTY_STRING), is(0));
	}
	
	@Test
	public void should_return_number_for_single_number_string() {
		assertThat(calculator.add("1"), is(1));
	}
	
	@Test
	public void should_return_sum_for_string_with_two_numbers_delimited_by_comma() {
		assertThat(calculator.add("1,2"), is(3));
	}
	
	@Test
	public void should_return_sum_for_string_with_multiple_numbers_delimited_by_comma() {
		assertThat(calculator.add("4,5,9,1"), is(19));
	}
	
	@Test
	public void should_return_sum_for_string_with_numbers_delimited_by_newline() {
		assertThat(calculator.add("1\n2\n3"), is(6));
	}
	
	@Test
	public void should_return_sum_for_string_with_numbers_delimited_by_both_comma_and_newline() {
		assertThat(calculator.add("1\n2,3"), is(6));
	}
	
	@Test
	public void should_return_sum_for_string_with_custom_delimiter_defined_at_start() {
		assertThat(calculator.add("//;\n1;2"), is(3));
	}
	
	@Test
	public void should_return_sum_for_string_where_custom_delimiter_is_regex_meta_character() {
		assertThat(calculator.add("//^\n5^2^2^1"), is(10));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_passed_string_containing_negative_numbers() {
		calculator.add("2,-5,7,-1");
	}

}
