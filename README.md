String Calculator
-----------------

## Overview

A calculator for performing arithmetic operations on String input operands.

	*** TODO ****


## Gradle Build

The project is built with Gradle:

http://www.gradle.org/

This project includes the Gradle Wrapper (gradlew):

http://www.gradle.org/docs/current/userguide/gradle_wrapper.html

The Gradle Wrapper is a batch script on Windows or a shell script on *nix. When you start a Gradle build via the wrapper, Gradle will be automatically downloaded and used to run the build. This provides the benefit that anyone can work with it without needing to install Gradle beforehand. It also ensures that users are guaranteed to use the version of Gradle that the build was designed to work with.


## Build Tasks

### Clean the output directories:

	./gradlew clean

### Running tests

	./gradlew test
	
Test reports can be found at:

	build/reports/tests/index.html

Main application acceptance tests can be found in:

	*** TODO ***

Source code also contains a suite of unit tests and some supporting test utilities in the package:

	net.tools.search.utils

### Running application

Firstly, install the application

	./gradlew installApp
	
Then navigate to

	build/install/calculator

and execute on Unix

	./bin/calculator

or on Windows

	./bin/calculator.bat
	
### Creating a distribution

Execute this command

	./gradlew distZip
	
and then you will find the distribution created at:

	build/distributions/calculator.zip