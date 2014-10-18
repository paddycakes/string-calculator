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

}
