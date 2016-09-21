# WikipediaBookCreator

## Description
This project is done as part of the Test Automation assignment of QA Engineer job application at Crossover. Assigned TCM is automated as part of this assignment.

## Technolgies used
###Automation Tool###: Selenium Webdriver
###Programming language###: Java
###Framework###: TestNG using Page Object Model
###Libs/JARs used###: Selenium 2.53.1, TestNG 6.9.9, Java JDK 1.8.0_101
###IDE used###: Eclips

##Instalation Process##
Unzip the zip file and import the project in Eclips

##Steps to Run##
Run testng.xml file as TestNG. Specify the browser name as "chrome","firefox" or "ie" in browser parameter and URL for the application under test in url parameter.

##Dependencies##
None

##Implementation Overview##
This project is implemented using Selenium in Java and using TestNG framework. The test case has been written in src\org\wikipedia\tests\BookCreaterTests.java class. Tests using various methods written in various Page Objects. All classes extends BaseClass.java which is an abstract class.