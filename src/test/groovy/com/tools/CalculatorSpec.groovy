package com.tools

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

}
