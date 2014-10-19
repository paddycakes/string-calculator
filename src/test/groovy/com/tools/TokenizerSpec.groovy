package com.tools

import spock.lang.Specification

class TokenizerSpec extends Specification {
	
	private static final String EMPTY_STRING = ""
	
	def tokenizer
	
	def "should tokenize an empty string and return an array with a single '0' string element"() {
		given:
			tokenizer = new Tokenizer(EMPTY_STRING)
			
		when:
			def numbers = tokenizer.tokenize()
			
		then:
			numbers.size() == 1
			numbers[0] == "0"
	}
	
	def "should tokenize input strings and return multi number string arrays"(String input, String[] tokens) {
		expect:
			new Tokenizer(input).tokenize() == tokens

		where:
			input     |  tokens
			"1,2,3"   |  ["1", "2", "3"] 
			"10,5"    |  ["10", "5"]
			"8,9,1,1" |  ["8", "9", "1", "1"]
	}

}
