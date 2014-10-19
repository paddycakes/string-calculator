package com.tools

import spock.lang.Specification

class TokenizerSpec extends Specification {
	
	private static final String EMPTY_STRING = ""
	
	def tokenizer
	
	def "should ensure empty string equal to '0' invariant if input string passed to constructor is empty"() {
		given:
			tokenizer = new Tokenizer(EMPTY_STRING)
			
		when:
			def numbers = tokenizer.tokenize()
			
		then:
			numbers.size() == 1
			numbers[0] == "0"
	}
	
	def "should ensure non-null invariant if input string passed to constructor is null"() {
		when:
			tokenizer = new Tokenizer(null)
			
		then:
			NullPointerException e = thrown()
			e.message == "Input to be tokenized cannot be null."
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
