package com.tools

import spock.lang.Specification

class ValidatorSpec extends Specification {
	
	def validator
	
	def "should ensure non-null invariant if values passed to constructor are null"() {
		when:
			validator = new Validator(null)
			
		then:
			NullPointerException e = thrown()
			e.message == "Values to be validated cannot be null."
	}
	
	def "should throw IllegalArgumentException when passed any negative numbers"() {
		given:
			validator = new Validator([10, -5])
			
		when:
			validator.validate()

		then:
			IllegalArgumentException e = thrown()
			e.message == "Negatives now allowed: -5"
	}
	
	def "should remove any numbers greater than 1000s"(List<Integer> values, List<Integer> validatedValues) {
		given:
			validator = new Validator(values)
			
		expect:
			validator.validate() == validatedValues
			
		where:
			values                      | validatedValues
			[0,1001]                    | [0]
			[10, 1001, 1000, 99, 10999] | [10, 1000, 99]
			[1001, 1002, 1003]          | []
	}
	
	def "should input values are all valid should return same values"(List<Integer> values, List<Integer> validatedValues) {
		given:
			validator = new Validator(values)
		
		expect:
			validator.validate() == validatedValues
		
		where:
			values                     | validatedValues
			[0]                        | [0]
			[1, 11, 21, 31, 41]        | [1, 11, 21, 31, 41]
			[996, 997, 998, 999, 1000] | [996, 997, 998, 999, 1000]
	}

}
