package com.tools

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import spock.lang.Specification

class CalculatorSpec extends Specification {
	
	private static final String EMPTY_STRING = ""
	
	def calculator
	
	def setup() {
		calculator = new Calculator()
	}
	
	def "should return 0 for an empty string"() {
		when:
			def sum = calculator.add(EMPTY_STRING)
			
		then:
			sum == 0
	}
	
	def "should return number for single number string input"(String input, int sum) {
		expect:
			calculator.add(input) == sum

		where:
			input | sum
			 "1"  |  1 
			 "2"  |  2
			 "3"  |  3
	}
	
	def "should return sum for string with two numbers delimited by comma"(String input, int sum) {
		expect:
			calculator.add(input) == sum

		where:
			input | sum
			"1,2" | 3
			"2,1" | 3
			"0,15"| 15
	}
	
	def "should return sum for string with multiple numbers delimited by comma"(String input, int sum) {
		expect:
			calculator.add(input) == sum

		where:
			input        | sum
			"4,5,9"      | 18
			"8,1,12,4"   | 25
			"2,5,7,11,3" | 28
	}
	
	def "should return sum for string with_numbers delimited by both comma and newline"() {
		when:
			def sum = calculator.add("1\n2,3")
			
		then:
			sum == 6
	}
	
	def "should return sum for string with custom delimiter defined at start"() {
		expect:
			calculator.add(input) == sum

		where:
			input            | sum
			"//;\n1;2"       | 3
			"//@\n15@5@25"   | 45
			"//!\n9!5!12!3"  | 29
	}
	
	def "should return sum for string where custom delimiter is a regex meta character"() {
		expect:
		calculator.add(input) == sum

	where:
		input            | sum
		"//^\n1^2^3"     | 6
		"//.\n9.5.3"     | 17
		"//+\n7+1+1"     | 9
	}
	
}
