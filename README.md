String Calculator
-----------------

## Overview

A kata to create a String Calculator for performing arithmetic operations on String input operands.

Make sure you only test for correct inputs. There is no need to test for invalid inputs for this kata.

### Steps

1. Create a simple String calculator with a method int Add(string numbers)
  - The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
  - Start with the simplest test case of an empty string and move to 1 and two numbers
  - Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
  - Remember to refactor after each passing test
2. Allow the Add method to handle an unknown amount of numbers
3. Allow the Add method to handle new lines between numbers (instead of commas).
  - The following input is ok:  “1\n2,3”  (will equal 6)
  - The following input is NOT ok:  “1,\n” (not need to prove it - just clarifying)
4. Support different delimiters
  - To change a delimiter, the beginning of the string will contain a separate line that looks like this:   “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
  - The first line is optional. all existing scenarios should still be supported
5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.if there are multiple negatives, show all of them in the exception message
6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
7. Delimiters can be of any length with the following format:  “//[delimiter]\n” for example: “//[***]\n1***2***3” should return 6
8. Allow multiple delimiters like this:  “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
9. Make sure you can also handle multiple delimiters with length longer than one char


## Gradle Build

The project is built with Gradle:

http://www.gradle.org/

This project includes the Gradle Wrapper (gradlew):

http://www.gradle.org/docs/current/userguide/gradle_wrapper.html

The Gradle Wrapper is a batch script on Windows or a shell script on *nix. When you start a Gradle build via the wrapper, Gradle will be automatically downloaded and used to run the build. This provides the benefit that anyone can work with it without needing to install Gradle beforehand. It also ensures that users are guaranteed to use the version of Gradle that the build was designed to work with.

## Spock for BDD Testing

I have provided a JUnit acceptance test suite in the following class:

	com.tools.CalculatorTest

However, I have written most of the tests using a BDD approach using Spock. Spock is a developer testing and specification framework for Java and Groovy applications. It provides a very powerful and expressive specification language through the power of a Groovy DSL. Spock specifications conventionally have the suffix 'Spec'. One of it's power features, and one which I have used extensively in this kata, is the ability it affords for data driven testing. Often it is useful to exercise the same test code multiple times, with varying inputs and expected results. Spock’s data driven testing support makes this extremely easy. For example, in CalculatorSpec:

```
def "should allow custom delimiters of any length"() {
	expect:
		calculator.add(input) == sum

	where:
		input                 | sum
		"//[***]\n1***2***3"  | 6
		"//[&&]\n5&&4&&3"     | 12
		"//[!!!!]\n5!!!!6"    | 11
}
```

## Build Tasks

### Clean the output directories:

	./gradlew clean

### Running tests

	./gradlew test
	
Test reports can be found at:

	build/reports/tests/index.html

Example screenshot:

![Test Results](https://github.com/paddycakes/string-calculator/blob/master/images/test-output.png)

	
### Creating a distribution

Execute this command

	./gradlew distZip
	
and then you will find the distribution created at:

	build/distributions/calculator.zip