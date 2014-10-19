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

}
