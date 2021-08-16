## open-weather-map-auto-test
This is a Automation TestNG Framework in Selenium. Using Java as main programming language


## Concepts Included

* Page Object Model (POM) & Page Factory in Selenium
* WebDriver Management and DesireCapabilities
* TestNG Cross Browser Testing: Chrome and Firefox
* TestNG Parallel Execution
* TestNG DataProvider with Excel
* TestNG Parameters
* TestNG Listeners
* TestNG Assert
* Extent Report

## Tools

* Maven
* Java SDK
* TestNG
* Selenium Webdriver
* WebDriver Management
* Extent Report
* Apache POI (Excel)

## Language

* Java

## Requirements

In order to execute the test cases in this project you need to have the following installed locally:

* Maven 3.8.1 (https://mkyong.com/maven/how-to-install-maven-in-windows/)
* Java 1.8 (https://mkyong.com/java/how-to-set-java_home-on-windows-10/)
* IDE: Intellij (https://www.jetbrains.com/help/idea/installation-guide.html#silent)
* Plugins for Intellij: Maven, TestNG if you want to execute the test case on IDE.
* Chrome browser
* Firefox browser

## Assumption

* Because of using WebDriver Manager for downloading Chromedriver.exe and geckodriver.exe so make sure the downloading is allowed by Company Networking (If have)
* All Test cases are working fine on Window 10. I have not checked with other operation system as Mac OS or Ubuntu

## Execution:

To execute these test case using command line, navigate to `open-weather-map-auto-test` directory and run:

`mvn clean install`
or
`mvn clean install -Psearch-test` (search-test is profile Id which is configured in pom.xml file)

To execute these test case on IDE (Intellij), open the project in IDE

`right-click on testng.xml (under open-weather-map-auto-test directory) then click on Run option`

## Reporting - using Extent Report

Extent Report (`extent-report.html`) are written into the `/extent-reports` directories after a successful run.

![image](https://user-images.githubusercontent.com/16249454/129309546-cab0c003-ce75-49f8-a547-3ca27ac54ffb.png)

## Documents
Below files is under project directory `open-weather-map-auto-test`:

* TestCase.xlsx
* Test Plan_OpenWeather_v1.0.docx
* Defect.xlsx
* StaticAnalysisCodeResult_usingSonarLint.png
