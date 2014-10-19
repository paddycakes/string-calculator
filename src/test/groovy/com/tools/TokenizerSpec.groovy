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
	
	def "should tokenize input strings with comma delimiter and return multi number string arrays"(String input, String[] tokens) {
		expect:
			new Tokenizer(input).tokenize() == tokens

		where:
			input     |  tokens
			"1,2,3"   |  ["1", "2", "3"] 
			"10,5"    |  ["10", "5"]
			"8,9,1,1" |  ["8", "9", "1", "1"]
	}
	
	def "should tokenize input strings with newline delimiter and return multi number string arrays"(String input, String[] tokens) {
		expect:
			new Tokenizer(input).tokenize() == tokens

		where:
			input        |  tokens
			"1\n2\n3"    |  ["1", "2", "3"]
			"10\n5"      |  ["10", "5"]
			"8\n9\n1\n1" |  ["8", "9", "1", "1"]
	}
	
	def "should tokenize input strings with a mixture of comma and newline delimiters and return multi number string arrays"(String input, String[] tokens) {
		expect:
			new Tokenizer(input).tokenize() == tokens

		where:
			input         |  tokens
			"1\n2,3"      |  ["1", "2", "3"]
			"10\n5\n9,11" |  ["10", "5", "9", "11"]
			"8,9,1\n1"    |  ["8", "9", "1", "1"]
	}
	
	def "should tokenizise input strings with custom delimiter defined at start"(String input, String[] tokens) {
		expect:
			new Tokenizer(input).tokenize() == tokens

		where:
			input            | tokens
			"//;\n1;2"       | ["1", "2"]
			"//@\n15@5@25"   | ["15", "5", "25"]
			"//!\n9!5!12!3"  | ["9", "5", "12", "3"]
	}

}
